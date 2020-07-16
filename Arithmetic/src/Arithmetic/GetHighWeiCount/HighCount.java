package Arithmetic.GetHighWeiCount;

/**
 * @description:�����λ��
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

        // �ߵ�λ�Ե�
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


        /*// �ҵ����λ
        for ( m = c.length - 1; m >= 0;) {//���ܴ���00007722 �˴���ȥ��ǰ����0
            if (c[m] > 0)
                break;
            m--;
        }*/
        // �����λ��ʼ��ӡ�˻�
        System.out.print("�˻���");
        for (int n = 0; n <= c.length; n++) {
            System.out.print(c[n]);
        }
        System.out.println("");

    }

    /**
     * �ߵ�λ�Ի�
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
