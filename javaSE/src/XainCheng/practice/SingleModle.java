package XainCheng.practice;

/**
 * @description:�̰߳�ȫ�ĵ���ģʽ
 * @author: slfang
 * @time: 2020/12/7 21:43
 */
public class SingleModle {
    private static SingleModle instance;

    private SingleModle(){
    }

    /**
     * ˫��������
     * ����һ��ģʽ�����Ż��������ж��������Ƿ�Ϊ null ����Ϊ�ڲ��������е��߳�һִ���˵�һ���жϵ�ʱ��
     * ��Ϊnull���ɴ˿�����һ���߳�����ִ�����ʼ�����������ͷ����Ժ���̲߳���֪���Ѿ���ʼ��������˿�
     * �������鲻�����ٴ��жϻ��ٳ�ʼ��һ�Σ����Υ���˵���ģʽ�ĳ����ˡ�
     * @return
     */
    public static SingleModle getInstance() {    //�Ի�ȡʵ���ķ�������ͬ��
        if(instance==null){
            synchronized (SingleModle.class){
                if(instance==null){
                    instance = new SingleModle();
                }
            }
        }
        return instance;
    }



}
