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
        sparkConf.setAppName("Spring Boot Spark App");
        sparkConf.setMaster("spark://localhost:7077");
//        sparkConf.set("spark.hadoop.fs.s3a.endpoint", "http://192.168.50.243:9000");
//        sparkConf.set("spark.hadoop.fs.s3a.access.key", "Fp960Q5nijxeHT0eVYMp");
//        sparkConf.set("spark.hadoop.fs.s3a.secret.key", "xMHZyvCZy6j2yzvXZSMzTXBCIgUielkjUq899eTg");
//        sparkConf.set("spark.hadoop.fs.s3a.path.style.access", "true");
//        sparkConf.set("spark.hadoop.fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem");
//        sparkConf.set("spark.hadoop.fs.s3a.connection.ssl.enabled", "false");
//        sparkConf.set("spark.hadoop.fs.s3a.connection.maximum", "100");
//        sparkConf.set("spark.hadoop.io.native.lib.available", "false");
//        sparkConf.set("spark.submit.deployMode", "cluster");
//        sparkConf.set("spark.hadoop.fs.s3a.endpoint", "s3.amazonaws.com")  // AWS S3 endpoint
        sparkConf.set("spark.hadoop.fs.s3a.endpoint", "http://192.168.50.243:9000")  // AWS S3 endpoint
                .set("spark.hadoop.fs.s3a.access.key", "Fp960Q5nijxeHT0eVYMp")
                .set("spark.hadoop.fs.s3a.secret.key", "xMHZyvCZy6j2yzvXZSMzTXBCIgUielkjUq899eTg")
//                .set("spark.hadoop.fs.s3a.connection.ssl.enabled", "true")  // SSL should be enabled for AWS S3
                .set("spark.hadoop.fs.s3a.connection.ssl.enabled", "false")  // SSL should be enabled for AWS S3
//                .set("spark.hadoop.fs.s3a.path.style.access", "false")  // Set to false for AWS S3
                .set("spark.hadoop.fs.s3a.path.style.access", "true")  // Set to false for AWS S3
                .set("spark.sql.extensions", "io.delta.sql.DeltaSparkSessionExtension")
                .set("spark.sql.catalog.spark_catalog", "org.apache.spark.sql.delta.catalog.DeltaCatalog")
                .set("spark.hadoop.io.native.lib.available", "false")
                .set("spark.hadoop.fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")

                // Optional: increase the connection pool size to AWS S3
//                .set("spark.hadoop.fs.s3a.aws.credentials.provider", "org.apache.hadoop.fs.s3a.SimpleAWSCredentialsProvider")
                .set("spark.hadoop.fs.s3a.connection.maximum", "100")
                .set("spark.hadoop.fs.s3a.connection.timeout", "5000")
                .set("spark.hadoop.fs.s3a.attempts.maximum", "3")
                .set("spark.hadoop.fs.s3a.retry.limit", "3")
                .set("spark.hadoop.fs.s3a.retry.interval", "1000ms")
                .set("spark.local.dir", "C:\\Users\\Public\\spark")
                .set("spark.sql.warehouse.dir","C:\\Users\\Public\\sparkWarehouse")
                .set("spark.hadoop.home.dir", "C:\\Users\\Zakharko.Ihor\\hadoop");

        return SparkSession.builder()
                .config(sparkConf)
//                .enableHiveSupport()
                .getOrCreate();

//        var javaSparkContext = spark.sparkContext();
//        javaSparkContext.hadoopConfiguration().set("fs.s3a.endpoint", "http://192.168.50.243:9000");
//        javaSparkContext.hadoopConfiguration().set("fs.s3a.endpoint", "http://10.187.2.147:9000");
//        javaSparkContext.hadoopConfiguration().set("fs.s3a.access.key", "Fp960Q5nijxeHT0eVYMp");
//        javaSparkContext.hadoopConfiguration().set("fs.s3a.secret.key", "xMHZyvCZy6j2yzvXZSMzTXBCIgUielkjUq899eTg");
//        javaSparkContext.hadoopConfiguration().set("fs.s3a.path.style.access", "true");
//        javaSparkContext.hadoopConfiguration().set("fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem");
//        javaSparkContext.hadoopConfiguration().set("fs.s3a.connection.ssl.enabled", "false");
//        javaSparkContext.hadoopConfiguration().set("fs.s3a.connection.maximum", "100");
//        return spark;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }
}
