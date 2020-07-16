package Class;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName
 * @Description TODO
 * @Author fangshilei
 * @Date 2019/10/22 10:14
 * @Version 1.0
 **/
public class MethodTest {


    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        
        Class t = Class.forName("Class.MethodTest");
        Method v2 = t.getMethod("say");
        v2.setAccessible(true);
        Object in2 = v2.invoke(t.newInstance());
        System.out.println(in2);
    }

    public String aa(String s){
        return s;
    }

    public String say(){
        return "hello";
    }

    private boolean invoiceDateSubmitted(Map<String, Object> baseInfo){
        return false;
    }
}
