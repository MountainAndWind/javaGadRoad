package Arithmetic.digui;

/**
 * @description:ŷ����¼������Լ��
 * @author: slfang
 * @time: 2020/5/21 15:41
 */
public class GCD {

    public static int getMaxYuShu(int m,int n){
        if(n==0){
           return m;
        }else{
            return getMaxYuShu(n,m%n);
        }
    }

    public static void main(String[] args) {
        int maxYuShu = getMaxYuShu(100, 30);
        System.out.println(maxYuShu);
    }
}
