package com.lhz.nettystudy.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author JerAxxxxx
 * @version Revision 1.0.0
 * @date 2020/1/6 15:38
 */
public class NioClient {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);

            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            socketChannel.connect(new InetSocketAddress(8899));

            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                selectionKeys.forEach(selectionKey -> {
                    if (selectionKey.isConnectable()) {
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        if (client.isConnectionPending()) {
                            try {
                                client.finishConnect();

                                ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                                writeBuffer.put((LocalDateTime.now() + "连接成功").getBytes());

                                writeBuffer.flip();
                                client.write(writeBuffer);

                                ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());

                                executorService.submit(() -> {
                                    while (true) {
                                        try {
                                            writeBuffer.clear();
                                            InputStreamReader inputStreamReader = new InputStreamReader(System.in);

                                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                                            String sendMessage = bufferedReader.readLine();
                                            writeBuffer.put(sendMessage.getBytes());

                                            writeBuffer.flip();

                                            client.write(writeBuffer);

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        try {
                            client.register(selector, SelectionKey.OP_READ);
                        } catch (ClosedChannelException e) {
                            e.printStackTrace();
                        }


                    } else if (selectionKey.isReadable()) {
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                        try {
                            int count = client.read(readBuffer);
                            if (count > 0) {
                                String receiveMessage = new String(readBuffer.array(), 0, count);
                                System.out.println(receiveMessage);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                });
                selectionKeys.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
