package dataStructure.Queue;


/**
 * @description:���ȶ���  ���ʵ�ֹ���
 * ������N�в���M�������С������
 *
 * ʵ��Ӧ�ó�����1. �ϲ�����С�ļ���2. �����ܶ�ʱ����3�����ö��� Top K  4�����ö�����λ��
 * @author: slfang
 * �������ֻ��Ҫ������logn+1�αȽ�
 * ɾ�����Ԫ�صĲ��������2logn�αȽ�
 * @time: 2020/7/20 21:05
 */
public class PriorityQueue<Key extends Comparable<Key>> {

    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(10);
        for (int i = 0; i <5 ; i++) {
            priorityQueue.insert(i);
        }

        while (!priorityQueue.isEmpty()){
            System.out.println(priorityQueue.deleteMax());
        }
    }



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
        //�Ȳ��뵽��Ӧλ��
        int index = count++;
        pq[index] = val;
        swim(index);
    }

    /**
     * ɾ���Ѷ�
     */
    Key deleteMax(){
        //�Ѷ��ÿ�
        Key maxVal = pq[1];
        exchange(pq,1,count-1);
        pq[count-1] = null;
        count--;
        sink(1);
        return maxVal;
    }

    /**
     * �³�
     * ����Ķѵ�����״̬���������ӽڵ�����һ��С�Ķ������ƣ���ô����Ҫ�³���ά���ѵ�ƽ��
     * @param i
     */
    private void sink(int i) {
        while(2*i<count){
            int a = 2*i;
            if(a<count-1&&pq[a].compareTo(pq[a+1])<0){
               a++;
            }
            if(pq[a].compareTo(pq[i])<0){
              break;
            }else{
              exchange(pq,i,a);
              i=a;
            }
        }
    }


    /**
     * �ϸ�
     * @param index
     */
    void swim(int index){
        while (index>1&&pq[index].compareTo(pq[index/2])>0){//��������ǰ�ڵ�С�ڸ��ڵ��λ�ã���ֱ�����ڵ�Ϊֹ
            exchange(pq,index,index/2);
            index = index/2;
        }
    }

    /**
     * ������Ԫ�ؽ���
     */
    void exchange(Key[] arr,int i,int j){
        Key temp=arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    boolean isEmpty(){
        return count==1;
    }
}
