package com.lhz.nettystudy.nio;

import java.nio.ByteBuffer;

/**
 * @author JerAxxxxx
 * @version Revision 1.0.0
 * @date 2020/1/2 10:46
 */
public class NioTest5 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);
        buffer.putInt(123);
        buffer.putLong(12321L);
        buffer.putChar('啊');
        buffer.putDouble(123.123);

        buffer.flip();
        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getDouble());

    }
}
