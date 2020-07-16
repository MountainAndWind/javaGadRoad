package Arithmetic.sort;

//堆排序
//堆排序是利用堆这种数据结构而设计的一种排序算法，堆排序是一种选择排序，它的最坏，最好，平均时间复杂度均为O(nlogn)，它也是不稳定排序

/**
 *
 *
 *
 * 堆是具有以下性质的完全二叉树：每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆；或者每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆。
 * 简单的公式来描述一下堆的定义就是:
 *     大顶堆：arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]
 *     小顶堆：arr[i] <= arr[2i+1] && arr[i] <= arr[2i+2]
 *
 *     堆排序的基本思想与步骤
 *          堆排序的基本思想是：将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点，然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了
 *
 * 堆排序整体的时间复杂度是 O(nlogn)。
 *
 *
 *  注：此处并不局限于堆排序
 *     包含堆的删除（删除顶节点，尾节点替换到root节点，重新构造堆），插入（插入尾部，构造堆）
 *     堆的应用：优先级队列、求 Top K 和求中位数。
 *     优先级队列：
 *         它首先应该是一个队列。我们前面讲过，队列最大的特性就是先进先出。不过，在优先级队列中，数据的出队顺序不是先进先出，而是按照优先级来，优先级最高的，最先出队。
 *
 */
public class HeapSort {   //(1)
	public static void main(String[] args) {
		HeapSort heapSort = new HeapSort();
		int[] array = { 19, 8, 27, 6, 35, 14, 3, 12, 1, 0, 9, 10, 7 };
		//int[] array = new int[]{12,0,3,4,19,33,11,8,31};
		System.out.println("Before heap:");
		heapSort.printArray(array);

		heapSort.heapSort(array);
	}


	private void heapSort(int[] array) {
		if(array==null||array.length<=1){
			return;
		}
		//1、构建大堆，找出最大的数，放到堆顶
		builderMaxHeap(array);
		//{27, 35, 19, 12, 9, 14, 3, 6, 1, 0, 8, 10, 7}
		//2、交换；堆顶元素到数组尾
		/*for (int i = 0; i < array.length-2; i++) {
			exchangeElements(array,0,array.length-1-i);
			maxHeap(array,array.length-1,0);
		}*/
		for (int i = array.length - 1; i >= 1; i--) {
			exchangeElements(array,0,i);
			//buildMaxHeapTree(arr,i);

			// 此处有两个注意点
			// 1、应为此处是子树已经排序好了的，找到一边就行了，不向一开始是乱序的    没有必要再调用buildMaxHeapTree
			// 2、maxHeap 中heapSize 参数设置 buildMaxHeapTree中可以是数组长度，但是此处不能，应为随着遍历数组后逐渐固定最大值（大堆的下标0的位置与末尾交换），不能参与到maxHeap中的交换中去
			maxHeap(array,i,0);
		}
		System.out.println("After heap sort:printArray：");
		printArray(array);
	}

	/**
	 * 构建大堆
	 * @param array
	 */
	private void builderMaxHeap(int[] array) {
		int firstNoLeftNodeIndex = array.length/2-1;//倒数第一个非叶子节点，从右向左，从小到上
		for (int i = firstNoLeftNodeIndex; i >=0 ; i--) {
			maxHeap(array,array.length,i);
		}
	}

	/**
	 * 细粒度构建大堆
	 * @param array
	 * @param heapSize
	 * @param i
	 */
	private void maxHeap(int[] array, int heapSize, int i) {
		int left = 2*i+1;
		int right = 2*i+2;
		int tempIndex = i;
		if (left<heapSize&&array[left]>array[i]){
			tempIndex = left;
		}
		if (right<heapSize&&array[right]>array[tempIndex]){
			tempIndex = right;
		}
		if(tempIndex!=i){
			exchangeElements(array,i,tempIndex);
			maxHeap(array,heapSize,tempIndex);
		}
	}


	//（3）
	public void printArray(int[] array) {
		System.out.print("{");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			if (i < array.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("}");
	}


	public void exchangeElements(int[] array, int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}
}