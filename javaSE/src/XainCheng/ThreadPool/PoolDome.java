package XainCheng.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description  �̳߳����ų���
 * @Author slfang
 * @Time 2019/4/2 8:53
 * @Version 1.0
 **/
public class PoolDome {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // ExecutorService�ӿ������������ɸ�submit���������ذ汾
       /* <T> Future<T> submit(Callable<T> task);
         <T> Future<T> submit(Runnable task, T result);
         Future<?> submit(Runnable task);*/
        test1();
         //test();
        // Executor��ExecutorService ��Executor ������е��Ʊʼ�
        //test1();  execute()��ִ��һ������û�з���ֵ��
        //test2();  submit()���ύһ���߳������з���ֵ��
        //test3();  ����future��һЩ����

        // ����ʹ���̳߳ػ��������
        // �̳߳ز�����ʹ�� Executors ȥ����������ͨ�� ThreadPoolExecutor �ķ�ʽ�������Ĵ���ʽ��д��ͬѧ������ȷ�̳߳ص����й��򣬹����Դ�ľ��ķ��ա�
        // ԭ��FixedThreadPool �� SingleThreadPool:�����������г���Ϊ Integer.MAX_VALUE�����ܻ�ѻ����������󣬴Ӷ�����
        //       CachedThreadPool �� ScheduledThreadPool:����Ĵ����߳�����Ϊ Integer.MAX_VALUE�����ܻᴴ���������̣߳��Ӷ����� OOM��
        // �������԰���ͰͿ����ֲ�
        // Executors�ṩ�ľ�̬����������Դ���о�ʵ��Ϊ ThreadPoolExecutor�Ĺ��췽�� new ThreadPoolExecutor(1, 1,
        //                                                 0L, TimeUnit.MILLISECONDS,
        //                                           new LinkedBlockingQueue<Runnable>())) ����newScheduledThreadPool������
        // new ThreadPoolExecutor(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,new DelayedWorkQueue());todo ������潲


        // ThreadPoolExecutor���췽��7���������  �е��Ʊʼǡ�4��ThreadPoolExecutor���̳߳ش����ߣ���
        //public ThreadPoolExecutor(
        //     int corePoolSize, // �����̳߳ش�С
        //     int maximumPoolSize, // �������̳߳ش�С
        //     long keepAliveTime, // ��ʱ��û���˵��þͻ��ͷ�
        //     TimeUnit unit, // ��ʱ��λ
        //     BlockingQueue<Runnable> workQueue, // ��������
        //         ���з�Ϊ�н�������޽���� todo
        //     ThreadFactory threadFactory, // �̹߳����������̵߳ģ�һ�� ���ö�
        //     RejectedExecutionHandler handle // �ܾ�����)
        //         ���н�������߳�������maximumPoolSizeִ�оܾ�����  4��
        // ����ģ�Ϳ��Կ���JUC �ʼ�

        // ��չ �е��Ʊʼ�4��ThreadPoolExecutor���̳߳ش����ߣ��Լ��̳߳ص���չ
        //      �̳߳�Ӧ�����ȥ����
        //      �˽⣺IO�ܼ��ͣ�CPU�ܼ��ͣ������ţ�   Ҫ���Ч������CPU�������ܼ�������ͬʱ���е�����Ӧ������CPU�ĺ�������

