package version8.atguigu.exer;

import org.junit.Test;
import version8.atguigu.Employee;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @description:lambda���ʽ��ϰ
 * @author: slfang
 * @time: 2020/6/22 10:42
 */
public class LambdaStudy {

    List<Employee> emps = Arrays.asList(
            new Employee(101, "����", 18, 9999.99),
            new Employee(102, "����", 59, 6666.66),
            new Employee(103, "����", 28, 3333.33),
            new Employee(104, "����", 8, 7777.77),
            new Employee(105, "����1", 38, 5555.55),
            new Employee(105, "����2", 38, 5555.55),
            new Employee(105, "����3", 38, 5555.55)
    );

    /*����Collections.sort()������
    ͨ����������Ƚ�����Employee(�Ȱ�������Ƚϣ��ٰ��������Ƚ�)��
    ʹ��lambda��Ϊ��������*/
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
      1������ʽ�����ӿڣ��ӿ����������󷽷���public String getValue(String str)
      2��������TestLambda ���б�д����ʹ�ýӿ���Ϊ��������һ���ַ�ת���ɴ�д��������Ϊ�����ķ���ֵ
      3���ٽ�һ���ַ��ĵڶ����͵��ĸ�����λ�ý��н�ȡ
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
