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

    public static void main(String[] args) throws InterruptedException, IOException {
       /* System.out.println(Runtime.getRuntime().availableProcessors());//计算当前cpu是几核处理器*/
        ExecutorService executorService = Executors.newWorkStealingPool();
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
