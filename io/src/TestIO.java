import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 * TestIO
 */
public class TestIO {

    public static void main(String[] args){
        try {
            copyFile2(new File("."), new File("../io_bk/"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void search(File folder, String search) {
        
    }


    public static void copyFile2(File srcFile, File destFile) throws Exception {
        if (srcFile.isDirectory()) {
            System.out.println("这是一个文件夹:"+srcFile.getName());
            if (!destFile.exists()) {
                destFile.mkdir();
            }

            File[] files = srcFile.listFiles();
            for (File file : files) {
                String fileName = file.getName();
                File newDestFile = new File(destFile.getAbsolutePath(), fileName);
                copyFile2(file, newDestFile);
            }
        } else {
            System.out.println("开始copy文件:"+srcFile.getName());
            BufferedReader br = new BufferedReader(new FileReader(srcFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(destFile));

            while (true) {
                String line = br.readLine();
                if (null == line) {
                    break;
                }
                bw.write(line);
                bw.newLine();
            }
            bw.close();
            br.close();
        }
    }

    public static void copyFile(String srcFileStr, String destFileStr) throws Exception {
        File srcFile = new File(srcFileStr);
        File destFile = new File(destFileStr);
        BufferedReader br = new BufferedReader(new FileReader(srcFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(destFile));

        while (true) {
            String line = br.readLine();
            if (null == line) {
                break;
            }
            bw.write(line);
            bw.newLine();
        }
        bw.close();
        br.close();
    }

    public static void testDataStream() throws Exception {
        File file = new File("test5.txt");

        // FileOutputStream fos = new FileOutputStream(file);
        // DataOutputStream dos = new DataOutputStream(fos);
        // dos.writeBoolean(true);
        // dos.writeInt(100);
        // dos.writeUTF("我就是我");
        // dos.close();
        // fos.close();

        FileInputStream fis = new FileInputStream(file);
        DataInputStream dis = new DataInputStream(fis);
        boolean b= dis.readBoolean();
        int i = dis.readInt();
        String str =  dis.readUTF();
        System.out.println(b);
        System.out.println(i);
        System.out.println(str);
        dis.close();
        fis.close();
    }

    public static void testPrintWriter() throws Exception {
        File file = new File("test4.txt");
        PrintWriter pw = new PrintWriter(file);
        pw.println("this is");
        pw.println("very good!");
        pw.close();
    }

    public static void testBufferedReader() throws Exception {
        File file = new File("test.txt");  
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
        }
        br.close();
    }

    public static void testInputStreamReader() throws Exception {
        File file = new File("test.txt");  
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.defaultCharset());
        char[] cs = new char[(int)file.length()];
        inputStreamReader.read(cs);
        inputStreamReader.close();
        System.out.println(new String(cs));
    }

    public static void testWriter() {
        File file = new File("test3.txt");
        try (FileWriter writer = new FileWriter(file)) {
            String data = "12345qwert";
            writer.write(data.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testReader() {
        File file = new File("test.txt");

        try (FileReader reader = new FileReader(file)) {
            char[] all = new char[(int)file.length()];
            reader.read(all);
            for (char c : all) {
                System.out.println(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testFileOutputStream() throws Exception {
        File file = new File("test2.txt");

        byte data[] = {88, 89};
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(data);
        fileOutputStream.close();
    }

    public static void testFileInputStream() {
        File file = new File("test.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);

            byte[] all = new byte[(int)file.length()];
            fileInputStream.read(all);
            for (byte b : all) {
                System.out.println(b);
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testFile() {
        File file = new File("test.txt");
        System.out.println("绝对路径：" + file.getAbsolutePath());

        System.out.println(file.length());
    }

    public static void testFile2(File rootFile) {
        if (rootFile.isDirectory()) {
            File[] files = rootFile.listFiles();
            
            for (File file : files) {
                if (file.isDirectory()) {
                    testFile2(file);
                } else {
                    System.out.println(file.getAbsolutePath());
                }
            }
        } else {
            System.out.println(rootFile.getAbsolutePath());
        }
    }
}