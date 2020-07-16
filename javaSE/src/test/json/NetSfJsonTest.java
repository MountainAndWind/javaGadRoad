package test.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @description:
 * @author: slfang
 * @time: 2020/4/13 17:06
 */
public class NetSfJsonTest {
    public static void main(String[] args) {
        //创建JSONObject对象
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","wln");
        jsonObject.put("password","123");
        System.out.println("1：" + jsonObject);

        //增加属性
        jsonObject.element("sex","男");
        System.out.println("2：" + jsonObject);

        //判断输出对象的类型
        boolean isArray = jsonObject.isArray();
        boolean isEmpty = jsonObject.isEmpty();
        boolean isNullObject = jsonObject.isNullObject();
        System.out.println("3：" + "是否是数组：" + isArray +" 是否是空：" + isEmpty + " 是否是空对象：" + isNullObject);

        //创建JSONArray
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(0,"aa");
        jsonArray.add("BB");
        jsonArray.add(1,"AB");
        jsonArray.add("cc");

        //将JSONArray 添加到JSONObject
        jsonObject.element("student",jsonArray);
        System.out.println("4：" + jsonObject);
    }
}
