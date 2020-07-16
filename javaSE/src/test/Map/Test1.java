package test.Map;

import ENUM_19.Test;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: slfang
 * @time: 2020/1/19 14:25
 */
public class Test1 {

    private  AtomicInteger nextServerCyclicCounter = new AtomicInteger(0);

    public static void main(String[] args) {
        /*HashMap<String,Object> baseInfo = new HashMap<>();
        baseInfo.put("VALUES","1");
        String valuestr = (String) baseInfo.get("VALUES");
        System.out.println("valuestr"+valuestr);
        System.out.println("1".equals(baseInfo.get("type")));*/
        Test1 test1 = new Test1();
        int i = test1.incrementAndGetModulo(3);
        System.out.println(i);


    }


    private int incrementAndGetModulo(int modulo) {
        int current;
        int next;
        do {
            current = this.nextServerCyclicCounter.get();
            next = (current + 1) % modulo;
        } while(!this.nextServerCyclicCounter.compareAndSet(current, next));

        return next;
    }
}
