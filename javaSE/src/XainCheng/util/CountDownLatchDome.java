package XainCheng.util;

import java.util.concurrent.CountDownLatch;

/**
 * @description
 * CountDownLatch案列 //https://zapldy.iteye.com/blog/746458
 * 创建三个线程依次打印出设置abc的值并且打印
 *
 * @Author slfang
 * @Time 2019/4/10 16:08
 * @Version 1.0
 **/
public class CountDownLatchDome {

    private static int a;

    private static int b;

    private static int c;

    public static void main(String[] args) throws InterruptedException {
        //test1();
        test2();



    }

    private static void test2() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i <6 ; i++) {
            new Thread(()->{
                countDownLatch.countDown();//每次有线程调用 countDown() 数量-1，假设计数器变为0，
                // countDownLatch.await() 就会被唤醒，继续执行
                System.out.println("门sauna  消费减一");
            }).start();
        }

        countDownLatch.await();// 等待计数器归零，然后再向下执行
        System.out.println("结束");
    }

    private static void test1() {
        CountDownLatch latch = new CountDownLatch(2);
        new A(latch).start();
        new B(latch).start();
        new C(latch).start();
    }

    static class A extends Thread{

        private CountDownLatch countDownLatch;

        public A(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            a = 3;
            System.out.println("线程A设置a值为3");
            this.countDownLatch.countDown();
        }
    }
    static class B extends Thread{

        private CountDownLatch countDownLatch;

        public B(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            b = 3*a;
            System.out.println("线程B设置b值为"+b);
            this.countDownLatch.countDown();
        }
    }
    static class C extends Thread{

        private CountDownLatch countDownLatch;

        public C(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }
        @Override
        public void run() {
            try {
                this.countDownLatch.await();
                c = a+b;
                System.out.println("线程C设置c值为"+c);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
