package bio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.io.OutputStream;

/**
 * Client
 */
public class Client implements Runnable {

    private final static String HOST = "127.0.0.1";
    private final static int PORT = 8765;

    public String msg;

    @Override
    public void run() {
        try {
            Socket socket = new Socket(HOST, PORT);

            // 模拟耗时2~5秒
            Thread.sleep(new Random().nextInt(3000) + 2000);

            // 发送数据
            OutputStream out = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(out, true);
            pw.println(msg);

            // 接收数据
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String resp = br.readLine();
            System.out.println("客户端接受到服务端的信息：" + resp);
            br.close();
            is.close();

            pw.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        Client c1 = new Client();
        c1.msg = "c1";

        Client c2 = new Client();
        c2.msg = "c2";

        Client c3 = new Client();
        c3.msg = "c3";

        new Thread(c1).start();
        new Thread(c2).start();
        new Thread(c3).start();
    }
}