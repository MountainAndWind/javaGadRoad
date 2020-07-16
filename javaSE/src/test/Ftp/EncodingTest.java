package test.Ftp;

import test.IO.FileUtils;

import java.io.*;

/**
 * @description:
 * @author: slfang
 * @time: 2020/4/4 22:26
 */
public class EncodingTest {
    public static void main(String[] args) throws IOException {
        File srcFolder = new File("F:\\localFtpTest");
        String path="F:\\localFtpTest\\project_sendcontent20200403104601444.xml";
        // 递归功能实现
        //getAllJavaFilePaths(srcFolder,"GBK","utf-8");
        getAllJavaFilePaths(srcFolder,"utf-8","GBK");
        String xmldata =FileUtils.readFileToString(path);
        System.out.println("xmldata::"+xmldata);
        /*String path="F:\\localFtpTest\\project_sendcontent20200403112109989.xml";
        String path1 = readFileToString(path);
        System.out.println("path::"+path1);*/
    }

    private static void getAllJavaFilePaths(File srcFolder,String oldCharset,String newCharset) throws IOException {

        // 获取该目录下所有的文件或者文件夹的File数组
        File[] fileArray = srcFolder.listFiles();

        // 遍历该File数组，得到每一个File对象
        for (File file : fileArray) {

            // 继续判断是否以.java结尾,不是的话继续调用getAllJavaFilePaths()方法
            if (file.isDirectory()) {

                getAllJavaFilePaths(file,oldCharset,oldCharset);

            } else {

                if (file.getName().endsWith(".java")) {

                    // 以GBK格式,读取文件
                    FileInputStream fis = new FileInputStream(file);
                    InputStreamReader isr = new InputStreamReader(fis, oldCharset);
                    BufferedReader br = new BufferedReader(isr);
                    String str = null;

                    // 创建StringBuffer字符串缓存区
                    StringBuffer sb = new StringBuffer();

                    // 通过readLine()方法遍历读取文件
                    while ((str = br.readLine()) != null) {
                        // 使用readLine()方法无法进行换行,需要手动在原本输出的字符串后面加"\n"或"\r"
                        str += "\n";
                        sb.append(str);
                    }
                    String str2 = sb.toString();

                    // 以UTF-8格式写入文件,file.getAbsolutePath()即该文件的绝对路径,false代表不追加直接覆盖,true代表追加文件
                    FileOutputStream fos = new FileOutputStream(file.getAbsolutePath(), false);
                    OutputStreamWriter osw = new OutputStreamWriter(fos, newCharset);
                    osw.write(str2);
                    osw.flush();
                    osw.close();
                    fos.close();
                    br.close();
                    isr.close();
                    fis.close();
                }
            }
        }
    }

    private static void test(String filePath) {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
            String code = EncodeUtil.getEncode(bis, false);
            System.out.println("文件编码方式为："+code);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 读取文件转换成字字符创
     * @param path
     * @return
     */
    public static String readFileToString(String path)
    {
        int len=0;
        StringBuffer str=new StringBuffer("");
        File file=new File(path);
        try {
            FileInputStream is=new FileInputStream(file);
            InputStreamReader isr= new InputStreamReader(is,"GBK");
            BufferedReader in= new BufferedReader(isr);
            String line=null;
            while( (line=in.readLine())!=null )
            {
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
}
