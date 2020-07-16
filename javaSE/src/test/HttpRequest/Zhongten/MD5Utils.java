package test.HttpRequest.Zhongten;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import java.security.MessageDigest;

/**
 * @description:
 * @author: slfang
 * @time: 2020/6/15 10:27
 */
public class MD5Utils {

    public static void main(String[] args) {
        String reqStr="<req>" +
                "<head>" +
                "<ALLAMOUNT>200</ALLAMOUNT>" +
                "<ALLCOUNT>1</ALLCOUNT>" +
                "</head>" +
                "<list><detail><SRCSERIALNO>ZJDF20200700026</SRCSERIALNO>" +
                "<SRCNOTECODE>ZJDF20200700026</SRCNOTECODE>" +
                "<ORGCODE>1010101001-0001</ORGCODE>" +
                "<APPLYORGCODE>1010101001-0001</APPLYORGCODE>" +
                "<PAYDATE>2020-07-01</PAYDATE>" +
                "<PAYTYPECODE>zjdf</PAYTYPECODE>" +
                "<SETTLEMENTMODECODE>101</SETTLEMENTMODECODE>" +
                "<PURPOSE>测试</PURPOSE>" +
                "<OURORGCODE>1010101001-0001</OURORGCODE>" +
                "<OURBANKACCOUNTNUMBER>121912205710501</OURBANKACCOUNTNUMBER>" +
                "<OURAMOUNT>200</OURAMOUNT>" +
                "<OURCURCODE>CNY</OURCURCODE>" +
                "<OPPOBJECTNAME>小花网络科技（深圳）有限公司</OPPOBJECTNAME>" +
                "<OPPBANKLOCATIONCODE>104584001645</OPPBANKLOCATIONCODE>" +
                "<OPPBANKLOCATIONS>中国银行股份有限公司深圳前海支行</OPPBANKLOCATIONS>" +
                "<OPPBANKACCOUNTNUMBER>121912205710501</OPPBANKACCOUNTNUMBER>" +
                "<OPPBANKACCOUNTNAME>小花网络科技（深圳）有限公司</OPPBANKACCOUNTNAME>" +
                "<OPPDIRECTCURCODE>CNY</OPPDIRECTCURCODE>" +
                "<OPPPRIVATEFLAG>1</OPPPRIVATEFLAG>" +
                "<CREATEDBY>002095</CREATEDBY>" +
                "<LASTMODIFIEDBY>002095</LASTMODIFIEDBY>" +
                "<CAPITALCATEGORYCODE></CAPITALCATEGORYCODE>" +
                "<ISURGENT>0</ISURGENT>"+
                "</detail>" +
                "</list>" +
                "</req>";

        String sqlt = "FW2020";
        String md5Value = MD5Utils.md5String(reqStr,sqlt);
        System.out.println(md5Value);
    }

    /**
     * 对字符串进行md5加密
     * @param md5String
     * @param md5Key
     * @return md5加密后的密文
     */
    public static String md5String(String md5String,String md5Key){
        String md5Value = MD5Encoder(md5String + md5Key);
        return md5Value;
    }
    public final static String MD5Encoder(String s) {
        return MD5Encoder(s,"utf-8");
    }

    public final static String MD5Encoder(String s, String charset) {
        try {
            byte[] btInput = s.getBytes(charset);
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < md.length; i++) {
                int val = ((int) md[i]) & 0xff;
                if (val < 16){
                    sb.append("0");
                }
                sb.append(Integer.toHexString(val));
            }
            return sb.toString();
        } catch (Exception e) {
            System.out.println("MD5Encoder异常：" + e.getMessage());
            return null;
        }
    }
}
