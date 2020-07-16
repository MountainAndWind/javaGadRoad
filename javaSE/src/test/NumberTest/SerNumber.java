package test.NumberTest;

import util.DateUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: slfang
 * @time: 2020/3/26 14:21
 */
public class SerNumber {

    private static AtomicInteger atomicNum = new AtomicInteger(1);

    public String getNewAutoNum() {
        int newNum = atomicNum.getAndIncrement();
        String newStrNum = String.format("%010d", newNum);
        return newStrNum;
    }


    public static void main(String[] args) {
        SerNumber number = new SerNumber();
        String newAutoNum = number.getNewAutoNum();
        System.out.println(newAutoNum);
        System.out.println(newAutoNum);
        System.out.println(newAutoNum);
    }

}
