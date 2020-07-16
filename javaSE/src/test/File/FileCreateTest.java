package test.File;

import java.io.*;

/**
 * @description:
 * @author: slfang
 * @time: 2020/4/6 18:26
 */
public class FileCreateTest {

    public static void main(String[] args) throws IOException {
        String filePath="F:\\localFtpTest\\project_sendcontent20200403112109989.xml";
        String s = readFileToString(filePath);
        System.out.println("s::"+s);
    }


    /**
     * 读取文件转换成字字符创
     * @param path
     * @return
     */
    public static String readFileToString(String path)
    {
        System.out.println("readFileToString:开始");
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
                //String gbkStr = transCode("UTF-8", "GBK", line);
                //System.out.println(line);
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

    private static void testWrite() throws IOException {
        String t = "哈啥啥活动啊.txt";
        /*System.out.println("t::"+t);
        String gbk= new String(t.getBytes( "UTF-8"));
        System.out.println(gbk);
        String unicode = new String(gbk.getBytes(),"UTF-8");
        System.out.println(unicode);
        String utf8= new String(unicode.getBytes("GBK"));
        System.out.println(utf8);*/
        transCode("GBK","UTF-8",t);
        File file = new File("F:\\"+t);
        file.createNewFile();
    }

    private static void testWrite2() throws IOException {
        String t = "32试试大苏打.txt";
        String t1 = new String(t.getBytes("utf8"), "GBK");
        File file = new File("F:\\"+t1);
        file.createNewFile();
    }
    /**
     * @param str 目标字符串
     * @param oldCharset
     * @param changeCharset
     * @throws IOException
     */
    private static String transCode(String oldCharset,String changeCharset,String str) throws IOException {
        System.out.println("transCode::开始");
        System.out.println("str::"+str);
        String gbk= new String(str.getBytes( changeCharset));
        System.out.println(gbk);
        String unicode = new String(gbk.getBytes(),changeCharset);
        System.out.println(unicode);
        String utf8= new String(unicode.getBytes(oldCharset));
        System.out.println(utf8);
        return utf8;
    }


    /*public static void main(String[] args) throws IOException {
        String t = "这是一个字符串aaa111";
        System.out.println(t);
        FileCreateTest convert = new FileCreateTest();
        byte[] fullByte = convert.gbk2utf8(t);
        String fullStr;

        {
            try {
                fullStr = new String(fullByte, "UTF-8");
                System.out.println("string from GBK to UTF-8 byte:  " + fullStr);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }*/

    public byte[] gbk2utf8(String chenese){
        char c[] = chenese.toCharArray();
        byte [] fullByte =new byte[3*c.length];
        for(int i=0; i<c.length; i++){
            int m = (int)c[i];
            String word = Integer.toBinaryString(m);
//         System.out.println(word);

            StringBuffer sb = new StringBuffer();
            int len = 16 - word.length();
            //补零
            for(int j=0; j<len; j++){
                sb.append("0");
            }
            sb.append(word);
            sb.insert(0, "1110");
            sb.insert(8, "10");
            sb.insert(16, "10");

//         System.out.println(sb.toString());

            String s1 = sb.substring(0,8);
            String s2 = sb.substring(8,16);
            String s3 = sb.substring(16);

            byte b0 = Integer.valueOf(s1, 2).byteValue();
            byte b1 = Integer.valueOf(s2, 2).byteValue();
            byte b2 = Integer.valueOf(s3, 2).byteValue();
            byte[] bf = new byte[3];
            bf[0] = b0;
            fullByte[i*3] = bf[0];
            bf[1] = b1;
            fullByte[i*3+1] = bf[1];
            bf[2] = b2;
            fullByte[i*3+2] = bf[2];

        }
        return fullByte;
    }
}
