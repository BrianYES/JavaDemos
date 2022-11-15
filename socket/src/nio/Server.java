package nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server implements Runnable {

    private final static int PORT = 8765;

    /** 多路复用器 管理所有通道 */
    private Selector selector;

    /** 读缓冲区 */
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);

    /** 写缓冲区 */
    private ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

    public Server() {
        try {
            // 打开多路复用器
            this.selector = Selector.open();
            // 打开服务端通道
            ServerSocketChannel ssc = ServerSocketChannel.open();
            // 设置通道非阻塞
            ssc.configureBlocking(false);
            // 绑定监听端口
            ssc.bind(new InetSocketAddress(PORT));
            // 把通道注册到多路复用器上，并监听ACCEPT事件
            ssc.register(this.selector, SelectionKey.OP_ACCEPT);
            System.out.println("server start port:" + PORT);
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
                        if (key.isAcceptable()) {
                            this.accept(key);
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

    private void accept(SelectionKey key) {
        try {
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel sc = ssc.accept();
            // 设置客户端的通道
            sc.configureBlocking(false);
            // 注册监听客户端请求READ
            sc.register(this.selector, SelectionKey.OP_READ);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void read(SelectionKey key) {
        try {
            SocketChannel sc = (SocketChannel) key.channel();
            int len = sc.read(readBuffer);
            // 没有数据
            if (len == -1) {
                key.channel().close();
                key.cancel();
                return;
            }
            readBuffer.flip();
            byte[] bytes = new byte[readBuffer.remaining()];
            readBuffer.get(bytes);
            readBuffer.clear();
            readBuffer.flip();

            // 获取客户端请求数据
            String request = new String(bytes);
            System.out.println("获取客户端请求数据：" + request);

            // 返回给客户端的数据
            String response = "server response";
            writeBuffer.put(response.getBytes());
            writeBuffer.flip();
            sc.write(writeBuffer);
            writeBuffer.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new Server()).start();
    }
}
