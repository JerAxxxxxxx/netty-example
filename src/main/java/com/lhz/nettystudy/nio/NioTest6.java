package com.lhz.nettystudy.nio;

import java.nio.ByteBuffer;

/**
 * Slice Buffer 与原有的buffer共享相同的底层数组
 *
 * @author JerAxxxxx
 * @version Revision 1.0.0
 * @date 2020/1/2 10:54
 */
public class NioTest6 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }
        buffer.position(2);
        buffer.limit(6);

        ByteBuffer slice = buffer.slice();

        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            slice.put(i, (byte) (2 * b));
        }
        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }

    }
}
