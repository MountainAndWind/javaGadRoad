package Arithmetic.sort;

/**
 * 归并排序 相对于快速排序更像一一种分治 先拆分成小数组，小数组中排完序之后，再合并
 * 归并排序使用的就是分治思想。分治，顾名思义，就是分而治之，将一个大问题分解成小的
 * 子问题来解决。小的子问题解决了，大问题也就解决了。
 */
public class MergeSort {


	/*复杂度分析
	*我们假设对 n 个元素进行归并排序需要的时间是 T(n)，那分解成两个子数组排序的时间都是 T(n/2)。
	* 我们知道，merge() 函数合并两个有序子数组的时间复杂度是 O(n)。所以，套用前面的公式，归并排序的时间复杂度的计算公式就是：
	*
		T(1) = C；   n=1时，只需要常量级的执行时间，所以表示为C。
		T(n) = 2*T(n/2) + n； n>1

		T(n) = 2*T(n/2) + n
			 = 2*(2*T(n/4) + n/2) + n = 4*T(n/4) + 2*n
			 = 4*(2*T(n/8) + n/4) + 2*n = 8*T(n/8) + 3*n
			 = 8*(2*T(n/16) + n/8) + 3*n = 16*T(n/16) + 4*n
			 ......
			 = 2^k * T(n/2^k) + k * n
			 ......
			 通过这样一步一步分解推导，我们可以得到 T(n) = 2^kT(n/2^k)+kn。当 T(n/2^k)=T(1) 时，也就是 n/2^k=1，我们得到 k=log2n 。
			 我们将 k 值代入上面的公式，得到 T(n)=Cn+nlog2n 。
			 如果我们用大 O 标记法来表示的话，T(n) 就等于 O(nlogn)。所以归并排序的时间复杂度是 O(nlogn)。

			 空间复杂度为O(n)
	* */
	public void mergeSort(int[] a,int left,int right){
		if(left<right){
			int middle = (left+right)/2;
			mergeSort(a, left, middle);
			mergeSort(a,middle+1,right);
			merge(a,left,middle,right);//合并
		}
	}

	private void merge(int[] a, int left, int middle, int right) {
		int [] tmpArray = new int[a.length];
		int rightStart = middle+1;
		int tmp = left;
		int third = left;
		//比较两个小数组相应下标位置的数组大小，小的先放进新数组
		while(left<=middle&&rightStart<=right){
			if(a[left]<=a[rightStart]){
				tmpArray[third++] = a [left++];
			}else{
				tmpArray[third++] = a[rightStart++];
			}
		}
		//如果左边还有数据需要拷贝，把左边数组剩下的拷贝到新数组
		while(left<=middle){
			tmpArray[third++] = a[left++];
		}
		//如果右边还有数据......
		while(rightStart<=right){
			tmpArray[third++] = a[rightStart++];
		}
		while(tmp<=right){
			a[tmp] = tmpArray[tmp++];
		}
	}

	public static void main(String[] args){
		MergeSort mergeSort = new MergeSort();
		int [] a = new int[]{90,3,2,67,44,-9,87,65,11,9,2,8};
		mergeSort.mergeSort(a, 0, a.length-1);
		for(int n:a){
			System.out.print(" "+n);
		}
	}
}
