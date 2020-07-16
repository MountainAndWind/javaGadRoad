package Arithmetic.sort;


import java.util.Arrays;

/**
 * 希尔排序
 *不稳定
 * 希尔排序应运而生了，希尔排序通过加大插入排序中元素的间隔，并在这些有间隔的元素中进行插入排序，从而使数据项能够大跨度的移动。
 * 当这些数据项排过一趟序后，希尔排序算法减小数据项的间隔再进行排序，依次进行下去，最后间隔为1时，就是我们上面说的简单的直接插入排序。
 *
 * 希尔的原稿中，他建议间隔选为N/2，也就是每一趟都将排序分为两半，因此对于N=100的数组，逐渐减小的间隔序列为：50,25,12，6,3,1。
 * 这个方法的好处是不需要在开始排序前为找到初始序列的间隔而计算序列，只需要用2整除N。但是这已经被证明并不是最好的序列。
 *
 *间隔序列中的数字互质是很重要的指标，也就是说，除了1，他们没有公约数。（不懂）
 * 这个约束条件使得每一趟排序更有可能保持前一趟排序已经排好的结果，而希尔最初以N/2的间隔的低效性就是没有遵守这个准则。
 *
 *
 * 但是无论是什么间隔序列，最后必须满足一个条件，就是逐渐减小的间隔最后一定要等于1，因此最后一趟排序一定是简单的插入排序。
 */
public class HeerSort {

     //希尔排序
    public static void main(String[] args) {
        int[] a={49,38,65,97,76,13,27,49,78,34,12,64,1,33,85,29};
        heerSortHalfLength(a);
        //希尔排序
        //heerSortHalfLength(a);
        //变形方式  间隔序列中的数字互质是很重要的指标，也就是说，除了1，他们没有公约数。（不懂）
        //这个约束条件使得每一趟排序更有可能保持前一趟排序已经排好的结果，而希尔最初以N/2的间隔的低效性就是没有遵守这个准则。
        //heerSortKnuth(a);
        printArr(a);
    }

    static void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    /**
     * 与heerSortHalfLength就是一个间隔序列的计算不一样
     * @param arr
     */
    private static void heerSortKnuth(int[] arr) {
        int step = 1 ;
        int len = arr.length;
        while(step <= len/3){
            step = step*3 + 1;//1,4,13,40......
        }
        while(step > 0){
            //分别对每个增量间隔进行排序
            for(int i = step ; i < len ; i++){
                int temp = arr[i];
                int j = i;
                while(j > step-1 && temp <= arr[j-step]){
                    arr[j] = arr[j-step];
                    j -= step;
                }
                arr[j] = temp;
            }//end for
            System.out.println("间隔为"+step+"的排序结果为"+ Arrays.toString(arr));
            step = (step-1)/3;
        }//

    }

    private static void heerSortHalfLength(int[] arr) {
        int d = arr.length/2;
        while(true){
            for(int i=d;i<arr.length;i++){
                int temp =  arr[i];
                int j = i;
                while(j>d-1&&arr[j-d]>temp){
                    arr[j]= arr[j-d];
                    j-=d;
                }
                arr[j] = temp;
            }
            if(d==1){
                break;
            }
            d--;
        }
    }

    private static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j>0&&temp<arr[j-1]){
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] = temp;
        }

    }


}