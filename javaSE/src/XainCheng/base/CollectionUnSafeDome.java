package XainCheng.base;

import org.apache.axis2.saaj.util.SAAJUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @description:集合类不安全测试dome
 * @author: slfang
 * @time: 2020/11/27 15:00
 */
public class CollectionUnSafeDome {


    public static void main(String[] args) {

        //test1();
        test2();


    }

    /**
     * 解决方案
     */
    private static void test2() {
        List<String> list = new CopyOnWriteArrayList<>();
        // ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        // Set<String> set = new CopyOnWriteArraySet<>();
        // 也可以使用Collections.synchronizedSet(new HashSet<>());等 集合方法


        for (int i = 0; i <40 ; i++) {//ConcurrentModificationException会报错
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"执行");
                list.add(String.valueOf(UUID.randomUUID()).substring(5));
                System.out.println(list);
            },"dasd").start();
        }
    }

    /**
     * list 线程安全测试
     */
    public static void test1(){
        List<String> list = new ArrayList<>();
        ///Set<String> list = new HashSet<>();
        //Map<String,String> map = new HashMap<>();
        for (int i = 0; i <40 ; i++) {//ConcurrentModificationException会报错
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"执行");
                list.add(String.valueOf(UUID.randomUUID()).substring(5));
                System.out.println(list);
            },"dasd").start();
        }
    }
}
