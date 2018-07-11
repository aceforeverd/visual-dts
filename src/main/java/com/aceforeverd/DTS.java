package com.aceforeverd;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

public class DTS
{
    public static void main( String[] args ) {
        String logFile = "hdfs://dts/README.md";
        SparkSession spark = SparkSession.builder().appName("dts").getOrCreate();
        Dataset<String> logData = spark.read().textFile(logFile).cache();

        long numAs = logData.filter(s -> s.contains("a")).count();
        long numBs = logData.filter(s -> s.contains("b")).count();

        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);

        spark.stop();
    }
}
