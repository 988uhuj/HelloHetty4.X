package me.example.string;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import me.example.object.ObjectEchoClient;

public class StringClient {
	private final String host;
    private final int port;
    private final int firstMessageSize;

    public StringClient(String host, int port, int firstMessageSize) {
        this.host = host;
        this.port = port;
        this.firstMessageSize = firstMessageSize;
    }

    public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class)
             .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(
                            new StringClientHandler(firstMessageSize));
                }
             });

            System.out.println("client start2");
            
            // Start the connection attempt.
            b.connect(host, port).sync().channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        // Print usage if no argument is specified.
//        if (args.length < 2 || args.length > 3) {
//            System.err.println(
//                    "Usage: " + ObjectEchoClient.class.getSimpleName() +
//                    " <host> <port> [<first message size>]");
//            return;
//        }

        // Parse options.
        final String host = "127.0.0.1";
        final int port = 13000;
        final int firstMessageSize;

        if (args.length == 3) {
            firstMessageSize = Integer.parseInt(args[2]);
        } else {
            firstMessageSize = 256;
        }

        new StringClient(host, port, firstMessageSize).run();
    }
}