        //���cpu�ĺ���
        System.out.println(Runtime.getRuntime().availableProcessors());


    }

    /**
     * execute()��ִ��һ������û�з���ֵ��
     */
    private static void test1() {
        //�̳߳ص����������ֳɵĶ������������̵߳�����
        // Executors�ṩ��������Ҫ�����̳߳ص����ַ���
        // Executors�����ڴ�������
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService1 = Executors.newFixedThreadPool(4);
        ExecutorService executorService2 = Executors.newCachedThreadPool();

        //���� Timer ������ȱ�ݣ�Java 5 �Ƴ��˻����̳߳���Ƶ� ScheduledExecutor�������˼���ǣ�
        // ÿһ�������ȵ����񶼻����̳߳���һ���߳�ȥִ�У���������ǲ���ִ�еģ��໥֮�䲻���ܵ����š���Ҫע����ǣ�
        // ֻ�е������ִ��ʱ�䵽��ʱ��ScheduedExecutor �Ż���������һ���̣߳�����ʱ�� ScheduledExecutor ��������ѯ�����״̬��

        //ScheduleAtFixedRate ÿ��ִ��ʱ��Ϊ��һ������ʼ�������һ��ʱ��������ÿ��ִ��ʱ��Ϊ :initialDelay, initialDelay+period, initialDelay+2*period, ����
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        long initialDelay1 = 1;
        long period1 = 1;
        // �����ڿ�ʼ1����֮��ÿ��1����ִ��һ��job1
        scheduledExecutorService.scheduleAtFixedRate(()->{
            System.out.println(Thread.currentThread().getName()+"��ʱ�̳߳�ִ���˲���scheduleAtFixedRate����");
        },initialDelay1,period1,TimeUnit.SECONDS);


        long initialDelay2 = 1;
        long delay2 = 1;
        // ScheduleWithFixedDelay ÿ��ִ��ʱ��Ϊ��һ����������������һ��ʱ������
        // ��ÿ��ִ��ʱ��Ϊ��initialDelay, initialDelay+executeTime+delay,
        // initialDelay+2*executeTime+2*delay���ɴ˿ɼ���ScheduleAtFixedRate �ǻ��ڹ̶�ʱ��������������ȣ�
        // ScheduleWithFixedDelay ȡ����ÿ������ִ�е�ʱ�䳤�̣��ǻ��ڲ��̶�ʱ��������������ȡ�
        scheduledExecutorService.scheduleWithFixedDelay(()->
                {
                    System.out.println(Thread.currentThread().getName()+"��ʱ�̳߳�ִ���˲���scheduleWithFixedDelay");
                }
               , initialDelay2,
                delay2, TimeUnit.SECONDS);

       /* for (int i = 0; i <100 ; i++) {
            final int temp = i;
            scheduledExecutorService.execute(()->{//�˴��� execute���õ��Ǹ��ӿڵ� Executor�ķ���
                System.out.println(Thread.currentThread().getName()+"====="+temp);
            });
        }
        //executorService.shutdown();
        scheduledExecutorService.shutdown();*/
    }

    /**
     * submit()���ύһ���߳������з���ֵ��
     */
    private static void test2() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 5; i++) {
            Future<String> submit = executorService.submit(new Callable<String>() {//�����ڲ��෽ʽ
                @Override
                public String call() {
                    return Thread.currentThread().getName()+"this is call";
                }
            });
            // Callable�ӿ�������Runnable ����Ϊ���Ƕ���Ϊ��ʵ����������һ���߳�ִ�е�����Ƶġ�
            // Ȼ����A Runnable�����ؽ����Ҳ�����׳��������쳣���м�ʹ����������ת��FutureTask
            // ӦΪFutureTask ��ʵ��RunnableFuture  RunnableFuture�Ǽ̳�Runnable, Future
            // Future ���Ƕ��ھ����Runable ����callable�����ִ�н������ȡ������ѯ�Ƿ���ɣ���ȡ�����
            // ��Ҫʱͨ��get������ȡִ�н�����÷���������ֱ�����񷵻ؽ��
            // Future�еķ��������е��Ʊʼ� Future ֻ��һ���ӿ� FutureTask ������ʵ����
            try {
                System.out.println(submit.get());// ��ȡ���н��
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
                while (!future.isDone());//todo �о���д��
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }*/
            try {
                System.out.println(future.get());
                //get()����������ȡִ�н������������������������һֱ�ȵ�����ִ����ϲŷ��أ�
                //get(long timeout, TimeUnit unit)������ȡִ�н���������ָ��ʱ���ڣ���û��ȡ���������ֱ�ӷ���null��
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
        executorService.shutdown();
    }
}


