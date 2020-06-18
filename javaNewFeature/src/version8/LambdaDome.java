package version8;

import org.junit.Test;
import version8.atguigu.Employee;

import java.util.Arrays;
import java.util.List;

/**
 * @description:Lambda���ʽѧϰdome
 * Lambda���ʽ��һ�������������ǿ������Ϊ��һ�ο��Դ��ݵĴ��룬��ʾjava���Ա����������
 * @author: slfang
 * @time: 2020/6/17 17:02
 */
public class LambdaDome {


    List<Employee> emps = Arrays.asList(
            new Employee(101, "����", 18, 9999.99),
            new Employee(102, "����", 59, 6666.66),
            new Employee(103, "����", 28, 3333.33),
            new Employee(104, "����", 8, 7777.77),
            new Employee(105, "����", 38, 5555.55)
    );

    @Test
    public void Test1() {
        emps.stream().forEach(System.out::println);
        System.out.println("--------");
        emps.stream().filter((e) -> e.getAge() > 30).forEach(System.out::println);
        System.out.println("--------");
        emps.stream().filter((e) -> e.getAge() > 30).map(Employee::getName).limit(1).forEach(System.out::println);
    }
}
