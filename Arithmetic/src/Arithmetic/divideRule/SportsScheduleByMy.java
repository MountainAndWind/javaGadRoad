package Arithmetic.divideRule;

/**
 * @description:����ʵ�ֱ�������
 * ͼƬ���ݼ��е��Ʊʼ�
 * @author: slfang
 * @time: 2020/5/26 11:17
 */
public class SportsScheduleByMy {
    /*��1����������
    ��2���������˷�
     ��3��Strassen����˷�
    ��4�����̸���
    ��5���ϲ�����
    ��6����������
    ��7������ʱ��ѡ��
    ��8����ӽ��������
    ��9��ѭ�����ճ̱�
    ��10����ŵ��*/

    public static void main(String[] args) {
        int n=8;
        int [][]table = new int[n][n];
        shcedule(table,n);
        int c = 0;
        for (int i = 0; i <n ; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(table[i][j]+" ");
                c++;
                if(c%n==0){
                    System.out.println();
                }
            }
        }
    }

    /**
     * ����
     * @param table
     * @param n
     */
    private static void shcedule(int[][] table, int n) {
         if(n==1){
             table[0][0]=1;
         }else{
             int m = n/2;
             //���ϲ���
             shcedule(table,m);
             //���ϲ���
             for (int i = 0; i < m; i++) {
                 for (int j = m; j < n; j++) {
                     table[i][j]=table[i][j-m]+m;
                 }
             }
             //����
             for (int i = m; i < n; i++) {
                 for (int j = 0; j <m; j++) {
                     table[i][j]=table[i-m][j]+m;
                 }
             }
             //����
             for (int i = m; i <n ; i++) {
                 for (int j = m; j < n; j++) {
                     table[i][j]=table[i-m][j-m];
                 }
             }
         }

    }
}
