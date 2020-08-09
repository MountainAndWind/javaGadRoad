package dataStructure.Queue;

import java.util.PriorityQueue;

/**
 * @description:��λ������
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
            // ��������Ѻ�������Ԫ�ظ�����ż��������������λ�����Ǹ��ԶѶ�Ԫ�ص�ƽ��ֵ
            return (double) (maxHeap.peek() + minHeap.peek()) / 2;
        } else {
            // ��������Ѻ�������Ԫ�ظ���������������������λ���󶥶ѵĶѶ�Ԫ��
            return (double) maxHeap.peek();
        }
    }



}
