package com.lhz.nettystudy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

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
        System.out.println(SelectorProvider.provider().getClass());

        for (int i = 0; i < posts.length; i++) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //配置是否阻塞   true:阻塞   false:非阻塞
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            InetSocketAddress address = new InetSocketAddress(posts[i]);
            serverSocket.bind(address);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听端口: " + posts[i]);
        }

        while (true) {
            int numbers = selector.select();
            System.out.println("numbers:" + numbers);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectionKeys:" + selectionKeys);

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isConnectable()) {
                    //测试此密钥的通道是否已完成，或未能完成,完成其套接字连接操作。
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //配置是否阻塞   true:阻塞   false:非阻塞
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);
                    //一定要移除
                    iterator.remove();
                    System.out.println("获得客户端连接:" + socketChannel);
                } else if (selectionKey.isReadable()) {
                    //是否是可读的
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    int byteRead = 0;
                    while (true) {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                        byteBuffer.clear();
                        int read = socketChannel.read(byteBuffer);

                        if (read <= 0) {
                            break;
                        }
                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);
                        byteRead += read;
                    }
                    System.out.println("读取:" + byteRead + ",来自于:" + socketChannel);
                    iterator.remove();
                }
            }
        }


    }
}
