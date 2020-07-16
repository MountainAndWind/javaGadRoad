package Arithmetic.sort;

/**
 * @description:Index
 * @author: slfang
 * @time: 2020/4/13 21:11
 */
public class AllSort {

    public static void main(String[] args) {
        //int[] arr = new int[]{12,0,3,4,19,33,11,8,31};
        int[] array = { 19, 8, 27, 6, 35, 14, 3, 12, 1, 0, 9, 10, 7 ,11};
        heapSort(array);
        //bubbleSort(arr);
        //selectSort(arr);
        //directInsertSort(arr);

        //冒泡排序、插入排序、选择排序这三种排序算法，它们的时间复杂度都是 O(n2)，比较高，适合小规模数据的排序。


        //归并排序和快速排序。时间复杂度为 O(nlogn) 的排序算法，这两种排序算法适合大规模的数据排序
        /*MergeSort mergeSort = new MergeSort();
        int [] a = new int[]{90,3,2,67,44,-9,87,65,11,9,2,8};
        mergeSort.mergeSort(a, 0, a.length-1);
        for(int n:a){
            System.out.print(" "+n);
        }*/
        //quicklySort(arr);


        //dichotomiaInsertSort(arr);
        //heerSort(arr);
        //heapSort(arr);


    }



    /**
     * 快速排序  找到基数的位置
     * 快速排序是对冒泡排序的一种改进，一种划分交换排序采用的是
     * 分治策略（一般与递归结合使用），以减少排序过程中的比较次数。
     * https://www.cnblogs.com/ysocean/p/8032632.html  优化分析
     * @param arr
     */
    private static void quicklySort(int[] arr) {
         if(arr.length>1){
             quickSort(arr,0,arr.length-1);
         }
         printArr(arr);
    }

    private static void quickSort(int[] a, int low, int high) {
        if(low<high){
            int middle = getMiddle(a,low,high);
            quickSort(a, 0, middle-1);
            quickSort(a,middle+1,high);
        }
    }

    /**
     * 12,0,3,4,19,33,11,8,31
     * @param arr
     * @param leftIndex
     * @param rightIndex
     */
    private static int getMiddle(int[] arr, int leftIndex, int rightIndex) {
         int temp = arr[leftIndex];
         while (leftIndex<rightIndex){
             while (leftIndex<rightIndex&&temp<arr[rightIndex]){
                 rightIndex--;
             }
             arr[leftIndex]=arr[rightIndex];//找到小的数放到左边
             while (leftIndex<rightIndex&&temp>arr[leftIndex]){
                 leftIndex++;
             }
             arr[rightIndex]=arr[leftIndex];//找到大的数放到右边
         }
         arr[leftIndex]=temp;
         return leftIndex;
    }

    /**
     * 选择排序
     * 选择排序性能分析：
     * 选择排序和冒泡排序执行了相同次数的比较：N*（N-1）/2，但是至多只进行了N次交换。
     * 当 N 值很大时，比较次数是主要的，所以和冒泡排序一样，用大O表示是O(N2) 时间级别。但是由于选择排序交换的次数少，
     * 所以选择排序无疑是比冒泡排序快的。当 N 值较小时，如果交换时间比选择时间大的多，那么选择排序是相当快的。
     * @param arr
     */
    private static void selectSort(int[] arr) {
        int min;
        for (int i = 0; i <arr.length ; i++) {
            min=i;
            for (int j = i; j <arr.length ; j++) {
                if(arr[min]>arr[j]){
                    min=j;
                }
            }
            if(min!=i){
                exchange(arr,i,min);
            }
        }
        printArr(arr);
    }

    /**
     * 冒泡排序
     * 冒泡排序性能分析：
     * 假设参与比较的数组元素个数为 N，则第一轮排序有 N-1 次比较，第二轮有 N-2 次，如此类推，这种序列的求和公式为：
     * N-1）+（N-2）+...+1 = N*（N-1）/2,当 N 的值很大时，算法比较次数约为 N2/2次比较，忽略减1
     * 假设数据是随机的，那么每次比较可能要交换位置，可能不会交换，假设概率为50%，那么交换次数为 N2/4。
     * 不过如果是最坏的情况，初始数据是逆序的，那么每次比较都要交换位置。
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        for (int i = 1; i <arr.length; i++) {
            for (int j =0; j <arr.length-i; j++) {
                if(arr[j]>arr[j+1]){
                    exchange(arr,j,j+1);
                }
            }
        }
        printArr(arr);
    }

    /**
     * 堆排序
     * 循环构建大堆找出最大值，利用完全二叉树的性质 下标关系存在   父节点下标为i 左子树为2i  右子树为2i+1
     * 上述二叉树性质由此可以构造一个一维数组
     * 每次构造大堆时不用遍历全部，只需一半，因为子树可以利用  上述性质找到
     * 具体图解可参考 https://www.cnblogs.com/chengxiao/p/6129630.html
     *
     *  堆排序的时间复杂度，主要在初始化堆过程和每次选取最大数后重新建堆的过程:
     *  https://blog.csdn.net/loveliuzz/article/details/77618530
     * @param arr
     */
    private static void heapSort(int[] arr) {
        buildMaxHeapTree(arr,arr.length);
        for (int i = arr.length - 1; i >= 1; i--) {
            exchange(arr,0,i);
            //buildMaxHeapTree(arr,i);
            maxHeap(arr,i,0);//应为此处是子树已经排序好了的，找到一边就行了，不想一开始是乱序的    没有必要再调用buildMaxHeapTree
        }
        printArr(arr);
    }

