package com.lhz.nettystudy.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author JerAxxxxx
 * @version Revision 1.0.0
 * @date 2019/12/13 14:25
 */
public class NioTest3 {
    public static void main(String[] args) throws IOException {
        FileOutputStream outputStream = new FileOutputStream("2.txt");
        FileChannel channel = outputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(512);
        byte[] bytes = "fuckyou111".getBytes();
        for (int i = 0; i < bytes.length; i++) {
            buffer.put(bytes[i]);
        }
        buffer.flip();
        channel.write(buffer);
        outputStream.close();
    }
}
