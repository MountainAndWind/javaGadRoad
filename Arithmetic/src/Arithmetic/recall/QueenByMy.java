package Arithmetic.recall;

/**
 * @description:�˻ʺ�����㷨
 *              һ�������зŻʺ�
 *              ���Ұ˻ʺ�ķ���
 *              ԲȦ����ʺ�
 *              �ʺ�����λ�������Ѿ�����б�Խǲ��ܷŻʺ�
 * @author: slfang
 * @time: 2020/5/31 18:19
 */
public class QueenByMy {
    public static int num = 0;//�ۼƷ���
    private static final int MAX_SISE=8;

    /**
     * ���̶�ά����
     */
    private static int[][] QUEEN=new int[MAX_SISE][MAX_SISE];


    /**
     * ��¼ÿһ�лʺ�ڷŵ�λ��
     */
    private static int[] COLS = new int[MAX_SISE];

    /*
    * Ҫ��8*8�Ĺ������������з�8���ʺ�
    * ʹ���������ʺ󶼲��ܻ���Ե���
    * �����ǻʺ��ܳԵ�ͬһ�С�ͬһ�С�
    * ͬһ�Խ��ߵ����ӡ�
    * ͳ�ƹ��ж����ַ���
    * */

    public static void main(String[] args) {
        getQueenCount(0);
    }

    private static void getQueenCount(int n) {
        /*
        * �ҵ����Էŵ�λ�ã�����һ����������±��Ƿ��ܷŵ���Ϣ����true����ʾ���ܷ��룩  ��false ��ʾ���ܷ��룩
        * */
        boolean[] isLegal =new boolean[MAX_SISE];


        for (int j = 0; j <n ; j++) {//��ǰ������Щ�ܴ�����Щ���ܴ��  Ȼ���¼��isLegal ������ true ��ʾ���ܷţ�false��ʾ�ܷ�
            isLegal[COLS[j]]=true;//ͬһ������Ϊtrue;

            //���´�����Ϊ�˼���б�Խǵ�λ��  ˼���ǿ���������б��
            int d = n-j;//�����ֵ
            int index = COLS[j];
            //��б����
            if(index-d>=0){
                isLegal[index-d]=true;
            }
            //��б����
            if(index+d<MAX_SISE){
                isLegal[index+d]=true;
            }
        }


        for (int j = 0; j <MAX_SISE ; j++) {
            if(!isLegal[j]){
                COLS[n]=j;
                if(n<MAX_SISE-1){
                    getQueenCount(n+1);
                }else{
                    System.out.println("�ҳ�һ������ʽ");
                    num++;
                    printQueen(num);
                }
            }
        }
    }

    private static void printQueen(int num) {
        System.out.println("��"+num+"����ʽ");
        for (int i = 0; i < MAX_SISE; i++) {
            for (int j = 0; j < MAX_SISE; j++) {
                if(COLS[i]==j){
                    System.out.print(" 0 ");
                }else{
                    System.out.print(" + ");
                }
            }
            System.out.println();
        }
    }

}
