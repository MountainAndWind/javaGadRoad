package test.HttpRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import test.HttpRequest.Bean.Contents;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: slfang
 * @time: 2020/4/10 17:02
 */
public class JsonTest {

    public static void main(String[] args) {

        String testStr="{\n" +
                "                    \"control\":\"Attendance\",\n" +
                "                    \"id\":\"smart-time\",\n" +
                "                    \"title\":[\n" +
                "                        {\n" +
                "                            \"text\":\"出差\",\n" +
                "                            \"lang\":\"zh_CN\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"value\":{\n" +
                "                        \"tips\":[\n" +
                "\n" +
                "                        ],\n" +
                "                        \"members\":[\n" +
                "\n" +
                "                        ],\n" +
                "                        \"departments\":[\n" +
                "\n" +
                "                        ],\n" +
                "                        \"files\":[\n" +
                "\n" +
                "                        ],\n" +
                "                        \"children\":[\n" +
                "\n" +
                "                        ],\n" +
                "                        \"stat_field\":[\n" +
                "\n" +
                "                        ],\n" +
                "                        \"attendance\":{\n" +
                "                            \"date_range\":{\n" +
                "                                \"type\":\"halfday\",\n" +
                "                                \"new_begin\":1578499200,\n" +
                "                                \"new_end\":1578672000,\n" +
                "                                \"new_duration\":216000\n" +
                "                            },\n" +
                "                            \"type\":3\n" +
                "                        },\n" +
                "                        \"sum_field\":[\n" +
                "\n" +
                "                        ],\n" +
                "                        \"related_approval\":[\n" +
                "\n" +
                "                        ]\n" +
                "                    }\n" +
                "                }";

        String a = "{'email':'dsadas'}";


        Contents returnInfo = JSONObject.parseObject(testStr, Contents.class);
        Contents returnInfo2 = JSONObject.parseObject(testStr, Contents.class);
        List<Contents> list = new ArrayList<>();
        list.add(returnInfo);
        list.add(returnInfo2);
        HashMap map = new HashMap();
        String s = JSON.toJSONString(map);
        System.out.println("s::"+s);
        String  jsonObject =  JSON.toJSONString(list);
        System.out.println(jsonObject);
       /* String new_duration = returnInfo.getValue().getAttendance().getDate_range().getNew_duration();
        double day = GetUnixStarp.getDay(new_duration);
        System.out.println(day);
        System.out.println(new Date());*/
        /*ApproveReturnInfo returnInfo = JSONObject.parseObject(test2, ApproveReturnInfo.class);
        System.out.println("JSON字符串转换成Java对象\n" + returnInfo.toString());//Student{name='公众号编程大道', sex='m', age=2}
*/
    }
}
