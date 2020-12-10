package XainCheng.ThreadPool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description  WorkSteelPool 精灵线程主线程不阻塞看出输出、任务窃取线程
 * @Author slfang
 * @Time 2019/4/2 11:22
 * @Version 1.0
 **/
public class WorkSteelPoolDome {

    /**
     * 每个线程都有要处理的队列的任务，如果其中的线程完成自己的队列的任务，
     * 那么它可以去其他线程中获取其他线程的任务去执行
     *
     * newWorkStealingPool 不是ThreadPoolExecutor的扩展，他是新线程类的ForkJoinPool的扩展
     * 适合处理很耗时的操作
     * @param args
     * @throws InterruptedException
     * @throws IOException
     */
    public static void main(String[] args) throws InterruptedException, IOException {
       /* System.out.println(Runtime.getRuntime().availableProcessors());//计算当前cpu是几核处理器*/
        ExecutorService executorService = Executors.newWorkStealingPool();// 会根据Cpu是几核然后启动对应的线程数量看源码
        executorService.execute(new R(1000));
        executorService.execute(new R(2000));
        executorService.execute(new R(3000));
        executorService.execute(new R(4000));
        executorService.execute(new R(5000));
        executorService.execute(new R(6000));
        executorService.execute(new R(7000));
        executorService.execute(new R(8000));
        executorService.execute(new R(9000));
        executorService.execute(new R(10000));
        System.in.read();
    }

    static class R implements Runnable {
        int time;

        public R(int time) {
            this.time = time;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time + ":" + Thread.currentThread().getName());
        }
    }
}
