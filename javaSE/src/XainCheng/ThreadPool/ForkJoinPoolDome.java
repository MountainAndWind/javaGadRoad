package XainCheng.ThreadPool;

import javafx.concurrent.Task;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @description ForkJoin线程池  测试1000000下的质数
 * @Author slfang
 * @Time 2019/4/2 14:48
 * @Version 1.0
 **/
public class ForkJoinPoolDome {

    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();

    static {
        for (int i = 0; i <nums.length ; i++) {
            nums[i] = r.nextInt(100);
       }
        System.out.println(Arrays.stream(nums).sum());
    }

   /* static class AddTask extends RecursiveAction{//抽像方法

        int start,end;

        AddTask(int s,int e){
            this.start = s;
            this.end = e;
        }

        @Override
        protected void compute() {
            if(end-start>MAX_NUM){
                int middle = start+(end-start)/2;
                AddTask addTask1 = new AddTask(start,middle);
                AddTask addTask2 = new AddTask(middle,end);
                addTask1.fork();
                addTask2.fork();
            }else{
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum+=nums[i];
                }
                System.out.println("form:"+start+"to"+end+":"+sum);
            }
        }
    }*/
    static class AddTask2 extends RecursiveTask<Long> {//抽像方法

        int start,end;

        AddTask2(int s,int e){
            this.start = s;
            this.end = e;
        }

        @Override
        protected Long compute() {
            if(end-start>MAX_NUM){
                int middle = start+(end-start)/2;
                AddTask2 addTask1 = new AddTask2(start,middle);
                AddTask2 addTask2 = new AddTask2(middle,end);
                addTask1.fork();
                addTask2.fork();
                return addTask1.join()+addTask2.join();
            }else{
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum+=nums[i];
                }
                /*System.out.println("form:"+start+"to"+end+":"+sum);*/
                return sum;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        ForkJoinPool pool = new ForkJoinPool();
        AddTask2 addTask = new AddTask2(0,nums.length);
        pool.execute(addTask);
        System.out.println(addTask.join());
        System.in.read();
        /*ForkJoinPool pool = new ForkJoinPool();
        AddTask addTask = new AddTask(0,nums.length);
        pool.execute(addTask);
        System.in.read();*/
    }
}
