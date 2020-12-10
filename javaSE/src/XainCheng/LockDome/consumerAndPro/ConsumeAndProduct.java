package XainCheng.LockDome.consumerAndPro;

/**
 * @description  模拟生产者与消费者模式
 *
 *   场景生产者生产篮球对象消费者进行消费这个抽象可以理解为购买
 *   生产者一次只生产一个，且没消费时，不进行生产，
 *   消费者对篮球进行消费一个只消费一次，当没有资源时不进行消费。
 *
 * @Author slfang
 * @Time 2019/3/27 19:21
 * @Version 1.0
 **/
public class ConsumeAndProduct {

    public static void main(String[] args) {

        BaseketBall ball = new BaseketBall();

        Thread pro = new Thread(new ProThread(ball),"pro");
        Thread con = new Thread(new ConsumeThread(ball),"consuem");
        con.start();
        pro.start();
    }

}
class ProThread implements Runnable{

    private BaseketBall ball;

    public ProThread(BaseketBall baseketBall) {
        this.ball = baseketBall;
    }
    private int a = 0;
    @Override
    public void run() {
        //生产
       while (true){
           synchronized (ball){
               if(ball.flag){//默认为false、false表示为空
                   try {
                       ball.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               if(a%2==0){
                   System.out.println("生产者生产。。。。。。。。。。。。。。");
                   ball.setBanner("NIKE");
                   ball.setPrice("100");
                   System.out.println("生产结束。。。。。。。。。。。。。。。");

               }else{
                   System.out.println("生产者生产。。。。。。。。。。。。。。");
                   ball.setBanner("addies");
                   ball.setPrice("200");
                   System.out.println("生产结束。。。。。。。。。。。。。。。");
               }
               a++;
               ball.flag=true;
               ball.notify();
           }
       }
    }
}
class ConsumeThread implements Runnable{

    private BaseketBall ball;

    public ConsumeThread(BaseketBall baseketBall) {
        this.ball = baseketBall;
    }

    @Override
    public void run() {
        //消费
        while (true){
            synchronized (ball){//默认为fasle为空，生产后为true
               if(!ball.flag){
                   try {
                       ball.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
                System.out.println("消费者消费。。。。。。。。。。。。。。");
                System.out.println("消费了-----"+ball.getBanner()+"----"+ball.getPrice());
                System.out.println("消费结束。。。。。。。。。。。。。。。");
                ball.flag=false;
                ball.notify();
            }
        }
    }
}
class BaseketBall{

    private String banner;
    private String price;
    boolean flag;

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
