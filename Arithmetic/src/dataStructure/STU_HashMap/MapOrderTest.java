package dataStructure.STU_HashMap;

import java.util.HashMap;
import java.util.Set;

/**
 * @description: hashMap有序性测试
 * @author: slfang
 * @time: 2020/2/10 18:38
 */
public class MapOrderTest {
    public static void main(String[] args) {
        HashMap<String,String> map =  new HashMap();
        map.put("1","A");
        map.put("4","D");
        map.put("5","E");
        map.put("6","F");
        map.put("2","B");
        map.put("3","C");
        map.put("7","G");

        System.out.println(map);

        Set set = map.keySet();
        for (Object o : set) {
            System.out.println((String)o+"---"+map.get((String)o));
        }

        String asda = map.get("asda");
        System.out.println(asda);
    }
}
