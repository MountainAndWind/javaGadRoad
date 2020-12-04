package XainCheng.LockDome;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description:��д�� ReadWriteLockά��һ�Թ�����locks ��һ������ֻ��������һ������д�롣 read lock�����ɶ���Ķ����߳�ͬʱ���У�ֻҪû�����ߡ� write lock�Ƕ��ҵġ�
 * ��ռ����д����һ��ֻ�ܱ�һ���߳�ռ��
 * ������������������߳̿���ͬʱռ��
 * @author: slfang
 * @time: 2020/11/30 22:12
 */
public class ReadWriteLockDome {
    public static void main(String[] args) {
      //test2()
        test2();
    }
    public static void test2(){
        MyLockCache myCache = new MyLockCache(); // д��
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }
        // ��ȡ
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
        MyCache myCache = new MyCache(); // д��
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
        // ��ȡ
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
 * ���ö�д����֤�̰߳�ȫ
 */
class MyLockCache{
    private volatile  Map<String,Object> map = new HashMap<>();

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key,String value){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "д��" + key);
            TimeUnit.SECONDS.sleep(1);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "д��OK");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    // ȡ����
    public void get(String key) throws InterruptedException {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "��ȡ" + key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "��ȡOK value::" + o);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
}

/**
 * �̲߳���ȫ
 */
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    public void put(String key, Object value) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "д��" + key);
        TimeUnit.SECONDS.sleep(1);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "д��OK");
    }

    // ȡ����
    public void get(String key) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "��ȡ" + key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + "��ȡOK value::" + o);
    }

}
