package com.ihor.zakharko.spark_demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.*;
import org.apache.spark.sql.streaming.*;
import org.apache.spark.sql.types.StructType;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeoutException;

import static org.apache.spark.sql.types.DataTypes.StringType;
import static org.apache.spark.sql.functions.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PopularWordsServiceImpl implements PopularWordsService {
    private static final StructType RSVP_SCHEMA = new StructType()
            .add("1", StringType, true);

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
        Dataset<Row> kafkaDf = sourceDf.select(col("value").cast("string"));

        kafkaDf.printSchema();

        try {
            StreamingQuery query = kafkaDf.writeStream()
                    .format("console")
                    .outputMode("append")  // Changed to "append" for efficiency
                    .option("checkpointLocation", "s3a://my-test-bucket/checkpoints/transaction_ingestion_checkpoint")
                    .option("truncate", "false")
                    .start();

            query.awaitTermination();
        } catch (StreamingQueryException | TimeoutException e) {
            e.printStackTrace();  // Better error logging
            throw new RuntimeException(e);
        }

        return 0L;  // Consider meaningful return values or void method
    }

    public void getFromKafka() {

    }

}
