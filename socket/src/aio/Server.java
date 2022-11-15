package aio;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private AsynchronousServerSocketChannel assc;

    public Server() {
        try {
            // 使用线程池
//            ExecutorService executor = Executors.newCachedThreadPool();
//            AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withCachedThreadPool(executor, 1);
//            assc = AsynchronousServerSocketChannel.open(channelGroup);
            assc = AsynchronousServerSocketChannel.open();
            assc.bind(new InetSocketAddress(8765));
            System.out.println("server start port:8765");

            assc.accept(this, new ServerCompletionHandler());

            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AsynchronousServerSocketChannel getAssc() {
        return assc;
    }

    public static void main(String[] args) {
        new Server();
    }
}
