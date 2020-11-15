//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.neusoft.talentbase.webservice.mainDataInterface.service;

import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import org.apache.axis.encoding.Base64;

public class DESUtil {
    public static final String KEY_ALGORITHM = "DES";
    public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
    public static final String key = "LlRCrnfSg9fDekM0KV+rkA==";

    public DESUtil() {
    }

    private static SecretKey keyGenerator(String keyStr) throws Exception {
        byte[] input = HexString2Bytes(keyStr);
        DESKeySpec desKey = new DESKeySpec(input);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(desKey);
        return securekey;
    }

    private static int parse(char c) {
        if (c >= 'a') {
            return c - 97 + 10 & 15;
        } else {
            return c >= 'A' ? c - 65 + 10 & 15 : c - 48 & 15;
        }
    }

    public static byte[] HexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;

        for(int i = 0; i < b.length; ++i) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte)(parse(c0) << 4 | parse(c1));
        }

        return b;
    }

    public static String encrypt(String data) throws Exception {
        Key deskey = keyGenerator("LlRCrnfSg9fDekM0KV+rkA==");
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        SecureRandom random = new SecureRandom();
        cipher.init(1, deskey, random);
        byte[] results = cipher.doFinal(data.getBytes());

        for(int i = 0; i < results.length; ++i) {
            System.out.print(results[i] + " ");
        }

        System.out.println();
        return Base64.encode(results);
    }

    public static String decrypt(String data) throws Exception {
        Key deskey = keyGenerator("LlRCrnfSg9fDekM0KV+rkA==");
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(2, deskey);
        return new String(cipher.doFinal(Base64.decode(data)));
    }

    public static void main(String[] args) throws Exception {
        System.out.println(decrypt("qQmTf/Rmk7UaF7wjbaPT1dyaaOVFnRuCZA8UHFj6OOt6g1UCqgKUl1f/wj6qKDGdP9EA8CWYGBaP3+WPb5zNGaPE1RUce2vA087JH8ymzxPhxtFhKRR7J0gBxw6fV42/nP9LuOeBOZGCey3RU3NfWA=="));
    }
}
