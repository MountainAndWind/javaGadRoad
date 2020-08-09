package dataStructure.Queue;


/**
 * @description:优先队列  大堆实现构造
 * 场景：N中查找M（最大最小）个数
 *
 * 实际应用场景：1. 合并有序小文件，2. 高性能定时器，3、利用堆求 Top K  4、利用堆求中位数
 * @author: slfang
 * 插入操作只需要不超过logn+1次比较
 * 删除最大元素的操作不查过2logn次比较
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
        //先插入到对应位置
        int index = count++;
        pq[index] = val;
        swim(index);
    }

    /**
     * 删除堆顶
     */
    Key deleteMax(){
        //堆顶置空
        Key maxVal = pq[1];
        exchange(pq,1,count-1);
        pq[count-1] = null;
        count--;
        sink(1);
        return maxVal;
    }

    /**
     * 下沉
     * 如果的堆的有序状态比其两个子节点其中一个小的而被打破，那么就需要下沉来维护堆的平衡
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
     * 上浮
     * @param index
     */
    void swim(int index){
        while (index>1&&pq[index].compareTo(pq[index/2])>0){//浮动到当前节点小于父节点的位置，且直到顶节点为止
            exchange(pq,index,index/2);
            index = index/2;
        }
    }

    /**
     * 数组中元素交换
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
