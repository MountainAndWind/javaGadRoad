package IO_18;


import org.junit.Test;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @description  压缩io测试类
 * @Author slfang
 * @Time 2018/12/23 13:05
 * @Version 1.0
 **/
public class ZipIoTest {
    /**
    * @Description 单文件测试
    * @Author slfang
    * @Date  2018年12月23日 13:42:05
    * @Param 
    * @return
    **/
    @Test
    public void test1(){
       BufferedReader in ;
       {
           try {
               BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\admin\\Desktop\\Java学习测试\\read.txt"));
               BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("C:\\Users\\admin\\Desktop\\Java学习测试\\read1.gz")));
               int ww;
               while ((ww = reader.read())!=-1)
                   out.write(ww);
               reader.close();
               out.close();
               in = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream("C:\\Users\\admin\\Desktop\\Java学习测试\\read1.gz"))));
               String s;
               while ((s = in.readLine())!=null){
                   System.out.println(s);
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
   }
   
   /**
   * @Description 多文件测试使用的事zip进行多文件保存使用checkSum类和ZipOutputStream类
   * @Author slfang
   * @Date  2018年12月23日 19:55:13
   * @Param
   * @return 
   **/
   
   
}
