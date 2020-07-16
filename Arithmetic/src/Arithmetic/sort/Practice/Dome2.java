package Arithmetic.sort.Practice;

import org.junit.Test;

/**
 * @description:高级排序
 * @author: slfang
 * @time: 2020/7/6 13:50
 */
public class Dome2 {

    int[] arr = new int[]{12,0,3,4,19,33,11,8,31};
    /**
     * mergeSort归并排序 ，分治思想
     */
    @Test
    public void test1(){
        mergeSort(0,arr.length-1);
        printArr(arr);
    }

    /**
     * 快速排序
     */
    @Test
    public void test2(){
        quick(arr,0,arr.length-1);
        printArr(arr);
    }

    private void quick(int[] arr, int left, int right) {
        if(left<right){
            int partition = partition(arr,left,right);//分区  此处是用第一个位置
            quick(arr,left,partition);
            quick(arr,partition+1,right);
        }
    }

    /**
     * 分区
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private int partition(int[] arr, int left, int right) {
        int temp = arr[left];//基准元素
        while (left<right){
            while (left<right&&arr[right]>=temp){//小的往左
                right--;
            }
            arr[left] = arr[right];
            while (left<right&&arr[left]<=temp){
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;
        return left;
    }


    private void mergeSort(int start ,int end) {
        if(start<end){
            int mid = (start+end)/2;
            mergeSort(start,mid);
            mergeSort(mid+1,end);
            merae(start,end);
        }
    }

    private void merae(int start, int end) {
        int mid = (start+end)/2;
        int start2 = mid+1;
        int index = start;
        int temp  = start;
        int[] tempArr = new int[arr.length];//此处需要一个临时数组做过度
        while (start<=mid&&start2<=end){
            if(arr[start]<arr[start2]){
                tempArr[index++]=arr[start];
                start++;
            }else{
                tempArr[index++]=arr[start2];
                start2++;
            }
        }
        while (start<=mid){
            tempArr[index++] = arr[start++];
        }
        while (start2<=end){
            tempArr[index++] = arr[start2++];
        }

        while (temp<=end){
            arr[temp] = tempArr[temp++];
        }
    }


    public void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
