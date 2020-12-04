package XainCheng.base;

import javax.sound.midi.Soundbank;

/**
 * @description:守护线程测试样列
 * @author: slfang
 * @time: 2020/11/25 14:15
 */
public class DaemonThread implements Runnable{


    @Override
    public void run() {
        while (true){
            System.out.println("protect");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new DaemonThread(),"DaemonThread1");
        thread.setDaemon(true);
        thread.start();
        for (int i = 0; i < 400; i++) {
            if(i==100){
                Thread.sleep(2000);
            }
            System.out.println(Thread.currentThread().getName()+":::"+i);
        }
        // 用户线程结束， 守护线程也结束，虚拟机不会等待守护线程执行完毕
    }
}
