package test.Ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FtpUtil {
    /**
     * ��־����
     **/
    private static final Logger logger = LoggerFactory.getLogger(FtpUtil.class);

    /**
     * ��Ŀ¼������
     */
    public static final String DIR_NOT_EXIST = "��Ŀ¼������";

    /**
     * "��Ŀ¼��û���ļ�
     */
    public static final String DIR_CONTAINS_NO_FILE = "��Ŀ¼��û���ļ�";

    private String ftpAddress;
    private int ftpPort = 21;
    private String ftpUsername;
    private String ftpPassword;
    /**
     * FTP����Ŀ¼
     **/
    private String basePath;


    public FtpUtil(String ftpAddress, int ftpPort, String ftpUsername, String ftpPassword, String basePath) {
        this.ftpAddress = ftpAddress;
        this.ftpPort = ftpPort;
        this.ftpUsername = ftpUsername;
        this.ftpPassword = ftpPassword;
        this.basePath = basePath;
    }

    /**
     * �����ַ�����
     **/
    private static String localCharset = "GBK";

    /**
     * FTPЭ�����棬�涨�ļ�������Ϊiso-8859-1
     **/
    private static String serverCharset = "ISO-8859-1";

    /**
     * UTF-8�ַ�����
     **/
    private static final String CHARSET_UTF8 = "UTF-8";

    /**
     * OPTS UTF8�ַ�������
     **/
    private static final String OPTS_UTF8 = "OPTS UTF8";

    /**
     * ���û�������С4M
     **/
    private static final int BUFFER_SIZE = 1024 * 1024 * 4;

    /**
     * FTPClient����
     **/
    private static FTPClient ftpClient = null;


    /**
     * ���ظ�Ŀ¼�������ļ�������
     *
     * @param ftpPath  FTP�������ϵ����·�������磺test/123
     * @param savePath �����ļ������ص�·�������磺D:/test
     * @return �ɹ�����true�����򷵻�false
     */
    public boolean downloadFiles(String ftpPath, String savePath) {
        // ��¼
        login(ftpAddress, ftpPort, ftpUsername, ftpPassword);
        if (ftpClient != null) {
            try {
                String path = changeEncoding(basePath + ftpPath);
                // �ж��Ƿ���ڸ�Ŀ¼
                if (!ftpClient.changeWorkingDirectory(path)) {
                    logger.error(basePath + ftpPath + DIR_NOT_EXIST);
                    return Boolean.FALSE;
                }
                ftpClient.enterLocalPassiveMode();  // ���ñ���ģʽ����ͨһ���˿�����������
                String[] fs = ftpClient.listNames();
                // �жϸ�Ŀ¼���Ƿ����ļ�
                if (fs == null || fs.length == 0) {
                    logger.error(basePath + ftpPath + DIR_CONTAINS_NO_FILE);
                    return Boolean.FALSE;
                }
                for (String ff : fs) {
                    String ftpName = new String(ff.getBytes(serverCharset), localCharset);
                    File file = new File(savePath + '/' + ftpName);
                    try (OutputStream os = new FileOutputStream(file)) {
                        ftpClient.retrieveFile(ff, os);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            } catch (IOException e) {
                logger.error("�����ļ�ʧ��", e);
            } finally {
                closeConnect();
            }
        }
        return Boolean.TRUE;
    }

    /**
     * ����FTP������
     *
     * @param address  ��ַ���磺127.0.0.1
     * @param port     �˿ڣ��磺21
     * @param username �û������磺root
     * @param password ���룬�磺root
     */
    private void login(String address, int port, String username, String password) {
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(address, port);
            ftpClient.login(username, password);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //���ƻ�������С
            ftpClient.setBufferSize(BUFFER_SIZE);
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                closeConnect();
                logger.error("FTP   faiure");
            }
        } catch (Exception e) {
            logger.error("FTP login faiure", e);
        }
    }


    /**
     * FTP������·������ת��
     *
     * @param ftpPath FTP������·��
     * @return String
     */
    private static String changeEncoding(String ftpPath) {
        String directory = null;
        try {
            if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS_UTF8, "ON"))) {
                localCharset = CHARSET_UTF8;
            }
            directory = new String(ftpPath.getBytes(localCharset), serverCharset);
        } catch (Exception e) {
            logger.error("·������ת��ʧ��", e);
        }
        return directory;
    }

    /**
     * �ر�FTP����
     */
    private void closeConnect() {
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                logger.error("colse falire", e);
            }
        }
    }

    /**
     * ���ָ��Ŀ¼���Ƿ���ָ���ļ�
     *
     * @param ftpPath  FTP�������ļ����·�������磺test/123
     * @param fileName Ҫ���ص��ļ��������磺test.txt
     * @return �ɹ�����true�����򷵻�false
     */
    public boolean checkFileInFtp(String ftpPath, String fileName) {
        // ��¼
        login(ftpAddress, ftpPort, ftpUsername, ftpPassword);
        if (ftpClient != null) {
            try {
                String path = changeEncoding(basePath + ftpPath);
                // �ж��Ƿ���ڸ�Ŀ¼
                if (!ftpClient.changeWorkingDirectory(path)) {
                    logger.error(basePath + ftpPath + DIR_NOT_EXIST);
                    return Boolean.FALSE;
                }
                ftpClient.enterLocalPassiveMode();  // ���ñ���ģʽ����ͨһ���˿�����������
                String[] fs = ftpClient.listNames();
                // �жϸ�Ŀ¼���Ƿ����ļ�
                if (fs == null || fs.length == 0) {
                    logger.error(basePath + ftpPath + DIR_CONTAINS_NO_FILE);
                    return Boolean.FALSE;
                }
                for (String ff : fs) {
                    String ftpName = new String(ff.getBytes(serverCharset), localCharset);
                    if (ftpName.equals(fileName)) {
                        return Boolean.TRUE;
                    }
                }
            } catch (IOException e) {
                logger.error("�������", e);
            } finally {
                closeConnect();
            }
        }
        return Boolean.TRUE;
    }

    /**
     * ���ظ�Ŀ¼�������ļ������� ����ʵ����Ҫ�޸�ִ���߼�
     *
     * @param ftpPath  FTP�������ϵ����·�������磺test/123
     * @param savePath �����ļ������ص�·�������磺D:/test
     * @return �ɹ�����true�����򷵻�false
     */
    public Map<String, Object> downLoadTableFile(String ftpPath, String savePath) {
        // ��¼
        login(ftpAddress, ftpPort, ftpUsername, ftpPassword);
        Map<String, Object> resultMap = new HashMap<>();
        if (ftpClient != null) {
            try {
                String path = changeEncoding(basePath + "/" + ftpPath);
                // �ж��Ƿ���ڸ�Ŀ¼
                if (!ftpClient.changeWorkingDirectory(path)) {
                    logger.error(basePath + "/" + ftpPath + DIR_NOT_EXIST);
                    resultMap.put("result", false);
                    return resultMap;
                }
                ftpClient.enterLocalPassiveMode();  // ���ñ���ģʽ����ͨһ���˿�����������
                String[] fs = ftpClient.listNames();
                // �жϸ�Ŀ¼���Ƿ����ļ�
                if (fs == null || fs.length == 0) {
                    logger.error(basePath + "/" + ftpPath + DIR_CONTAINS_NO_FILE);
                    resultMap.put("result", false);
                    return resultMap;
                }
                List<String> tableFileNameList = new ArrayList<>();
                //���ݱ��������ļ���
                String tableDirName = savePath + "/" + ftpPath;
                File tableDirs=new File(tableDirName);
                if(!tableDirs.exists()){
                    tableDirs.mkdirs();
                }
                for (String ff : fs) {
                    String ftpName = new String(ff.getBytes(serverCharset), localCharset);
                    File file = new File(tableDirName + "/" + ftpName);
                    //�洢�ļ�������ʱʹ��
                    tableFileNameList.add(tableDirName + "/" + ftpName);
                    try (OutputStream os = new FileOutputStream(file)) {
                        ftpClient.retrieveFile(ff, os);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
                resultMap.put("fileNameList", tableFileNameList);
                resultMap.put("result", true);
                return resultMap;
            } catch (IOException e) {
                logger.error("�����ļ�ʧ��", e);
            } finally {
                closeConnect();
            }
        }
        resultMap.put("result", false);
        return resultMap;
    }
}
