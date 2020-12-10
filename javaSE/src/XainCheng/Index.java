package XainCheng;

import XainCheng.LockDome.FairSuoDome;
import XainCheng.LockDome.TicketSellWindow;
import XainCheng.ThreadPool.ForkJoinPoolDome;
import XainCheng.ThreadPool.PoolDome;
import XainCheng.base.CollectionUnSafeDome;
import XainCheng.base.DaemonThread;
import XainCheng.LockDome.ReadWriteLockDome;
import XainCheng.jucContainer.BlockingQueueDome;
import XainCheng.util.CountDownLatchDome;
import XainCheng.util.CyclicBarrierDemo;
import XainCheng.util.SemaphoreDome;
import sun.plugin2.jvm.RemoteJVMLauncher;

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
        // 1��NEW���½���2��RUNNABLE (Java�߳��н�������ready���������У�running������״̬��ͳ�ĳ�Ϊ�����С��� )��
        // 3��BLOCKED ����״̬���߳������ڽ���synchronized�ؼ������εķ���������(��ȡ��)ʱ��״̬��
        // 4��WAITING �����״̬���߳���Ҫ�ȴ������߳�����һЩ�ض�������֪ͨ���жϣ� wait join �����waiting 5��TIMED_WAITING����ʱ�ȴ�����״̬��ͬ��WAITING����������ָ����ʱ������з���
        // 6����ֹ(TERMINATED)����ʾ���߳��Ѿ�ִ����ϡ�
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
        //synchronized��ȱ��   �е��Ʊʼ������� 2
        //ͬ�������ı׶˷�������Ҫ�޸ĵĶ�������Ҫ������̫���˷���Դ

        //��ʾ�� lock (�ֶ�������)     synchronized����ʽ���������Զ��ͷ�  ReentrantLockDome.Dome
        //ʹ��lock��JVM�����Ѹ��ٵ�ʱ�������ȣ����ܸ��ã����и��õ���չ��  Ҳ����ʹ�ö�д�����Ч��
        ReentrantLock lock = new ReentrantLock();//�޲��� ��Ϊ�ǹ�ƽ�� ԭ��AQS
        FairSuoDome fairSuoDome = new FairSuoDome();

        //�̵߳�Э��ͨ��
        //������������ģʽ
        //�ȴ�����
        //��ٻ������� �����߳��в���ĳ�������ı仯��ʹ��if ������ while��
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
        PoolDome poolDome = new PoolDome();

        // Stream��ʽ���� ,ForkJoin
        ForkJoinPoolDome forkJoinPoolDome = new ForkJoinPoolDome();

        //JDK8��������CompletableFuture�������첽��̣��첽ͨ����ζ�ŷ�������ʹ������
        //     �����񵥶������������̷߳���������߳��У�����ͨ���ص����������߳��еõ��첽�����ִ��״̬���Ƿ���ɣ����Ƿ��쳣����Ϣ
        //Futrue��Java���棬ͨ��������ʾһ���첽��������ã��������ǽ������ύ���߳�
        //     �����棬Ȼ�����ǻ�õ�һ��Futrue����Future������isDone������ �ж�����
        //     �Ƿ������������get��������һֱ����ֱ���������Ȼ���ȡ�������������˵
        //     ���ַ�ʽ������ͬ���ģ���Ϊ��Ҫ�ͻ��˲��������ȴ����߲�����ѯ����֪�������Ƿ���ɡ�
        //Java 8������CompletableFuture����������������Google Guava��ListenableFuture��SettableFuture�����������ṩ������ǿ��Ĺ��ܣ�
        //     ��Javaӵ���������ķ��������ģ�ͣ�Future��Promise �� Callback(��Java8֮ǰ��ֻ����Callback ��Future)��
        //     ������е���









































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
