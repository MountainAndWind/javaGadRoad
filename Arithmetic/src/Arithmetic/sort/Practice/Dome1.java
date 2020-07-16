package Arithmetic.sort.Practice;

import org.junit.Test;

/**
 * @description: 联系排序
 * @author: slfang
 * @time: 2020/7/6 10:03
 */
public class Dome1 {
    int[] arr = new int[]{12,0,3,4,19,33,11,8,31};

    /**
     * 冒泡
     */
    @Test
    public void test1(){
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length-i; j++) {
                if(arr[j]>arr[j+1]){
                    //exchange(arr[j],arr[j+1]);
                    exchange(arr,j,j+1);
                }
            }
        }
        printArr(arr);
    }

    /**
     * 直接插入排序
     */
    @Test
    public void test2(){
        int j;
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            j = i;
            while (j>0&&temp<arr[j-1]){
                   arr[j] = arr[j-1];
                   j--;
            }
           arr[j] = temp;
        }
        printArr(arr);
    }

    /**
     * 选择排序
     */
    @Test
    public void test3(){
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i+1; j <arr.length; j++) {
                if(arr[min]>arr[j]){
                   min=j;
                }
            }
            if(i!=min){
               exchange(arr,i,min);
            }
        }
        printArr(arr);
    }



    public  void exchange(int[] arr, int j, int i) {
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }


    public void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


}
