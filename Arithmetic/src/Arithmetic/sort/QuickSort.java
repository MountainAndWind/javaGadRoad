package Arithmetic.sort;

/**
 * 快速排序是对冒泡排序的一种改进，由C. A. R. Hoare在1962年提出的一种划分交换排序，采用的是分治策略（一般与递归结合使用），以减少排序过程中的比较次数。
 * 那不是已经有了归并排序了吗，但是归并排序的空间复杂度O(n)，会长生的额外的空间
 * 1、找到中间值
 * 2、递归（从上至下的）
 * 3、O(nlogn)
 */
public class QuickSort {

	/**
	 * 但是快速排序还是有一定的问题，就是我们找的基准元素可能会导致一边倒的情况，数据全在左边，或者全是右边
	 * 这个时候可以采用三项去取中 为了找到一个数组中的中值数据，一般是取数组中第一个、中间的、最后一个，选择这三个数中位于中间的数。
	 *
	 *  如果使用三数据取中划分方法，则必须遵循快速排序算法不能执行三个或者少于三个的数据，如果大量的子数组都小于3个，
	 * 那么使用快速排序是比较耗时的。联想到前面我们讲过简单的排序（冒泡、选择、插入）
	 * 当数组长度小于M的时候（high-low <= M）， 不进行快排，而进行插入排序。转换参数M的最佳值和系统是相关的，一般来说，
	 *  5到15间的任意值在多数情况下都能令人满意。
	 * @param a
	 */
	public void quick(int [] a){
		if(a.length>0){
			//quickSort(a,0,a.length-1);
			quickSort2(a,0,a.length-1);
		}
	}


	//数组array中下标为i和j位置的元素进行交换
	private static void swap(int[] array , int i , int j){
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * 第二种实现  次数与第一种方式不同  次数是哨兵游标移动 后交换   理解起来相对于第一种要好
	 * @param arr
	 * @param low
	 * @param high
	 */
	private void quickSort2(int[] arr, int low, int high) {
		if(low<high){
           int middle = getMiddle2(arr,low,high);
			quickSort2(arr, low, middle);
			quickSort2(arr,middle+1,high);
		}
	}

	/**
	 * @param arr
	 * @param low
	 * @param high
	 * @return
	 */
	private int getMiddle2(int[] arr, int low, int high) {
	    //当数组长度小于M的时候（high-low <= M） 可以采用插入排序
        int js = getJs(low, high);
        //int js = getJs2(low, high);  优化
        while (low<high){// 左右游标相遇时候停止， 所以跳出外部while循环
            while (low<high&&arr[high]>=arr[js]){
                high--;
            }
            while (low<high&&arr[low]<=arr[js]){
                low++;
            }
            swap(arr,low,high);
        }
        swap(arr,js,low);
        return js;
    }

	/**
	 * 获取基数 默认实现为取第一个
	 * @param low
	 * @param high
	 */
	private int getJs(int low, int high) {
        return low;
    }

    /**
     * 优化 三项取中划分   取数组下标第一个数、中间的数、最后一个数的中间值
     * @param right
     * @param left
     * @return
     */
    private int getJs2(int[] array,int right, int left) {
        int center = (right-left)/2+left;
        if(array[left] > array[right]){ //得到 array[left] < array[right]
            swap(array, left, right);
        }
        if(array[center] > array[right]){ //得到 array[left] array[center] < array[right]
            swap(array, center, right);
        }
        if(array[center] > array[left]){ //得到 array[center] <  array[left] < array[right]
            swap(array, center, left);
        }

        return left; //array[left]的值已经被换成三数中的中位数， 将其返回
    }

	/**
	 * 快速排序
	 * @param a
	 * @param low
	 * @param high
	 */
	private void quickSort(int[] a, int low, int high) {
		if(low<high){
			int middle = getMiddle(a,low,high);
			quickSort(a, low, middle-1);
			quickSort(a,middle+1,high);
		}
	}
	
/**
 * 获取中间下标
 * @param a
 * @param low
 * @param high
 * @return
 */
private int getMiddle(int[] a, int low, int high) {
	int temp = a[low];//基准元素  选择 p 到 r 之间的任意一个数据作为 pivot（分区点）(一般情况下，可以选择 p 到 r 区间的最后一个元素) 此处选择第一个
	while(low<high){
		while(low<high&&a[high]>=temp){//小的往左，
			high--;
		}
		a[low] = a[high];
		while(low<high&&a[low]<=temp){//大的往右
			low++;
		}
		a[high] = a[low];
	}
	a[low] = temp;//插入到排序后正确的位置
	return low;
}

public static void main(String[] args){
	QuickSort quickSort = new QuickSort();
	int [] a = {19,2,3,90,67,33,-7,24,3,56,34,5};
	quickSort.quick(a);
	for(int num :a){
		System.out.println(" "+num);
	}
}
}
