package dataStructure.STU_HashMap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 * @description
 * @Author slfang
 * @Time 2019/4/2 20:11
 * @Version 1.0
 **/
public class Dome_HashMap {
    public static void main(String[] args) {
      /*  HashSet<String> set  = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            set.add(i+"");
        }
        System.out.println(set.toArray().getClass());
        System.out.println( Object[].class);
        System.out.println(Object.class);*/

       /* HashSet set = new HashSet();
        Object[] objects = set.toArray();
        Arrays.asList(objects);*/

        LinkedList linkedList = new LinkedList();
        linkedList.add("a");
        linkedList.add(1,"as");
       /* List list = new ArrayList();
        for (int i = 0; i < 5; i++) {
            list.add(i+"");
        }
        Object set = list.set(4, "66");
        list.remove(1);
        System.out.println(set);
        System.out.println(list);*/
        HashMap map = new HashMap();
        for (int i = 0; i <10 ; i++) {
            map.put(i,i);
        }

        Set<Integer> set = map.keySet();
        for (Integer  set1 : set) {
            System.out.println(set1+"-------------"+map.get(set1));
        }

    }
}