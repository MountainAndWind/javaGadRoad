package test.Ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

public class FtpUtilsNew  {
    private String hostname = "";
    private Integer port = 21;
    private String username = "";
    private String password = "";
    private FTPClient ftpClient = null;

    public FtpUtilsNew(String hostname, Integer port, String username, String password) {
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public void initFtpClient() {
        System.out.println("FtpUtils-->initFtpClient");
        this.ftpClient = new FTPClient();
        this.ftpClient.setControlEncoding("utf-8");
        FTPClientConfig conf = new FTPClientConfig("WINDOWS");
        this.ftpClient.configure(conf);

        try {
            System.out.println("connecting...ftp������:" + this.hostname + ":" + this.port);
            System.out.println("connecting...ftp������:" + this.hostname + ":" + this.port);
            this.ftpClient.connect(this.hostname, this.port);
            this.ftpClient.login(this.username, this.password);
            int replyCode = this.ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("connect failed...ftp������:" + this.hostname + ":" + this.port);
                System.out.println("connect failed...ftp������:" + this.hostname + ":" + this.port);
            }

            System.out.println("connect successfu...ftp������:" + this.hostname + ":" + this.port);
            System.out.println("connect successfu...ftp������:" + this.hostname + ":" + this.port);
        } catch (IOException var3) {
            System.out.println("FtpUtils-->initFtpClient-->exception::" + var3);
            System.out.println("FtpUtils-->initFtpClient-->exception::" + var3);
        }

    }

    public boolean uploadFile(String pathname, String fileName, String originfilename) {
        System.out.println("FtpUtils-->uploadFile");
        boolean flag = false;
        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(new File(originfilename));
            this.initFtpClient();
            boolean flag11 = this.ftpClient.setFileType(2);
            boolean flag22 = this.ftpClient.changeWorkingDirectory(pathname);
            this.ftpClient.enterLocalPassiveMode();
            boolean flag3 = this.ftpClient.storeFile(pathname + fileName, inputStream);
            inputStream.close();
            boolean flag4 = this.ftpClient.logout();
            flag = true;
            System.out.println("�ϴ��ļ��ɹ�");
        } catch (Exception var22) {
            System.out.println("FtpUtils-->uploadFile-->exception::" + var22);
            System.out.println("FtpUtils-->uploadFile-->exception::" + var22);
        } finally {
            if (this.ftpClient.isConnected()) {
                try {
                    this.ftpClient.disconnect();
                } catch (IOException var21) {
                    System.out.println("FtpUtils-->ftpClient.disconnect-->exception::" + var21);
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException var20) {
                    System.out.println("FtpUtils-->inputStream.close-->exception::" + var20);
                }
            }

        }

