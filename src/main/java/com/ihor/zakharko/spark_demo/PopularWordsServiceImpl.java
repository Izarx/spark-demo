package com.ihor.zakharko.spark_demo;

import com.ihor.zakharko.spark_demo.conf.BinaryToStringUDF;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.streaming.Trigger;
import org.apache.spark.sql.types.DataTypes;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeoutException;

import static org.apache.spark.sql.functions.callUDF;
import static org.apache.spark.sql.functions.col;

@Slf4j
@Service
@RequiredArgsConstructor
public class PopularWordsServiceImpl implements PopularWordsService {

    private final SparkSession session;

    @EventListener(ApplicationReadyEvent.class)
    public long someMethod() {
//        session.udf().register("binaryToString", new BinaryToStringUDF(), DataTypes.StringType);

//        Dataset<Row> sourceDf = session.readStream()
//                .format("kafka")
//                .option("kafka.bootstrap.servers", "192.168.0.109:9092")
//                .option("subscribe", "transactions")
//                .option("maxOffsetsPerTrigger", 1000)
//                .option("startingOffsets", "earliest")
//                .option("failOnDataLoss", "false")
//                .option("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer")
//                .load();
//        sourceDf.printSchema();
////        Dataset<Row> resultDf = sourceDf.repartition().selectExpr(
////                "CAST(value AS STRING) AS value"
////        );
//
//        Dataset<String> resultDf = sourceDf.selectExpr("CAST(value AS STRING)")
//                .as(Encoders.STRING());
//
//        resultDf.explain(true);
////        session.sql("CREATE TABLE IF NOT EXISTS delta_table_name " +
////                "(value STRING) " +
////                "USING DELTA LOCATION 's3a://my-bucket/transaction_raw2'");
//
//        log.info("Tuts");
//        try {
//
//            StreamingQuery query = resultDf.writeStream()
//                    .format("delta")
//                    .outputMode("append")  // Use append mode to add new records
//                    .option("checkpointLocation", "s3a://aaty-data/checkpoints/transaction_ingestion_checkpoint")
//                    .option("path", "s3a://aaty-data/transaction_raw3")  // Delta table path
//                    .trigger(Trigger.ProcessingTime("5 seconds"))  // Process data every 5 seconds
//                    .start();

//            StreamingQuery query = resultDf.writeStream()
//                    .format("console")
//                    .outputMode("append")  // Changed to "append" for efficiency
//                    .option("checkpointLocation", "s3a://aaty-data/checkpoints/transaction_ingestion_checkpoint1")
//                    .option("truncate", "false")
//                    .start();

//            query.awaitTermination();
//        } catch (StreamingQueryException | TimeoutException e) {
//            log.error("Error in streaming query", e);
//            throw new RuntimeException(e);
//        }

         var readDf = session.read()
                .format("delta")
                .option("path", "s3a://aaty-data/transaction_raw3")
                .load();

        readDf.show();

        return 0L;
    }

}
