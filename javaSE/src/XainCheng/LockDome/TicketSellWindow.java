package XainCheng.LockDome;

/**
 * @description  售票案列    模拟访问同一资源造成数据安全的问题
 * @Author slfang
 * @Time 2019/3/27 9:09
 * @Version 1.0
 **/
public class TicketSellWindow implements Runnable{

    public  int tickets = 200;

    //定义所对象
   /* private Lock lock = new ReentrantLockDome();*/
    @Override
    public void run() {
        while(true) {
            sell();
        }
    }

    /**
     * 同步方法解决安全问题  默认锁的是this  静态方法锁锁的是类的字节码对象
     */
    synchronized  private void sell() {
        if(tickets>=0){
            System.out.println(Thread.currentThread().getName()+"卖出第"+tickets--);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TicketSellWindow ticketSellWindow = new TicketSellWindow();
        Thread t1 = new Thread(ticketSellWindow,"窗口1");
        Thread t2 = new Thread(ticketSellWindow,"窗口2");
        Thread t3 = new Thread(ticketSellWindow,"窗口3");
        //多个线程操作同一资源会造成数据问题
        t1.start();
        //t1.join();
        t2.start();
        //t2.join();
        t3.start();
    }
}
