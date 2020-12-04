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
        //BlockingQueue 是一个接口其下实现类有
        // AbstractQueue ,ArrayBlockingQueue ， DelayQueue ， LinkedBlockingDeque ，
        // LinkedBlockingQueue ， LinkedTransferQueue ， PriorityBlockingQueue ， SynchronousQueue

        //组设队列方法测试
        // 添加               移除           对首操作                 数据为空或者慢时的状态
        //    add             remove         element                  抛出异常
        //    offer           poll           peek                     有返回值不抛出异常
        //    put             take                                    阻塞
        //    offer(参数)     poll(参数)                              延时等待
        test4();

        // SynchronousQueue 同步队列测试 没有容量进去一个元素，必须等待取出来之后，才能再往里面放一个元素！
        // 相对于ArrayBlockingQueue 具有轻量级的优势
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
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));  //内部实现final ReentrantLock lock = this.lock;保证安全
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));  //内部实现final ReentrantLock lock = this.lock;保证安全
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));  //内部实现final ReentrantLock lock = this.lock;保证安全
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));  //内部实现final ReentrantLock lock = this.lock;保证安全
    }

    public static void test3() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
        //blockingQueue.put("a");
        System.out.println("=-===========");
        System.out.println(blockingQueue.take());  //内部实现final ReentrantLock lock = this.lock;保证安全
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
    }

    public static void test1() {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        // IllegalStateException: Queue full 抛出异常！
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
        // IllegalStateException: Queue full 抛出异常！
        System.out.println(blockingQueue.offer("d"));
        System.out.println("=-===========");
        System.out.println(blockingQueue.poll());  //内部实现final ReentrantLock lock = this.lock;保证安全
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }


}
