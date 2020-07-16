package IO_18;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * @description ByteBuffer中的四个索引mark,limit,position,capacity
 * @Author slfang
 * @Time 2018/12/13 20:59
 * @Version 1.0
 **/
public class ByteBuffer_INDEX {
    static void changeNobChar(CharBuffer charBuffer){
        char c = charBuffer.get();
        charBuffer.mark();//将mark设置为position
        char c1 = charBuffer.get();
        charBuffer.reset();
        charBuffer.put(c1).put(c);
    }
    public static void main(String[] agrs){
        char[] chars = "thisiszlk".toCharArray();
        ByteBuffer buffer = ByteBuffer.allocate(chars.length * 2);
        CharBuffer charBuffer = buffer.asCharBuffer();
        charBuffer.put(chars);
        System.out.println(charBuffer.rewind());
        changeNobChar(charBuffer);
        Buffer rewind = charBuffer.rewind();
        System.out.println(charBuffer.rewind());
    }
}
