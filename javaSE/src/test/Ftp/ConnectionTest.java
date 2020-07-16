package test.Ftp;

import test.IO.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ConnectionTest {

    public static void main(String[] args) {

      /*  FtpUtil ftpUtil = new FtpUtil("192.168.100.220",21,"zjzwdxt","Zjz123456","/");

        ftpUtil.downloadFiles("/receivedealing","F:/localFtpTest");*/
        FtpUtilsNew ftpUtils = new FtpUtilsNew("192.168.100.220",21,"zjzwdxt","Zjz123456");
        ftpUtils.downloadFile("/receivedealing","wai_nt20191120101010.xml","F:/localFtpTest");
        ftpUtils.deleteFile("/receivedealing","asdas.xml");
        //(String url, int port,String username, String password, String remotePath,String fileName,String localPath) {
        //FtpUtilsNew ftpUtils = new FtpUtilsNew("192.168.100.220",21,"zjzwdxt","Zjz123456");
        /*FtpUtil.downFile("192.168.100.220",21,"zjzwdxt","Zjz123456",
                "/receivedealing","wai_nt20191120101010.xml","F:/localFtpTest");*/
        /*System.out.println("安徽省大");
        ftpUtils.initFtpClient();
        ftpUtils.downloadFile("/receivedealing","wai_nt20191120101010.xml","F:/localFtpTest");*/
    }

}
