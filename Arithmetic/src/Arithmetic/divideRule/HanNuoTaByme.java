package Arithmetic.divideRule;

/**
 * @description:��ŵ��ʵ��
 * @author: slfang
 * @time: 2020/6/2 9:54
 */
public class HanNuoTaByme {


    private static int index=1;

    public static void main(String[] args) {
        HanNuoTaByme hanNuoTaByme = new HanNuoTaByme();
        hanNuoTaByme.excute(3,"A","B","C");
    }


    private void excute(int i, String from, String depand, String des) {
        //��������   A    B    C
        //�����һ��Բ��  ֱ���ƶ���C
        //���������Բ��  ���ƶ���A��B   ���ƶ�A��C  ���ƶ�B��C
        //���������Բ��  ���ƶ�A��C   A��B  C��B   A��C  B��A   B��C  A��C
        if(i==1){//���ֻ��һ���Ļ���ֱ���ƶ���Ŀ������
            move(1, from, des);
        }else{
            excute(i-1,from,des,depand);
            move(i,from,des);
            excute(i-1,depand,from,des);
        }

    }

    private void move(int i, String from, String des) {
        System.out.println("��һ����"+index+++" ��"+from+"��"+des);
    }
}
