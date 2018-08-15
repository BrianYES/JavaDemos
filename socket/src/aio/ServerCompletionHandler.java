package aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class ServerCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, Server> {

    @Override
    public void completed(AsynchronousSocketChannel asc, Server server) {
        // 当有下一个客户端接入的时候 直接调用Server的accept方法，这样反复执行下去，保证多个客户端都可以阻塞
        server.getAssc().accept(server, this);

        System.out.println("当前线程：" + Thread.currentThread().getName());

        // 获取客户端请求数据
        try {
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            asc.read(readBuffer).get();
            readBuffer.flip();
            String request = new String(readBuffer.array()).trim();
            System.out.println("收到客户端的请求数据:" + request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 发送服务端响应数据
        try {
            ByteBuffer buf = ByteBuffer.allocate(1024);
            String response = "server response";
            buf.put(response.getBytes());
            buf.flip();
            asc.write(buf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, Server attachment) {
        exc.printStackTrace();
    }
}
