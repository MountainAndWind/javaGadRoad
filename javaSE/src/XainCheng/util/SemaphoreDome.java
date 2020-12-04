package XainCheng.util;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: slfang
 * @time: 2020/11/30 16:31
 */
public class SemaphoreDome {

    public static void main(String[] args) {
        //��������λ   6��---3��ͣ��λ��
        // �߳�������ͣ��λ! ������

        //���ã� ���������Դ�����ʹ�ã��������������������߳�����
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "in");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() +"out");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }



    }


}
