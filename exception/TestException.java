import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestException {
    public static void main(String[] args) {
        testCustomException();
    }

    public static void testCustomException() {
        try {
            throw new CustomException("Hello Exception");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testRuntimeException() {
        int k = 5/0;
    }

    public static void test() {
        File file = new File("test.txt");
        try {
            new FileInputStream(file);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d = sdf.parse("2016-06/03");
        } catch (FileNotFoundException | ParseException e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("文件找不到喽");
            } else if (e instanceof ParseException) {
                System.out.println("日期解析错误");
            }
        } finally {
            System.out.println("这个你必须要走的哦");
        }
    }

    public static void method1() {
        try {
            method2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void method2() throws FileNotFoundException {
        File file = new File("test.txt");
        new FileInputStream(file);
    }
}