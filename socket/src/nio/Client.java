package nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Client implements Runnable {

    private final static String HOST = "127.0.0.1";
    private final static int PORT = 8765;

    private Selector selector;

    private ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);

    public Client() {
        try {
            // 打开多路复用器
            this.selector = Selector.open();
            // 打开客户端通道
            SocketChannel sc = SocketChannel.open();
            // 设置通道非阻塞
            sc.configureBlocking(false);
            // 连接
            sc.connect(new InetSocketAddress(HOST, PORT));
            // 把通道注册到多路复用器上，并监听CONNECT事件
            sc.register(this.selector, SelectionKey.OP_CONNECT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 多路复用器开始监听
                this.selector.select();
                // 返回多路复用器的结果
                Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();

                    if (key.isValid()) {
                        if (key.isConnectable()) {
                            this.connect(key);
                        }
                        if (key.isReadable()) {
                            this.read(key);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void connect(SelectionKey key) {
        try {
            SocketChannel sc = (SocketChannel) key.channel();
            // 必须要有
            sc.finishConnect();
            // 注册监听服务端响应READ
            sc.register(this.selector, SelectionKey.OP_READ);

            // 发送请求数据
            String msg = "client request";
            byte[] bytes = msg.getBytes();
            writeBuffer.put(bytes);
            writeBuffer.flip();
            sc.write(writeBuffer);
            writeBuffer.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void read(SelectionKey key) {
        try {
            SocketChannel sc = (SocketChannel) key.channel();
            int len = sc.read(readBuffer);
            if (len > 0) {
                readBuffer.flip();
                byte[] bytes = new byte[readBuffer.remaining()];
                readBuffer.get(bytes);
                readBuffer.clear();
                readBuffer.flip();

                // 获取服务端响应数据
                String response = new String(bytes);
                System.out.println("获取服务端响应数据：" + response);
            } else if (len == -1) {
                key.channel().close();
                key.cancel();
            } else {
                // 没有读取到消息，可能TCP处于Keep-Alive状态，接收到的是TCP握手消息
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new Client()).start();
    }
}
