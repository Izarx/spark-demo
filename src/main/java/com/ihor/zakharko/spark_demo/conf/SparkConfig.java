package com.ihor.zakharko.spark_demo.conf;

import org.apache.spark.SparkConf;
import org.apache.spark.serializer.KryoSerializer;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class SparkConfig {

    @Bean
    SparkSession sparkContext() {
        try {
            SparkConf sparkConf = new SparkConf()
                    .setAppName("Spring Boot Spark App")
                    .setMaster("spark://localhost:7077")
//                    .setMaster("local[*]")
                    .set("spark.hadoop.fs.s3a.endpoint", "http://192.168.0.109:9000")
                    .set("spark.hadoop.fs.s3a.access.key", "minio")
                    .set("spark.hadoop.fs.s3a.secret.key", "minio123")
                    .set("spark.hadoop.fs.s3a.connection.ssl.enabled", "false")
                    .set("spark.hadoop.fs.s3a.path.style.access", "true")
                    .set("spark.sql.extensions", "io.delta.sql.DeltaSparkSessionExtension")
                    .set("spark.sql.catalog.spark_catalog", "org.apache.spark.sql.delta.catalog.DeltaCatalog")
                    .set("spark.hadoop.io.native.lib.available", "false")
                    .set("spark.hadoop.fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")
                    .set("spark.hadoop.fs.s3a.connection.maximum", "100")
                    .set("spark.hadoop.fs.s3a.connection.timeout", "5000")
                    .set("spark.hadoop.fs.s3a.attempts.maximum", "3")
                    .set("spark.hadoop.fs.s3a.retry.limit", "3")

                    .set("spark.submit.deployMode", "client")
                    .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
//                    .set("spark.driver.host", "127.0.0.1")
//                    .set("spark.serializer", "org.apache.spark.serializer.JavaSerializer")
//                    .set("spark.kryo.registrationRequired", "true")
//                    .registerKryoClasses(new Class[]{
//                            scala.collection.immutable.List.class,
//                            scala.collection.Seq.class,
//                    })

                    .set("spark.hadoop.fs.s3a.retry.interval", "1000ms");

            return SparkSession.builder()
                    .config(sparkConf)
                    .getOrCreate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Spark Session. Check Spark configuration and S3 settings.", e);
        }
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
