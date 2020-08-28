package com.example.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("NioTest2.txt");
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        int count = 0;

        while (fileChannel.read(byteBuffer) != -1) {

            byteBuffer.flip();

            while (byteBuffer.remaining() > 0) {
                byte b = byteBuffer.get();
                System.out.println("Character: " + ((char) b));
                count += 1;
            }
            byteBuffer.clear();
        }

        System.out.println(count);
    }
}
