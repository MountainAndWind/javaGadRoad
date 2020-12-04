package XainCheng.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @description:
 * @author: slfang
 * @time: 2020/11/30 16:10
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,new Thread(
                ()->{
                    System.out.println(Thread.currentThread().getName() + "�ﵽ�������֮��Ĵ���");
                }
        ));
        for (int i = 0; i < 7; i++) {
            int temp = i;
            new Thread(()->{
                System.out.println("������"+temp);
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {

                }
            }).start();
        }
    }
}
