package XainCheng.base;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: slfang
 * @time: 2020/12/2 14:04
 */
public class ThreadTest {

    public static void main(String[] args) {
        //int i = test1();
        //System.out.println("main  ------   end::"+i);
        HashMap<String,Object> baseInfo = new HashMap<>();
        baseInfo.put("TIME_INTERVAL","1");
        String o =(String)baseInfo.get("TIME_INTERVAL");
        System.out.println(Long.parseLong(o));
    }

    public static int test1(){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println("list::"+list);
        new Thread(()->{
            for (String s : list) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"ฯ๛ทัมหฃบฃบ"+s);
            }
        }).start();
        return 1;
    }
}
