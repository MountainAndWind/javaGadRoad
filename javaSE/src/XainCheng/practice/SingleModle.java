package XainCheng.practice;

/**
 * @description:线程安全的单列模式
 * @author: slfang
 * @time: 2020/12/7 21:43
 */
public class SingleModle {
    private static SingleModle instance;

    private SingleModle(){
    }

    /**
     * 双重锁机制
     * 对上一种模式进行优化，这里判断了两次是否为 null 是因为在并发环境中当线程一执行了第一个判断的时候
     * 是为null，可此刻另外一个线程正好执行完初始化操作，在释放锁以后该线程并不知道已经初始化，如果此刻
     * 进入代码块不进行再次判断会再初始化一次，这就违背了单例模式的初衷了。
     * @return
     */
    public static SingleModle getInstance() {    //对获取实例的方法进行同步
        if(instance==null){
            synchronized (SingleModle.class){
                if(instance==null){
                    instance = new SingleModle();
                }
            }
        }
        return instance;
    }



}
