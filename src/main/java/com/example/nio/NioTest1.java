package com.example.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class NioTest1 {

    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }

        buffer.flip();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