        return flag;
    }

    public boolean uploadFile(String pathname, String fileName, InputStream inputStream) {
        System.out.println("FtpUtils-->uploadFile");
        boolean flag = false;

        try {
            System.out.println("��ʼ�ϴ��ļ�");
            this.initFtpClient();
            this.ftpClient.setFileType(2);
            this.ftpClient.makeDirectory(pathname);
            this.ftpClient.changeWorkingDirectory(pathname);
            boolean flag1 = this.ftpClient.storeFile(new String(fileName.getBytes("GBK"), "iso-8859-1"), inputStream);
            System.out.println("FtpUtils-->uploadFile-->flag1::" + flag1);
            inputStream.close();
            this.ftpClient.logout();
            flag = true;
            System.out.println("�ϴ��ļ��ɹ�");
            System.out.println("�ϴ��ļ��ɹ�");
        } catch (Exception var18) {
            System.out.println("FtpUtils-->uploadFile-->exception::" + var18);
            System.out.println("FtpUtils-->uploadFile-->exception::" + var18);
        } finally {
            if (this.ftpClient.isConnected()) {
                try {
                    this.ftpClient.disconnect();
                } catch (IOException var17) {
                    System.out.println("FtpUtils-->ftpClient.disconnect-->exception::" + var17);
                    System.out.println("FtpUtils-->ftpClient.disconnect-->exception::" + var17);
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException var16) {
                    System.out.println("FtpUtils-->inputStream.close-->exception::" + var16);
                    System.out.println("FtpUtils-->inputStream.close-->exception::" + var16);
                }
            }

        }

        return flag;
    }

    public boolean changeWorkingDirectory(String directory) {
        boolean flag = true;

        try {
            flag = this.ftpClient.changeWorkingDirectory(directory);
            if (flag) {
                System.out.println("�����ļ���" + directory + " �ɹ���");
            } else {
                System.out.println("�����ļ���" + directory + " ʧ�ܣ���ʼ�����ļ���");
            }
        } catch (IOException var4) {
            System.out.println("FtpUtils-->changeWorkingDirectory-->exception::" + var4);
        }

        return flag;
    }

    /*public boolean CreateDirecroty(String remote) throws IOException {
        boolean success = true;
        String directory = remote + "/";
        if (!directory.equalsIgnoreCase("/") && !this.changeWorkingDirectory(new String(directory))) {
            int start = false;
            int end = false;
            int start;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }

            int end = directory.indexOf("/", start);
            String path = "";
            String paths = "";

            do {
                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                path = path + "/" + subDirectory;
                if (!this.existFile(path)) {
                    if (this.makeDirectory(subDirectory)) {
                        this.changeWorkingDirectory(subDirectory);
                    } else {
                        System.out.println("����Ŀ¼[" + subDirectory + "]ʧ��");
                        this.changeWorkingDirectory(subDirectory);
                    }
                } else {
                    this.changeWorkingDirectory(subDirectory);
                }

                paths = paths + "/" + subDirectory;
                start = end + 1;
                end = directory.indexOf("/", start);
            } while(end > start);
        }

        return success;
    }*/

    public boolean existFile(String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = this.ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }

        return flag;
    }

    public boolean makeDirectory(String dir) {
        boolean flag = true;

        try {
            flag = this.ftpClient.makeDirectory(dir);
            if (flag) {
                System.out.println();
                System.out.println("�����ļ���" + dir + " �ɹ���");
            } else {
                System.out.println("�����ļ���" + dir + " ʧ�ܣ�");
            }
        } catch (Exception var4) {
            System.out.println("FtpUtils-->makeDirectory-->exception::" + var4);
        }

        return flag;
    }


    /**
     *
     * @param pathname Զ��
     * @param localpath ���ر����ļ�
     * @return
     */
    public boolean downloadFileByDir(String pathname, String localpath) {
        System.out.println("FtpUtils-->downloadFileByDir");
        System.out.println("ftp������ָ����ȡ�ļ�·��::"+pathname);
        System.out.println("���ػ�д·��::"+localpath);
        boolean flag = false;
        FileOutputStream os = null;
        try {
            System.out.println("��ʼ�����ļ�");
            this.initFtpClient();
            this.ftpClient.changeWorkingDirectory(pathname);
            FTPFile[] ftpFiles = this.ftpClient.listFiles();
            FTPFile[] var10 = ftpFiles;
            int var9 = ftpFiles.length;

            for(int var8 = 0; var8 < var9; ++var8) {
                FTPFile file = var10[var8];
                File localFile = new File(localpath + "/" + file.getName());
                os = new FileOutputStream(localFile);
                this.ftpClient.retrieveFile(file.getName(), os);
                os.close();
            }

            this.ftpClient.logout();
            flag = true;
            System.out.println("�����ļ��ɹ�");
        } catch (Exception var24) {
            System.out.println("FtpUtils-->downloadFile-->exception::" + var24);
        } finally {
            if (this.ftpClient.isConnected()) {
                try {
                    this.ftpClient.disconnect();
                } catch (IOException var23) {
                    System.out.println("FtpUtils-->ftpClient.disconnect-->exception::" + var23);
                }
            }

            if (os != null) {
                try {
                    os.close();
                } catch (IOException var22) {
                    System.out.println("FtpUtils-->os.close-->exception::" + var22);
                }
            }

        }

        return flag;
    }



    public boolean downloadFile(String pathname, String filename, String localpath) {
        boolean flag = false;
        FileOutputStream os = null;

        try {
            System.out.println("��ʼ�����ļ�");
            this.initFtpClient();
            this.ftpClient.changeWorkingDirectory(pathname);
            FTPFile[] ftpFiles = this.ftpClient.listFiles();
            FTPFile[] var10 = ftpFiles;
            int var9 = ftpFiles.length;

            for(int var8 = 0; var8 < var9; ++var8) {
                FTPFile file = var10[var8];
                if (filename.equalsIgnoreCase(file.getName())) {
                    File localFile = new File(localpath + "/" + file.getName());
                    os = new FileOutputStream(localFile);
                    this.ftpClient.retrieveFile(file.getName(), os);
                    os.close();
                }
            }

            this.ftpClient.logout();
            flag = true;
            System.out.println("�����ļ��ɹ�");
        } catch (Exception var24) {
            System.out.println("FtpUtils-->downloadFile-->exception::" + var24);
        } finally {
            if (this.ftpClient.isConnected()) {
                try {
                    this.ftpClient.disconnect();
                } catch (IOException var23) {
                    System.out.println("FtpUtils-->ftpClient.disconnect-->exception::" + var23);
                }
            }

            if (os != null) {
                try {
                    os.close();
                } catch (IOException var22) {
                    System.out.println("FtpUtils-->os.close-->exception::" + var22);
                }
            }

        }

        return flag;
    }

    public boolean deleteFile(String pathname, String filename) {
        boolean flag = false;

        try {
            System.out.println("��ʼɾ���ļ�");
            this.initFtpClient();
            this.ftpClient.changeWorkingDirectory(pathname);
            this.ftpClient.dele(filename);
            this.ftpClient.logout();
            flag = true;
            System.out.println("ɾ���ļ��ɹ�");
        } catch (Exception var13) {
            System.out.println("FtpUtils-->deleteFile-->exception::" + var13);
        } finally {
            if (this.ftpClient.isConnected()) {
                try {
                    this.ftpClient.disconnect();
                } catch (IOException var12) {
                    System.out.println("FtpUtils-->ftpClient.disconnect-->exception::" + var12);
                }
            }

        }

        return flag;
    }
}
