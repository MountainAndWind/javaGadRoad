package XainCheng.base;


/**
 * @description ���Ա����̱߳���
 * @Author slfang
 * @Time 2019/3/30 19:48
 * @Version 1.0
 **/
public class ThreadLocalDome {

    //�ֲ߳̾�������Ч�ʸߣ���    �����������̹߳���
    static final ThreadLocal LOCAL = new ThreadLocal();

    public static void main(String[] args) {


        Thread thread = new Thread("SET"){
            @Override
            public void run() {
               while (true){
                   LOCAL.set(new Student("SETNAME"));
                   System.out.println("set�̵߳õ�"+LOCAL.get());
               }
            }
        };
        thread.start();

        Thread thread2 = new Thread("GET"){
            @Override
            public void run() {
               while (true){
                   System.out.println("GET�̵߳õ�"+LOCAL.get());
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
