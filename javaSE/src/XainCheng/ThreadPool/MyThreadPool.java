package XainCheng.ThreadPool;

import java.util.concurrent.*;

/**
 * @description:
 * @author: slfang
 * @time: 2020/12/4 14:54
 */
public class MyThreadPool  {

    /*** new ThreadPoolExecutor.AbortPolicy() // �������ˣ������˽���������������˵ģ��׳��� ��*
     * new ThreadPoolExecutor.CallerRunsPolicy() // ������ȥ��� *
     * new ThreadPoolExecutor.DiscardPolicy() //�������ˣ��������񣬲����׳��쳣��
     * new ThreadPoolExecutor.DiscardOldestPolicy() //�������ˣ�����ȥ������ľ�����Ҳ���� �׳��쳣�� */
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 4,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());//�������ˣ�����ȥ�� ����ľ�����Ҳ�����׳��쳣��

       try {
           for (int i = 0; i < 100000; i++) {
               final int temp = i;
               Future<Integer> future =  threadPoolExecutor.submit(()->{
                   System.out.println(Thread.currentThread().getName()+"ִ��");
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
