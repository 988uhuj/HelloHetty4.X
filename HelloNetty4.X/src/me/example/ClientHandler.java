package me.example;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Logger;

public class ClientHandler extends ChannelInboundHandlerAdapter  {
	
	
	
//	@Override
//	public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress,
//			SocketAddress localAddress, ChannelPromise promise)
//			throws Exception {
//		 System.out.println(remoteAddress.toString() + localAddress.toString());
//		
//		 // 将字符串，构造成ChannelBuffer，传递给服务端
//    	sendMSG(ctx, "032.06.760N118.55.438E032.06.760N#nupt-102#2");
//        System.out.println("Client channelConnected");
//	}
//
//	@Override
//    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
//		 System.out.println("write" + msg);
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//        cause.printStackTrace();
//        ctx.close();
//    }
//    
//    private void sendMSG(ChannelHandlerContext e, String msg){
////    	String msg = "032.06.760N118.55.438E032.06.760N";
//        e.writeAndFlush(msg);
//        
//        System.out.println("send a msg");
//    }
	
	
	 private static final Logger logger = Logger.getLogger(
	            ClientHandler.class.getName());

	    private final int messageSize;
	    private ByteBuf content;
	    private ChannelHandlerContext ctx;

	    public ClientHandler(int messageSize) {
//	        if (messageSize <= 0) {
//	            throw new IllegalArgumentException(
//	                    "messageSize: " + messageSize);
//	        }
	        this.messageSize = messageSize;
	    }

	    @Override
	    public void channelActive(ChannelHandlerContext ctx)
	            throws Exception {
	        this.ctx = ctx;

	        // Initialize the message.
//	        content = ctx.alloc().directBuffer(messageSize).w

	        		
	        		
	        // Send the initial messages.
	        generateTraffic();
	        
	        System.out.println("channelActive");
	    }

	    @Override
	    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
	        content.release();
	    	System.out.println("channelInactive");
	    }


	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx,
	            Throwable cause) throws Exception {
	        // Close the connection when an exception is raised.
//	        logger.log(
//	                Level.WARNING,
//	                "Unexpected exception from downstream.",
//	                cause);
	        ctx.close();
	    }

	    long counter;

	    private void generateTraffic() {
	        // Flush the outbound buffer to the socket.
	        // Once flushed, generate the same amount of traffic again.
//	        ctx.writeAndFlush(content.duplicate().retain()).addListener(trafficGenerator);
	        ctx.writeAndFlush("sdfsd").addListener(trafficGenerator);
	        System.out.println("write");
	    }

	    private final ChannelFutureListener trafficGenerator = new ChannelFutureListener() {
	        @Override
	        public void operationComplete(ChannelFuture future) throws Exception {
	            if (future.isSuccess()) {
	                generateTraffic();
	            }
	        }
	    };
    
    
}