package test.xml.push;

import ln.TimeUtil;
import test.xml.FtpUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: slfang
 * @time: 2020/5/9 17:04
 */
public class PushTest2 {

    public static void main(String[] args) throws Exception {
        HashMap baseInfo = new HashMap();
        baseInfo.put("serverip","192.168.43.179");
        baseInfo.put("port","21");
        baseInfo.put("username","1950475445@qq.com");
        baseInfo.put("pwd","xxfsl1314");
        baseInfo.put("xmlHeader","<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        baseInfo.put("pwd","xxfsl1314");
        baseInfo.put("webpath_Input","/ftpTest");
        xmlPush(baseInfo);
    }

    /**
     * 封装Xml推送
     * @param baseInfo
     */
    private static void xmlPush(Map<String, Object> baseInfo) throws Exception {
        String bwxml="";//推送xml
        String smbUrl="";//推送地址
        String xmlFileName="";//生成文件名称
        String filePre="";
        String PUSH_ROW_CHILD_XML_NAME="";//推送xml标签名
        /*数据准备*/
        String str =baseInfo.get("xmlHeader")+"\n";
        str=str+"<QUALITY_SEND><data_content><row id=\"1\">\n";
        str=str+"<row id=1><BJBH>A121啊多大啊打 da</BJBH>\n";
        str=str+"<GGZH>131</GGZH>\n";
        str=str+"<CGMC>啊打算打</CGMC>\n";
        str=str+"<QFPDRS>爱答啊231231</QFPDRS>\n";
        str=str+"</row>\n";
        str=str+"</row></data_content></QUALITY_SEND>\n";

        writeStringToFile(str);
    }

    public static void writeStringToFile(String str) throws IOException {
        File txt=new File("E:\\a.xml");
        if(!txt.exists()){
            txt.createNewFile();
        }
        byte bytes[]=new byte[512];
        bytes=str.getBytes();
        int b=bytes.length;   //是字节的长度，不是字符串的长度
        FileOutputStream fos=new FileOutputStream(txt);
        fos.write(bytes,0,b);
        fos.write(bytes);
        fos.close();
    }

    //将一个字符串转化为输入流
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
}
