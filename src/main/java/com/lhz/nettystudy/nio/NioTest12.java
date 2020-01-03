package com.lhz.nettystudy.nio;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * @author JerAxxxxx
 * @version Revision 1.0.0
 * @date 2020/1/3 16:29
 */
public class NioTest12 {

    private static final int AMOUNT = 5;

    public static void main(String[] args) throws IOException {
        int[] posts = new int[5];
        for (int i = 0; i < AMOUNT; i++) {
            posts[i] = 5000 + i;
        }

        Selector selector = Selector.open();
    }
}
