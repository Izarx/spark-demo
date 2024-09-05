package com.ihor.zakharko.spark_demo.conf;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class SparkConfig {
    @Bean
    SparkSession sparkContext() {
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("Spring Boot Spark App")
                .setMaster("spark://localhost:7077")
//                .setMaster("local[*]")
                .setJars(new String[]{"/mnt/c/Users/Zakharko.Ihor/IdeaProjects/spark/spark-demo/target/spark-demo-0.0.1-SNAPSHOT.jar"});
        sparkConf.set("spark.hadoop.fs.s3a.endpoint", "http://10.187.12.15:9000")
//        sparkConf.set("spark.hadoop.fs.s3a.endpoint", "http://192.168.50.243:9000")
//              .set("spark.hadoop.fs.s3a.endpoint", "s3.amazonaws.com")  // AWS S3 endpoint
                .set("spark.hadoop.fs.s3a.access.key", "Fp960Q5nijxeHT0eVYMp")
                .set("spark.hadoop.fs.s3a.secret.key", "xMHZyvCZy6j2yzvXZSMzTXBCIgUielkjUq899eTg")
//                .set("spark.hadoop.fs.s3a.connection.ssl.enabled", "true")  // SSL should be enabled for AWS S3
                .set("spark.hadoop.fs.s3a.connection.ssl.enabled", "false")
//                .set("spark.hadoop.fs.s3a.path.style.access", "false")  // Set to false for AWS S3
                .set("spark.hadoop.fs.s3a.path.style.access", "true")
                .set("spark.sql.extensions", "io.delta.sql.DeltaSparkSessionExtension")
                .set("spark.sql.catalog.spark_catalog", "org.apache.spark.sql.delta.catalog.DeltaCatalog")
                .set("spark.hadoop.io.native.lib.available", "false")
                .set("spark.hadoop.fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")

//                .set("spark.hadoop.fs.s3a.aws.credentials.provider", "org.apache.hadoop.fs.s3a.SimpleAWSCredentialsProvider")
                .set("spark.hadoop.fs.s3a.connection.maximum", "100")
                .set("spark.hadoop.fs.s3a.connection.timeout", "5000")
                .set("spark.hadoop.fs.s3a.attempts.maximum", "3")
                .set("spark.hadoop.fs.s3a.retry.limit", "3")
//                .set("spark.submit.deployMode", "cluster")
//                .set("spark.sql.warehouse.dir","/opt/sparkWarehouse")
//                .set("spark.sql.warehouse.dir", "s3a://my-test-bucket/warehouse")
                .set("spark.hadoop.fs.s3a.retry.interval", "1000ms");
//                .set("spark.local.dir", "C:\\Users\\Public\\spark")
//                .set("spark.sql.warehouse.dir","C:\\Users\\Public\\sparkWarehouse")
//                .set("spark.hadoop.home.dir", "C:\\Users\\Zakharko.Ihor\\hadoop");

        return SparkSession.builder()
                .config(sparkConf)
                .getOrCreate();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }
}
