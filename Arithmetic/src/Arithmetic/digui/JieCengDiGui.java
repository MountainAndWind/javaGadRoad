package Arithmetic.digui;

/**
 * @description:½×²ãµÝ¹é
 * @author: slfang
 * @time: 2020/5/21 17:22
 */
public class JieCengDiGui {


    static int getJieValue(int n){
        if(n==1){
          return 1;
        }else{
          return n*getJieValue(n-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(getJieValue(2));
        System.out.println(getJieValue(3));
        System.out.println(getJieValue(4));
        System.out.println(getJieValue(5));
        System.out.println(getJieValue(6));
    }
}
