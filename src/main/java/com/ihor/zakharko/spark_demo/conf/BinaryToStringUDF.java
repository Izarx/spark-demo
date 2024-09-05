package com.ihor.zakharko.spark_demo.conf;

import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.types.DataTypes;

import java.nio.charset.StandardCharsets;

public class BinaryToStringUDF implements UDF1<byte[], String> {

    @Override
    public String call(byte[] binaryData) {
        if (binaryData == null) {
            System.out.println("Binary data null");
            return null;  // Handle null case as needed
        }
        System.out.println("SerializedSerialized object type: " + binaryData.getClass().getName());
        return new String(binaryData, StandardCharsets.UTF_8);
    }
}
