package com.zlk.jvm.OOMTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:java堆溢出测试类
 * @author: slfang
 * @time: 2020/6/16 22:15
 */
public class HeapOOM {

    static class OOMObj{
    }

    /*参数设置-Xms20m -Xmx20 -XX:+HeapDumpOnOutOfMemoryError*/
    //-Xmx –Xms：指定最大堆和最小堆
    public static void main(String[] args) {
        List<OOMObj> list = new ArrayList<>();
        while (true){
            list.add(new OOMObj());
        }
    }
}
