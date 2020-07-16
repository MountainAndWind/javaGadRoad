package XainCheng.ThreadPool;

import java.util.concurrent.*;

/**
 * @description  线程池入门程序
 * @Author slfang
 * @Time 2019/4/2 8:53
 * @Version 1.0
 **/
public class Dome1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       /* ExecutorService executorService = Executors.newFixedThreadPool(4);
        Executors.newWorkStealingPool();
        System.out.println(executorService);
        for (int i = 0; i <5 ; i++) {
            executorService.execute(
                    new Runnable() {
                        @Override
                        public void run() {
                            System.out.println(Thread.currentThread().getName()+"启动了");
                        }
                    }
            );
        }*/
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 5; i++) {
            Future<String> submit = executorService.submit(new Callable<String>() {
                @Override
                public String call() {
                    return Thread.currentThread().getName()+"this is call";
                }
            });
            System.out.println(submit.get());
            System.out.println(submit);
        }
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
      /*  System.out.println();
        System.out.println(executorService.isShutdown());
        executorService.shutdown();
        System.out.println(executorService.isShutdown());
        System.out.println(executorService.isTerminated());*/
    }
}
