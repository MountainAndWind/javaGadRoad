package dataStructure.Queue;


/**
 * @description:优先队列  大堆实现构造
 * 场景：N中查找M（最大最小）个数
 * @author: slfang
 * @time: 2020/7/20 21:05
 */
public class PriorityQueue<Key extends Comparable<Key>> {


    /**
     * 实列数组
     */
    private Key[] pq;

    /**
     * 容量数
     */
    private int count;


    public PriorityQueue() {
    }

    public PriorityQueue(int size) {
        this.pq = (Key[])new Comparable[size+1];
        this.count = 1;
    }

    void insert(Key val){
        int index = count;
        swim(index,val);
    }


    /**
     * 上浮
     * @param index
     * @param val
     */
    void swim(int index, Key val){
        while (index>1){

        }

    }




}
