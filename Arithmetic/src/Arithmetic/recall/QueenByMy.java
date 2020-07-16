package Arithmetic.recall;

/**
 * @description:八皇后回溯算法
 *              一个棋盘中放皇后
 *              查找八皇后的方法
 *              圆圈代表皇后
 *              皇后所在位置上下已经正反斜对角不能放皇后
 * @author: slfang
 * @time: 2020/5/31 18:19
 */
public class QueenByMy {
    public static int num = 0;//累计方案
    private static final int MAX_SISE=8;

    /**
     * 棋盘二维数组
     */
    private static int[][] QUEEN=new int[MAX_SISE][MAX_SISE];


    /**
     * 记录每一列皇后摆放的位置
     */
    private static int[] COLS = new int[MAX_SISE];

    /*
    * 要在8*8的国际象棋棋盘中放8个皇后，
    * 使任意两个皇后都不能互相吃掉。
    * 规则是皇后能吃掉同一行、同一列、
    * 同一对角线的棋子。
    * 统计共有多少种方案
    * */

    public static void main(String[] args) {
        getQueenCount(0);
    }

    private static void getQueenCount(int n) {
        /*
        * 找到可以放的位置，设置一个数组存入下标是否能放的信息，用true（表示不能放入）  与false 表示（能放入）
        * */
        boolean[] isLegal =new boolean[MAX_SISE];


        for (int j = 0; j <n ; j++) {//当前列中那些能存在那些不能存放  然后记录再isLegal 数组中 true 表示不能放，false表示能放
            isLegal[COLS[j]]=true;//同一行设置为true;

            //以下代码是为了计算斜对角的位置  思想是可以理解计算斜率
            int d = n-j;//计算差值
            int index = COLS[j];
            //正斜方向
            if(index-d>=0){
                isLegal[index-d]=true;
            }
            //反斜方向
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
                    System.out.println("找出一种排序方式");
                    num++;
                    printQueen(num);
                }
            }
        }
    }

    private static void printQueen(int num) {
        System.out.println("第"+num+"排序方式");
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
