package IO_18;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @description
 * @Author slfang
 * @Time 2018/12/11 21:28
 * @Version 1.0
 **/
public class NIO {
    public static void main(String[] args) throws Exception{
        FileChannel channel = new RandomAccessFile("C:\\Users\\admin\\Desktop\\java学习测试\\read.txt","rw").getChannel();
        FileChannel channel1 = new RandomAccessFile("C:\\Users\\admin\\Desktop\\java学习测试\\out.txt","rw").getChannel();
        ByteBuffer bytebuffer = ByteBuffer.allocate(1024);
        while (channel.read(bytebuffer)!=-1){
           /* System.out.println(channel.size());
            channel.position(channel.size());*/
            channel1.position(channel1.size());
            bytebuffer.flip();
            channel1.write(bytebuffer);
            bytebuffer.clear();
        }

    }
}
