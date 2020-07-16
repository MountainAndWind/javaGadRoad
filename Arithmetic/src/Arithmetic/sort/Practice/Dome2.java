package Arithmetic.sort.Practice;

import org.junit.Test;

/**
 * @description:�߼�����
 * @author: slfang
 * @time: 2020/7/6 13:50
 */
public class Dome2 {

    int[] arr = new int[]{12,0,3,4,19,33,11,8,31};
    /**
     * mergeSort�鲢���� ������˼��
     */
    @Test
    public void test1(){
        mergeSort(0,arr.length-1);
        printArr(arr);
    }

    /**
     * ��������
     */
    @Test
    public void test2(){
        quick(arr,0,arr.length-1);
        printArr(arr);
    }

    private void quick(int[] arr, int left, int right) {
        if(left<right){
            int partition = partition(arr,left,right);//����  �˴����õ�һ��λ��
            quick(arr,left,partition);
            quick(arr,partition+1,right);
        }
    }

    /**
     * ����
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private int partition(int[] arr, int left, int right) {
        int temp = arr[left];//��׼Ԫ��
        while (left<right){
            while (left<right&&arr[right]>=temp){//С������
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
        int[] tempArr = new int[arr.length];//�˴���Ҫһ����ʱ����������
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
