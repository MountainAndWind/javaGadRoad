package Arithmetic.sort;


import java.util.Arrays;

/**
 * ϣ������
 *���ȶ�
 * ϣ������Ӧ�˶����ˣ�ϣ������ͨ���Ӵ����������Ԫ�صļ����������Щ�м����Ԫ���н��в������򣬴Ӷ�ʹ�������ܹ����ȵ��ƶ���
 * ����Щ�������Ź�һ�����ϣ�������㷨��С������ļ���ٽ����������ν�����ȥ�������Ϊ1ʱ��������������˵�ļ򵥵�ֱ�Ӳ�������
 *
 * ϣ����ԭ���У���������ѡΪN/2��Ҳ����ÿһ�˶��������Ϊ���룬��˶���N=100�����飬�𽥼�С�ļ������Ϊ��50,25,12��6,3,1��
 * ��������ĺô��ǲ���Ҫ�ڿ�ʼ����ǰΪ�ҵ���ʼ���еļ�����������У�ֻ��Ҫ��2����N���������Ѿ���֤����������õ����С�
 *
 *��������е����ֻ����Ǻ���Ҫ��ָ�꣬Ҳ����˵������1������û�й�Լ������������
 * ���Լ������ʹ��ÿһ��������п��ܱ���ǰһ�������Ѿ��źõĽ������ϣ�������N/2�ļ���ĵ�Ч�Ծ���û���������׼��
 *
 *
 * ����������ʲô������У�����������һ�������������𽥼�С�ļ�����һ��Ҫ����1��������һ������һ���Ǽ򵥵Ĳ�������
 */
public class HeerSort {

     //ϣ������
    public static void main(String[] args) {
        int[] a={49,38,65,97,76,13,27,49,78,34,12,64,1,33,85,29};
        heerSortHalfLength(a);
        //ϣ������
        //heerSortHalfLength(a);
        //���η�ʽ  ��������е����ֻ����Ǻ���Ҫ��ָ�꣬Ҳ����˵������1������û�й�Լ������������
        //���Լ������ʹ��ÿһ��������п��ܱ���ǰһ�������Ѿ��źõĽ������ϣ�������N/2�ļ���ĵ�Ч�Ծ���û���������׼��
        //heerSortKnuth(a);
        printArr(a);
    }

    static void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    /**
     * ��heerSortHalfLength����һ��������еļ��㲻һ��
     * @param arr
     */
    private static void heerSortKnuth(int[] arr) {
        int step = 1 ;
        int len = arr.length;
        while(step <= len/3){
            step = step*3 + 1;//1,4,13,40......
        }
        while(step > 0){
            //�ֱ��ÿ�����������������
            for(int i = step ; i < len ; i++){
                int temp = arr[i];
                int j = i;
                while(j > step-1 && temp <= arr[j-step]){
                    arr[j] = arr[j-step];
                    j -= step;
                }
                arr[j] = temp;
            }//end for
            System.out.println("���Ϊ"+step+"��������Ϊ"+ Arrays.toString(arr));
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