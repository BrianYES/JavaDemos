package brian.concurrency.singleton;

/**
 * 饿汉模式
 * 单例的实例在类装载的时候创建
 * 线程安全
 * 性能不好，资源浪费
 *
 * @author Brian
 * @date 2018/7/4
 */
public class SingletonTest2 {

    /**
     * 私有的构造函数
     * 保证外面不能使用new来实例化对象
     */
    private SingletonTest2() {

    }

    /**
     * 单例对象
     */
    private static SingletonTest2 instance1 = new SingletonTest2();

    /**
     * 静态的工厂方法
     */
    public static SingletonTest2 getInstance1() {
        return instance1;
    }


    ///////////////////////////////////////////////////////////////

    // 另外一种写法，在静态代码块执行实例化操作
    // 注意: 必须对象声明在实例化前面

    private static SingletonTest2 instance2;

    static {
        instance2 = new SingletonTest2();
    }

    public static SingletonTest2 getInstance2() {
        return instance2;
    }

}
