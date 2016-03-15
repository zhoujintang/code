package com.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
	  private final ByteBuf firstMessage;

	    /**
	     * Creates a client-side handler.
	     */
	    public EchoClientHandler() {
	        firstMessage = Unpooled.buffer(256);
	        for (int i = 0; i < firstMessage.capacity(); i ++) {
	            firstMessage.writeByte((byte) i);
	        }
	    }
  /**
   *此方法会在连接到服务器后被调用 
   * */
  public void channelActive(ChannelHandlerContext ctx) {
	  ctx.writeAndFlush(firstMessage);
  }
  /**
   *此方法会在接收到服务器数据后调用 
   * */
  public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
    System.out.println("Client received: " + ByteBufUtil.hexDump(in.readBytes(in.readableBytes())));
  }
  /**
   *捕捉到异常 
   * */
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
    ctx.close();
  }

}