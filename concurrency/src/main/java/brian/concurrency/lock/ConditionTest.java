package brian.concurrency.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConditionTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            try {
                lock.lock();
                log.info("thread1 locked");
                Thread.sleep(1000);
                log.info("thread1 condition will await");
                condition.await(); // 进入condition队列
                log.info("thread1 condition did await");
            } catch (Exception e) {
                log.error("thread1", e);
            } finally {
                log.info("thread1 will unlock");
                lock.unlock();
                log.info("thread1 unlocked");
            }
        }).start();

        new Thread(() -> {
            try {
                lock.lock();
                log.info("thread2 locked");
                Thread.sleep(3000);
                log.info("thread2 condition will signalAll");
                condition.signalAll(); // 从condition队列回到sync队列
                log.info("thread2 condition did signalAll");
            } catch (Exception e) {
                log.error("thread2", e);
            } finally {
                log.info("thread2 will unlock");
                lock.unlock();
                log.info("thread2 unlocked");
            }
        }).start();
    }
}
