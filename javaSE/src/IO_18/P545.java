package IO_18;

import org.junit.Test;

import java.io.*;

/**
 * @description
 * @Author slfang
 * @Time 2018/11/28 20:46
 * @Version 1.0
 **/
public class P545 {
    static String fileName = "C:\\Users\\admin\\Desktop\\新建文本文档 (3).txt";
    static void display()throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile(fileName,"r");
        for(int i=1;i<=7;i++){
            System.out.println("value"+i+"-----------------------"+accessFile.readDouble());
        }
        /*System.out.println("utf8"+accessFile.readUTF());*/
        accessFile.close();
    }
    static void display2()throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile(fileName,"r");
        for(int i=1;i<=8;i++){
            System.out.println("value"+i+"-----------------------"+accessFile.readDouble());
        }
        /*System.out.println("utf8"+accessFile.readUTF());*/
        accessFile.close();
    }
    /**
    * @Description //TODO //如果这样写会覆盖上原本的值
    * @Author slfang
    * @Date  2018年11月28日 21:15:05
    * @Param
    * @return 
    **/
    
    @Test
    public void  m1()throws IOException{
        RandomAccessFile accessFile = new RandomAccessFile(fileName,"rw");
        for(int i=1;i<=7;i++){
            accessFile.writeDouble(i*12.1);
        }
        accessFile.writeUTF("------------------------------------------End");
        accessFile.close();
        display();
        accessFile = new RandomAccessFile(fileName, "rw");
        accessFile.seek(5*8);
        accessFile.writeUTF("我");//如果这样写会覆盖上原本的值
        accessFile.close();
         display();
    }

    /**
    * @Description //TODO  RandomAccessFile随机读写实验
    * @Author slfang
    * @Date  2018年11月28日 21:15:29
    * @Param
    * @return 
    **/
    @Test
    public void m2()throws IOException{
        RandomAccessFile accessFile;
        display();
        accessFile = new RandomAccessFile(fileName, "rw");
        //创建一个临时区
        //创建临时空文件
        File tempFile = File.createTempFile("temp",null);
        //在虚拟机终止时，请求删除此抽象路径名表示的文件或目录
        tempFile.deleteOnExit();
        FileOutputStream out = new FileOutputStream(tempFile);
        accessFile.seek(5*8);
        byte[] a = new byte[4];
        int num = 0;
        while ((num=accessFile.read(a))!=-1){
            out.write(a,0,num);
        }
        accessFile.seek(5*8);
        accessFile.writeDouble(231.231);//如果这样写会覆盖上原本的值
        FileInputStream fis = new FileInputStream(tempFile);
        while(-1 != (num = fis.read(a)))
        {
            accessFile.write(a,0,num);
        }
        System.out.println("1111111111111111111111111111111111111");
        accessFile.close();
        display2();
    }

    
}
