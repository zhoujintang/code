package com.netty.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
/**
 * Sharable��ʾ�˶�����channel�乲��
 * handler�������ǵľ���ҵ����
 * */

public class EchoServerHandler extends ChannelInboundHandlerAdapter {
	
  @Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.handlerAdded(ctx);
	}
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
      ctx.write(msg);
  }
  public void channelReadComplete(ChannelHandlerContext ctx) { 
    ctx.writeAndFlush(Unpooled.EMPTY_BUFFER) //flush������д�ص�����
    .addListener(ChannelFutureListener.CLOSE); //��flush��ɺ�ر�channel
  } 
  public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) { 
    cause.printStackTrace();//��׽�쳣��Ϣ
    ctx.close();//�����쳣ʱ�ر�channel 
  } 	
}