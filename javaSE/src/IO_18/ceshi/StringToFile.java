package IO_18.ceshi;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.io.*;

public class StringToFile {

    public static void main(String[] args) throws IOException {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<QUALITY_INNERPLATCOUNT>\n");
        xmlBuilder.append("<data_content>\n");
        xmlBuilder.append("<row id=\"1\">\n");
        xmlBuilder.append("你哈珀\n");
        xmlBuilder.append("</row>\n");
        xmlBuilder.append("</data_content>\n");
        xmlBuilder.append("<content_check>\n");
        xmlBuilder.append("<rowcount>1</rowcount>\n");
        xmlBuilder.append("</content_check>\n");
        xmlBuilder.append("</QUALITY_INNERPLATCOUNT>\n");
        InputStream stringStream = getStringStream(xmlBuilder.toString());
        copyFile(stringStream,new FileOutputStream("E:\\file\\fileTest.txt"));

    }


    static void copyFile(InputStream inputStream,OutputStream outputStream) throws IOException {
        BufferedInputStream in = new BufferedInputStream(inputStream);
        BufferedOutputStream out = new BufferedOutputStream(outputStream);
        int len;
        byte []arr = new byte[1024*10];
        while ((len = in.read(arr))!=-1){
            out.write(arr,0,len);
        }
        out.flush();
        in.close();
        out.close();
    }

    //将一个字符串转化为输入流
    public static InputStream getStringStream(String sInputString){
        System.out.println("getStringStream::");
        if (sInputString != null && !sInputString.trim().equals("")){
            try{
                ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
                System.out.println("String 转换成InputStream");
                return tInputStringStream;
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }

}
