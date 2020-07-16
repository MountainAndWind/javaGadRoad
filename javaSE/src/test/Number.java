package test;

import javax.xml.transform.Source;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * @ClassName
 * @Description TODO
 * @Author fangshilei
 * @Date 2019/10/15 14:52
 * @Version 1.0
 **/
public class Number {

    public static void main(String[] args) {
        double a=10000000.00;
        double b =1600000.00 ;
        double c =7116186.00;

        BigDecimal a1 = new BigDecimal(a+"");
        BigDecimal c1 = new BigDecimal(c+"");
        System.out.println(a1.add(c1).toString());
        System.out.println(sum(a,b));
    }

    /**
     * double 相加
     * @param d1
     * @param d2
     * @return
     */
    public static BigDecimal sum(double d1,double d2){
        BigDecimal bd1 = new BigDecimal(d1+"");
        BigDecimal bd2 = new BigDecimal(d2+"");
        return bd1.add(bd2);
    }

    private static String HanDigiStr[] = new String[] { "零", "壹", "贰", "叁", "肆", "伍",
            "陆", "柒", "捌", "玖" };

    private static String HanDiviStr[] = new String[] { "", "拾", "佰", "仟", "万", "拾",
            "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰",
            "仟", "万", "拾", "佰", "仟" };

    /**
     *
     * @param NumStr 输入字符串必须正整数，只允许前导空格(必须右对齐)，不宜有前导零
     * @return
     */
    static String PositiveIntegerToHanStr(String NumStr) {
        String RMBStr = "";
        boolean lastzero = false;
        boolean hasvalue = false; // 亿、万进位前有数值标记
        int len, n;
        len = NumStr.length();
        if (len > 15)
            return "数值过大!";
        for (int i = len - 1; i >= 0; i--) {
            if (NumStr.charAt(len - i - 1) == ' ')
                continue;
            n = NumStr.charAt(len - i - 1) - '0';
            if (n < 0 || n > 9)
                return "输入含非数字字符!";

            if (n != 0) {
                if (lastzero)
                    RMBStr += HanDigiStr[0]; // 若干零后若跟非零值，只显示一个零
                // 除了亿万前的零不带到后面
                // if( !( n==1 && (i%4)==1 && (lastzero || i==len-1) ) ) //
                // 如十进位前有零也不发壹音用此行
                if (!(n == 1 && (i % 4) == 1 && i == len - 1)) // 十进位处于第一位不发壹音
                    RMBStr += HanDigiStr[n];
                RMBStr += HanDiviStr[i]; // 非零值后加进位，个位为空
                hasvalue = true; // 置万进位前有值标记

            } else {
                if ((i % 8) == 0 || ((i % 8) == 4 && hasvalue)) // 亿万之间必须有非零值方显示万
                    RMBStr += HanDiviStr[i]; // “亿”或“万”
            }
            if (i % 8 == 0)
                hasvalue = false; // 万进位前有值标记逢亿复位
            lastzero = (n == 0) && (i % 4 != 0);
        }

        if (RMBStr.length() == 0)
            return HanDigiStr[0]; // 输入空字符或"0"，返回"零"
        return RMBStr;
    }
}
