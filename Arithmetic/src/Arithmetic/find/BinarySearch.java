package Arithmetic.find;


/**
 *  二分查找依赖于底层数组随机访问的特性，时间复杂度为O(logn)
 *
 * 1. 循环退出条件注意是 low<=high，而不是 low
 * 2、那二分查找能否依赖其他数据结构呢？比如链表。答案是不可以的，主要原因是二分查找算法需要按照下标随机访问元素。我们在数组和链表那两节讲过，数组按照下标随机访问数据的时间复杂度是 O(1)，
 *    而链表随机访问的时间复杂度是 O(n)。所以，如果数据使用链表存储，二分查找的时间复杂就会变得很高。
 * 3、如果我们的数据集合有频繁的插入和删除操作，要想用二分查找，要么每次插入、删除操作之后保证数据仍然有序，要么在每次二分查找之前都先进行排序。针对这种动态数据集合，无论哪种方法，维护有序的成本都是很高的。
 *    所以，二分查找只能用在插入、删除操作不频繁，一次排序多次查找的场景中。针对动态变化的数据集合，二分查找将不再适用。那针对动态数据集合，如何在其中快速查找某个数据呢？别急，等到二叉树那一节我会详细讲。
 * 4、再次，数据量太小不适合二分查找
 * 5、不适合太大的数据，二分查找的底层需要依赖数组这种数据结构，而数组为了支持随机访问的特性，要求内存空间连续，对内存的要求比较苛刻。比如，我们有 1GB 大小的数据，如果希望用数组来存储，那就需要 1GB 的连续内存空间。
 *
 *
 * 二分查找相对于其他查找是最省空间的
 *
 */
public class BinarySearch {
	
	/**
	 * 递归的方式
	 * @param elem
	 * @param array
	 * @param low
	 * @param high
	 * @return
	 */
	public int binarySearch(int elem,int [] array,int low,int high){
		if(low>high){
			return -1;
		}
		int middle = (low+high)/2;
		if(array[middle] == elem){
			System.out.println("找到对应元素值，下标为："+middle);
			return middle;
		}
		if(array[middle]<elem){
			//找右边
			return binarySearch(elem, array, middle+1, high);
		}
		if(array[middle]>elem){
			//找左边
			return binarySearch(elem, array, low, middle-1);
		}
		return -1;
	}
	
	/**
	 * 非递归
	 * @param args
	 */
	public int directBinarySearch(int[] array,int elem){
		int low = 0;
		int high = array.length-1;
		while(low<=high){
			int middle = (low+high)/2;
			//此处写法可能存在问题low 和 high 比较大的话两者之和就有可能会溢出
			//改进的方法是将 mid 的计算方式写成 low+(high-low)/2。更进一步，如果要将性能优化到极致的话，
			// 我们可以将这里的除以 2 操作转化成位运算 low+((high-low)>>1)。因为相比除法运算来说，
			// 计算机处理位运算要快得多。

			if(elem>array[middle]){
				//右边找
				low = middle+1;
			}else  if(elem<array[middle]){
				high = middle - 1;
			}else{
				System.out.println("找到相应元素，下标为："+middle);
				return middle;
			}
		}
		return -1;
	}
	
	
	public static void main(String[] args){
		/*BinarySearch binarySearch = new BinarySearch();
		int [] array = {10,23,4,3,2,5,1,2,623,92,23,23,234,2,34,234,234,2,10};
		QuickSort quickSort = new QuickSort();
        quickSort.quick(array);
		for(int n:array){
			System.out.print(" "+n);
		}
//		binarySearch.binarySearch(5, array, 0, array.length-1);
        int i = binarySearch.directBinarySearch(array, 3);
        System.out.println(i);*/
        System.out.println(Math.sqrt(3));//1.7320508075688772
        System.out.println(square(3));//1.7320508075688772

    }

    public static double square(int target){
        double base = 0;
        int l = 0,r = 9;
        double step = 10;
        for (int i = 0;i < 7 ;i++){
            step *= 0.1;
            while (l <= r){
                int middle = l + (r - l)/2;
                if(Math.pow((base+middle*step),2) == target){
                    return middle;
                }
                if(Math.pow((base+middle*step),2) < target){
                    l = middle + 1;
                }
                if(Math.pow((base+middle*step),2) > target){
                    r = middle - 1;
                }
            }
            base += (r)*step;
            l = 0;
            r = 9;
        }
        return base;
    }
}
