package version8.atguigu.excr2;

import org.junit.Test;
import version8.atguigu.Employee;

import java.util.Arrays;
import java.util.List;

/**
 * @description: Stream 练习
 * @author: slfang
 * @time: 2020/6/22 11:42
 */
public class StreamStudy {

    /*
            1.	给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
            ，给定【1，2，3，4，5】， 应该返回【1，4，9，16，25】。
         */
    @Test
    public void test1(){
        int[] arr = new int[]{1,2,3,4,5};
        Arrays.stream(arr).map(e -> e*e).forEach(System.out::println);
    }

    /*
	 2.	怎样用 map 和 reduce 方法数一数流中有多少个Employee呢？
	 */
    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66, Employee.Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
    );
    public void test2(){
        //怎样用 map 和 reduce 方法数一数流中有多少个Employee呢？

    }

}
