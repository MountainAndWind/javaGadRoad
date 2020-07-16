package Arithmetic.exhaustion;

/**
 * @description:穷举法 泊松分酒
 * 有三个酒杯 容量分别是12  8  5  。目标怎样倒出6的方法
 * 思路  指定倒酒的规则
 *       规则1  一号杯——》二号杯——》三号杯——》一号杯
 *       规则2  当二号杯为空的时候就往里面倒
 * @author: slfang
 * @time: 2020/5/21 16:29
 */
public class ShareWine {
    private int b1 = 12;
    private int b2 = 8;
    private int b3 = 5;
    private int m = 6;//目标酒量
    //假设一开始12,0,0
    void find(int bb1,int bb2,int bb3){
        System.out.println("一号杯当前容量：："+bb1);
        System.out.println("二号杯当前容量：："+bb2);
        System.out.println("三号杯当前容量：："+bb3);

        if(bb1==m||bb2==m||bb3==m){
            System.out.println("查找到了");
            return;
        }

        if(bb2==0){//给二号杯倒酒
            if(bb1>b2){//bb1超过b2的size
                find(bb1-b2,b2,bb3);
            }else{//bb1不超过b2的size，全部倒入
                find(0,bb1,b3);
            }
        }else if(bb2!=0&&bb3!=b3){//2往3号倒酒
            if(bb2+bb3<=b3){//全部倒入
                find(bb1,0,bb3+bb2);
            }else{
                find(bb1,bb2-(b3-bb3),b3);
            }
        }else if(bb3==b3){//如果3满的话  向1倒
            if(bb1+bb3>b1){//
                find(b1, bb2, bb3-(b1-bb1));
            }else{
                find(bb1+bb3, bb2, 0);
            }
        }
    }

    public static void main(String[] args) {
        ShareWine wine = new ShareWine();
        wine.find(12,0,0);
    }

}
