package test2;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.logging.Logger;

/**  
 * All rights Reserved, Designed By www.weaver.com
 * @Title:  FtpUtil.java
 * @Package util   
 * @Description:�ļ��ϴ���FTP������������
 * @author: zhiwen.qiu    
 * @date:   2019��8��30�� ����10:24:32   
 * @version V1.0 
 * @Copyright: 2019 www.weaver.com Inc. All rights reserved. 
 * ע�⣺�����ݽ������Ϻ���΢����Ƽ��ɷ����޹�˾�ڲ����ģ���ֹ��й�Լ�������������ҵĿ��
 */
public class FtpUtils {
    //ftp��������ַ
    private String hostname = "";
    //ftp�������˿ں�Ĭ��Ϊ21
    private Integer port = 21 ;
    //ftp��¼�˺�
    private String username = "";
    //ftp��¼����
    private String password = "";
    
    private FTPClient ftpClient = null;
    
    private Logger log=null;
    
    
    
    
    public FtpUtils(String hostname, Integer port, String username, String password, Logger log) {
		this.hostname = hostname;
		this.port = port;
		this.username = username;
		this.password = password;
		this.log = log;
	}

	/**
     * ��ʼ��ftp������
     */
    public void initFtpClient() {
    	log.info("FtpUtils-->initFtpClient");
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        try {
            System.out.println();
            System.out.println("connecting...ftp������:"+this.hostname+":"+this.port); 
            log.info("connecting...ftp������:"+this.hostname+":"+this.port); 
            ftpClient.connect(hostname, port); //����ftp������
            ftpClient.login(username, password); //��¼ftp������
            int replyCode = ftpClient.getReplyCode(); //�Ƿ�ɹ���¼������
            if(!FTPReply.isPositiveCompletion(replyCode)){
                System.out.println("connect failed...ftp������:"+this.hostname+":"+this.port); 
                log.info("connect failed...ftp������:"+this.hostname+":"+this.port); 
            }
            System.out.println("connect successfu...ftp������:"+this.hostname+":"+this.port); 
            log.info("connect successfu...ftp������:"+this.hostname+":"+this.port); 
        }catch (IOException e) { 
           System.out.println("FtpUtils-->initFtpClient-->exception::"+e);
           log.warning("FtpUtils-->initFtpClient-->exception::"+e);
        } 
    }

