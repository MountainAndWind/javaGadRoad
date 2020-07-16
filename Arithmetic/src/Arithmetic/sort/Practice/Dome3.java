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
     * ����ʱ�临�Ӷ�Ϊ nlogn ���ռ临�Ӷ�Ϊn   �ڼ�������»��˻���n2
     * ���ѡ��ķ������й����ÿ��ѡ��ķ����㶼��һ�ߵ��Ļ���
     * ����Ҫ���ľ����Ż������㣬�Ż����������ȡ�� ������ȡ�У����ȡ��
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
     * ��ȡ������::�Ż����������ȡ�� ������ȡ�У����ȡ��
     * @param arr
     * @param low
     * @param high
     * @return
     */
    private int getMid(int[] arr, int low, int high) {//�˴��ǵ���
        int mid = arr[low];//�˴�Ĭ��ȡ��һ��
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
