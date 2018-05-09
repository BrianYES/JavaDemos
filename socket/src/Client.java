import java.net.Socket;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Client
 */
public class Client {

    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.1", 8888);
            
            OutputStream os = s.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            Scanner scanner = new Scanner(System.in);
            bw.write(scanner.next());
            scanner.close();;
            bw.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}