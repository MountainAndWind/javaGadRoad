package XainCheng.ThreadPool;
import java.util.*;
import java.util.concurrent.*;

/**
 * @description  计算1 到 20 万的质数
 * @Author slfang
 * @Time 2019/4/2 16:07
 * @Version 1.0
 **/
public class CountNumberDome {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start1 = System.currentTimeMillis();
        List<Integer> list = getPrime(1,200000);
        long end1 = System.currentTimeMillis();
        System.out.println(list.size());
        System.out.println("单线程环境下的时间是:"+(end1-start1));

        long start2 = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<List<Integer>> submit1 = executorService.submit(new Task(1, 80000));
        Future<List<Integer>> submit2 = executorService.submit(new Task(80001, 130000));
        Future<List<Integer>> submit3 = executorService.submit(new Task(130001,170000));
        Future<List<Integer>> submit4 = executorService.submit(new Task(170001,200000));
        long end2 = System.currentTimeMillis();
        System.out.println(submit1.get().size()+submit2.get().size()+submit3.get().size()+submit4.get().size());
        System.out.println("多线程环境下的时间是:"+(end2-start2));

        TimeUnit.SECONDS.sleep(1);
        System.out.println("演示结束。。。。。");
    }


    static class Task implements Callable<List<Integer>>{

        int start,end;

        Task(int start,int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public List<Integer> call() throws Exception {
            return getPrime(start,end);
        }
    }

    public static boolean isPrime(Integer integer){
        for (int i = 2; i <=integer/2 ; i++) {
            if(integer%i==0) return false;
        }
        return true;
    }

    private static List<Integer> getPrime(int start, int end) {
        List list = new ArrayList();
        for (int i = start; i <end ; i++) {
            if(isPrime(i)){
                 list.add(i);
            }
        }
        return list;
    }

}
