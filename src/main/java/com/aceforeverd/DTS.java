package com.aceforeverd;

import com.mongodb.spark.MongoSpark;
import com.mongodb.spark.config.WriteConfig;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.bson.Document;

import static java.util.Arrays.asList;

public class DTS
{
    public static void main( String[] args ) {
        String logFile = "hdfs:/dts/README.md";
        // JavaSparkContext sc
        SparkSession spark = SparkSession.builder()
                .appName("dts")
                .config("spark.mongodb.input.uri", "mongodb://127.0.0:27017/test.dts")
                .config("spark.mongodb.output.uri", "mongodb://127.0.0.1:27017/test.dts")
                .getOrCreate();
        JavaSparkContext sc = new JavaSparkContext(spark.sparkContext());
        Dataset<String> logData = spark.read().textFile(logFile).cache();

        long numAs = logData.filter(s -> s.contains("a")).count();
        long numBs = logData.filter(s -> s.contains("b")).count();

        WriteConfig writeConfig = WriteConfig.create(sc);
        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
        JavaRDD<Document> dtsDocuments = sc.parallelize(asList(numAs, numBs))
                .map(new Function<Long, Document>() {
                    @Override
                    public Document call(Long aLong) throws Exception {
                        return Document.parse("{spark: " + aLong);
                    }
                });

        MongoSpark.save(dtsDocuments, writeConfig);

        sc.close();
        spark.stop();
    }
}
