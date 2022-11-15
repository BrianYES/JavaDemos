package brian.concurrency.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SynchronizedTest2 {

    /**
     * 修饰一个类
     */
    public static void test1(String name) {
        synchronized (SynchronizedTest2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("{}:{}", name, i);
            }
        }
    }

    /**
     * 修饰静态方法，作用所有实例
     */
    public static synchronized void test2(String name) {
        for (int i = 0; i < 10; i++) {
            log.info("{}:{}", name, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedTest2 demo1 = new SynchronizedTest2();
        SynchronizedTest2 demo2 = new SynchronizedTest2();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            demo1.test2("test1");
        });
        executorService.execute(() -> {
            demo2.test2("test2");
        });
        executorService.shutdown();
    }
}
