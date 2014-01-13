package me.example.object;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ObjectEchoClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = Logger.getLogger(
            ObjectEchoClientHandler.class.getName());

    private final List<Integer> firstMessage;

    /**
     * Creates a client-side handler.
     */
    public ObjectEchoClientHandler(int firstMessageSize) {
        firstMessage = new ArrayList<Integer>(firstMessageSize);
        for (int i = 0; i < firstMessageSize; i ++) {
            firstMessage.add(Integer.valueOf(i));
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // Send the first message if this handler is a client-side handler.
        ctx.writeAndFlush(firstMessage);
        
        System.out.println("write a msg");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // Echo back the received object to the server.
//        ctx.write(msg);
        
//        System.out.println("write a msg");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        System.out.println("write channelReadComplete");
    }

    @Override
    public void exceptionCaught(
            ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}