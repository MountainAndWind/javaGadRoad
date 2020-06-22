package version8.atguigu.exer;

import org.junit.Test;
import version8.atguigu.Employee;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @description:lambda表达式练习
 * @author: slfang
 * @time: 2020/6/22 10:42
 */
public class LambdaStudy {

    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 59, 6666.66),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七1", 38, 5555.55),
            new Employee(105, "田七2", 38, 5555.55),
            new Employee(105, "田七3", 38, 5555.55)
    );

    /*调用Collections.sort()方法，
    通过定制排序比较两个Employee(先按照年龄比较，再按照姓名比较)，
    使用lambda作为参数传递*/
    @Test
    public void test1(){
        Collections.sort(emps,(x,y) ->{
            if(x.getAge()==y.getAge()){
               return x.getName().compareTo(y.getName());
            }else{
               return Integer.compare(x.getAge(),y.getAge());
            }
        });

        for (Employee emp : emps) {
            System.out.println(emp);
        }
    }

    /*
      1、声明式函数接口，接口中声明抽象方法，public String getValue(String str)
      2、声明类TestLambda 类中编写方法使用接口作为参数，讲一个字符转换成大写，并且作为方法的返回值
      3、再讲一个字符的第二个和第四个索引位置进行截取
    * */
    @Test
    public void test2(){
        //valueHandler("dasd",e ->e.);
        AbTest abTest = (e) -> e.toUpperCase();
        String avssasd = abTest.getValue("Avssasd");
        System.out.println(avssasd);
        System.out.println("-----------------");
        Function<String,String> function = (x) -> (String) x.subSequence(2,4);
        String apply = function.apply("2020123131");
        System.out.println(apply);
    }

    @Test
    public void test3(){
        BiFunction<Double,Double,Double> biFunction = (x,y) -> x+y;
        Double apply = biFunction.apply(12.3, 43.2);
        System.out.println(apply);
        System.out.println("------------");
        BiFunction<Double,Double,Double> biFunction2 = (x,y) -> x*y;
        Double apply2 = biFunction2.apply(12.3, 43.2);
        System.out.println(apply2);
    }

   /* public  String valueHandler(String str,AbTest lambda){
       return lambda.getValue(str);
    }*/



}
