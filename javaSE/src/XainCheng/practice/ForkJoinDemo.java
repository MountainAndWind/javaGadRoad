package XainCheng.practice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @description:求和计算的任务！
 * @author: slfang
 * @time: 2020/12/4 16:04
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //test1(); 8799
        //test2();467
        //test3();291
    }

    //单线程方法
    public static void test1() {
        Long sum = 0L;
        long start = System.currentTimeMillis();
        for (Long i = 1L; i <= 10_0000_0000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + " 时间：" + (end - start));
    }

    // forkJoin 方法
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        Long sum = submit.get();
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + " 时间：" + (end - start));
    }


    // Stream并行流
    public static void test3() {
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum="+sum+"时间："+(end-start));
    }

        private Long start;
        private Long end;

        // 临界值
        private Long temp = 10000L;

    public ForkJoinDemo(Long start, Long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute () {
            if ((end - start) < temp) {
                long sum = 0l;
                for (long i = start; i < end; i++) {
                    sum += i;
                }
                return sum;
            } else {
                long middle = (start + end) / 2; // 中间值
                ForkJoinDemo task = new ForkJoinDemo(start, middle);
                task.fork();
                ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, end);
                task2.fork();
                return task.join() + task2.join();
            }
        }
    }
