package dataStructure.Queue;

import java.util.PriorityQueue;

/**
 * @description:中位数查找
 * @author: slfang
 * @time: 2020/7/26 22:12
 */
public class MedianFinder {


    int count=0;
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((x, y)-> y-x );
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    void addEle(int i){
        count++;
        maxHeap.offer(i);
        minHeap.offer(maxHeap.poll());
        if((count & 1) != 0){
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if ((count & 1) == 0) {
            // 如果两个堆合起来的元素个数是偶数，数据流的中位数就是各自堆顶元素的平均值
            return (double) (maxHeap.peek() + minHeap.peek()) / 2;
        } else {
            // 如果两个堆合起来的元素个数是奇数，数据流的中位数大顶堆的堆顶元素
            return (double) maxHeap.peek();
        }
    }



}
