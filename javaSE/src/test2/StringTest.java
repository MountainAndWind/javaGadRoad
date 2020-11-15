package test2;

import java.io.UnsupportedEncodingException;
public class StringTest {
    /*public static void main(String args[]) throws UnsupportedEncodingException{
        String s = " 强力清除广告，上网更快的浏览器！";
        System.out.println(s);
        System.out.println(convertMoString(s));
    }
    public static synchronized String convertMoString(String sMo) {
        String sReturn = sMo;
        if (sReturn == null) {
            return sReturn;
        }
        try {
            sReturn = sReturn.toUpperCase();
            sReturn = sReturn.replace('，', ',');
            sReturn = sReturn.replace('。', '.');
            sReturn = sReturn.replace('；', ';');
            sReturn = sReturn.replace('！','!');
            sReturn = sReturn.replace('？', '?');
            sReturn = sReturn.replace('：', ':');
            sReturn = sReturn.replace('"', '＂');
            sReturn = sReturn.replace('“', '＂');
            sReturn = sReturn.replace('”', '＂');
            // sReturn = sReturn.replace('-', ' ');
            // sReturn = sReturn.replace('_', ' ');
            sReturn = sReturn.replace('，', ',');
            sReturn = sReturn.replace('0', '0');
            sReturn = sReturn.replace('1', '1');
            sReturn = sReturn.replace('2', '2');
            sReturn = sReturn.replace('3', '3');
            sReturn = sReturn.replace('4', '4');
            sReturn = sReturn.replace('5', '5');
            sReturn = sReturn.replace('6', '6');
            sReturn = sReturn.replace('7', '7');
            sReturn = sReturn.replace('8', '8');
            sReturn = sReturn.replace('9', '9');
            sReturn = sReturn.replace('A', 'A');
            sReturn = sReturn.replace('B', 'B');
            sReturn = sReturn.replace('C', 'C');
            sReturn = sReturn.replace('D', 'D');
            sReturn = sReturn.replace('E', 'E');
            sReturn = sReturn.replace('F', 'F');
            sReturn = sReturn.replace('G', 'G');
            sReturn = sReturn.replace('H', 'H');
            sReturn = sReturn.replace('I', 'I');
            sReturn = sReturn.replace('J', 'J');
            sReturn = sReturn.replace('K', 'K');
            sReturn = sReturn.replace('L', 'L');
            sReturn = sReturn.replace('M', 'M');
            sReturn = sReturn.replace('N', 'N');
            sReturn = sReturn.replace('O', 'O');
            sReturn = sReturn.replace('P', 'P');
            sReturn = sReturn.replace('Q', 'Q');
            sReturn = sReturn.replace('R', 'R');
            sReturn = sReturn.replace('S', 'S');
            sReturn = sReturn.replace('T', 'T');
            sReturn = sReturn.replace('U', 'U');
            sReturn = sReturn.replace('V', 'V');
            sReturn = sReturn.replace('W', 'W');
            sReturn = sReturn.replace('X', 'X');
            sReturn = sReturn.replace('Y', 'Y');
            sReturn = sReturn.replace('Z', 'Z');
            sReturn = strReplace(sReturn, "‘", "'");
        } catch (Exception ex) {
            return sMo;
        }
        return sReturn;
    }

    public static synchronized String strReplace(String sAll, String sOld,String sNew) {
        int iT = 0;
        String sF = null;
        String sH = null;
        *//* 如果新串中包括旧串,不让替多只让替少 *//*
        if (sNew.indexOf(sOld) != -1) {
            return sAll;
        }
        if ((sAll == null) || (sOld == null) || (sNew == null)) {
            return sAll;
        }
        iT = sAll.indexOf(sOld);
        while (iT != -1) {
            sF = sAll.substring(0, iT);
            sH = sAll.substring(iT + sOld.length());
            sAll = sF + sNew + sH;
            iT = sAll.indexOf(sOld);
        }
        return sAll;
    }*/
}