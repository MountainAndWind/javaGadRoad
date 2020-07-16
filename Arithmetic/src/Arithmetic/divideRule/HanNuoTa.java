package Arithmetic.divideRule;

/**
 * @description:哈诺塔算法
 * 思想：三根柱子，第一根柱子上有方块且从上到下是一次增大的。现在借助中间柱子到第三根柱子，且也是从上到下是一次增大的
 * @author: slfang
 * @time: 2020/5/21 15:41
 */
public class HanNuoTa {
    private int i=1;

    /**
     *
     * @param n  柱1上的方块数
     * @param from
     * @param dependOn
     * @param to
     */
    public void create(int n,String from,String dependOn,String to){
        if(n==1){
            move(1,from,to);
        }else{
            create(n-1,from,to,dependOn);
            move(n,from,to);
            create(n-1,dependOn,from,to);
        }
    }

    private void move(int n, String from, String to) {
        System.out.println("第"+i+++"步,将盘子"+from+"------>"+to);
    }


    public static void main(String[] args) {
        HanNuoTa hanNuoTa = new HanNuoTa();
        hanNuoTa.create(4,"A","B","C");
    }
}
