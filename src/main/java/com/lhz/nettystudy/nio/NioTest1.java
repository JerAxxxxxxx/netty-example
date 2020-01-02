package com.lhz.nettystudy.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @author JerAxxxxx
 * @version Revision 1.0.0
 * @date 2019/12/13 13:59
 */
public class NioTest1 {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }
        //实现状态的翻转
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
