package brian.concurrency.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SynchronizedTest1 {

    /**
     * 修饰代码块
     */
    public void test1(String name) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("{}:{}", name, i);
            }
        }
    }

    /**
     * 修饰方法
     * 子类继承方法不带synchronized，需要添加
     */
    public synchronized void test2(String name) {
        for (int i = 0; i < 10; i++) {
            log.info("{}:{}", name, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedTest1 demo = new SynchronizedTest1();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            demo.test2("test1");
        });
        executorService.execute(() -> {
            demo.test2("test2");
        });
        executorService.shutdown();
    }
}
