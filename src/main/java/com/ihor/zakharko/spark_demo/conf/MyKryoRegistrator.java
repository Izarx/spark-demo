package com.ihor.zakharko.spark_demo.conf;

import com.esotericsoftware.kryo.Kryo;
import org.apache.spark.serializer.KryoRegistrator;

import java.nio.ByteBuffer;

public class MyKryoRegistrator implements KryoRegistrator {
    @Override
    public void registerClasses(Kryo kryo) {
        // Register custom classes and serializers here
        kryo.register(org.apache.spark.sql.execution.datasources.v2.DataSourceRDDPartition.class);
        kryo.register(scala.collection.Seq.class);
        kryo.register(scala.collection.immutable.List.class);
        kryo.register(ByteBuffer.class, new HeapByteBufferSerializer());
    }
}
