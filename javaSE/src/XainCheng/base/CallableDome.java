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

        //FutureTask �Ǽ��ʵ��Runable ���Կ��Խ�FutureTask���� ��Callabled��runable��һ��������
        FutureTask<Integer> futureTask = new FutureTask<Integer>(
                ()->{
                    System.out.println(Thread.currentThread().getName()+"ʵ��Callable�ӿ�");
                    return 123;
                }
        );
        new Thread(futureTask,"test1").start();
        new Thread(futureTask,"test2").start();//����ᱻ���棬Ч�ʸ�   futureTask�е� ���ֻ�ᱻ��ӡһ��
        new Thread(new FutureTask<Integer>(new CallImp()),"test3").start();
        Integer integer = futureTask.get();//��������������ŵ����
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
