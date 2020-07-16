package Arithmetic.sort;

/**
 * @description:选择排序
 * @author: slfang
 * @time: 2020/4/13 20:59
 */
public class SelectSortByMy {


    public static void main(String[] args) {
        int[] arr = new int[]{12,0,3,4,19,33,11,8,31};
        sort(arr);
    }

    private static void sort(int[] arr) {
        int min=-1;
        for (int i = 0; i <arr.length-1; i++) {
            min=i;
            for (int j = i+1; j <arr.length ; j++) {
                if(arr[j]<arr[min]){
                    min=j;
                }
            }
            if(i!=min){
                int temp = arr[min];
                arr[min]=arr[i];
                arr[i]=temp;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
