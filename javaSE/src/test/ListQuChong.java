package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName
 * @Description TODO
 * @Author fangshilei
 * @Date 2019/11/19 18:00
 * @Version 1.0
 **/
public class ListQuChong {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("d");
        list.add("e");

        Map<String,Integer> map = new HashMap<>();
        for(String str:list){
            Integer i = 1; //定义一个计数器，用来记录重复数据的个数
            if(map.get(str) != null){
                i=map.get(str)+1;
            }
            map.put(str,i);
        }
        System.out.println("重复数据的个数："+map.toString());


        System.out.print("重复的数据为：");
        for(String s:map.keySet()){
            if(map.get(s) > 1){
                System.out.print(s+" ");
            }
        }
    }
}
