package XainCheng.LockDome;

/**
 * @description  模拟生产者与消费者模式
 *
 *   对ConsumeAndProduct.java进行优化将生产的同步性封装到篮球实体类中
 *
 * @Author slfang
 * @Time 2019/3/27 19:21
 * @Version ConsumeAndProductUpdate version
 **/
public class ConsumeAndProductUpdate {

    public static void main(String[] args) {

        BaseketBallUpdate ball = new BaseketBallUpdate();

        Thread pro = new Thread(new ProThreadUpdate(ball),"pro");
        Thread con = new Thread(new ConsumeThreadUpdate(ball),"consuem");
        con.start();
        pro.start();
    }

}

class ProThreadUpdate implements Runnable{

    private BaseketBallUpdate ball;

    public ProThreadUpdate(BaseketBallUpdate BaseketBallUpdate) {
        this.ball = BaseketBallUpdate;
    }
    private int a = 0;
    @Override
    public void run() {
        //生产
       while (true){
           if(a%2==0){
               ball.set("nike","122");
           }else{
               ball.set("addies","233");
           }
           a++;
       }
    }
}

class ConsumeThreadUpdate implements Runnable{

    private BaseketBallUpdate ball;

    public ConsumeThreadUpdate(BaseketBallUpdate BaseketBallUpdate) {
        this.ball = BaseketBallUpdate;
    }

    @Override
    public void run() {
        //消费
        while (true){
            synchronized (ball){//默认为fasle为空，生产后为true
               ball.get();
        }
    }
}
}
class BaseketBallUpdate{

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

    public synchronized void set(String banner, String price) {
        if(this.flag){//默认为false、false表示为空
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("生产者生产。。。。。。。。。。。。。。");
        this.setBanner(banner);
        this.setPrice(price);
        System.out.println("生产结束。。。。。。。。。。。。。。。");
        this.flag=true;
        this.notify();
    }
    public synchronized void get() {
        if(!this.flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("消费者消费。。。。。。。。。。。。。。");
        System.out.println("消费了-----"+this.getBanner()+"----"+this.getPrice());
        System.out.println("消费结束。。。。。。。。。。。。。。。");
        this.flag=false;
        this.notify();
    }
}
