package dataStructure.Queue;



import java.util.*;
import java.util.PriorityQueue;

/**
 * @description: ���ȶ���letcodeˢ�����
 * @author: slfang
 * @time: 2020/7/23 14:59
 */
public class PriorityQueuePriticeDome {

    static ArrayDeque<Integer> deq = new ArrayDeque<Integer>();


    public static void main(String[] args) {
       /* test1();

        test2();*/

        /*test3();*/

        /*test4();*/

        test5();
    }

    /**
     * 347��ǰK����ƵԪ��
     */
    private static void test5() {
        int[] nums = new int[]{4,3,1,4,4,7,81,1,2,2,3};
        int[] pre = getPre(nums, 2);
        System.out.println(Arrays.toString(pre));
    }

    private static int[] getPre(int[] nums, int k) {
        int[] res = new int[k];
        HashMap<Integer,Integer> hashMap = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            /*if(hashMap.get(nums[i])==null){
                hashMap.put(nums[i],1);
            }else{
                hashMap.put(nums[i],hashMap.get(nums[i])+1);
            }*/
            hashMap.put(nums[i], hashMap.getOrDefault(nums[i], 0) + 1);
        }

        PriorityQueue<HashMap.Entry<Integer,Integer>> priorityQueue =
                new PriorityQueue<>((x,y) -> y.getValue().compareTo(x.getValue()));
        Set<Map.Entry<Integer, Integer>> entries = hashMap.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            priorityQueue.offer(entry);
        }
        for (int i = 0; i <k ; i++) {
            res[i] = priorityQueue.poll().getKey();
        }
        return res;

    }

    /**
     *  295���������е���λ��
     *  ��λ��Ϊ�����б��м���������б��ǳ�����ż��
     *
     */
    private static void test4() {
        MedianFinder finder = new MedianFinder();
        int[] nums = new int[]{1,3,-1,-3,5,10,6,7,0,4,33};//-3,-1,0,1 ,3, 4  5,6,7,10,44
        for (int num : nums) {
            finder.addEle(num);
        }
        double median = finder.findMedian();
        System.out.println(median);
    }





    /**
     * 239�������������ֵ
     */
    private static void test3() {
        int[] nums = new int[]{3,2,1,-3,5,3,6,7};
        int[] arr = maxSlidingWindow(nums,3);
        int[] arr2 = maxSlidingWindow2(nums,3);
        System.out.println(Arrays.toString(arr2));
    }

    /**
     * ����˫�˶��У�����ӣ��ұ���ӣ���߷������ֵ���ұ߼���ʱһ�αȽ϶����е�����
     * �ȵ�ǰ����ֵС�Ļ������ӦΪ�������������������
      * @return
     * @param nums
     * @param k
     */
    private static int[] maxSlidingWindow2(int[] nums, int k) {
        if(nums.length==0&&nums.length<=k){
            return nums;
        }
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length-k+1];
        for (int i = 0; i <nums.length; i++) {
            //�ж���û�г���Χ
            //i-k+1�������˫�˶��еĵ�һ��λ�ã����getFirst< ˵�� ������Χ����������https://www.bilibili.com/video/BV1YV411o7Gr/  33��
            if(!deque.isEmpty()&&deque.getFirst()<(i-k+1)){
                deque.removeFirst();
            }
            //�ȵ�ǰ����ֵС�Ļ������ӦΪ�������������������
            while (!deque.isEmpty()&&nums[i]>nums[deque.getLast()]){
                deque.removeLast();
            }
            deque.add(i);
            if(i>=k-1){
                res[i-k+1] = nums[deque.getFirst()];
            }
        }
        return res;
    }

    /**
     * 215���������ҳ���K���Ԫ��
     */
    private static void test2() {
        //˼·ͬ�����ù��������ʵ��

    }



    /**
     * 23���ϲ�K����������
     */
    static void test1(){
        LinkedList list1 = new LinkedList();
        for (int i = 0; i <5 ; i++) {
            list1.add(i);
        }
        System.out.println(list1);
        LinkedList list2 = new LinkedList();
        for (int i = 0; i <8 ; i=i+2) {
            list2.add(i);
        }
        System.out.println(list2);

        LinkedList list3 = new LinkedList();
        for (int i = 2; i <15 ; i=i+4) {
            list3.add(i);
        }
        System.out.println(list3);
        PriorityQueue queue = new PriorityQueue();
        for (int i = 0; i <list1.size() ; i++) {
            queue.add((Integer) list1.get(i));
        }
        for (int i = 0; i <list2.size() ; i++) {
            queue.add((Integer) list2.get(i));
        }
        for (int i = 0; i <list3.size() ; i++) {
            queue.add((Integer) list3.get(i));
        }
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }

    public static void clean_deque(int i, int k,int[] nums) {
        // remove indexes of elements not from sliding window
        if (!deq.isEmpty() && deq.getFirst() == i - k)
            deq.removeFirst();

        // remove from deq indexes of all elements
        // which are smaller than current element nums[i]
        while (!deq.isEmpty() && nums[i] > nums[deq.getLast()])
            deq.removeLast();
    }


    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        // init deque and output
        int max_idx = 0;
        for (int i = 0; i < k; i++) {
            clean_deque(i, k,nums);
            deq.addLast(i);
            // compute max in nums[:k]
            if (nums[i] > nums[max_idx])
                max_idx = i;
        }
        int [] output = new int[n - k + 1];
        output[0] = nums[max_idx];

        // build output
        for (int i  = k; i < n; i++) {
            clean_deque(i, k,nums);
            deq.addLast(i);
            output[i - k + 1] = nums[deq.getFirst()];
        }
        return output;
    }
}
