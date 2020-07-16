package Arithmetic.tanxin;

/**
 * @description:背包算法
 * @author: slfang
 * @time: 2020/5/24 20:33
 */
public class Backpack {
    private int  MAX_WEIGHT = 20;
    private int[] weights = new int[]{35,30,60,50,40,10,25};
    private int[] values = new int[]{10,40,30,50,35,40,30};

    public static void main(String[] args) {
        Backpack backpack = new Backpack();
        backpack.excuteBackPack();
    }

    private void excuteBackPack() {
        double [] singValue = new double[values.length];
        int [] index = new int[weights.length];//按性价比排序物品的下标

        for (int i = 0; i <values.length ; i++) {
            singValue[i]=(double) values[i]/weights[i];
            index[i] = i;//默认排序
        }

        for (int i = 0; i <singValue.length ; i++) {
            int max = i;
            for (int j = i; j < singValue.length; j++) {
                if(singValue[max]<singValue[j]){
                    max=j;
                }
            }
            if(max!=i){
                //exchange(singValue[max],singValue[i]);
                double temp1 = singValue[i];//
                singValue[i] = singValue[max];
                singValue[max] = temp1;

                int temp = index[i];
                index[i]=index[max];
                index[max] = temp;
            }
        }
        System.out.println("排序后index::"+index);
        System.out.println("排序后singValues::"+singValue);
        //index 5  1  6  3  4  2  0
        int maxValue = 0;
        for (int i = 0; i <index.length ; i++) {
            if(MAX_WEIGHT>weights[index[i]]){
                System.out.println("装入体重为："+weights[index[i]]);
                maxValue+=weights[index[i]]*singValue[i];
                MAX_WEIGHT=MAX_WEIGHT-weights[i];
            }
        }
        System.out.println("装出最大价值："+maxValue);
    }

    private void exchange(double i, double i1) {
        double temp = i;
        i =i1;
        i1=temp;
    }

}
