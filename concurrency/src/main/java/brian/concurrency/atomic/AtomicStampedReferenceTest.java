package brian.concurrency.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtomicStampedReferenceTest {

    private static AtomicStampedReference<String> demo = new AtomicStampedReference<>("test", 0);

    public static void main(String[] args) {
        demo.compareAndSet("test", "test1", 0, 1); // yes
        demo.compareAndSet("test1", "test2", 0, 2); // no
        demo.compareAndSet("test1", "test3", 1, 3); // yes
        demo.compareAndSet("test2", "test4", 2, 4); // no
        log.info("ref:{}, stamp:{}", demo.getReference(), demo.getStamp());
    }

}
