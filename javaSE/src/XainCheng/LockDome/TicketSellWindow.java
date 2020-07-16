package XainCheng.LockDome;

/**
 * @description
 * @Author slfang
 * @Time 2019/3/27 9:09
 * @Version 1.0
 **/
public class TicketSellWindow implements Runnable{

    public  int tickets = 100;

    //定义所对象
   /* private Lock lock = new ReentrantLockDome();*/
    @Override
    public void run() {
        while(true) {
            sell();
        }
    }

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
        TicketSellWindow ticketSellWindow2 = new TicketSellWindow();
        TicketSellWindow ticketSellWindow3 = new TicketSellWindow();
        Thread t1 = new Thread(ticketSellWindow,"窗口1");
        Thread t2 = new Thread(ticketSellWindow2,"窗口2");
        Thread t3 = new Thread(ticketSellWindow3,"窗口3");

        t1.start();
        t1.join();
        t2.start();
        t3.start();
    }

}
