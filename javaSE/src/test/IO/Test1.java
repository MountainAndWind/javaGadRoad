package test.IO;

import jdk.internal.util.xml.impl.Input;
import util.StringUtils;

import java.io.*;
import java.net.URL;
import java.util.HashMap;

public class Test1 {

    public static void main(String[] args) throws IOException {
      /*  ClassLoader classLoader = Test1.class.getClassLoader();
        String resource = classLoader.getResource("/").getPath();
        System.out.println(resource);*/
        String url="C:\\Users\\19504\\Documents\\WeChat Files\\19504\\FileStorage\\File\\2020-01";
        int i = url.indexOf("19504");
        String substring = url.substring(0, i+6);
        System.out.println(substring);
        //FileUtils.printByReadLine("E:\\workplace\\persional\\JAVA_BCXX\\web\\prop\\test.properties");
        /*t1();*/
    }

    static void t1() throws IOException {
        HashMap hashMap=new HashMap();
        File file = new File("E:\\workplace\\persional\\JAVA_BCXX\\web\\prop\\test.properties");
        BufferedReader in = new BufferedReader(new FileReader(file));
        String string="";
        boolean flag=false;
        while ((string = in.readLine())!=null){
            if(flag){
                if(StringUtils.isNotBlank(string)&&!string.startsWith("#")){
                    String[] lineValue = string.split("=");
                    int i = string.indexOf("=");
                    if(string.length()==i+1){
                        hashMap.put(lineValue[0],"");
                    }else{
                        hashMap.put(lineValue[0],lineValue[1]);
                    }
                }
            }
            if(string.contains("read start")){
                flag=true;
            }
        }
        System.out.println(hashMap);
    }
}
