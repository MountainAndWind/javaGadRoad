package Arithmetic.sort;

/**
 * ���������Ƕ�ð�������һ�ָĽ�����C. A. R. Hoare��1962�������һ�ֻ��ֽ������򣬲��õ��Ƿ��β��ԣ�һ����ݹ���ʹ�ã����Լ�����������еıȽϴ�����
 * �ǲ����Ѿ����˹鲢�������𣬵��ǹ鲢����Ŀռ临�Ӷ�O(n)���᳤���Ķ���Ŀռ�
 * 1���ҵ��м�ֵ
 * 2���ݹ飨�������µģ�
 * 3��O(nlogn)
 */
public class QuickSort {

	/**
	 * ���ǿ�����������һ�������⣬���������ҵĻ�׼Ԫ�ؿ��ܻᵼ��һ�ߵ������������ȫ����ߣ�����ȫ���ұ�
	 * ���ʱ����Բ�������ȥȡ�� Ϊ���ҵ�һ�������е���ֵ���ݣ�һ����ȡ�����е�һ�����м�ġ����һ����ѡ������������λ���м������
	 *
	 *  ���ʹ��������ȡ�л��ַ������������ѭ���������㷨����ִ�����������������������ݣ���������������鶼С��3����
	 * ��ôʹ�ÿ��������ǱȽϺ�ʱ�ġ����뵽ǰ�����ǽ����򵥵�����ð�ݡ�ѡ�񡢲��룩
	 * �����鳤��С��M��ʱ��high-low <= M���� �����п��ţ������в�������ת������M�����ֵ��ϵͳ����صģ�һ����˵��
	 *  5��15�������ֵ�ڶ�������¶����������⡣
	 * @param a
	 */
	public void quick(int [] a){
		if(a.length>0){
			//quickSort(a,0,a.length-1);
			quickSort2(a,0,a.length-1);
		}
	}


	//����array���±�Ϊi��jλ�õ�Ԫ�ؽ��н���
	private static void swap(int[] array , int i , int j){
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * �ڶ���ʵ��  �������һ�ַ�ʽ��ͬ  �������ڱ��α��ƶ� �󽻻�   �����������ڵ�һ��Ҫ��
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
	    //�����鳤��С��M��ʱ��high-low <= M�� ���Բ��ò�������
        int js = getJs(low, high);
        //int js = getJs2(low, high);  �Ż�
        while (low<high){// �����α�����ʱ��ֹͣ�� ���������ⲿwhileѭ��
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
	 * ��ȡ���� Ĭ��ʵ��Ϊȡ��һ��
	 * @param low
	 * @param high
	 */
	private int getJs(int low, int high) {
        return low;
    }

    /**
     * �Ż� ����ȡ�л���   ȡ�����±��һ�������м���������һ�������м�ֵ
     * @param right
     * @param left
     * @return
     */
    private int getJs2(int[] array,int right, int left) {
        int center = (right-left)/2+left;
        if(array[left] > array[right]){ //�õ� array[left] < array[right]
            swap(array, left, right);
        }
        if(array[center] > array[right]){ //�õ� array[left] array[center] < array[right]
            swap(array, center, right);
        }
        if(array[center] > array[left]){ //�õ� array[center] <  array[left] < array[right]
            swap(array, center, left);
        }

        return left; //array[left]��ֵ�Ѿ������������е���λ���� ���䷵��
    }

	/**
	 * ��������
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
 * ��ȡ�м��±�
 * @param a
 * @param low
 * @param high
 * @return
 */
private int getMiddle(int[] a, int low, int high) {
	int temp = a[low];//��׼Ԫ��  ѡ�� p �� r ֮�������һ��������Ϊ pivot�������㣩(һ������£�����ѡ�� p �� r ��������һ��Ԫ��) �˴�ѡ���һ��
	while(low<high){
		while(low<high&&a[high]>=temp){//С������
			high--;
		}
		a[low] = a[high];
		while(low<high&&a[low]<=temp){//�������
			low++;
		}
		a[high] = a[low];
	}
	a[low] = temp;//���뵽�������ȷ��λ��
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
