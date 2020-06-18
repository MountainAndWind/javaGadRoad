package version8;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:  hashMap 1.8的特性
 * @author: slfang
 * @time: 2020/6/17 16:14
 */
public class HashMap8 {


    //1.8中加入了红黑树  当前链表中大于8 且map中总容量大于64  转换成红黑树；转换成红黑树之后除了添加其他操作效率都显著提升。
    //应为1.7是头插法插入，1.8之后就是尾插法
    //ConCurrentHashMap  采用cas（底层操作系统支持的效率高）   putVal  /// no lock when adding to empty bin
    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

    }
}
