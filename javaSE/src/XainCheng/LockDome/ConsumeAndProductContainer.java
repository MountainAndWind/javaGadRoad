package XainCheng.LockDome;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description 使用同步容器实现成产者与消费者  此处应该是使用生产和消费者各使用单线程来实现，各自多线程会出现线程安全问题
 * @Author slfang
 * @Time 2019/4/1 14:07
 * @Version 1.0
 **/
public class ConsumeAndProductContainer {
    static Object object = new Object();
    public static void main(String[] args) {


        /*LinkedBlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<Integer>(3);
        for (int i = 0; i <2 ; i++) {
            Thread thread = new Thread("pro"){
                @Override
                public void run() {
                   while (true){
                       synchronized (object){
                           try {
                               linkedBlockingQueue.put(1);
                               System.out.println("生产者生产了 此时容量："+linkedBlockingQueue.size());
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                       }
                   }
                }
            };
            thread.start();
        }

        for (int i = 0; i <2; i++) {
            Thread thread = new Thread("con"){
                @Override
                public void run() {
                   while (true){
                       synchronized (object){
                           try {
                               Thread. sleep(3000);
                               linkedBlockingQueue.take();
                               System.out.println("消费者消费了  此时容量："+linkedBlockingQueue.size());
                           }catch (Exception e){
                               e.printStackTrace();
                           }
                       }
                   }
                }
            };
            thread.start();
        }*/
        BlockingQueue publicBoxQueue= new LinkedBlockingQueue(5);   //定义了一个大小为5的盒子

        Thread pro= new Thread(new ProducerQueue(publicBoxQueue));
        Thread con= new Thread(new ConsumerQueue(publicBoxQueue));

        pro.start();
        con.start();
    }
}
class ProducerQueue implements Runnable {

    private final BlockingQueue proQueue;

    public ProducerQueue(BlockingQueue proQueue)
    {
        this .proQueue =proQueue;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

        for (int i=0;i<10;i++)
        {
            try {
                proQueue .put(i);
                System. out .println("生产者生产的苹果编号为 : " +proQueue.size());  //放入十个苹果编号 为1到10

                /*Thread.sleep(3000);*/
            } catch (InterruptedException  e) {
                // TODO: handle exception
                e.printStackTrace();
            }

        }

    }


}
class ConsumerQueue implements Runnable {

    private final BlockingQueue conQueue;

    public ConsumerQueue(BlockingQueue conQueue)
    {
        this .conQueue =conQueue;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for (int i=0;i<10;i++)
        {
            try {
                Thread. sleep(3000);  //在这里sleep是为了看的更加清楚些
                conQueue.take();
                System. out .println("消费者消费的苹果 ：" +conQueue.size());

            } catch (InterruptedException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }


}
