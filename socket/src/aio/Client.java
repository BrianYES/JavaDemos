package aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

public class Client implements Runnable {

    private AsynchronousSocketChannel asc;

    public Client(String request) {
        try {
            asc = AsynchronousSocketChannel.open();
            asc.connect(new InetSocketAddress("127.0.0.1", 8765));

            // 保证连接成功
            Thread.sleep(1000);

//            String request = "client request";
            asc.write(ByteBuffer.wrap(request.getBytes()));

            try {
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                asc.read(readBuffer).get();
                readBuffer.flip();
                String response = new String(readBuffer.array()).trim();
                System.out.println("收到服务端的响应数据:" + response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {

        }
    }

    public static void main(String[] args) throws Exception {
        Client c1 = new Client("client request1");
        Client c2 = new Client("client request2");
        Client c3 = new Client("client request3");

        new Thread(c1).start();
        new Thread(c2).start();
        new Thread(c3).start();

    }
}
