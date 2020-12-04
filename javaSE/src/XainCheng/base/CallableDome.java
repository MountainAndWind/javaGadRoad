package XainCheng.base;

import org.apache.batik.ext.awt.image.rendered.CachableRed;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @description:
 * @author: slfang
 * @time: 2020/11/30 15:17
 */
public class CallableDome {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //FutureTask 是间接实现Runable 所以可以将FutureTask看做 是Callabled到runable的一个适配类
        FutureTask<Integer> futureTask = new FutureTask<Integer>(
                ()->{
                    System.out.println(Thread.currentThread().getName()+"实现Callable接口");
                    return 123;
                }
        );
        new Thread(futureTask,"test1").start();
        new Thread(futureTask,"test2").start();//结果会被缓存，效率高   futureTask中的 输出只会被打印一次
        new Thread(new FutureTask<Integer>(new CallImp()),"test3").start();
        Integer integer = futureTask.get();//这个方法会阻塞放到最后
        System.out.println(integer);

    }
}

class CallImp implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"Call");
        return 33;
    }
}
