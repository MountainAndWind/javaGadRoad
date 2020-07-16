package IO_18.ceshi;/**
 * Created by Administrator on 2019/7/8 0008.
 */

import java.io.*;

/**
 * @program: JAVA_BCXX
 * @description: 文件拷贝
 * @author: KI
 * @create: 2019-07-08 18:13
 */
public class FileCopy {

    static void copyFile() throws IOException {

        BufferedInputStream in = new BufferedInputStream(new FileInputStream("C:\\Users\\Administrator\\Desktop\\ceshi\\1.txt"));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("C:\\Users\\Administrator\\Desktop\\ceshi\\2.txt"));
        int len;
        byte []arr = new byte[1024*10];
        while ((len = in.read(arr))!=-1){
            out.write(arr,0,len);
        }
        out.flush();
        in.close();
        out.close();
    }

    public static void main(String[] args) throws IOException {
          copyFile();
    }
}