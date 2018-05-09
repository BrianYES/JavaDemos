import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.nio.Buffer;

/**
 * TestSocket
 */
public class TestSocket {

    public static void main(String[] args) throws Exception {
        testPing();
    }

    public static void testPing() throws Exception {
        Process process = Runtime.getRuntime().exec("ping 10.128.16.74 -c 3");
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line=reader.readLine()) != null) {
            if (line.length() != 0) {
                sb.append(line + "\n");
            }
        }
        reader.close();
        System.out.println("本次指令返回的消息是：");
        System.out.println(sb.toString());
    }

    public static void testIP() throws Exception {
        InetAddress host = InetAddress.getLocalHost();
        String ip = host.getHostAddress();
        System.out.println("本机ip地址：" + ip);
    }
}