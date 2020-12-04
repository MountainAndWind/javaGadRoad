package XainCheng;

import XainCheng.LockDome.FairSuoDome;
import XainCheng.LockDome.TicketSellWindow;
import XainCheng.ThreadPool.PoolDome;
import XainCheng.base.CollectionUnSafeDome;
import XainCheng.base.DaemonThread;
import XainCheng.LockDome.ReadWriteLockDome;
import XainCheng.jucContainer.BlockingQueueDome;
import XainCheng.util.CountDownLatchDome;
import XainCheng.util.CyclicBarrierDemo;
import XainCheng.util.SemaphoreDome;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:���߳��Լ�JUC
 * @author: slfang
 * @time: 2020/11/24 16:58
 */
public class Index {

    public static void main(String[] args) throws InterruptedException {
        Index index = new Index();
        //�̵߳�״̬
        //Thread.State; 6��
        //�̵߳���������

        //�̵߳Ĵ���
        //1���̳�Thread��
        for(int j = 0;j < 50;j++) {

            //����Thread���currentThread()������ȡ��ǰ�߳�
            System.out.println(Thread.currentThread().getName() + " " + j);

            if(j == 10) {
                //������������һ���߳�
                new Thread(){
                  public void run(){
                      System.out.println("-----------------");
                  }
                }.start();

                //�����������ڶ����߳�
               index.new Thread1().start();
               index.new Thread1().start();
            }

            //2��ʵ�� Runnable �ӿ�
            new Thread(new Runnable(){

                @Override
                public void run() {
                    System.out.println("run");
                }
            },"run 1").start();
        }

        // Thread �����runnable runnable Ҫ����һЩ������oop  ���ⵥ�̳е�����
        //����̲߳���ͬһ����Դ�������������  TicketSellWindow
        //�����֣�ͨ��Callable��Future�ӿڴ����߳�  XainCheng/ThreadPool/Dome1
        PoolDome dome1 = new PoolDome();
        //�̷߳��������
        //�̵߳���ֹ
        Thread thread = new Thread(()-> System.out.println("lambda�߳̿���"),"lambda�߳�");
        thread.interrupt();
        thread.stop();
        //�߳�����
        thread.sleep(1000);
        //�̵߳�����  �õ�ǰ������ִ�е��߳�ֹͣ�����ǲ����������̴߳�����״̬תΪ����״̬
        // ��cpu���µ��� Ч�������ò�һ���ɹ���ֻ�����̴߳���ͬһ����״̬ ���¾���
        thread.yield();
        //join   �����߳�ִ�����֮����ִ���������̣߳������߳���������������ɲ��  ��ȥʹ��
        thread.join();
        //�̵߳����ȼ�  1-10  ֻ��Ȩ������  ���Ǿ���
        int priority = thread.getPriority();
        thread.setPriority(10);
        //�̷߳�Ϊ�û��߳����ػ��߳�  �ػ��߳��ֳƾ����̣߳����߳�����߳�֮�֣�setDeamon��boolean�� �����߳�����Ϊ�ػ��߳�
        //���������е��̶߳����ػ��߳�ʱjvm�˳����÷��������߳�����ǰ���ã�����Ч������ǰ���߳�������ϣ������߳�Ҳ��֮�˳���
        //��ν�ػ��߳���ָ�ڳ������е�ʱ���ں�̨�ṩһ��ͨ�÷�����̣߳��������������߳̾���һ���ܳ�ְ���ػ��ߣ����������̲߳������ڳ����в��ɻ�ȱ�Ĳ��֡�
        // �� �ˣ������еķ��ػ��߳̽���ʱ������Ҳ����ֹ�ˣ�ͬʱ��ɱ�������е������ػ��̡߳�������˵��ֻҪ�κη��ػ��̻߳������У�����Ͳ�����ֹ��
        DaemonThread daemonThread = new DaemonThread();
        //�߳�ͬ������
        //���߳��µ�����  ����ͬһ������������ݰ�ȫ����
        TicketSellWindow ticketSellWindow = new TicketSellWindow();
        //��� ���м���  ʹ�ùؼ���  synchronized
        //����ʹ�������������Ӱ��Ч�ʣ��Լ����ܵ���
        //ͬ�������ı׶˷�������Ҫ�޸ĵĶ�������Ҫ������̫���˷���Դ
        //��ʾ�� lock (�ֶ�������)     synchronized����ʽ���������Զ��ͷ�  ReentrantLockDome.Dome
        //ʹ��lock��JVM�����Ѹ��ٵ�ʱ�������ȣ����ܸ��ã����и��õ���չ��  Ҳ����ʹ�ö�д�����Ч��
        ReentrantLock lock = new ReentrantLock();//�޲��� ��Ϊ�ǹ�ƽ��
        FairSuoDome fairSuoDome = new FairSuoDome();

        //�̵߳�Э��ͨ��
        //������������ģʽ
        //�ȴ�����

        //��ٻ�������  todo

        //ConsumeAndProCondition  ����lock��conditionʵ����������������ģ��  ����ʵ�־�׼����

        // synchronized�����Ƕ���
        // ��̬ͬ���������������ֽ������

        //�����಻��ȫ
        CollectionUnSafeDome unSafeDome = new CollectionUnSafeDome();

        //Callbale  Callable�ӿ�������Runnable ����Ϊ���Ƕ���Ϊ��ʵ����������һ���߳�ִ�е�����Ƶġ�
        // Ȼ����A Runnable�����ؽ����Ҳ�����׳��������쳣���м�ʹ����������ת��FutureTask

        // �̸߳����õĸ�����  CountDownLatch��CyclicBarrier��Semaphore
        // CountDownLatch ����һ�������̵߳ȴ�ֱ���������߳���ִ�е�һ�������ɵ�ͬ��������
        CountDownLatchDome countDownLatchDome = new CountDownLatchDome();
        //����һ���߳�ȫ���ȴ��˴˴ﵽ��ͬ���ϵ��ͬ�������� ѭ���������漰�̶���С���̷߳��ĳ����к����ã���Щ�̱߳���ż���ȴ��˴ˡ�
        // ���ϱ���Ϊѭ�� ����Ϊ�������ڵȴ����̱߳��ͷ�֮������ʹ�á� ˵���˾���CountDownLatch�ķ���
        CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo();

        // Semaphore һ�������ź����� �ڸ����ϣ��ź���ά��һ�����֤�� ����б�Ҫ��ÿ��acquire()����������ֱ�����֤���ã�Ȼ�����ʹ������
        SemaphoreDome semaphoreDome = new SemaphoreDome();

        //��д�� ReadWriteLock  ����ʱ����Զ���߳�ȡ����д��ʱ�����һ���߳�ȥд
        ReadWriteLockDome dome = new ReadWriteLockDome();

        //��������  �����Ӷ���    �������������д��ͻ�����  �������Ϊ�յĻ���ȡ�ͻ�����
        BlockingQueueDome dome2 = new BlockingQueueDome();

        //�̳߳� �ػ�����
        // ��������У����ʣ�ռ��ϵͳ����Դ�� �Ż���Դ��ʹ�ã�=>�ػ����� �̳߳ء����ӳء��ڴ�ء������///..... ���������١�ʮ���˷���Դ
        // �ػ�����������׼����һЩ��Դ������Ҫ�ã������������ã�����֮�󻹸��ҡ�
        // ������Դ������,�����Ӧ���ٶ�,�������


































    }
    private int i;
    class Thread1 extends Thread{
        @Override
        public void run() {
            for(;i < 10;i++) {
                //��ͨ���̳�Thread��ķ�ʽʵ�ֶ��߳�ʱ������ֱ��ʹ��this��ȡ��ǰִ�е��߳�
                System.out.println(this.getName() + " "  + i);
            }
        }
    }

}
