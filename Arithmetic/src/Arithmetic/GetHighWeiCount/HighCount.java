package Arithmetic.GetHighWeiCount;

/**
 * @description:计算高位数
 * @author: slfang
 * @time: 2020/5/30 22:49
 */
public class HighCount {


    public static void main(String[] args) {
        //covertdata("123456".toCharArray(),6);
        getCount("4673","12");
    }

    private static void getCount(String s, String s1) {
        char[] charsA= s.toCharArray();
        char[] charsB = s1.toCharArray();
        int a = charsA.length;
        int b = charsB.length;
        int[] c = new int[a+b+2];

        // 高低位对调
        covertdata(charsA, a);
        covertdata(charsB, b);

        for (int i = 0; i <a; i++) {
            for (int j = 0; j <b ; j++) {
                c[i+j]+=Integer.parseInt(String.valueOf(charsA[i]))*Integer.parseInt(String.valueOf(charsB[j]));
            }
        }

        for (int i = 0; i < c.length; i++) {
            int m = c[i]/10;
            c[i]=c[i]%10;
            if(m>0){
               c[i+1]+=m;
            }
        }


        /*// 找到最高位
        for ( m = c.length - 1; m >= 0;) {//可能存在00007722 此处是去掉前方的0
            if (c[m] > 0)
                break;
            m--;
        }*/
        // 由最高位开始打印乘积
        System.out.print("乘积：");
        for (int n = 0; n <= c.length; n++) {
            System.out.print(c[n]);
        }
        System.out.println("");

    }

    /**
     * 高低位对话
     * @param s
     * @param a
     */
    private static void covertdata(char[] s, int a) {//abcde 01234
        int minddle = a/2;
        for (int i = 0; i < minddle; i++) {
            char temp = s[i];
            s[i]=s[a-i-1];
            s[a-i-1]=temp;
        }
        System.out.println(s);
    }

}
