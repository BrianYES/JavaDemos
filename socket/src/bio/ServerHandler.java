package bio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class ServerHandler implements Runnable {

    private Socket socket ;

    public ServerHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 模拟耗时2~5秒
            Thread.sleep(new Random().nextInt(3000) + 2000);

            // 接收数据
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String msg = br.readLine();
            System.out.println("服务端接受到客户端的信息：" + msg);

            // 发送数据
            OutputStream out = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(out, true);
            pw.println("response success " + msg);

            br.close();
            is.close();
            pw.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
