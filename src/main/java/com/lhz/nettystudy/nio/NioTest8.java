package com.lhz.nettystudy.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author JerAxxxxx
 * @version Revision 1.0.0
 * @date 2020/1/3 10:45
 */
public class NioTest8 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("1.txt");
        FileOutputStream fos = new FileOutputStream("2.txt");
        //获取通道
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();
        //分配缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(512);
        //将通道的数据存入缓冲区
        while (true) {
            byteBuffer.clear();
            int read = inChannel.read(byteBuffer);
            System.out.println("read: " + read);

            if (-1 == read) {
                break;
            }
            //切换读取数据的模式
            byteBuffer.flip();
            //将缓冲区的数据写入通道
            outChannel.write(byteBuffer);
            //清空缓冲区
        }
        outChannel.close();
        inChannel.close();
        fis.close();
        fos.close();
    }
}
