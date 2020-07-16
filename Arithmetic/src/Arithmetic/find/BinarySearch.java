package Arithmetic.find;


/**
 *  ���ֲ��������ڵײ�����������ʵ����ԣ�ʱ�临�Ӷ�ΪO(logn)
 *
 * 1. ѭ���˳�����ע���� low<=high�������� low
 * 2���Ƕ��ֲ����ܷ������������ݽṹ�أ������������ǲ����Եģ���Ҫԭ���Ƕ��ֲ����㷨��Ҫ�����±��������Ԫ�ء���������������������ڽ��������鰴���±�����������ݵ�ʱ�临�Ӷ��� O(1)��
 *    ������������ʵ�ʱ�临�Ӷ��� O(n)�����ԣ��������ʹ������洢�����ֲ��ҵ�ʱ�临�Ӿͻ��úܸߡ�
 * 3��������ǵ����ݼ�����Ƶ���Ĳ����ɾ��������Ҫ���ö��ֲ��ң�Ҫôÿ�β��롢ɾ������֮��֤������Ȼ����Ҫô��ÿ�ζ��ֲ���֮ǰ���Ƚ�������������ֶ�̬���ݼ��ϣ��������ַ�����ά������ĳɱ����Ǻܸߵġ�
 *    ���ԣ����ֲ���ֻ�����ڲ��롢ɾ��������Ƶ����һ�������β��ҵĳ����С���Զ�̬�仯�����ݼ��ϣ����ֲ��ҽ��������á�����Զ�̬���ݼ��ϣ���������п��ٲ���ĳ�������أ��𼱣��ȵ���������һ���һ���ϸ����
 * 4���ٴΣ�������̫С���ʺ϶��ֲ���
 * 5�����ʺ�̫������ݣ����ֲ��ҵĵײ���Ҫ���������������ݽṹ��������Ϊ��֧��������ʵ����ԣ�Ҫ���ڴ�ռ����������ڴ��Ҫ��ȽϿ��̡����磬������ 1GB ��С�����ݣ����ϣ�����������洢���Ǿ���Ҫ 1GB �������ڴ�ռ䡣
 *
 *
 * ���ֲ��������������������ʡ�ռ��
 *
 */
public class BinarySearch {
	
	/**
	 * �ݹ�ķ�ʽ
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
			System.out.println("�ҵ���ӦԪ��ֵ���±�Ϊ��"+middle);
			return middle;
		}
		if(array[middle]<elem){
			//���ұ�
			return binarySearch(elem, array, middle+1, high);
		}
		if(array[middle]>elem){
			//�����
			return binarySearch(elem, array, low, middle-1);
		}
		return -1;
	}
	
	/**
	 * �ǵݹ�
	 * @param args
	 */
	public int directBinarySearch(int[] array,int elem){
		int low = 0;
		int high = array.length-1;
		while(low<=high){
			int middle = (low+high)/2;
			//�˴�д�����ܴ�������low �� high �Ƚϴ�Ļ�����֮�;��п��ܻ����
			//�Ľ��ķ����ǽ� mid �ļ��㷽ʽд�� low+(high-low)/2������һ�������Ҫ�������Ż������µĻ���
			// ���ǿ��Խ�����ĳ��� 2 ����ת����λ���� low+((high-low)>>1)����Ϊ��ȳ���������˵��
			// ���������λ����Ҫ��öࡣ

			if(elem>array[middle]){
				//�ұ���
				low = middle+1;
			}else  if(elem<array[middle]){
				high = middle - 1;
			}else{
				System.out.println("�ҵ���ӦԪ�أ��±�Ϊ��"+middle);
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
