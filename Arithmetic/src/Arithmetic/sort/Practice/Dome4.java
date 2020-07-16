package Arithmetic.sort.Practice;

import org.junit.Test;

import java.util.Arrays;

/**
 * @description:
 * @author: slfang
 * @time: 2020/7/12 20:09
 */
public class Dome4
{

    int[] arr = new int[]{12,0,3,4,19,33,11,8,31};

    /**
     * 冒泡排序
     * 思路每次两两相比较冒泡出最大数值
     */
    @Test
    public void Test1(){
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length-i; j++) {
                if(arr[j]>arr[j+1]){
                    exchange(arr,j,j+1);
                }
            }
        }
        printArr(arr);
    }


    /**
     * 选择排序
     * 思路：找到最小的索引循环一次交换一次，相比冒泡减少了交换的次数
     */
    @Test
    public void Test2(){
        for (int i = 0; i < arr.length-1; i++) {
            int min = i;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[min]>arr[j]){
                   min = j;
                }
            }
            if(min!=i){
                exchange(arr,min,i);
            }
        }
        printArr(arr);
    }

    /**
     * 插入排序
     * 思想：每次遍历的左边都是已经排序好的序列，在排序好序列种从有到左的位置，找到正确的位置
     * 复制效率要高于交换的所以 插入的时间复杂读要高于冒泡
     */
    @Test
    public void test3(){
        for (int i = 1; i < arr.length; i++) {//第一个数已经是有序
            int temp = arr[i];
            int j = i;
            while (j>0&&temp<arr[j-1]){
                arr[j] =arr[j-1];
                j--;
            }
            arr[j] = temp;
        }
        printArr(arr);
    }

    /**
     * 希尔排序
     * 扩大间距比较排序
     */
    @Test
    public void test4(){
        int[] a={49,38,65,97,76,13,27,49,78,34,12,64,1,33,85,29};
        heerSortHalfLength(a);
        printArr(a);
    }

    /**
     * 归并排序
     * 分治思想：递归分开两边,递归在最后一层中，然后返回一层一层合并，采用的是从下至上递归
     * 空间复杂度为O(n) 没次都要新建一个临时数组
     * 时间复杂度 log n
     */
    @Test
    public void Test5(){
        if(arr.length<1&&arr==null){
            return;
        }
        int left = 0;
        int right = arr.length-1;
        guibin(arr,left,right);
        printArr(arr);
    }

    public void guibin(int[] arr, int left, int right) {
       if(left<right){
           int mid = (right+left)/2;
           guibin(arr,left,mid);
           guibin(arr,mid+1,right);
           hebin(arr,left,mid,right);
       }
    }

    /**
     * 快速排序
     * 思路：递归找到比较值一般取第一个数   递归自上到下排序
     * 时间复杂度log n
     * 空间复杂度 o(1)
     */
    @Test
    public void Test6(){
        quick(arr,0,arr.length-1);
        printArr(arr);
    }


    /**
     * 大堆排序
     */
    @Test
    public void test7(){
        buildMaxHeap(arr);
        for (int i = arr.length-1; i>0 ; i--) {
            exchange(arr,0,i);
            buildHeap(arr,i,0);
        }
        printArr(arr);
    }

    /**
     * 大堆构造
     * @param arr
     */
    private void buildMaxHeap(int[] arr) {
        int fristNoLeftNode = arr.length/2-1;
        for (int i = fristNoLeftNode; i >=0 ; i--) {
            buildHeap(arr,arr.length,i);
        }
    }

    /**
     * 细粒度构造  浮动最大数到堆顶
     * @param arr
     * @param heapSize
     * @param i
     */
    private void buildHeap(int[] arr, int heapSize, int i) {
        int left = 2*i+1;
        int right = 2*i+2;
        int tempIndex = i;
        if(left<heapSize&&arr[left]>arr[tempIndex]){
            tempIndex=left;
        }
        if(right<heapSize&&arr[right]>arr[tempIndex]){
            tempIndex = right;
        }
        if(i!=tempIndex){
            exchange(arr,tempIndex,i);
            buildHeap(arr,heapSize,tempIndex);
        }
    }

    private void quick(int[] arr, int low, int high) {
        if(low<high){
            int mid = getMiddle(arr, low, high);
            quick(arr,low,mid);
            quick(arr,mid+1,high);
        }
    }

    private int getMiddle(int[] arr, int leftIndex, int rightIndex) {
        int js = getJs(arr,leftIndex,rightIndex);
        while (leftIndex<rightIndex){
            while (leftIndex<rightIndex&&arr[js]<arr[rightIndex]){
                rightIndex--;
            }
            while (leftIndex<rightIndex&&arr[js]>arr[leftIndex]){
                leftIndex++;
            }
            exchange(arr,leftIndex,rightIndex);
        }
        exchange(arr,js,leftIndex);
        return js;
    }

    private int getJs(int[] arr, int leftIndex, int rightIndex) {
        return leftIndex;
    }


    public void hebin(int[] arr, int left, int middle, int right) {
        int indexL = left;
        int rightStart = middle+1;
        int third = left;
        int[] tmpArray = new int[arr.length];
        //比较两个小数组相应下标位置的数组大小，小的先放进新数组
        while(left<=middle&&rightStart<=right){
            if(arr[left]<=arr[rightStart]){
                tmpArray[third++] = arr [left++];
            }else{
                tmpArray[third++] = arr[rightStart++];
            }
        }

        while (left<=middle){
            tmpArray[third++] = arr [left++];
        }
        while (rightStart<=right){
            tmpArray[third++] = arr[rightStart++];
        }

        //在把临时数组的数据转存到目标数组
        while(indexL<=right){
            arr[indexL] = tmpArray[indexL++];
        }
    }

    /**
     *  没过
     * 注意不是在for循环中减间隔，而是在while中减
     * @param a
     * todo
     */
    private void heerSortHalfLength(int[] a) {
        int le = a.length/2;
        while (le>1){
            for (int i = le; i < a.length;i++) {
                int temp = a[i];
                int j = i;
                while (j>le-1&&temp<a[j-le]){
                    arr[j] = arr[j-le];
                    j-=le;
                }
                a[j] = temp;
            }
            le--;
        }
    }


    public  void exchange(int[] arr, int j, int i) {
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public void printArr(int[] a){
        /*for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }*/
        System.out.println(Arrays.toString(a));
    }
}
