package me.example.object;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Logger;

public class ObjectEchoServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = Logger.getLogger(
            ObjectEchoServerHandler.class.getName());

    @Override
    public void channelRead(
            ChannelHandlerContext ctx, Object msg) throws Exception {
        // Echo back the received object to the client.
//        ctx.write(msg);
    	
    	 System.out.println("receive msg = " + msg.toString());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        
        System.out.println("channelReadComplete");
    }

    @Override
    public void exceptionCaught(
            ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}