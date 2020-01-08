package com.lhz.nettystudy.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * p50视频不完整
 *
 * @author JerAxxxxx
 * @version Revision 1.0.0
 * @date 2020/1/8 10:24
 */
public class OldServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8899);
        while (true) {
            Socket socket = serverSocket.accept();

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            try {
                byte[] byteArray = new byte[4096];

                while (true) {
                    dataInputStream.read(byteArray, 0, byteArray.length);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
