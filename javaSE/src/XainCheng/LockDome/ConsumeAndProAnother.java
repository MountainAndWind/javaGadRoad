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
public class ConsumeAndProAnother {

    private static int MXX_SIZE = 10;

    public static void main(String[] args) {
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
                            if(list.size()>MXX_SIZE){
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
                        if(list.size()==0){
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
