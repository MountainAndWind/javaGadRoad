package test.json;

/*各种转换方式
* https://www.cnblogs.com/ibigboy/p/11124524.html#_label5
* */
import com.alibaba.fastjson.JSONObject;

public class JsonTest2 {

    public static void main(String[] args) {

        /*String stuString = "{\"age\":2,\"name\":\"公众号编程大道\",\"sex\":\"m\"}";*/
        String stuString = "{name:'zhangsan',sex:'1',age:'123'}";

                 //JSON字符串转换成Java对象
        Student student1 = JSONObject.parseObject(stuString, Student.class);
        System.out.println("JSON字符串转换成Java对象\n" + student1.toString());//Student{name='公众号编程大道', sex='m', age=2}
    }
}
