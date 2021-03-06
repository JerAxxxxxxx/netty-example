package com.lhz.nettystudy.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * @author JerAxxxxx
 * @version Revision 1.0.0
 * @date 2019/12/13 15:47
 */
public class NioTest4 {
    public static void main(String[] args) throws Exception {
        method5();
    }


    private static void method5() throws FileNotFoundException {
        SortedMap<String, Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> entries = map.entrySet();
        for (Map.Entry<String, Charset> entry : entries) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    private static void method4() throws FileNotFoundException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");
        //获取通道
        FileChannel channel = randomAccessFile.getChannel();
        //分配缓冲区大小
        ByteBuffer buffer1 = ByteBuffer.allocate(100);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);

    }

    /**
     * 通道之间的数据传输
     */
    private static void method3() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("4.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);

        inChannel.transferTo(0, inChannel.size(), outChannel);
        inChannel.close();
        outChannel.close();
    }


    /**
     * 2.使用直接缓冲区完成文件的复制(内存映射文件)
     */
    private static void method() {
        try {
            FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
            FileChannel outChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);

            //内存映射文件
            MappedByteBuffer inMappedByteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outMappedByteBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
            //直接对缓冲区做数据的读写操作
            byte[] bytes = new byte[inMappedByteBuffer.limit()];
            inMappedByteBuffer.get(bytes);
            outMappedByteBuffer.put(bytes);

            inChannel.close();
            outChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1
     *
     * @throws FileNotFoundException
     */
    private static void method2() throws Exception {
        FileInputStream fis = new FileInputStream("1.jpg");
        FileOutputStream fos = new FileOutputStream("2.jpg");
        //获取通道
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();
        //分配缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //将通道的数据存入缓冲区
        while (inChannel.read(byteBuffer) != -1) {
            //切换读取数据的模式
            byteBuffer.flip();
            //将缓冲区的数据写入通道
            outChannel.write(byteBuffer);
            //清空缓冲区
            byteBuffer.clear();
        }
        outChannel.close();
        inChannel.close();
        fis.close();
        fos.close();
    }
}
