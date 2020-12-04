package XainCheng.LockDome;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description:读写锁 ReadWriteLock维护一对关联的locks ，一个用于只读操作，一个用于写入。 read lock可以由多个阅读器线程同时进行，只要没有作者。 write lock是独家的。
 * 独占锁（写锁）一次只能被一个线程占有
 * 共享锁（读锁）多个线程可以同时占有
 * @author: slfang
 * @time: 2020/11/30 22:12
 */
public class ReadWriteLockDome {
    public static void main(String[] args) {
      //test2()
        test2();
    }
    public static void test2(){
        MyLockCache myCache = new MyLockCache(); // 写入
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }
        // 读取
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                try {
                    myCache.get(temp + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }

    public static void test1(){
        MyCache myCache = new MyCache(); // 写入
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                try {
                    myCache.put(temp + "", temp + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
        // 读取
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                try {
                    myCache.get(temp + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}

/**
 * 是用读写锁保证线程安全
 */
class MyLockCache{
    private volatile  Map<String,Object> map = new HashMap<>();

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key,String value){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            TimeUnit.SECONDS.sleep(1);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入OK");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    // 取，读
    public void get(String key) throws InterruptedException {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取OK value::" + o);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
}

/**
 * 线程不安全
 */
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    public void put(String key, Object value) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "写入" + key);
        TimeUnit.SECONDS.sleep(1);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "写入OK");
    }

    // 取，读
    public void get(String key) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "读取" + key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取OK value::" + o);
    }

}
