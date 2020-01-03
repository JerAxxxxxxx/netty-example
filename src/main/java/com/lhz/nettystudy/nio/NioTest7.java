package com.lhz.nettystudy.nio;

import java.nio.ByteBuffer;

/**
 * 只读buffer,我们可以随时将一个普通buffer调用asReadOnlyBuffer()方法回返一个只读buffer
 * 但不能将只读buffer转换为读写buffer
 *
 * @author JerAxxxxx
 * @version Revision 1.0.0
 * @date 2020/1/3 9:39
 */
public class NioTest7 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        //class java.nio.HeapByteBuffer
        System.out.println(buffer.getClass());
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        //class java.nio.HeapByteBufferR
        System.out.println(readOnlyBuffer.getClass());
        readOnlyBuffer.put((byte) 3);
    }
}
