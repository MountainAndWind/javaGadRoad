package XainCheng.ThreadPool;

import java.util.concurrent.*;

/**
 * @description:
 * @author: slfang
 * @time: 2020/12/4 14:54
 */
public class MyThreadPool  {

    /*** new ThreadPoolExecutor.AbortPolicy() // 银行满了，还有人进来，不处理这个人的，抛出异 常*
     * new ThreadPoolExecutor.CallerRunsPolicy() // 哪来的去哪里！ *
     * new ThreadPoolExecutor.DiscardPolicy() //队列满了，丢掉任务，不会抛出异常！
     * new ThreadPoolExecutor.DiscardOldestPolicy() //队列满了，尝试去和最早的竞争，也不会 抛出异常！ */
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 4,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());//队列满了，尝试去和 最早的竞争，也不会抛出异常！

       try {
           for (int i = 0; i < 100000; i++) {
               final int temp = i;
               Future<Integer> future =  threadPoolExecutor.submit(()->{
                   System.out.println(Thread.currentThread().getName()+"执行");
                   return temp;
               });
           }
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           threadPoolExecutor.shutdown();
       }

    }

}