    /**
    * �ϴ��ļ�
    * @param pathname ftp���񱣴��ַ
    * @param fileName �ϴ���ftp���ļ���
    *  @param originfilename ���ϴ��ļ������ƣ����Ե�ַ�� * 
    * @return
    */
    @SuppressWarnings("static-access")
	public boolean uploadFile(String pathname, String fileName,String originfilename){
    	log.info("FtpUtils-->uploadFile");
        boolean flag = false;
        InputStream inputStream = null;
        try{
            System.out.println("��ʼ�ϴ��ļ�");
            log.info("��ʼ�ϴ��ļ�");
            inputStream = new FileInputStream(new File(originfilename));
            initFtpClient();
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            CreateDirecroty(pathname);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
            System.out.println("�ϴ��ļ��ɹ�");
            log.info("�ϴ��ļ��ɹ�");
        }catch (Exception e) {
        	System.out.println("FtpUtils-->uploadFile-->exception::"+e);
        	log.warning("FtpUtils-->uploadFile-->exception::"+e);
        }finally{
            if(ftpClient.isConnected()){ 
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                	log.warning("FtpUtils-->ftpClient.disconnect-->exception::"+e);
                }
            } 
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                	log.warning("FtpUtils-->inputStream.close-->exception::"+e);
                } 
            } 
        }
        return flag;
    }
    
    /**
     * �ϴ��ļ�
     * @param pathname ftp���񱣴��ַ
     * @param fileName �ϴ���ftp���ļ���
     * @param inputStream �����ļ��� 
     * @return
     */
    @SuppressWarnings("static-access")
	public boolean uploadFile( String pathname, String fileName,InputStream inputStream){
    	log.info("FtpUtils-->uploadFile");
        boolean flag = false;
        try{
        	log.info("��ʼ�ϴ��ļ�");
            initFtpClient();
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            CreateDirecroty(pathname);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
            System.out.println("�ϴ��ļ��ɹ�");
            log.info("�ϴ��ļ��ɹ�");
        }catch (Exception e) {
        	System.out.println("FtpUtils-->uploadFile-->exception::"+e);
        	log.info("FtpUtils-->uploadFile-->exception::"+e);
        }finally{
            if(ftpClient.isConnected()){ 
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                	System.out.println("FtpUtils-->ftpClient.disconnect-->exception::"+e);
                	log.info("FtpUtils-->ftpClient.disconnect-->exception::"+e);
                }
            } 
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                	System.out.println("FtpUtils-->inputStream.close-->exception::"+e);
                	log.warning("FtpUtils-->inputStream.close-->exception::"+e);
                } 
            } 
        }
        return flag;
    }
    
    /**
     * �ı�Ŀ¼·��
     * @param directory
     * @return
     */
     public boolean changeWorkingDirectory(String directory) {
            boolean flag = true;
            try {
                flag = ftpClient.changeWorkingDirectory(directory);
                if (flag) {
                  System.out.println("�����ļ���" + directory + " �ɹ���");

                } else {
                    System.out.println("�����ļ���" + directory + " ʧ�ܣ���ʼ�����ļ���");
                }
            } catch (IOException e) {
            	System.out.println("FtpUtils-->changeWorkingDirectory-->exception::"+e);
            }
            return flag;
        }

    /**
     * �������Ŀ¼�ļ��������ftp�������Ѵ��ڸ��ļ����򲻴���������ޣ��򴴽�
     * @param remote
     * @return
     * @throws IOException
     */
    public boolean CreateDirecroty(String remote) throws IOException {
        boolean success = true;
        String directory = remote + "/";
        // ���Զ��Ŀ¼�����ڣ���ݹ鴴��Զ�̷�����Ŀ¼
        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory))) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            String path = "";
            String paths = "";
            while (true) {
                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                path = path + "/" + subDirectory;
                if (!existFile(path)) {
                    if (makeDirectory(subDirectory)) {
                        changeWorkingDirectory(subDirectory);
                    } else {
                        System.out.println("����Ŀ¼[" + subDirectory + "]ʧ��");
                        changeWorkingDirectory(subDirectory);
                    }
                } else {
                    changeWorkingDirectory(subDirectory);
                }

                paths = paths + "/" + subDirectory;
                start = end + 1;
                end = directory.indexOf("/", start);
                // �������Ŀ¼�Ƿ񴴽����
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }

    /**
     * �ж�ftp�������ļ��Ƿ����    
     * @param path
     * @return
     * @throws IOException
     */
    public boolean existFile(String path) throws IOException {
            boolean flag = false;
            FTPFile[] ftpFileArr = ftpClient.listFiles(path);
            if (ftpFileArr.length > 0) {
                flag = true;
            }
            return flag;
        }
    //����Ŀ¼
    public boolean makeDirectory(String dir) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                System.out.println("�����ļ���" + dir + " �ɹ���");

            } else {
                System.out.println("�����ļ���" + dir + " ʧ�ܣ�");
            }
        } catch (Exception e) {
        	System.out.println("FtpUtils-->makeDirectory-->exception::"+e);
        }
        return flag;
    }
    
    /** * �����ļ� * 
    * @param pathname FTP�������ļ�Ŀ¼ * 
    * @param filename �ļ����� * 
    * @param localpath ���غ���ļ�·�� * 
    * @return */
    public  boolean downloadFile(String pathname, String filename, String localpath){ 
        boolean flag = false; 
        OutputStream os=null;
        try { 
            System.out.println("��ʼ�����ļ�");
            initFtpClient();
            //�л�FTPĿ¼ 
            ftpClient.changeWorkingDirectory(pathname); 
            FTPFile[] ftpFiles = ftpClient.listFiles(); 
            for(FTPFile file : ftpFiles){ 
                if(filename.equalsIgnoreCase(file.getName())){ 
                    File localFile = new File(localpath + "/" + file.getName()); 
                    os = new FileOutputStream(localFile); 
                    ftpClient.retrieveFile(file.getName(), os); 
                    os.close(); 
                } 
            } 
            ftpClient.logout(); 
            flag = true; 
            System.out.println("�����ļ��ɹ�");
        } catch (Exception e) { 
        	System.out.println("FtpUtils-->downloadFile-->exception::"+e);
        } finally{ 
            if(ftpClient.isConnected()){ 
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                	System.out.println("FtpUtils-->ftpClient.disconnect-->exception::"+e);
                }
            } 
            if(null != os){
                try {
                    os.close();
                } catch (IOException e) {
                	System.out.println("FtpUtils-->os.close-->exception::"+e);
                } 
            } 
        } 
        return flag; 
    }
    
    /** * ɾ���ļ� * 
    * @param pathname FTP����������Ŀ¼ * 
    * @param filename Ҫɾ�����ļ����� * 
    * @return */ 
    public boolean deleteFile(String pathname, String filename){ 
        boolean flag = false; 
        try { 
            System.out.println("��ʼɾ���ļ�");
            initFtpClient();
            //�л�FTPĿ¼ 
            ftpClient.changeWorkingDirectory(pathname); 
            ftpClient.dele(filename); 
            ftpClient.logout();
            flag = true; 
            System.out.println("ɾ���ļ��ɹ�");
        } catch (Exception e) { 
        	System.out.println("FtpUtils-->deleteFile-->exception::"+e);
        } finally {
            if(ftpClient.isConnected()){ 
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                	System.out.println("FtpUtils-->ftpClient.disconnect-->exception::"+e);
                }
            } 
        }
        return flag; 
    }
}
