package XainCheng.LockDome;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description  公平锁案列
 * @Author slfang
 * @Time 2019/3/29 16:43
 * @Version 1.0
 **/
public class FairSuoDome implements Runnable{
        //创建公平锁
        private static ReentrantLock lock=new ReentrantLock(true);
        public void run() {
            while(true){
                lock.lock();
                try{
                    System.out.println(Thread.currentThread().getName()+"获得锁");
                }finally{
                    lock.unlock();
                }
            }
        }
        public static void main(String[] args) {
            FairSuoDome lft=new FairSuoDome();
            Thread th1=new Thread(lft);
            Thread th2=new Thread(lft);
            th1.start();
            th2.start();
        }
}
