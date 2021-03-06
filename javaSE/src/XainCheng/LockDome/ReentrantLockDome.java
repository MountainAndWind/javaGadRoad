package XainCheng.LockDome;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description ReentrantLock的一些api演示 目前是对tryLock的一些操作进行演示
 * @Author slfang
 * @Time 2019/3/29 15:37
 * @Version 1.0
 **/
public class ReentrantLockDome {

    /**
     * Lock接口中每个方法的使用，lock()、tryLock()、tryLock(long time, TimeUnit unit)
     * 和lockInterruptibly()是用来获取锁的。unLock()方法是用来释放锁的
     */
    ReentrantLock lock = new ReentrantLock();

    private int nums=100;

    class Dome implements Runnable{
        @Override
        public void run() {

            lock.lock();
            try {
                while (nums>=0){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"::消费了"+nums--);
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
                lock.unlock();
            }
            finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        /*ReentrantLockDome.Dome dome = new ReentrantLockDome().new Dome();
        for (int i = 0; i < 3; i++) {
            new Thread(dome,"测试1").start();
            new Thread(dome,"测试2").start();
            new Thread(dome,"测试3").start();
        }*/

        test1();

       /* ReentrantLock lock = new ReentrantLock();
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread("线程持续"+i){
                public void run(){
                    lock.lock();
                        System.out.println("持续");
                        try {
                            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        finally {
                            lock.unlock();
                        }
                }
            };
            thread.start();
        }*/

       /* Thread thread2 = new Thread("try"){
            public void run(){
                try {
                    lock.tryLock();
                    System.out.println("try执行了");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if(lock.tryLock()){
                         lock.unlock();
                    }else{
                        System.out.println("释放放不掉");
                    }
                }
            }
        };*/
        //thread2.start();

        /*ReentrantLock lock = new ReentrantLock(true);//开启公平锁模式
        for (int i = 0; i <100 ; i++) {
            Thread a = new Thread(""+i){
                public void run(){
                        lock.lock();
                        try {
                            System.out.println(Thread.currentThread().getName()+"运行----------------------------------");
                            *//*TimeUnit.SECONDS.sleep(1000);*//*
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        finally {
                            lock.unlock();
                        }
                    }
            };
            a.start();
        }*/
    }

    /**
     * lockInterruptibly()方法比较特殊，当通过这个方法去获取锁时，如果线程正在等待获取锁，
     * 则这个线程能够响应中断，即中断线程的等待状态。
     */
    private static void test1() throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();
        Thread thread = new Thread(new InterThread(lock), "test1");
        Thread thread1 = new Thread(new InterThread(lock), "test2");
        thread.start();
        thread1.start();
        TimeUnit.SECONDS.sleep(4);
        //thread1.interrupt();
    }
}

class InterThread implements Runnable{
    private ReentrantLock lock ;

    public InterThread(ReentrantLock lock){
        this.lock =lock;
    }

    @Override
    public void run() {
        try{
            lock.lockInterruptibly();
            while (true){
                System.out.println(Thread.currentThread().getName()+"执行");
            }
        }catch (Exception e){
            System.out.println(Thread.currentThread().getName()+"ex");
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
