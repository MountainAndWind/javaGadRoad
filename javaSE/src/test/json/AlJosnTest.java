package test.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: slfang
 * @time: 2020/4/26 15:45
 */
public class AlJosnTest {

    public static void main(String[] args) {

        List<HashMap<String,Object>> arr = new ArrayList<HashMap<String,Object>>();
        for (int i = 0; i <10 ; i++) {
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("bm","AA"+i);
            hashMap.put("ftbl","BB"+i);
            arr.add(hashMap);
        }
        String msg= com.alibaba.fastjson.JSONObject.toJSONString(arr);
        System.out.println("msg::"+msg);
    }
}
