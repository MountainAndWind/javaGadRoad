package version8.atguigu.excr2;

import org.junit.Before;
import org.junit.Test;
import version8.atguigu.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public void test1() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        Arrays.stream(arr).map(e -> e * e).forEach(System.out::println);
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

    @Test
    public void test2() {
        //怎样用 map 和 reduce 方法数一数流中有多少个Employee呢？
        Optional<Integer> reduce = emps.stream().map(e -> 1).reduce(Integer::sum);
        Integer integer = reduce.get();
        System.out.println(integer);
    }

    List<Transaction> transactions = null;

    @Before
    public void before(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    //1. 找出2011年发生的所有交易， 并按交易额排序（从低到高）
    @Test
    public void test3(){
        transactions.stream().
                filter(e -> e.getYear()==2011).
                sorted((x,y) -> -Integer.compare(x.getValue(),y.getValue())).forEach(System.out::println);
    }

    //2. 交易员都在哪些不同的城市工作过？
    @Test
    public void test4(){
        transactions.stream().map(e -> e.getTrader().getCity()).collect(Collectors.toSet()).forEach(System.out::println);
    }

    //3. 查找所有来自剑桥的交易员，并按姓名排序
    @Test
    public void test5(){
        transactions.stream()
                .filter(e -> e.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .sorted((x,y) -> x.getName().compareTo(y.getName()) )
                .distinct()
                .forEach(System.out::println);

    }

    //4. 返回所有交易员的姓名字符串，按字母顺序排序
    @Test
    public void test6(){
        transactions.stream()
                .map(e -> e.getTrader().getName())
                .distinct()
                .sorted((x,y) -> x.compareTo(y)).forEach(System.out::println);

        System.out.println("----------------------");
        String reduce = transactions.stream()
                .map(e -> e.getTrader().getName())
                .sorted((s1, s2) -> s1.compareToIgnoreCase(s2))
                .reduce("", String::concat);
        System.out.println(reduce);


        System.out.println("----------------------");
        transactions.stream()
                .map(e -> e.getTrader().getName())
                .flatMap(StreamStudy::filterCharacter)  //接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
                .sorted((s1, s2) -> s1.compareToIgnoreCase(s2))
                .forEach(System.out::print);

    }

    public static Stream<String> filterCharacter(String str){
        List<String> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch.toString());
        }
        return list.stream();
    }


    //5. 有没有交易员是在米兰工作的？
    @Test
    public void test7(){
        boolean b = transactions.stream().map(Transaction::getTrader).anyMatch(e -> e.getCity().equals("Milan"));
        System.out.println(b);
    }

    //6. 打印生活在剑桥的交易员的所有交易额
    @Test
    public void test8(){
        Optional<Integer> sum = transactions.stream()
                .filter((e) -> e.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);

        System.out.println(sum.get());
    }

    //7. 所有交易中，最高的交易额是多少
    @Test
    public void test9(){
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo);
        System.out.println(max.get());

    }
}

