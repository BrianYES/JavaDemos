package brian.concurrency.singleton;

/**
 * 枚举模式
 * 线程安全
 * 推荐使用
 *
 * @author Brian
 * @date 2018/7/4
 */
public class SingletonTest3 {

    /**
     * 私有的构造函数
     * 保证外面不能使用new来实例化对象
     */
    private SingletonTest3() {

    }

    public static SingletonTest3 getInstance() {
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton {
        INSTANCE;

        private SingletonTest3 singleton;

        /**
         * JVM保证构造方法只被调用一次
         * 线程安全
         */
        Singleton() {
            singleton = new SingletonTest3();
        }

        public SingletonTest3 getSingleton() {
            return singleton;
        }
    }
}
