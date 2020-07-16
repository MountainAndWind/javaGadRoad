package Arithmetic.find;

import Arithmetic.sort.QuickSort;
import org.junit.Test;

import java.util.Arrays;

/**
 * @description:二分查找进阶
 * 1、查找第一个等于给定值
 * 2、查找最后一个等于给定值
 * 3、查找第一个大于等于给定值
 * 4、查找最后一个小于等于给定值
 * 5、循环有序数组的的二分查找
 * @author: slfang
 * @time: 2020/7/8 15:18
 */
public class BinaryUpSearch {

    int [] array = {10,23,4,3,2,5,1,2,623,92,23,23,234,2,34,234,234,2,10};

    /**
     * 1、查找第一个等于给定值
     */
    @Test
    public void Test1(){
        sortArr();
        System.out.println(findFirst(array,2));
    }

    private void sortArr() {
        QuickSort quickSort = new QuickSort();
        quickSort.quick(array);
        System.out.println(Arrays.toString(array));
    }

    //变体二：查找最后一个值等于给定值的元素
    @Test
    public void Test2(){
        sortArr();
        System.out.println(findLast(array,2));
    }
    //变体三：查找第一个大于给定值的元素  或者大于等于
    @Test
    public void Test3(){
        sortArr();
       /* 大于
        System.out.println(findFirstHigher(array,2));
        System.out.println(findFirstHigher(array,10));
        System.out.println(findFirstHigher(array,50));*/
        /*
        大于等于
        System.out.println(bsearch(array,2));
        System.out.println(bsearch(array,10));
        System.out.println(bsearch(array,50));*/
    }


    //变体4：查找最后一个小于等于给定值
    @Test
    public void Test4(){
        sortArr();
        //小于等于
        System.out.println(bsearch2(array,2));
        System.out.println(bsearch2(array,10));
        System.out.println(bsearch2(array,50));
    }

    //变体5、循环有序数组的的二分查找   4，5，6，1，2，3
    // 1. 找到分界下标，分成两个有序数组
    // 2. 判断目标值在哪个有序数据范围内，做二分查找
    @Test
    public void Test5(){
        int [] arr2 = new int[]{4,5,6,7,8,9,1,2,3};
        //获取初始位置
        int start = getStart(arr2);
        //小于等于
        System.out.println(bsearch3(arr2,2,start));
        System.out.println(bsearch3(arr2,10,start));
        System.out.println(bsearch3(arr2,50,start));
    }

    private int getStart(int[] arr) {
        int start=0;
        for (int i = 1; i < arr.length; i++) {
            if(arr[i]<arr[start]){
                start = i;
            }
        }
        return start;
    }


    private int findFirstHigher(int[] array, int i) {
        int low = 0;
        int high = array.length-1;
        while (low<high){
            int middle = low+((high-low) >> 1);
            if(array[middle]>i){
                high = middle-1;
            }else if(array[middle]<i){
                low = middle+1;
            }else{
                if(middle==array.length-1){
                    return -1;
                }else{
                    return middle+1;
                }
            }
        }
        return  array[low]<i?low+1:low;
    }

    public int bsearch2(int[] a, int value) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid =  low + ((high - low) >> 1);
            if (a[mid] <= value) {
                if(mid==a.length-1||array[mid+1]>value){
                    return mid;
                }else {
                    low = mid+1;
                }
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }


    public int bsearch3(int[] a, int value, int start) {
        int low = start;
        int high = start - 1;
        while (low <= high) {
            int mid =  low + ((high - low) >> 1);
            if (a[mid] <= value) {
                if(mid==a.length-1||array[mid+1]>value){
                    return mid;
                }else {
                    low = mid+1;
                }
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public int bsearch(int[] a, int value) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid =  low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if ((mid == 0) || (a[mid - 1] < value)) return mid;
                else high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }


    private int findLast(int[] array, int i) {
        int low = 0;
        int high = array.length-1;
        while (low<=high){
            int middle = low+((high-low) >> 1);
            if(array[middle]>i){
                high = middle-1;
            }else if(array[middle]<i){
                low = middle+1;
            }else{
                if(middle==array.length-1||array[middle+1]!=i){
                    return middle;
                }else{
                    low = middle+1;
                }
            }
        }
        return -1;
    }

    public int findFirst(int[] array, int i){
        int low = 0;
        int high = array.length-1;
        while (low<=high){
            int middle = low+((high-low) >> 1);
            if(array[middle]>i){
                high = middle-1;
            }else if(array[middle]<i){
                low = middle+1;
            }else{
                if(middle==0||array[middle-1]!=i){
                    return middle;
                }else{
                    high = middle-1;
                }
            }
        }
        return -1;
    }


}
