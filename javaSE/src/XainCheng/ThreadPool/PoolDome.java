package XainCheng.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description  线程池入门程序
 * @Author slfang
 * @Time 2019/4/2 8:53
 * @Version 1.0
 **/
public class PoolDome {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // ExecutorService接口中声明了若干个submit方法的重载版本
       /* <T> Future<T> submit(Callable<T> task);
         <T> Future<T> submit(Runnable task, T result);
         Future<?> submit(Runnable task);*/
        test1();
         //test();
        // Executor、ExecutorService 、Executor 具体见有道云笔记
        //test1();  execute()，执行一个任务，没有返回值。
        //test2();  submit()，提交一个线程任务，有返回值。
        //test3();  测试future的一些方法

        // 但是使用线程池会出现问题
        // 线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
        // 原因：FixedThreadPool 和 SingleThreadPool:允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致
        //       CachedThreadPool 和 ScheduledThreadPool:允许的创建线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，从而导致 OOM。
        // 以上引自阿里巴巴开发手册
        // Executors提供的静态工厂方法中源码中均实现为 ThreadPoolExecutor的构造方法 new ThreadPoolExecutor(1, 1,
        //                                                 0L, TimeUnit.MILLISECONDS,
        //                                           new LinkedBlockingQueue<Runnable>())) ，除newScheduledThreadPool外他是
        // new ThreadPoolExecutor(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,new DelayedWorkQueue());todo 这个后面讲


        // ThreadPoolExecutor构造方法7大参数讲解  有道云笔记【4、ThreadPoolExecutor（线程池创建者）】
        //public ThreadPoolExecutor(
        //     int corePoolSize, // 核心线程池大小
        //     int maximumPoolSize, // 最大核心线程池大小
        //     long keepAliveTime, // 超时了没有人调用就会释放
        //     TimeUnit unit, // 超时单位
        //     BlockingQueue<Runnable> workQueue, // 阻塞队列
        //         队列分为有界队列与无界队列 todo
        //     ThreadFactory threadFactory, // 线程工厂：创建线程的，一般 不用动
        //     RejectedExecutionHandler handle // 拒绝策略)
        //         当有界队列中线程数大于maximumPoolSize执行拒绝策略  4种
        // 参数模型可以狂神JUC 笔记

        // 扩展 有道云笔记4、ThreadPoolExecutor（线程池创建者）以及线程池的扩展
        //      线程池应该如何去设置
        //      了解：IO密集型，CPU密集型：（调优）   要最高效地利用CPU，计算密集型任务同时进行的数量应当等于CPU的核心数。

        //输出cpu的核数
        System.out.println(Runtime.getRuntime().availableProcessors());


    }

    /**
     * execute()，执行一个任务，没有返回值。
     */
    private static void test1() {
        //线程池的作用限制现成的饿数量，管理线程的作用
        // Executors提供了以下主要创建线程池的四种方法
        // Executors类似于创建工厂
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService1 = Executors.newFixedThreadPool(4);
        ExecutorService executorService2 = Executors.newCachedThreadPool();

        //鉴于 Timer 的上述缺陷，Java 5 推出了基于线程池设计的 ScheduledExecutor。其设计思想是，
        // 每一个被调度的任务都会由线程池中一个线程去执行，因此任务是并发执行的，相互之间不会受到干扰。需要注意的是，
        // 只有当任务的执行时间到来时，ScheduedExecutor 才会真正启动一个线程，其余时间 ScheduledExecutor 都是在轮询任务的状态。

        //ScheduleAtFixedRate 每次执行时间为上一次任务开始起向后推一个时间间隔，即每次执行时间为 :initialDelay, initialDelay+period, initialDelay+2*period, …；
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        long initialDelay1 = 1;
        long period1 = 1;
        // 从现在开始1秒钟之后，每隔1秒钟执行一次job1
        scheduledExecutorService.scheduleAtFixedRate(()->{
            System.out.println(Thread.currentThread().getName()+"定时线程池执行了测试scheduleAtFixedRate方法");
        },initialDelay1,period1,TimeUnit.SECONDS);


        long initialDelay2 = 1;
        long delay2 = 1;
        // ScheduleWithFixedDelay 每次执行时间为上一次任务结束起向后推一个时间间隔，
        // 即每次执行时间为：initialDelay, initialDelay+executeTime+delay,
        // initialDelay+2*executeTime+2*delay。由此可见，ScheduleAtFixedRate 是基于固定时间间隔进行任务调度，
        // ScheduleWithFixedDelay 取决于每次任务执行的时间长短，是基于不固定时间间隔进行任务调度。
        scheduledExecutorService.scheduleWithFixedDelay(()->
                {
                    System.out.println(Thread.currentThread().getName()+"定时线程池执行了测试scheduleWithFixedDelay");
                }
               , initialDelay2,
                delay2, TimeUnit.SECONDS);

       /* for (int i = 0; i <100 ; i++) {
            final int temp = i;
            scheduledExecutorService.execute(()->{//此处的 execute调用的是父接口的 Executor的方法
                System.out.println(Thread.currentThread().getName()+"====="+temp);
            });
        }
        //executorService.shutdown();
        scheduledExecutorService.shutdown();*/
    }

    /**
     * submit()，提交一个线程任务，有返回值。
     */
    private static void test2() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 5; i++) {
            Future<String> submit = executorService.submit(new Callable<String>() {//匿名内部类方式
                @Override
                public String call() {
                    return Thread.currentThread().getName()+"this is call";
                }
            });
            // Callable接口类似于Runnable ，因为它们都是为其实例可能由另一个线程执行的类设计的。
            // 然而，A Runnable不返回结果，也不能抛出被检查的异常。中间使用适配器做转换FutureTask
            // 应为FutureTask 是实现RunnableFuture  RunnableFuture是继承Runnable, Future
            // Future 就是对于具体的Runable 或者callable任务的执行结果进行取消，查询是否完成，获取结果，
            // 必要时通过get方法获取执行结果，该方法会阻塞直到任务返回结果
            // Future中的方法详解见有道云笔记 Future 只是一个接口 FutureTask 是他的实现类
            try {
                System.out.println(submit.get());// 获取运行结果
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(submit);
        }
        //TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        executorService.shutdown();
        System.out.println();
        System.out.println(executorService.isShutdown());
        executorService.shutdown();
        System.out.println(executorService.isShutdown());
        System.out.println(executorService.isTerminated());
    }


    public static void test3() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            Future<Integer> future = executorService.submit(()->{
                System.out.println(Thread.currentThread().getName());
                return temp;
            });
            list.add(future);
        }
        for (Future future : list) {
           /* try {
                while (!future.isDone());//todo 研究次写法
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }*/
            try {
                System.out.println(future.get());
                //get()方法用来获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回；
                //get(long timeout, TimeUnit unit)用来获取执行结果，如果在指定时间内，还没获取到结果，就直接返回null。
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
        executorService.shutdown();
    }
}


