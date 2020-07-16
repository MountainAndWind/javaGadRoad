package Arithmetic.dynamicPlan;

/**
 * @description:����������
 * @author: slfang
 * @time: 2020/5/26 22:36
 */
public class LCSByMy {

    /**
     * ��ȡ�������  ʾ��ͼ���е��Ʊʼ�
     * ˼�룺
     *    ��i  �� j �ֱ��ʾ�����ַ��ĳ���
     *            \-  0                      ��i=0��j=0
     *    c[i,j] =\-  C[i-1,j-1]+1           ��i,j>0  ��  x[i] = y[j]{��ʾ�����ַ������һ���ַ����}
     *            \-  max{c[i,j-1],c[i-1,j]} ��i,j>0  ��  x[i] = y[j]{��ʾ�����ַ������һ���ַ������}
     *
     * @return
     */
    private static int getLcs(String a,String b){
        //ʹ�ö�ά����洢
        char[] charA = a.toCharArray();
        char[] charB = b.toCharArray();
        int[][] table = new int[charA.length][charB.length];
        for (int i = 0; i < charA.length; i++) {
            for (int j = 0; j < charB.length; j++) {
                if(i==0||j==0){
                    table[i][j]=0;
                }else if(charA[i]==charB[j]){//x(i)=y(i)
                   if(i==0){
                       table[i][j]=table[0][j-1]+1;
                   }else{
                       table[i][j]=table[i-1][j-1]+1;
                   }
                }else{//x(i)!=y(i)
                    if(i==0){
                        table[i][j]=table[0][j-1];
                    }else{
                        if(table[i-1][j]>table[i][j-1]){
                            table[i][j]=table[i-1][j];
                        }else{
                            table[i][j]=table[i][j-1];
                        }
                    }
                }
            }
        }
        return table[charA.length-1][charB.length-1];
    }


    public static void main(String[] args) {
        int lcs = getLcs("dynamicprogrammingmethod ", "Jesusohadfds");//ynami
        System.out.println(lcs);
    }
}
