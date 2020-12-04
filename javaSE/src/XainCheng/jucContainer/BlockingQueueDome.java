package XainCheng.jucContainer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: slfang
 * @time: 2020/12/1 21:29
 */
public class BlockingQueueDome {

    public static void main(String[] args) throws InterruptedException {
        //BlockingQueue ��һ���ӿ�����ʵ������
        // AbstractQueue ,ArrayBlockingQueue �� DelayQueue �� LinkedBlockingDeque ��
        // LinkedBlockingQueue �� LinkedTransferQueue �� PriorityBlockingQueue �� SynchronousQueue

        //������з�������
        // ���               �Ƴ�           ���ײ���                 ����Ϊ�ջ�����ʱ��״̬
        //    add             remove         element                  �׳��쳣
        //    offer           poll           peek                     �з���ֵ���׳��쳣
        //    put             take                                    ����
        //    offer(����)     poll(����)                              ��ʱ�ȴ�
        test4();

        // SynchronousQueue ͬ�����в��� û��������ȥһ��Ԫ�أ�����ȴ�ȡ����֮�󣬲������������һ��Ԫ�أ�
        // �����ArrayBlockingQueue ����������������
        testSynchronousQueue();

    }

    private static void testSynchronousQueue() {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName() + " put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName() + " put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "=>" + blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "=>" + blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "=>" + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();

    }

    public static void test4() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a1", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a2", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a3", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a4", 2, TimeUnit.SECONDS));
        //blockingQueue.put("a");
        System.out.println("=-===========");
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));  //�ڲ�ʵ��final ReentrantLock lock = this.lock;��֤��ȫ
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));  //�ڲ�ʵ��final ReentrantLock lock = this.lock;��֤��ȫ
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));  //�ڲ�ʵ��final ReentrantLock lock = this.lock;��֤��ȫ
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));  //�ڲ�ʵ��final ReentrantLock lock = this.lock;��֤��ȫ
    }

    public static void test3() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
        //blockingQueue.put("a");
        System.out.println("=-===========");
        System.out.println(blockingQueue.take());  //�ڲ�ʵ��final ReentrantLock lock = this.lock;��֤��ȫ
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
    }

    public static void test1() {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        // IllegalStateException: Queue full �׳��쳣��
        //System.out.println(blockingQueue.add("d"));
        System.out.println("=-===========");
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());
    }

    public static void test2() {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        // IllegalStateException: Queue full �׳��쳣��
        System.out.println(blockingQueue.offer("d"));
        System.out.println("=-===========");
        System.out.println(blockingQueue.poll());  //�ڲ�ʵ��final ReentrantLock lock = this.lock;��֤��ȫ
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }


}
