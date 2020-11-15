package AbstractMethod;

import java.util.PriorityQueue;

/**
 * @description:
 * @author: slfang
 * @time: 2020/2/10 18:01
 */
public class MainTest {

    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((x,y) -> y - x);
        for (int i = 0; i <5 ; i++) {
            priorityQueue.add(i);
        }

        for (int i = 10; i >5 ; i--) {
            priorityQueue.add(i);
        }
        while (!priorityQueue.isEmpty()){
            Object poll = priorityQueue.poll();
            System.out.println(poll);
        }


        /*AMethod a = new AMethod();
        System.out.println(a.getTotalTime());

        BMethod b = new BMethod();
        System.out.println(b.getTotalTime());*/
    }
}