    /**
     * 大堆构造
     * @param arr
     * @param length
     */
    private static void buildMaxHeapTree(int[] arr, int length) {
        /*每次构造大堆时不用遍历全部，只需一半，因为子树可以利用  上述性质找到*/
        int mid=(arr.length) / 2-1;//从最后一个非叶子结点开始
        for (int i = mid; i >= 0 ;i--) {//
            maxHeap(arr,length,i);//此处为什么调用
        }

    }

    /**
     * 细粒度构造
     * @param arr
     * @param i
     */
    private static void maxHeap(int[] arr,int heapSize, int i) {
        //比较最小力度二叉树的值大小
        int left = 2*i+1;//此处2*i+1   与2*i+2 是应为数组下标为0
        int right = 2*i+2;
        int largest=i;
        if (left < heapSize && arr[left] > arr[i]) {
            largest = left;
        }
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }
        if(i!=largest){                           //                5
            exchange(arr,largest,i);              //            15
            //此处为什么要递归：---------------->            10    9    5是当前遍历的当5 与 15 交换位置之后 5将会与下面都小
            maxHeap(arr,heapSize,largest);
        }
    }

    /**
     * 交换数组值
     * @param arr
     * @param j
     * @param i
     */
    private static void exchange(int[] arr, int j, int i) {
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    /**
     * 希尔排序
     * @param arr
     */
    private static void heerSort(int[] arr) {
    }

    /**
     * todo
     * 二分插入排序，在直接插入排序的基础上，采用二分法做比较
     * []{12,0,3,4,19,33,11,8,31};  1+2/2+3/2+4/2+5
     * 0 3 4 12 19 33     <-----11            
     * 0 3 4 12 19    33     index =j-1       
     * @param arr
     */
    private static void dichotomiaInsertSort(int[] arr) {
        for (int i = 1; i <arr.length ; i++) {
            int temp = arr[i];
            int begin=0;
            int end=i-1;
            int mid;
            while (begin<=end){
                mid=(begin+end)/2;
                if(arr[i]>arr[mid]){//i>mid 找右边
                    begin=mid+1;
                }else{//i<=mid 找右边
                    end=end-1;
                }
            }
            for (int j = i-1; j >= begin; j--) {//应为begin小于i
                //以左下标为标准，在左位置前插入该数据，左及左后边全部后移
                arr[j+1] = arr[j];
            }
            if(begin != i){
                //左位置插入该数据
                arr[begin] = temp;
            }
        }
        printArr(arr);
    }

    /**
     * 直接插入排序  每次遍历拿当前与之前排好序的数据做比较然后排序
     * []{12,0,3,4,19,33,11,8,31};
     * 0 3 4 12 19 33     <-----11
     * 0 3 4 12 19    33     index =j-1
     * 0 3 4 12    19 33     index =j-2
     * 0 3 4    12 19 33     index =j-3
     *
     * 插入排序性能分析：
     * 在第一轮排序中，它最多比较一次，第二轮最多比较两次，一次类推，
     * 第N轮，最多比较N-1次。因此有 1+2+3+...+N-1 = N*（N-1）/2。
     * 假设在每一轮排序发现插入点时，平均只有全体数据项的一半真的进行了比较，
     * 我们除以2得到：N*（N-1）/4。用大O表示法大致需要需要 O(N2) 时间级别。
     *
     * 复制的次数大致等于比较的次数，但是一次复制与一次交换的时间耗时不同，
     * 所以相对于随机数据，插入排序比冒泡快一倍，比选择排序略快。
     * 这里需要注意的是，如果要进行逆序排列，那么每次比较和移动都会进行，这时候并不会比冒泡排序快。
     * @param arr
     */
    private static void directInsertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
               int index;
               int temp = arr[i];
               for (int j=i;j>0;j--){
                   if(arr[j]<arr[j-1]){
                       arr[j]=arr[j-1];
                       index=j-1;
                   }else{
                       break;
                   }
                   arr[index]=temp;
               }
        }
        printArr(arr);
    }

    static void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
