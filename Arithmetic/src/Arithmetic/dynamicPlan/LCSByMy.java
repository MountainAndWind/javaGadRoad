package Arithmetic.dynamicPlan;

/**
 * @description:求出最长子序列
 * @author: slfang
 * @time: 2020/5/26 22:36
 */
public class LCSByMy {

    /**
     * 获取最长子序列  示意图见有道云笔记
     * 思想：
     *    用i  与 j 分别表示两个字符的长度
     *            \-  0                      若i=0或j=0
     *    c[i,j] =\-  C[i-1,j-1]+1           若i,j>0  且  x[i] = y[j]{表示两个字符的最后一个字符相等}
     *            \-  max{c[i,j-1],c[i-1,j]} 若i,j>0  且  x[i] = y[j]{表示两个字符的最后一个字符不相等}
     *
     * @return
     */
    private static int getLcs(String a,String b){
        //使用二维数组存储
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
