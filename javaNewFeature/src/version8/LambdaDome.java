package version8;

import org.junit.Test;
import version8.atguigu.Employee;

import java.util.Arrays;
import java.util.List;

/**
 * @description:Lambda表达式学习dome
 * Lambda表达式是一个匿名函数我们可以理解为是一段可以传递的代码，提示java语言表达能力提升
 * @author: slfang
 * @time: 2020/6/17 17:02
 */
public class LambdaDome {


    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 59, 6666.66),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
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
