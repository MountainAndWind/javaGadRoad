package Arithmetic.sort.Practice;

import org.junit.Test;

/**
 * @description:
 * @author: slfang
 * @time: 2020/7/7 10:34
 */
public class Dome3 {

    @Test
    public void test1(){
        int[] arr = new int[]{12,0,3,4,19,33,11,8,31,312,343,89,12,23,43,6,8,9,1321,43,77,89,2};
        quickSort(arr,0,arr.length-1);
        printArr(arr);

    }

    public void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    /**
     * 快排时间复杂度为 nlogn ，空间复杂度为n   在极端情况下回退化成n2
     * 这跟选择的分区点有关如果每次选择的分区点都是一边倒的话，
     * 我们要做的就是优化分区点，优化方法有随机取点 ，三点取中，五点取中
     * @param arr
     * @param low
     * @param high
     */
    private void quickSort(int[] arr, int low, int high) {
        if(low<high){
            int mid = getMid(arr,low,high);
            quickSort(arr,low,mid);
            quickSort(arr,mid+1,high);
        }
    }

    /**
     * 获取分区点::优化方法有随机取点 ，三点取中，五点取中
     * @param arr
     * @param low
     * @param high
     * @return
     */
    private int getMid(int[] arr, int low, int high) {//此处是倒叙
        int mid = arr[low];//此处默认取第一个
        while (low<high){
            while (low<high&&arr[high]<=mid){
                high--;
            }
            arr[low] = arr[high];
            while (low<high&&arr[low]>=mid){
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = mid;
        return low;
    }
}
