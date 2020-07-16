package dataStructure.Array;

/**
 * @description:数组测试类
 * @author: slfang
 * @time: 2020/6/30 13:33
 */
public class Dome1 {


    /*
    * a[k]_address = base_address + k * type_size
    * */
    /*二维数组内存寻址：
    int a[3][4]; 即 3 行 4 列总共有 12 个元素的数组
    对于 m * n 的数组，a [ i ][ j ] (i < m,j < n)的地址为：
    address = base_address + ( i * n + j) * type_size（理解数据从左到边累加计算）*/

    /*
    * 链表与数组的区别
    * 1、数组内存连续     链表内存不连续，正由于这个问题
    * 导致数据可以随机访问   链表不能随机访问 ，数据大小是固定了，
    * 扩容存在效率问题以及如果声明的数组过大，系统可能没有足够的连续内存空间分配给它（涉及JVM方面知识）
    * 链表本身没有大小的限制，天然地支持动态扩容
    * 2、链表相对于数组会有额外的空间来供给给前驱与后继
    * */
    public static void main(String[] args) {

    }
}
