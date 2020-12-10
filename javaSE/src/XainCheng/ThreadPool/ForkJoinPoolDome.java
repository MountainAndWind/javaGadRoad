package XainCheng.ThreadPool;


import XainCheng.practice.ForkJoinDemo;

import java.io.IOException;

/**
 * @description ForkJoin线程池  测试1000000下的质数
 * @Author slfang
 * @Time 2019/4/2 14:48
 * @Version 1.0
 **/
public class ForkJoinPoolDome {


      /*  ForkJoin 在 JDK 1.7 ， 并行执行任务！提高效率。大数据量！
        大数据：Map Reduce （把大任务拆分为小任务）
        ForkJoin 特点：工作窃取  处理子问题的线程会主动寻找其他的尚未完成的子问题来执行。
        原理：查看有道云笔记6、ForkJoinPool（精灵线程）使用与分而治之，处理大计算量场景下使用。
        */
    public static void main(String[] args) throws IOException {
        ForkJoinDemo forkJoinDemo =null;
    }
}
