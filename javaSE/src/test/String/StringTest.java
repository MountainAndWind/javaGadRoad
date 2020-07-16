package test.String;

import org.apache.commons.lang3.text.WordUtils;

import javax.sound.midi.Soundbank;

/**
 * @description:
 * @author: slfang
 * @time: 2020/5/15 16:31
 */
public class StringTest {

    public static void main(String[] args) {
          //test1();
          //test2();
        //test3();
        double a1 = 12.2;
        double b1 =12.3;
        System.out.println(a1>b1);
        String a="NULL";
        System.out.println(a=="NULL");
    }

    private static void test3() {
        System.out.println(Double.valueOf(null));
    }

    private static void test2() {
        String str="";
        System.out.println("7".equals(str));
    }

    private static void test1() {
        String spName="³ö²î";
        if("³ö²î".equals(spName)){
            System.out.println("cao");
        }
    }


}
