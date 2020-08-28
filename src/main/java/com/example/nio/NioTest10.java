package com.example.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class NioTest10 {

    public static void main(String[] args) throws IOException, InterruptedException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest10.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        FileLock fileLock = fileChannel.lock(3, 6, true);

        System.out.println("fileLock.isValid() = " + fileLock.isValid());
        System.out.println("fileLock.isShared() = " + fileLock.isShared());

        Thread.sleep(10000);
        fileLock.release();
        System.out.println("release");

        Thread.sleep(10000);
        randomAccessFile.close();

    }
}
