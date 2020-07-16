//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package test2;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class SmbFileUtil {

    public SmbFileUtil() {
    }

    public static void main(String[] args) throws Exception {
        String remark = "<p style=\"font-family:&#39;΢���ź�&#39;,&#39;Microsoft YaHei&#39;;font-size:12px;\"><span style=\"font-size: 12px; font-family: ΢���ź�;\">���Բ���</span><br/></p>";
        System.out.println(remark.replace("<br/>", ""));
    }


    public static void smbGet(String remoteUrl, String localDir) {
        InputStream in = null;
        BufferedOutputStream out = null;

        try {
            SmbFile remoteFile = new SmbFile(remoteUrl);
            if (remoteFile != null) {
                String fileName = remoteFile.getName();
                File localFile = new File(localDir + File.separator + fileName);
                in = new BufferedInputStream(new SmbFileInputStream(remoteFile));
                out = new BufferedOutputStream(new FileOutputStream(localFile));

                for(byte[] buffer = new byte[1024]; in.read(buffer) != -1; buffer = new byte[1024]) {
                    out.write(buffer);
                }

                return;
            }

            System.out.println("�����ļ�������");
        } catch (Exception var17) {
            var17.printStackTrace();
            return;
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException var16) {
                var16.printStackTrace();
            }

        }

    }

    public static void smbPut(String remoteUrl, String localFilePath) {
        InputStream in = null;
        BufferedOutputStream out = null;

        try {
            File localFile = new File(localFilePath);
            String fileName = localFile.getName();
            SmbFile remoteFile = new SmbFile(remoteUrl + "/" + fileName);
            in = new BufferedInputStream(new FileInputStream(localFile));
            out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));

            for(byte[] buffer = new byte[1024]; in.read(buffer) != -1; buffer = new byte[1024]) {
                out.write(buffer);
            }
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException var15) {
                var15.printStackTrace();
            }

        }

    }

    public static String smbPutFile(String remoteUrl, String data, String fileName) {
        InputStream in = null;
        BufferedOutputStream out = null;

        try {
            SmbFile remoteFile = new SmbFile(remoteUrl + "/" + fileName);
            in = new BufferedInputStream(new ByteArrayInputStream(data.getBytes("utf-8")));
            out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));

            for(byte[] buffer = new byte[1024]; in.read(buffer) != -1; buffer = new byte[1024]) {
                out.write(buffer);
            }
        } catch (Exception var15) {
            System.out.println(var15.getMessage());
            var15.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }

                if (in != null) {
                    in.close();
                }
            } catch (IOException var14) {
                System.out.println(var14.getMessage());
                var14.printStackTrace();
            }

        }

        return remoteUrl + "/" + fileName;
    }


    public static List getSharedFileList(String remoteUrl) {
        ArrayList fileNameList = new ArrayList();

        try {
            SmbFile smbFile = new SmbFile(remoteUrl);
            if (!smbFile.exists()) {
                System.out.println("Ŀ¼�����ڣ�remoteUrl=" + remoteUrl);
            } else {
                SmbFile[] files = smbFile.listFiles();
                SmbFile[] var4 = files;
                int var5 = files.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    SmbFile f = var4[var6];
                    fileNameList.add(f.getName());
                }
            }
        } catch (MalformedURLException var8) {
            System.out.println(var8.getMessage());
        } catch (SmbException var9) {
            System.out.println(var9.getMessage());
        }

        return fileNameList;
    }

    public static String getSmbXMLContent(String remoteUrl) {
        InputStream in = null;
        OutputStream out = null;
        String xmlContent = "";

        String var5;
        try {
            SmbFile remoteFile = new SmbFile(remoteUrl);
            if (remoteFile != null) {
                in = new BufferedInputStream(new SmbFileInputStream(remoteFile));
                xmlContent = InputStream2String(in, "");
                return xmlContent;
            }

            System.out.println("�����ļ�������-remoteUrl=" + remoteUrl);
            var5 = "";
        } catch (Exception var16) {
            System.out.println(var16.getMessage() );
            return xmlContent;
        } finally {
            try {
                in.close();
            } catch (IOException var15) {
                System.out.println(var15.getMessage());
            }

        }

        return var5;
    }

    public static String InputStream2String(InputStream in, String encode) throws Exception {
        StringBuffer bf = new StringBuffer();
        if (encode == null || encode.equalsIgnoreCase("")) {
            encode = "utf-8";
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(in, encode));
        String temps = "";

        while((temps = br.readLine()) != null) {
            bf.append(temps);
        }

        return bf.toString();
    }
}
