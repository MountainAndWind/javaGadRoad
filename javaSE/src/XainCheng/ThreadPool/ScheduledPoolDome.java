package XainCheng.ThreadPool;

import java.util.concurrent.*;

/**
 * @description  ScheduledPool测试类
 * @Author slfang
 * @Time 2019/4/2 11:01
 * @Version 1.0
 **/
public class ScheduledPoolDome {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
       /* ScheduledFuture<String> schedule = scheduledExecutorService.schedule(new Callable<String>() {//给定延迟后启动任务
            @Override
            public String call() {
                System.out.println("action");
                return Thread.currentThread().getName() + "call";
            }
        }, 5, TimeUnit.SECONDS);
        System.out.println("主线程");*/
       scheduledExecutorService.scheduleAtFixedRate(new Runnable() {//创建并执行在给定的初始延迟之后，随后以给定的时间段首先启用的周期性动作; 那就是执行将在initialDelay之后开始，然后是initialDelay+period ，然后是initialDelay + 2 * period ，等等。
           @Override
           public void run() {
               System.out.println("执行开始");
           }
       },5,2,TimeUnit.SECONDS);

        System.out.println("主线程");
    }


}
