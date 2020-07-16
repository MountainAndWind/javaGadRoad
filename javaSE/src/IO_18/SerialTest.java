package IO_18;

import java.io.*;
import java.util.Random;

/**
 * @description 序列化测试类 SerialTest为worm蠕虫
 * @Author slfang
 * @Time 2018/12/23 19:57
 * @Version 1.0
 **/
public class SerialTest implements Serializable{

    public class Data implements Serializable{
        private int n;
        public Data(int n){
            this.n = n;
        }
        public String toString(){
            return Integer.toString(n);
        }
    }

    private static Random random = new Random(47);
    private Data[] d = {
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10)),
    };
    private SerialTest next ;
    private char c;
    public SerialTest(){
        System.out.println("默认构造器");
    }
    public SerialTest (int i,char s){
        System.out.println("cons  ----"+i);
        c = s;
        if(--i>0)
             next = new SerialTest(i,(char)(s+1));//循环创建

    }
    public String toString(){
        StringBuilder result = new StringBuilder(":");
        result.append(c);
        result.append("(");
        for(Data data:d){
            result.append(data);
        }
        result.append(")");
        if(next==null){
           result.append(next);
        }
        return result.toString();
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerialTest worm = new SerialTest(6,'a');
        System.out.println("worm="+worm);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("C:\\Users\\admin\\Desktop\\Java学习测试\\序列化测试类.txt"));
        out.writeObject("worm storage\n");
        out.writeObject(worm);
        out.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("C:\\Users\\admin\\Desktop\\Java学习测试\\序列化测试类.txt"));
        String s = (String) in.readObject();
        SerialTest worm1 = (SerialTest) in.readObject();
        System.out.println(s+"w2="+worm1);
        in.close();
    }

}
