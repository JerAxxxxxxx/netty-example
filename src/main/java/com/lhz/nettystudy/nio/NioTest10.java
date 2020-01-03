package com.lhz.nettystudy.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @author JerAxxxxx
 * @version Revision 1.0.0
 * @date 2020/1/3 13:58
 */
public class NioTest10 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile("NioTest9.txt", "rw");
        FileChannel channel = accessFile.getChannel();

        FileLock lock = channel.lock(3, 6, true);
        System.out.println(lock.isValid());
        System.out.println(lock.isShared());

        lock.release();
        accessFile.close();
    }
}
