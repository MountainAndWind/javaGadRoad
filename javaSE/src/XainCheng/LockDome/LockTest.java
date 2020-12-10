package XainCheng.LockDome;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:lockInterruptibly测试类
 * @author: slfang
 * @time: 2020/12/9 10:56
 */
public class LockTest {
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        for (;;){
            System.out.println("1212");
        }
       /* LockTest  LockTest = new  LockTest();
        MyThread thread1 = new MyThread( LockTest);
        //MyThread thread2 = new MyThread( LockTest);
        thread1.start();
        //thread2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.interrupt();*/
    }

    public void insert(Thread thread) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            System.out.println(thread.getName() + "得到了锁");
            long startTime = System.currentTimeMillis();
            for (; ; ) {
                System.out.println("ada");
                if (System.currentTimeMillis() - startTime >= Integer.MAX_VALUE) break;
            }
        } finally {
            System.out.println(Thread.currentThread().getName() + "执行finally");
            lock.unlock();
            System.out.println(thread.getName() + "释放了锁");
        }
    }
}

class MyThread extends Thread {
    private  LockTest  LockTest = null;

    public MyThread( LockTest  LockTest) {
        this. LockTest =  LockTest;
    }

    @Override
    public void run() {
        try {
             LockTest.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断");
        }
    }
}