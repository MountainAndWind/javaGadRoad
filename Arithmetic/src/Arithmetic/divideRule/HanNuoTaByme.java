package Arithmetic.divideRule;

/**
 * @description:汉诺塔实现
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
        //三根柱子   A    B    C
        //如果是一个圆环  直接移动到C
        //如果是两个圆环  先移动到A到B   再移动A到C  再移动B到C
        //如果是三个圆环  先移动A到C   A到B  C到B   A到C  B到A   B到C  A到C
        if(i==1){//如果只有一个的话，直接移动到目的柱子
            move(1, from, des);
        }else{
            excute(i-1,from,des,depand);
            move(i,from,des);
            excute(i-1,depand,from,des);
        }

    }

    private void move(int i, String from, String des) {
        System.out.println("第一步："+index+++" 从"+from+"到"+des);
    }
}
