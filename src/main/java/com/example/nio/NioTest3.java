package com.example.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest3 {
    public static void main(String[] args) throws IOException {

        FileInputStream input = new FileInputStream("input.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(input, "utf-8");
        char[] chars = new char[512];
        int read;
        StringBuilder sb = new StringBuilder();
        while ((read = inputStreamReader.read(chars)) != -1) {
            sb.append(chars, 0, read);
        }

        String s = sb.toString();
//        System.out.println(sb.toString());

        FileOutputStream fileOutputStream = new FileOutputStream("NioTest3.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

//        byte[] message = "hello world welcome, nihao".getBytes();
        byte[] message = s.getBytes();

        for (int i = 0; i < message.length; i++) {

            byteBuffer.put(message[i]);

            if (byteBuffer.position() == byteBuffer.limit()) {
                byteBuffer.flip();
                fileChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        }

        // 最后有一部分内容未写入channel中
        byteBuffer.flip();
        fileChannel.write(byteBuffer);

        fileOutputStream.close();
    }
}
