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
     * ��װXml����
     * @param baseInfo
     */
    private static void xmlPush(Map<String, Object> baseInfo) throws Exception {
        String bwxml="";//����xml
        String smbUrl="";//���͵�ַ
        String xmlFileName="";//�����ļ�����
        String filePre="";
        String PUSH_ROW_CHILD_XML_NAME="";//����xml��ǩ��
        /*����׼��*/
        String str =baseInfo.get("xmlHeader")+"\n";
        str=str+"<QUALITY_SEND><data_content><row id=\"1\">\n";
        str=str+"<row id=1><BJBH>A121����󰡴� da</BJBH>\n";
        str=str+"<GGZH>131</GGZH>\n";
        str=str+"<CGMC>�������</CGMC>\n";
        str=str+"<QFPDRS>����231231</QFPDRS>\n";
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
        int b=bytes.length;   //���ֽڵĳ��ȣ������ַ����ĳ���
        FileOutputStream fos=new FileOutputStream(txt);
        fos.write(bytes,0,b);
        fos.write(bytes);
        fos.close();
    }

    //��һ���ַ���ת��Ϊ������
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
