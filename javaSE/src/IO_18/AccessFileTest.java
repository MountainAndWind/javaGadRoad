package IO_18;

import java.io.*;

/**
 * @description
 * @Author slfang
 * @Time 2018/11/28 21:34
 * @Version 1.0
 **/
public class AccessFileTest {
    public static void main(String[] args)
  {
    try
       {
                insert("C:\\Users\\admin\\Desktop\\新建文本文档 (3).txt",2,"插入的内容");
          }
         catch (IOException e)
          {
            }
    }

    private static void insert(String fileName,long pos,String content) throws IOException
   {
        //创建临时空文件
              File tempFile = File.createTempFile("temp",null);
           //在虚拟机终止时，请求删除此抽象路径名表示的文件或目录
               tempFile.deleteOnExit();
          FileOutputStream fos = new FileOutputStream(tempFile);

            RandomAccessFile raf = new RandomAccessFile(fileName,"rw");
             raf.seek(pos);
            byte[] buffer = new byte[4];
              int num = 0;
             while(-1 != (num = raf.read(buffer)))
                 {
                     fos.write(buffer,0,num);
               }
             raf.write(content.getBytes());
              FileInputStream fis = new FileInputStream(tempFile);
             while(-1 != (num = fis.read(buffer)))
                 {
                        raf.write(buffer,0,num);
                }
         }
}
