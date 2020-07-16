package IO_18;

import java.io.*;

/**
 * @description 标准io重定向
 * @Author slfang
 * @Time 2018/12/10 21:03
 * @Version 1.0
 **/
public class IORedirecting {

    public static void main(String []agrs)throws Exception{
        PrintStream consle = System.out;
        PrintStream out = new PrintStream(
                    new FileOutputStream("C:\\Users\\admin\\Desktop\\java学习测试12312\\11.txt"));
        BufferedInputStream in = new BufferedInputStream(
                new FileInputStream("C:\\Users\\admin\\Desktop\\java学习测试\\read.txt"));
        System.setIn(in);
        System.setErr(out);
        System.setOut(out);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s = reader.readLine())!=null){
            System.out.println(s);
        }
        System.setOut(consle);
    }

}
