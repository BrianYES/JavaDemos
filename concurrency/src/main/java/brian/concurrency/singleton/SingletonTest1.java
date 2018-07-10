package brian.concurrency.singleton;

/**
 * 懒汉模式
 * 单例的实例在第一次使用的时候进行创建
 *
 * @author Brian
 * @date 2018/7/4
 */
public class SingletonTest1 {

    /**
     * 私有的构造函数
     * 保证外面不能使用new来实例化对象
     */
    private SingletonTest1() {

    }

    ///////////////////////////////////////////////////////////////

    /**
     * 单例对象
     */
    private static SingletonTest1 instance1 = null;

    /**
     * 静态的工厂方法
     * 线程不安全
     */
    public static SingletonTest1 getInstance1() {
        if (instance1 == null) {
            instance1 = new SingletonTest1();
        }
        return instance1;
    }

    ///////////////////////////////////////////////////////////////

    /**
     * 单例对象
     */
    private static SingletonTest1 instance2 = null;

    /**
     * 线程安全
     * 性能不好
     */
    public synchronized static SingletonTest1 getInstance2() {
        if (instance2 == null) {
            instance2 = new SingletonTest1();
        }
        return instance2;
    }

    ///////////////////////////////////////////////////////////////

    /**
     * 单例对象
     */
    private volatile static SingletonTest1 instance3 = null;

    /**
     * 双重同步锁单例模式 (double check)
     * 指令重排可能会发生线程安全问题
     * 解决：使用volatile修饰对象，限制指令重排
     */
    public static SingletonTest1 getInstance3() {
        if (instance3 == null) {
            // 同步锁
            synchronized (SingletonTest1.class) {
                // 双重检测机制
                if (instance3 == null) {
                    instance3 = new SingletonTest1();
                    // instance = new SingletonTest1();的cpu指令是以下三步
                    // 1.分配对象内存空间
                    // 2.初始化对象
                    // 3.设置对象指向分配的内存地址
                    // 在多线程中可能会发生指令重排，顺序变成了132
                }
            }
        }
        return instance3;
    }

}
