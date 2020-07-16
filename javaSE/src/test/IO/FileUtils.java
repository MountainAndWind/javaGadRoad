package test.IO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * �ļ�����������
 * 2020/1/10
 * by fsl
 */
public class FileUtils {

    private static Logger logger  = LoggerFactory.getLogger(FileUtils.class);

   /* public static void main(String[] args) throws IOException {
        *//*String s = readFileToString("");
        logger.info(s);*//*
       *//* File file = new File("D:\\cxpc\\cxup.log");
        logger.info(file.getAbsolutePath());
        //Files("E:\\file");
        deleteFileByPath("D:\\fileTest");*//*
        String s = readFileToStringWithEncode("F:\\localFtpTest\\project_sendcontent20200403112109989.xml","");
        System.out.println(s);


        //delAllFile("D:\\fileTest");
    }
*/
    /**
     * ͨ��·��ɾ���ļ��ļ��г���
     * @param path ���ؾ���·��
     */
    private static void deleteFileByPath(String path) {
        logger.info("deleteFileByPath path::"+path);
        int flag;
        File file = new File(path);
        //�ж��ļ���Ϊnull���ļ�Ŀ¼����
        if (file == null || !file.exists()){
            flag = 0;
            logger.info("�ļ�ɾ��ʧ��,�����ļ�·���Ƿ���ȷ");
            return;
        }
        //ȡ�����Ŀ¼�µ��������ļ�����
        File[] files = file.listFiles();
        //������Ŀ¼�µ��ļ�����
        for (File f: files){
            //��ӡ�ļ���
            String name = file.getName();
            logger.info("��ǰ�ļ���::"+name);
            //�ж���Ŀ¼�Ƿ������Ŀ¼,������ļ���ɾ��
            if (f.isDirectory()){
                logger.info("��ǰ�ļ�Ϊ�ļ��� ��������");
                //deleteFileByPath(name);
            }else {
                f.delete();
            }
        }
        //ɾ�����ļ���  forѭ���Ѿ�����һ��ڵ��Ŀ¼��ա�
        //file.delete();
    }

    /**
     * ɾ���ļ����������ļ�
     * @param path
     * @return
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//��ɾ���ļ���������ļ�
                delFolder(path + "/" + tempList[i]);//��ɾ�����ļ���
                flag = true;
            }
        }
        return flag;
    }

    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //ɾ����������������
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); //ɾ�����ļ���
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * ��ȡ�ļ�ת�������ַ���
     * @param path
     * @return
     */
    public static String readFileToString(String path)
    {
        logger.info("path"+path);
        int len=0;
        StringBuffer str=new StringBuffer("");
        File file=new File(path);
        try {
            FileInputStream is=new FileInputStream(file);
            InputStreamReader isr= new InputStreamReader(is);
            BufferedReader in= new BufferedReader(isr);
            String line=null;
            while( (line=in.readLine())!=null )
            {
               /* if(len != 0) // �����з�������
                {
                    str.append("\r\n"+line);
                }
                else
                {
                    str.append(line);
                }*/
                System.out.println(line);
                str.append(line);
                len++;
            }
            in.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    /**
     * ��ȡ�ļ�ת�������ַ���
     *
     * @param s
     * @param path
     * @return
     */
    public static String readFileToStringWithEncode(String s, String path) throws IOException {
        logger.info("path"+s);
        File file =new File(s);
        String message="";//��ȡ�ļ�����
        if(file.isFile() && file.exists()) {
            BufferedReader br=new BufferedReader(new InputStreamReader(new
                    FileInputStream(file),"GBK"));
            String buff;
            while ((buff=br.readLine()) != null) {
                message=message+buff+"\n";
            }
            br.close();
        }
        return message;
    }

    static void Files(String path){
        logger.info("path:"+path);
        File file = new File(path);
        File[] files = file.listFiles();
        for (File file1 : files) {
            String name = file1.getName();
            logger.info(name);
        }
    }

    /**
     * �������bom��������
     * @param file
     * @return
     * @throws IOException
     */
    public static char[] loadWithBomFile(String file) throws IOException {
        // read text file, auto recognize bom marker or use
        // system default if markers not found.
        BufferedReader reader = null;
        CharArrayWriter writer = null;
        UnicodeReader r = new UnicodeReader(new FileInputStream(file), null);

        char[] buffer = new char[16 * 1024];   // 16k buffer
        int read;
        try {
            reader = new BufferedReader(r);
            writer = new CharArrayWriter();
            while( (read = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, read);
            }
            writer.flush();
            return writer.toCharArray();
        } catch (IOException ex) {
            throw ex;
        } finally {
            try {
                writer.close(); reader.close(); r.close();
            } catch (Exception ex) { }
        }
    }


    /**
     * ��ӡ�ļ�����
     * @param path
     * @throws IOException
     */
    static void printFile(String path) throws IOException {
        InputStream in = new FileInputStream(path);
        int len;
        byte []arr = new byte[1024];
        while ((len = in.read(arr))!=-1){
            System.out.println(new String(arr, 0, len));
        }
    }

    static void printByReadLine(String path) throws IOException {
        File file = new File(path);
        BufferedReader in = new BufferedReader(new FileReader(file));
        String string="";
        while ((string = in.readLine())!=null){
            System.out.println(string);
        }
    }

}
