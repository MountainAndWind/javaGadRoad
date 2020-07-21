package dataStructure.Queue;


/**
 * @description:���ȶ���  ���ʵ�ֹ���
 * ������N�в���M�������С������
 * @author: slfang
 * @time: 2020/7/20 21:05
 */
public class PriorityQueue<Key extends Comparable<Key>> {


    /**
     * ʵ������
     */
    private Key[] pq;

    /**
     * ������
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
     * �ϸ�
     * @param index
     * @param val
     */
    void swim(int index, Key val){
        while (index>1){

        }

    }




}
