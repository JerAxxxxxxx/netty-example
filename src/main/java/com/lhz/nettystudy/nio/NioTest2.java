package com.lhz.nettystudy.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author JerAxxxxx
 * @version Revision 1.0.0
 * @date 2019/12/13 14:21
 */
public class NioTest2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileChannel inChannel = fileInputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(512);
        inChannel.read(buffer);
        //操作反转
        buffer.flip();
        while (buffer.remaining() > 0) {
            byte b = buffer.get();
            System.out.println((char) b);
        }

        fileInputStream.close();
    }
}
