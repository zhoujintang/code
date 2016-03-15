package com.netty.client;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
public class EchoServer {
  private static final int port = 7089;
  public void start() throws InterruptedException {
    ServerBootstrap b = new ServerBootstrap();// ������������
    EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    EventLoopGroup group = new NioEventLoopGroup();// ͨ��nio��ʽ���������Ӻʹ�������
    try {
      b.group(bossGroup,group);
      b.channel(NioServerSocketChannel.class);// ����nio���͵�channel
      b.childHandler(new ChannelInitializer<SocketChannel>() {//�����ӵ���ʱ�ᴴ��һ��channel
            protected void initChannel(SocketChannel ch) throws Exception {
              // pipeline����channel�е�Handler����channel���������һ��handler������ҵ��
              ch.pipeline().addLast(new EchoServerHandler());
            }
          });
      ChannelFuture f = b.bind(7089).sync();// ������ɣ���ʼ��server��ͨ������syncͬ����������ֱ���󶨳ɹ�
      System.out.println(EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
      f.channel().closeFuture().sync();// Ӧ�ó����һֱ�ȴ���ֱ��channel�ر�
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      group.shutdownGracefully().sync();//�ر�EventLoopGroup���ͷŵ�������Դ�����������߳�
    }
  }
  public static void main(String[] args) {
    try {
      new EchoServer().start();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}