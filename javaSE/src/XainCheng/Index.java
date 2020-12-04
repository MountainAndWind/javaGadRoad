package XainCheng;

import XainCheng.LockDome.FairSuoDome;
import XainCheng.LockDome.TicketSellWindow;
import XainCheng.ThreadPool.PoolDome;
import XainCheng.base.CollectionUnSafeDome;
import XainCheng.base.DaemonThread;
import XainCheng.LockDome.ReadWriteLockDome;
import XainCheng.jucContainer.BlockingQueueDome;
import XainCheng.util.CountDownLatchDome;
import XainCheng.util.CyclicBarrierDemo;
import XainCheng.util.SemaphoreDome;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:多线程以及JUC
 * @author: slfang
 * @time: 2020/11/24 16:58
 */
public class Index {

    public static void main(String[] args) throws InterruptedException {
        Index index = new Index();
        //线程的状态
        //Thread.State; 6种
        //线程的生命周期

        //线程的创建
        //1、继承Thread类
        for(int j = 0;j < 50;j++) {

            //调用Thread类的currentThread()方法获取当前线程
            System.out.println(Thread.currentThread().getName() + " " + j);

            if(j == 10) {
                //创建并启动第一个线程
                new Thread(){
                  public void run(){
                      System.out.println("-----------------");
                  }
                }.start();

                //创建并启动第二个线程
               index.new Thread1().start();
               index.new Thread1().start();
            }

            //2、实现 Runnable 接口
            new Thread(new Runnable(){

                @Override
                public void run() {
                    System.out.println("run");
                }
            },"run 1").start();
        }

        // Thread 相对于runnable runnable 要更好一些更符合oop  避免单继承的问题
        //多个线程操作同一个资源会造成数据问题  TicketSellWindow
        //第三种，通过Callable和Future接口创建线程  XainCheng/ThreadPool/Dome1
        PoolDome dome1 = new PoolDome();
        //线程方法与控制
        //线程的终止
        Thread thread = new Thread(()-> System.out.println("lambda线程开启"),"lambda线程");
        thread.interrupt();
        thread.stop();
        //线程休眠
        thread.sleep(1000);
        //线程的礼让  让当前的正在执行的线程停止，但是不阻塞，将线程从运行状态转为就绪状态
        // 让cpu重新调度 效果上礼让不一定成功，只是让线程处理同一就绪状态 重新竞争
        thread.yield();
        //join   待此线程执行完成之后，再执行其他的线程，其他线程阻塞，可以想象成插队  少去使用
        thread.join();
        //线程的优先级  1-10  只是权重问题  不是绝对
        int priority = thread.getPriority();
        thread.setPriority(10);
        //线程分为用户线程与守护线程  守护线程又称精灵线程（主线程与次线程之分，setDeamon（boolean） 将该线程设置为守护线程
        //当正在运行的线程都是守护线程时jvm退出，该方法需在线程启动前调用，运行效果：当前主线程运行完毕，其他线程也随之退出）
        //所谓守护线程是指在程序运行的时候在后台提供一种通用服务的线程，比如垃圾回收线程就是一个很称职的守护者，并且这种线程并不属于程序中不可或缺的部分。
        // 因 此，当所有的非守护线程结束时，程序也就终止了，同时会杀死进程中的所有守护线程。反过来说，只要任何非守护线程还在运行，程序就不会终止。
        DaemonThread daemonThread = new DaemonThread();
        //线程同步机制
        //对线程下的问题  访问同一个对象造成数据安全问题
        TicketSellWindow ticketSellWindow = new TicketSellWindow();
        //解决 队列加锁  使用关键字  synchronized
        //但是使用锁会造成阻塞影响效率，以及性能倒置
        //同步方法的弊端方法中需要修改的东西才需要锁，锁太多浪费资源
        //显示锁 lock (手动开关锁)     synchronized是隐式锁，锁是自动释放  ReentrantLockDome.Dome
        //使用lock锁JVM将花费更少的时间来调度，性能更好，并有更好的扩展性  也可以使用读写锁提高效率
        ReentrantLock lock = new ReentrantLock();//无参数 ，为非公平锁
        FairSuoDome fairSuoDome = new FairSuoDome();

        //线程的协作通信
        //生产者消费者模式
        //等待唤醒

        //虚假唤醒问题  todo

        //ConsumeAndProCondition  利用lock与condition实现消费者与生产者模型  可以实现精准唤醒

        // synchronized锁的是对象
        // 静态同步方法锁的是类字节码对象

        //集合类不安全
        CollectionUnSafeDome unSafeDome = new CollectionUnSafeDome();

        //Callbale  Callable接口类似于Runnable ，因为它们都是为其实例可能由另一个线程执行的类设计的。
        // 然而，A Runnable不返回结果，也不能抛出被检查的异常。中间使用适配器做转换FutureTask

        // 线程辅常用的辅助类  CountDownLatch，CyclicBarrier，Semaphore
        // CountDownLatch 允许一个或多个线程等待直到在其他线程中执行的一组操作完成的同步辅助。
        CountDownLatchDome countDownLatchDome = new CountDownLatchDome();
        //允许一组线程全部等待彼此达到共同屏障点的同步辅助。 循环阻塞在涉及固定大小的线程方的程序中很有用，这些线程必须偶尔等待彼此。
        // 屏障被称为循环 ，因为它可以在等待的线程被释放之后重新使用。 说白了就是CountDownLatch的反向
        CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo();

        // Semaphore 一个计数信号量。 在概念上，信号量维持一组许可证。 如果有必要，每个acquire()都会阻塞，直到许可证可用，然后才能使用它。
        SemaphoreDome semaphoreDome = new SemaphoreDome();

        //读写锁 ReadWriteLock  读的时候可以多个线程取读，写的时候可以一个线程去写
        ReadWriteLockDome dome = new ReadWriteLockDome();

        //阻塞队列  阻塞加队列    队列中如果满了写入就会阻塞  如果队列为空的话读取就会阻塞
        BlockingQueueDome dome2 = new BlockingQueueDome();

        //线程池 池化技术
        // 程序的运行，本质：占用系统的资源！ 优化资源的使用！=>池化技术 线程池、连接池、内存池、对象池///..... 创建、销毁。十分浪费资源
        // 池化技术：事先准备好一些资源，有人要用，就来我这里拿，用完之后还给我。
        // 降低资源的消耗,提高响应的速度,方便管理。


































    }
    private int i;
    class Thread1 extends Thread{
        @Override
        public void run() {
            for(;i < 10;i++) {
                //当通过继承Thread类的方式实现多线程时，可以直接使用this获取当前执行的线程
                System.out.println(this.getName() + " "  + i);
            }
        }
    }

}
