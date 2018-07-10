package brian.concurrency.lock;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {

    private final static Map<String, String> map = new TreeMap<>();

    private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();

    private final static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public String get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys() {
        readLock.lock();
        try {
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public String put(String key, String value) {
        writeLock.lock();
        try {
            return map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }
}
