package test.json;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class JosnTest {


    public static void main(String[] args) {
        /*test1();*/
        String str = "{\"code\":0,\"message\":\"成功\",\"data\":{\"verifyId\":\"acb21422-fc31-4c18-b57b-f0284a01b64a\"}}";
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(str);
        Map map = new HashMap();
        for(Iterator iter = jsonObject.keys(); iter.hasNext();){
            String key = (String)iter.next();
            map.put(key, jsonObject.get(key));
        }
        System.out.println(map.get("code"));
        //test3();
    }
static void  test1(){
        HashMap map = new HashMap();
        HashMap rdMap1 = new HashMap();
        HashMap rdMap2 = new HashMap();
        HashMap inMap = new HashMap();
        HashMap pubMap = new HashMap();
        Object[] arr = new Object[2];

        rdMap1.put("RdSeq","GI");
        rdMap1.put("PayDate","9188");
        rdMap1.put("ApplyEntity","150011");
        rdMap2.put("RdSeq","GI");
        rdMap2.put("PayDate","9188");
        rdMap2.put("ApplyEntity","150011");

        arr[0]= rdMap1;
        arr[1]= rdMap2;

        inMap.put("ReqSeqID","GIS2712");
        inMap.put("TotalCount","2");
        inMap.put("RD",arr);

        pubMap.put("TransSource","GI");
        pubMap.put("TransCode","9188");
        pubMap.put("TransTime","150011");

        map.put("PUB", pubMap);
        map.put("in", inMap);

        String jsonText = JSONObject.toJSONString(map);
        System.err.println(jsonText);
        System.out.println("--------"+rdMap1);
    }



    static void  test2(){
        HashMap map = new HashMap();
        HashMap inMap = new HashMap();
        inMap.put("clientId","00000001");
        inMap.put("clientSecret","3");
        inMap.put("corNum","2");
        inMap.put("bilMon","2");
        inMap.put("currPage","1");
        map.put("paraMap",inMap);
        String jsonText = JSONObject.toJSONString(map);
        System.err.println(jsonText);
    }



    static void test3(){
        String json = "[{ ' id ' :0, ' text ' : ' field9329_0,field9330_0,field15831_0,field14878_0 ' }," +
                "{ ' id ' :1, ' text ' : ' field9329_0,field9330_0,field15831_0,field14878_0 ' }]";
        JSONArray jsonArray = JSONArray.parseArray(json);
        //取出数组第一个元素
        //JSONObject object = jsonArray.getJSONObject(0);
        //取出第一个元素的信息，并且转化为JSONObject

        for(int i=0;i<jsonArray.size();i++){
            JSONObject job = jsonArray.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
 System.out.println(job.get("id"));
            System.out.println(job.get("text")); // 得到 每个对象中的属性值

            Set<String> strings = job.keySet();
            for (String string : strings) {
                System.out.println(job.get("id"));
                System.out.println(job.get("text"));
            }

        }


    }
}
