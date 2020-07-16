package Arithmetic.divideRule;

/**
 * @description:分治实现比赛排期
 * 图片数据见有道云笔记
 * @author: slfang
 * @time: 2020/5/26 11:17
 */
public class SportsScheduleByMy {
    /*（1）二分搜索
    （2）大整数乘法
     （3）Strassen矩阵乘法
    （4）棋盘覆盖
    （5）合并排序
    （6）快速排序
    （7）线性时间选择
    （8）最接近点对问题
    （9）循环赛日程表
    （10）汉诺塔*/

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
     * 排期
     * @param table
     * @param n
     */
    private static void shcedule(int[][] table, int n) {
         if(n==1){
             table[0][0]=1;
         }else{
             int m = n/2;
             //左上部分
             shcedule(table,m);
             //右上部分
             for (int i = 0; i < m; i++) {
                 for (int j = m; j < n; j++) {
                     table[i][j]=table[i][j-m]+m;
                 }
             }
             //左下
             for (int i = m; i < n; i++) {
                 for (int j = 0; j <m; j++) {
                     table[i][j]=table[i-m][j]+m;
                 }
             }
             //右下
             for (int i = m; i <n ; i++) {
                 for (int j = m; j < n; j++) {
                     table[i][j]=table[i-m][j-m];
                 }
             }
         }

    }
}
