package brian.concurrency.atomic;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtomicReferenceFieldUpdaterTest {

    private static AtomicReferenceFieldUpdater updater = AtomicReferenceFieldUpdater.newUpdater(AtomicReferenceFieldUpdaterTest.class, String.class, "name");

    @Getter
    private volatile String name = "t1";

    public static void main(String[] args) {
        AtomicReferenceFieldUpdaterTest test = new AtomicReferenceFieldUpdaterTest();

        if (updater.compareAndSet(test, "t1", "t2")) {
            log.info("update success 1, name:{}", test.getName());
        }

        if (updater.compareAndSet(test, "t1", "t3")) {
            log.info("update success 2, name:{}", test.getName());
        } else {
            log.info("update failed, name:{}", test.getName());
        }
    }
}
