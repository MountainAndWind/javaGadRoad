package test.Ftp;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ConnectionTest2 {

    public static void main(String[] args) {

        FtpUtilsNew ftpUtils = new FtpUtilsNew("192.168.199.169",21,"1950475445@qq.com","xxfsl1314");
        System.out.println("安徽省大");
        ftpUtils.initFtpClient();
        ftpUtils.downloadFileByDir("/","F:/localFtpTest");
        /*String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><QUALITY_SEND><data_content><row id=\"1\"><HTMC>大阿打算</HTMC><CGMC>大阿打算</CGMC><WTLXR>大阿打算</WTLXR><LXDH>null</LXDH><FXSJ>null</FXSJ><XMZT>阿萨德</XMZT></row></data_content></QUALITY_SEND>";
        InputStream stringStream = getStringStream(xml);
        ftpUtils.uploadFile("/receivedealing","test",stringStream);*/

      /*  String s = FileUtils.readFileToString("F:\\TEST.xml");
        InputStream stringStream1 = getStringStream(s);
        ftpUtils.uploadFile("/ftpTest","test2.xml",stringStream1);*/
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
