package XainCheng.util;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: slfang
 * @time: 2020/11/30 16:31
 */
public class SemaphoreDome {

    public static void main(String[] args) {
        //场景抢车位   6车---3个停车位置
        // 线程数量：停车位! 限流！

        //作用： 多个共享资源互斥的使用！并发限流，控制最大的线程数！
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "in");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() +"out");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }



    }


}
