package dataStructure.Queue;

/**
 * @description:���и�ջһ�������п�����������ʵ�֣�Ҳ������������ʵ�֡�������ʵ�ֵ�ջ����˳��ջ��
 * ������ʵ�ֵ�ջ������ʽջ��ͬ����������ʵ�ֵĶ��н���˳����У�������ʵ�ֵĶ��н�����ʽ���С�
 * ����ʵ�ֽ�Ϊ��
 * ѭ����������ռ��˷ѵ�����
 * �пգ�head == tail  ��  ������(tail+1)%n=head  tail ָ���λ��ʵ������û�д洢���ݵģ�ѭ�����л��˷�һ������Ĵ洢�ռ䡣
 *
 * �������У���ʵ�����ڶ��л�������������������������˵�������ڶ���Ϊ�յ�ʱ�򣬴Ӷ�ͷȡ���ݻᱻ��������Ϊ��ʱ��û�����ݿ�ȡ��
 *     ֱ���������������ݲ��ܷ��أ���������Ѿ����ˣ���ô�������ݵĲ����ͻᱻ������ֱ���������п���λ�ú��ٲ������ݣ�Ȼ���ٷ��ء�
 *     ��Ӧ���Ѿ������ˣ������Ķ������һ���������� - ������ģ�͡����ǵģ����ǿ���ʹ���������У�����ʵ��һ���������� - ������ģ�͡���
 *     �����������У����ǻ�����ͨ��Э���������ߡ��͡������ߡ��ĸ�������������ݵĴ���Ч�ʡ�����ǰ������ӣ����ǿ��Զ����ü����������ߡ�����Ӧ��һ���������ߡ���
 *
 * ��������:�̰߳�ȫ�Ķ������ǽ�����������  ���ֱ�ӵ�ʵ�ַ�ʽ��ֱ���� enqueue()��dequeue() �����ϼ��������������ȴ󲢷��Ȼ�Ƚϵͣ�ͬһʱ�̽�����һ�������ȡ������
 *     ʵ���ϣ����������ѭ�����У����� CAS ԭ�Ӳ���������ʵ�ַǳ���Ч�Ĳ������С�
 *
 *
 * �̳߳�û�п����߳�ʱ���µ����������߳���Դʱ���̳߳ظ���δ��������ִ��������������ʵ�ֵ��أ�
 *     ���ִ������ԡ���һ���Ƿ������Ĵ�����ʽ��ֱ�Ӿܾ���������
 *     ��һ���������Ĵ�����ʽ���������Ŷӣ��ȵ��п����߳�ʱ��ȡ���Ŷӵ������������
 *     ����������ʵ�ַ�ʽ������ʵ��һ��֧�������Ŷӵ��޽���У�unbounded queue�������ǿ��ܻᵼ�¹���������Ŷӵȴ���
 *         ����������Ӧʱ����������ԣ������Ӧʱ��Ƚ����е�ϵͳ����������ʵ�ֵ������Ŷӵ��̳߳��ǲ����ʵġ�
 *     ����������ʵ�ֵ��н���У�bounded queue�������еĴ�С���ޣ������̳߳����Ŷӵ����󳬹����д�Сʱ��������������ͻᱻ�ܾ������ַ�ʽ����Ӧʱ�����е�ϵͳ��˵������Ը��Ӻ�����
 *         ����������һ�������Ķ��д�С��Ҳ�Ƿǳ��н����ġ�����̫���µȴ�������̫�࣬����̫С�ᵼ���޷��������ϵͳ��Դ�������������
 * @author: slfang
 * @time: 2020/7/1 17:37
 */
public class Dome1 {

    public static void main(String[] args) {
        System.out.println();
    }

}