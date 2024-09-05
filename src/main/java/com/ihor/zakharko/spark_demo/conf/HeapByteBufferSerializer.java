package com.ihor.zakharko.spark_demo.conf;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.nio.ByteBuffer;

public class HeapByteBufferSerializer extends Serializer<ByteBuffer> {
    @Override
    public void write(Kryo kryo, Output output, ByteBuffer buffer) {
        int length = buffer.remaining();
        output.writeInt(length);
        byte[] array = new byte[length];
        buffer.get(array);
        output.write(array);
    }

    @Override
    public ByteBuffer read(Kryo kryo, Input input, Class<ByteBuffer> type) {
        int length = input.readInt();
        byte[] array = new byte[length];
        input.read(array);
        return ByteBuffer.wrap(array);
    }
}

