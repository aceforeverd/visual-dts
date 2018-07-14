package com.aceforeverd;

import com.mongodb.spark.MongoSpark;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.bson.Document;
import scala.Tuple2;

import java.util.*;

public class DTS {
    public static void main(String[] args) {
        // JavaSparkContext sc
        SparkConf conf = new SparkConf().setAppName("dts");
        // JavaStreamingContext jsc = new JavaStreamingContext(conf, Durations.seconds(2));
        SparkSession spark =
            SparkSession.builder()
                .appName("dts")
                .config("spark.mongodb.input.uri", "mongodb://10.138.0.2:27017/test.dts")
                .config("spark.mongodb.output.uri", "mongodb://10.138.0.2:27017/test.dts")
                .getOrCreate();
        JavaSparkContext sc = new JavaSparkContext(spark.sparkContext());
        JavaStreamingContext ssc = new JavaStreamingContext(sc, Durations.minutes(1));

        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", "10.138.0.2:9092");
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", "kafka_order");
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);

        JavaInputDStream<ConsumerRecord<String, String>> stream =
            KafkaUtils.createDirectStream(ssc, LocationStrategies.PreferConsistent(),
                ConsumerStrategies.<String, String>Subscribe(Arrays.asList("order"), kafkaParams));

        stream
            .map(record -> {
                Document doc = Document.parse(record.value());
                Order order = new Order(doc);
                return order;
            })
            .mapToPair(order -> { return new Tuple2<>(order.getDate(), Collections.singletonList(order)); })
            .reduceByKey((d1, d2) -> {
                d1.addAll(d2);
                return d1;
            })
            .map(v -> v._2())
            .map(vals -> {
                if (vals.size() > 0) {
                    Results res = new Results(vals.get(0).getDate());
                    String str = "";
                    str += "{orders: [" + vals.get(0).toString();
                    for (int i = 1; i < vals.size(); i++) {
                        Order val = vals.get(i);
                        str = str + ", " + val.toString();
                        res.add(val);
                    }
                    str += "], ";
                    str += ("result: " + res.toString());
                    str += "}";
                    return str;
                }
                return "{}";
            })
            .map(r -> Document.parse(r))
            .foreachRDD(re -> { MongoSpark.save(re); });

        try {
            ssc.awaitTermination();
        } catch (Exception e) {
            System.out.printf("error: " + e);
        }
        sc.close();
        spark.stop();
    }
}
