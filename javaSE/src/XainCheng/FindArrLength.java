package XainCheng;

import java.util.Arrays;
import java.util.List;

/**
 * @description  使用多线程计算数组的长度
 * @Author slfang
 * @Time 2019/3/27 20:30
 * @Version 1.0
 **/
public class FindArrLength {

    public static void main(String[] args) {
         String s ="sdasdasdasdasdasdashakjsdhlasjdjasdlka;sdlk;asjdalsfkajsldjasjjalfjalsjd;lasjdkaskdasj;fjasf;ajs;fja;sljf;a;dasljdaskd;ka;sdkakdasldk;alsk;laks;fka;lsfk;askd;kd;k;sdksak;dask;dl;askd;laksdl;kas;ldk;askd;aks;dlka;sdk;askd;kas;dk;askd;lsak;lfkas;fk;askf;askf;ask;fadks;lk;fksf";
        int length = s.length();
        List<String> strings = Arrays.asList(s);
        strings.size();
    }
}
/**
* @Description
* @Author slfang
* @Date  2019年03月27日 20:31:05
* @Param 
* @return
**/
class ArrThread implements Runnable{

    @Override
    public void run() {
        
    }
}
