import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

import java.util.Date;

public class Demo {
    private static MemCachedClient mcc = new MemCachedClient();

    static {
        //创建Socket连接池实例
        SockIOPool spool = SockIOPool.getInstance();
        String[] servers = {"127.0.0.1:11211"};
        Integer[] weights = {3};
        //设置服务器信息
        spool.setServers(servers);
        spool.setWeights(weights);
        spool.setFailover(true);
        //设置初始连接数、最小和最大连接数以及最大处理时间
        spool.setInitConn(5);
        spool.setMinConn(5);
        spool.setMaxConn(250);
        spool.setMaxIdle(1000 * 60 * 60 * 6);
        //设置主线程睡眠时间
        spool.setMaintSleep(30);
        //设置TCP参数、连接超时等
        spool.setNagle(false);
        spool.setSocketTO(3000);
        spool.setSocketConnectTO(0);
        spool.setAliveCheck(true);
        //初始化连接池
        spool.initialize();
    }

    public static void main(String[] args) {
        String key = "key_test";
//        String value = "测试一下";
//        mcc.add(key, value);

        String value2 = (String) mcc.get(key);
        System.out.println(value2);

    }
}
