package com.zlk.jvm.OOMTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:java�����������
 * @author: slfang
 * @time: 2020/6/16 22:15
 */
public class HeapOOM {

    static class OOMObj{
    }

    /*��������-Xms20m -Xmx20 -XX:+HeapDumpOnOutOfMemoryError*/
    //-Xmx �CXms��ָ�����Ѻ���С��
    public static void main(String[] args) {
        List<OOMObj> list = new ArrayList<>();
        while (true){
            list.add(new OOMObj());
        }
    }
}
