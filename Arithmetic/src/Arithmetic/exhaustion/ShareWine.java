package Arithmetic.exhaustion;

/**
 * @description:��ٷ� ���ɷ־�
 * �������Ʊ� �����ֱ���12  8  5  ��Ŀ����������6�ķ���
 * ˼·  ָ�����ƵĹ���
 *       ����1  һ�ű����������ű����������ű�������һ�ű�
 *       ����2  �����ű�Ϊ�յ�ʱ��������浹
 * @author: slfang
 * @time: 2020/5/21 16:29
 */
public class ShareWine {
    private int b1 = 12;
    private int b2 = 8;
    private int b3 = 5;
    private int m = 6;//Ŀ�����
    //����һ��ʼ12,0,0
    void find(int bb1,int bb2,int bb3){
        System.out.println("һ�ű���ǰ��������"+bb1);
        System.out.println("���ű���ǰ��������"+bb2);
        System.out.println("���ű���ǰ��������"+bb3);

        if(bb1==m||bb2==m||bb3==m){
            System.out.println("���ҵ���");
            return;
        }

        if(bb2==0){//�����ű�����
            if(bb1>b2){//bb1����b2��size
                find(bb1-b2,b2,bb3);
            }else{//bb1������b2��size��ȫ������
                find(0,bb1,b3);
            }
        }else if(bb2!=0&&bb3!=b3){//2��3�ŵ���
            if(bb2+bb3<=b3){//ȫ������
                find(bb1,0,bb3+bb2);
            }else{
                find(bb1,bb2-(b3-bb3),b3);
            }
        }else if(bb3==b3){//���3���Ļ�  ��1��
            if(bb1+bb3>b1){//
                find(b1, bb2, bb3-(b1-bb1));
            }else{
                find(bb1+bb3, bb2, 0);
            }
        }
    }

    public static void main(String[] args) {
        ShareWine wine = new ShareWine();
        wine.find(12,0,0);
    }

}
