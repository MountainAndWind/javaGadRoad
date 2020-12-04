package XainCheng.LockDome;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.*;

/**
 * @description   利用lock与condition实现消费者与生产者模型
 *
 *  案列模型；
 *   5个消费者与2个生产者一起运行，容器大小为10    //次数避免使用   生产者与消费者各自是多线程
 * @Author slfang
 * @Time 2019/3/29 17:18
 * @Version 1.0
 **/
public class ConsumeAndProCondition {

    private static int MXX_SIZE = 10;

    private static int num=1;

    public static void main(String[] args) {
        //test1();
        test2();
    }

    /**
     * 测试condition的精确唤醒
     */
    private static void test2() {
        ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();
        new Thread(()->{
            for (int i = 0; i <100 ; i++) {
                lock.lock();
                try {
                    while (num!=1){
                        condition1.await();
                    }
                    System.out.println(Thread.currentThread().getName()+"唤醒----------------");
                    num=2;
                    condition2.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        },"线程1").start();

        new Thread(()->{
            for (int i = 0; i <100 ; i++) {
                lock.lock();
                try {
                    while (num!=2){
                        condition2.await();
                    }
                    System.out.println(Thread.currentThread().getName()+"唤醒----------------");
                    num=3;
                    condition3.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        },"线程2").start();

        new Thread(()->{
            for (int i = 0; i <100 ; i++) {
                lock.lock();
                try {
                    while (num!=3){
                        condition3.await();
                    }
                    System.out.println(Thread.currentThread().getName()+"唤醒----------------");
                    num=1;
                    condition1.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        },"线程3").start();
    }

    /**
     * 测试生产者消费者
     */
    public static void test1(){
        ReentrantLock lock = new ReentrantLock();
        Condition consume = lock.newCondition();
        Condition product = lock.newCondition();
        List list = new ArrayList();
        for (int i = 0; i < 1; i++) {//生产者线程
            Thread thread = new Thread(""+i){
                public void run(){
                    while (true){
                        try {
                            lock.lock();
                            /*lock.*/
                            while(list.size()>MXX_SIZE){//此处要用while而不是用if
                                // 就是用if判断的话，唤醒后线程会从wait之后的代码开始运行，但是不会重新判断if条件，直接继续运行if代码块之后的代码，
                                // 而如果使用while的话，也会从wait之后的代码运行，但是唤醒后会重新判断循环条件，如果不成立再执行while代码块之后
                                // 的代码块，成立的话继续wait。
                                try {
                                    product.await();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            list.add("A");
                            System.out.println(Thread.currentThread().getName()+"生产者生产了》》》》》》》》》》》size为"+list.size());
                            consume.signalAll();
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }finally {
                            lock.unlock();
                        }
                    }
                }
            };
            thread.start();
        }

        for (int i = 0; i < 1; i++) {//消费者线程
            Thread thread = new Thread(""+i){
                public void run(){
                    while (true){
                        lock.lock();
                        /*lock.*/
                        while(list.size()==0){
                            try {
                                consume.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        list.remove(0);
                        System.out.println(Thread.currentThread().getName()+"生产者消费了》》》》》size为"+list.size());
                        product.signalAll();
                    }
                }
            };
            thread.start();
        }
    }

}
