package XainCheng;


/**
 * @description 测试本地线程变量
 * @Author slfang
 * @Time 2019/3/30 19:48
 * @Version 1.0
 **/
public class ThreadLocalDome {

    static final ThreadLocal LOCAL = new ThreadLocal();

    public static void main(String[] args) {


        Thread thread = new Thread("SET"){
            @Override
            public void run() {
               while (true){
                   LOCAL.set(new Student("SETNAME"));
                   System.out.println("set线程得到"+LOCAL.get());
               }
            }
        };
        thread.start();

        Thread thread2 = new Thread("GET"){
            @Override
            public void run() {
               while (true){
                   System.out.println("GET线程得到"+LOCAL.get());
               }
            }
        };
        thread2.start();


    }
}
class Student{
    private String name;

    public Student(String name) {
        this.name = name;
    }
}
