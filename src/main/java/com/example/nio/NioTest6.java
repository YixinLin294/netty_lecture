package com.example.nio;

import java.nio.ByteBuffer;

public class NioTest6 {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put(((byte) i));
        }

        buffer.position(2);
        buffer.limit(6);

        ByteBuffer sliceBuffer = buffer.slice();

        for (int i = 0; i < sliceBuffer.capacity(); i++) {
            byte b = sliceBuffer.get(i);
            b *= 2;
            sliceBuffer.put(i, b);
        }

        System.out.println("------------------------------");

        System.out.println("buffer.position() = " + buffer.position());

        System.out.println("buffer.limit = " + buffer.limit());

        System.out.println("------------------------------");
/*
        buffer.position(0);
        buffer.limit(buffer.capacity());
*/
        buffer.clear();

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
            System.out.println("buffer.position() = " + buffer.position());
            System.out.println("buffer.limit() = " + buffer.limit());
        }
    }
}
