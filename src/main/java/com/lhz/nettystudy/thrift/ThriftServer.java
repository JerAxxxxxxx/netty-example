package com.lhz.nettystudy.thrift;

import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * @author JerAxxxxx
 * @version Revision 1.0.0
 * @date 2019/12/12 22:02
 */
public class ThriftServer {
    public static void main(String[] args) throws TTransportException {
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
    }
}
