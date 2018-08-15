package bio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Server
 */
public class Server {

    private final int PORT = 8765;

    public void run() {
        try {
            ServerSocket ss = new ServerSocket(PORT);
            System.out.println("server start... port:" + PORT);

            while (true) {
                // 阻塞
                Socket socket = ss.accept();
                System.out.println("有连接过来" + socket);

                new Thread(new ServerHandler(socket)).start();
            }

//            ss.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }
}