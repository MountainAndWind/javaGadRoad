package test2.share;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

import java.io.*;

/**  
 * All rights Reserved, Designed By www.weaver.com
 * @Title:  ShareDirUtils.java   
 * @Package util   
 * @Description:�ϴ��ļ�������Ŀ¼
 * @author: zhiwen.qiu    
 * @date:   2019��12��23�� ����4:29:31   
 * @version V1.0 
 * @Copyright: 2019 www.weaver.com Inc. All rights reserved. 
 * ע�⣺�����ݽ������Ϻ���΢����Ƽ��ɷ����޹�˾�ڲ����ģ���ֹ��й�Լ�������������ҵĿ��
 */
public class ShareDirUtils {
	

	// �����ļ������ڷ�����ip
    private String USER_DOMAIN = "";
    //�����û�
    private String USER_ACCOUNT = "";
    //��������
    private String USER_PWS = "";
    //�ֽڳ���
    private static final int byteLen = 1024;
    
    public ShareDirUtils(String uSER_DOMAIN, String uSER_ACCOUNT, String uSER_PWS) {
		USER_DOMAIN = uSER_DOMAIN;
		USER_ACCOUNT = uSER_ACCOUNT;
		USER_PWS = uSER_PWS;
	}
    
	/**
     * 
     * @Title smbPut
     * @Description ����Ŀ¼�ϴ��ļ�
     * @Param shareDirectory ����Ŀ¼
     * @Param localFilePath ����Ŀ¼�е��ļ�·��
     * @date 2019-01-10 20:16
     */
    public void smbPut(String shareDirectory, String filePath) {
    	System.out.println("ShareDirUtils-->smbPut");
    	System.out.println("ShareDirUtils-->smbPut-->shareDirectory::"+shareDirectory);
    	System.out.println("ShareDirUtils-->smbPut-->filePath::"+filePath);
        InputStream in = null;
        OutputStream out = null;
        try {
        	File localFile=new File(filePath);
            String fileName = localFile.getName();
            // ���������֤
            NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(USER_DOMAIN, USER_ACCOUNT,
                    USER_PWS);
            System.out.println("ShareDirUtils-->fileName::"+fileName);
            System.out.println("ShareDirUtils-->filePath::"+shareDirectory + fileName);
            SmbFile remoteFile = new SmbFile(shareDirectory, auth);
//            SmbFile remoteFile = new SmbFile(shareDirectory + fileName); 
            System.out.println("ShareDirUtils-->remoteFile::"+remoteFile);
            remoteFile.connect();
            System.out.println("ShareDirUtils-->connect-->success");
            in = new BufferedInputStream(new FileInputStream(localFile));
            System.out.println("ShareDirUtils-->in::"+in);
            out = new BufferedOutputStream(new SmbFileOutputStream(shareDirectory + fileName));
            System.out.println("ShareDirUtils-->out::"+out);
            byte[] buffer = new byte[byteLen];
            while (in.read(buffer) != -1) {
                out.write(buffer);
                buffer = new byte[byteLen];
            }
            out.flush();
            System.out.println("smbPut-->out.flush.....");
        } catch (Exception e) {
        	System.out.println("smbPut-->exception::"+e);
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
            	System.out.println("smbPut-->close-->exception::"+e.getMessage());
            }
        }
    }

}
