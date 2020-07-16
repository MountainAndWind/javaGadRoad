package test2;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @ClassName
 * @Description TODO
 * @Author fangshilei
 * @Date 2019/11/22 11:51
 * @Version 1.0
 **/
public class JsonTest2 {

    public static void main(String[] args) {

        String json1 = "[{ ' id ' :0, ' text ' : ' field9329_0,field9330_0,field15831_0,field14878_0 ' }," +
                "{ ' id ' :1, ' text ' : ' field9329_0,field9330_0,field15831_0,field14878_0 ' }]";
        JSONArray json = JSONArray.fromObject(json1);
        if(json.size()>0){
            for(int i=0;i<json.size();i++){
                JSONObject job = json.getJSONObject(i);   // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                System.out.println(job.get("id")) ;   // 得到 每个对象中的属性值
                System.out.println(job.get("text")) ;
            }
        }

    }
}
