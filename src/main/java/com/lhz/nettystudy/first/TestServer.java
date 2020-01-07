package com.lhz.nettystudy.first;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author JerAxxxxxxx
 * @version Revision 1.0.0
 * @date 2019/12/10 11:31
 */
public class TestServer {
    public static void main(String[] args) throws InterruptedException {
        //主要接收客户端的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //相应客户端的处理
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //辅助封装类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            /*方法链编程风格*/
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());
            //对jdk的future的拓展
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            //netty的优雅关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
