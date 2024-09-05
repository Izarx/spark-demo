package com.ihor.zakharko.spark_demo;

import lombok.RequiredArgsConstructor;
import org.apache.spark.sql.*;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SparkDemoServiceImpl implements SparkDemoService {

    private final SparkSession session;

    @Override
    public long topX() {
//        session.sql("CREATE SCHEMA IF NOT EXISTS journal");

//        var df = session.readStream()
//                .option("header", "true")
//                .option("inferSchema", "true")
//        df.show();
//        var modifiedDf = df.filter("location_id > 10");
//        modifiedDf.write().format("csv")
//                .option("header", "true")

//        var resultDf = df.filter("location_id > 10")
//                .withColumn("proc_timestamp", functions.current_timestamp())
//                .withColumn("proc_year", functions.year(functions.col("proc_timestamp")))
//                .withColumn("proc_month", functions.month(functions.col("proc_timestamp")))
//                .withColumn("proc_day", functions.dayofmonth(functions.col("proc_timestamp")));
//
//        resultDf.show();
//
//        resultDf.write()
//                .format("delta")
//                .mode(SaveMode.Append)
////                .partitionBy("proc_year", "proc_month", "proc_day")
//                .saveAsTable("journal.sales_transaction_raw");

        return 0L;
    }

    public long someMethod() {
//        checkAndCreateDb();

        Dataset<Row> sourceDf = session.readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", "localhost:9092")
                .option("subscribe", "transactions")
                .option("maxOffsetsPerTrigger", 1000)
                .option("max.poll.records", 1000)
                .option("startingOffsets", "earliest")
                .option("failOnDataLoss", "false")
                .load();

        // Cast Kafka key and value to String
        Dataset<Row> kafkaDf = sourceDf.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
                .withColumn("proc_timestamp", functions.current_timestamp())
                .withColumn("proc_year", functions.year(functions.col("proc_timestamp")))
                .withColumn("proc_month", functions.month(functions.col("proc_timestamp")))
                .withColumn("proc_day", functions.dayofmonth(functions.col("proc_timestamp")));

//        try {
//            StreamingQuery query = kafkaDf.writeStream()
//                    .format("delta")
//                    .outputMode("append")  // Changed to "append" for efficiency
//                    .option("path", "s3a://my-test-bucket/transaction_raw")
////                    .partitionBy("proc_year", "proc_month", "proc_day")
//                    .option("checkpointLocation", "s3a://my-test-bucket/checkpoints/transaction_ingestion_checkpoint")
////                    .toTable("journal.sales_transaction_raw");
////                    .option("truncate", "false")
//                    .start();
//
//            query.awaitTermination();
//        } catch (StreamingQueryException | TimeoutException e) {
//            e.printStackTrace();  // Better error logging
//            throw new RuntimeException(e);
//        }
        StreamingQuery start = kafkaDf.writeStream()
                .format("delta")
                .outputMode("append")
                .option("checkpointLocation", "s3a://my-test-bucket/checkpoints/transaction_ingestion_checkpoint")
                .start("s3a://my-test-bucket/transaction_raw");

        try {
            start.awaitTermination();
        } catch (StreamingQueryException e) {
            e.printStackTrace();  // Better error logging
            throw new RuntimeException(e);
        }
        return 0L;  // Consider meaningful return values or void method
    }

    private void checkAndCreateDb() {
        try {
            session.sql("USE journal");
        } catch (Exception e) {
            // Create the database if it does not exist
            session.sql("CREATE DATABASE journal");
            session.sql("USE journal");
        }
    }

}
