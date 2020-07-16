package test;

import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description:
 * @author: slfang
 * @time: 2020/4/27 15:53
 */
public class PasswordUtils {
    public static void main(String[] args) {
//MjkyODAw
        String mjkyODAw = decode("MjkyODAw");
        System.out.println(""+mjkyODAw);
        String s = stringToMD5(mjkyODAw);
        System.out.println("md5::"+s);
    }

    /**
     * @Description base64解码
     * @param
     * @return
     */
    public static String decode(String base64Str){
        String str = "";
        byte[] base64Data = DatatypeConverter.parseBase64Binary(base64Str);
        try{
            str = new String(base64Data,"utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }

    /**
     * md5加密
     * @param plainText
     * @return
     */
    public static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
