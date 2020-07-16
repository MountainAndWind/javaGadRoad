package test.xml.push;

import test.xml.FtpUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: slfang
 * @time: 2020/5/9 10:17
 */
public class PushTest1 {

    public static void main(String[] args) throws Exception {
        test1();
        //test2();
    }
    static  void test2(){
        FtpUtils ftpUtils = new FtpUtils
                ("192.168.253.9",21,"1950475445@qq.com","xxfsl1314");
        ftpUtils.initFtpClient();
        String path="C:\\Users\\19504\\Desktop\\测试文件.xml";
        ftpUtils.uploadFile("/receivedealing","哈哈哈萨达函数的啊.xml",path);
    }

    static void test1() throws IOException {
        // FtpUtilsNew("192.168.199.169",21,"1950475445@qq.com","xxfsl1314");
        FtpUtils ftpUtils = new FtpUtils
                ("192.168.253.9",21,"1950475445@qq.com","xxfsl1314");
        ftpUtils.initFtpClient();

        String s1 = "1sda大大大啊的.xml";
        StringBuilder builder = new StringBuilder();
       /* builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        builder.append("<QUALITY_SEND><data_content><row id=\"1\">\n");
        builder.append("<row id=1><BJBH>A121啊多大啊打 da</BJBH>\n");
        builder.append("<QFPDRS>爱答啊231231</QFPDRS>\n");
        builder.append("</row>\n");
        builder.append("</row></data_content></QUALITY_SEND>\n");*/

        /*String str="<?xml version=\"1.0\" encoding=\"UTF-8\"?><QUALITY_INNERPLATCOUNT>\n" +
                "<data_content>\n" +
                "<row id=\"1\">\n" +
                "<BJBH>dada</BJBH>\n" +
                "<QFPDRS></QFPDRS>\n" +
                "<CGMC>艾迪莎</CGMC>\n" +
                "<GGZH>1231打赏爱答</GGZH>\n" +
                "</row>\n" +
                "</data_content>\n" +
                "<content_check>\n" +
                "<rowcount>1</rowcount>\n" +
                "</content_check>\n" +
                "</QUALITY_INNERPLATCOUNT>";
*/

       /*String str ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
       str=str+"<QUALITY_SEND><data_content>\n";
       str=str+"<row id=1><BJBH>A121啊多大啊打 da</BJBH>\n";
       str=str+"<GGZH>131</GGZH>\n";
       str=str+"<CGMC>啊打算打</CGMC>\n";
       str=str+"<QFPDRS>爱答啊231231</QFPDRS>\n";
       str=str+"</row></data_content></QUALITY_SEND>\n";*/


        String str ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        str=str+"<QUALITY_SEND><data_content><row>\n";
        Map<String, String> fieldsValueByArr = new HashMap();
        fieldsValueByArr.put("BJBH","啊大大");
        fieldsValueByArr.put("QFPDRS","阿德飒1231");
        fieldsValueByArr.put("CGMC","dasd123大大 ");
        fieldsValueByArr.put("GGZH","31231ADsd");
        Set<Map.Entry<String, String>> entries = fieldsValueByArr.entrySet();
        for (Map.Entry<String, String> entry : entries) {
          str=str+"<"+entry.getKey()+">"+entry.getValue()+"</"+entry.getKey()+">"+"\n";
        }

        str=str+"</row></data_content></QUALITY_SEND>\n";

        InputStream stringStream = getStringStream(str);
        ftpUtils.uploadFile("/receivedealing",s1,stringStream);
    }

    public static InputStream getStringStream(String sInputString){
        if (sInputString != null && !sInputString.trim().equals("")){
            try{
                ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
                return tInputStringStream;
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @param str 目标字符串
     * @param oldCharset
     * @param changeCharset
     * @throws IOException
     */
    private static   String transCode(String oldCharset,String changeCharset,String str) throws IOException {
        String gbk= new String(str.getBytes( changeCharset));
        String unicode = new String(gbk.getBytes(),changeCharset);
        String utf8= new String(unicode.getBytes(oldCharset));
        return utf8;
    }
}
