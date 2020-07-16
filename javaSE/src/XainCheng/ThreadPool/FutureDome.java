package XainCheng.ThreadPool;

import java.util.concurrent.*;

/**
 * @description  futureTask dome
 * @Author slfang
 * @Time 2019/4/2 10:20
 * @Version 1.0
 **/
public class FutureDome {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Thread.currentThread().getName()+\"this is callMethod\"");
                return Thread.currentThread().getName()+"this is callMethod";
            }
        });

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> submit = (Future<String>) executorService.submit(futureTask);
        TimeUnit.SECONDS.sleep(4);
        while (submit.isDone()){
            System.out.println(futureTask.get());
        }
    }
}
