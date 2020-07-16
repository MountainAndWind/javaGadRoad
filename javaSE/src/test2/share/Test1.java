package test2.share;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

import java.io.*;

/**
 * @ClassName
 * @Description TODO
 * @Author fangshilei
 * @Date 2020/1/8 17:23
 * @Version 1.0
 **/
public class Test1 {

    public static void main(String[] args) {
        ShareDirUtils shareDirUtils = new ShareDirUtils("127.0.0.1","administrator","123");
        //String smbUrl = "smb://" + baseInfo.get("username") + ":" + baseInfo.get("pwd") + "@" + baseInfo.get("serverip") + baseInfo.get("webpath_Input");
        shareDirUtils.smbPut("smb://127.0.0.1" +"/xmlPushTest","E:\\tomcat\\apache-tomcat-7.0.55\\RUNNING.txt");
        /*ss();*/
    }

    public static void ss(){

        String  xmlFileName ="a.xml";
        String smbUrl = "smb://" + "administrator" + ":" + "123" + "@" +"127.0.0.1" +"/xmlPushTest";
        InputStream in = null;
        BufferedOutputStream out = null;
        System.out.println("*****************************-推送查询xml到查询目录开始-************************************");

        try {
            SmbFile remoteFile = new SmbFile(smbUrl + "/" + xmlFileName);
            in = new BufferedInputStream(new FileInputStream(new File("E:\\tomcat\\apache-tomcat-7.0.55\\RUNNING.txt")));
            out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));

            for(byte[] buffer = new byte[1024]; in.read(buffer) != -1; buffer = new byte[1024]) {
                out.write(buffer);
            }
        } catch (Exception var31) {
            System.out.println("*****************************-推送失败-************************************");
            System.out.println(var31.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }

                if (in != null) {
                    in.close();
                }
            } catch (IOException var30) {
                System.out.println("*****************************-推送失败-************************************");
                System.out.println(var30.getMessage());
            }

        }

        System.out.println("*****************************-推送查询xml到查询目录结束-************************************");
    }
}
