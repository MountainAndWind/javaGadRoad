package test;

import util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:配置问价读取
 * @author: slfang
 * @time: 2020/1/19 13:44
 */
public class ProperitesRead {

    public static void main(String[] args) {
       /* readProAndSetMap("",new HashMap<>());*/
    }



    /**
     *读取配置设置map
     * @param fileName 配置文件名
     * @throws IOException
     */
    public static void readProAndSetMap(String fileName, Map<String, Object> baseInfo)  {
        System.out.println("readProAndSetMap::start:::::::::::::::::::::::::::::::::");
        System.out.println();
        String path = "F:/wearwer\\ecology\\WEB-INF\\prop\\ZjzOaXmlGetJob.properties";
        File file = new File(path);
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
            String string="";
            boolean flag=false;
            while ((string = in.readLine())!=null){
                if(flag){
                    if(StringUtils.isNotBlank(string)&&!string.startsWith("#")){
                        System.out.println ("配置信息::"+string);
                        String[] lineValue = string.split("=");
                        int i = string.indexOf("=");
                        if(string.length()==i+1){
                            baseInfo.put(lineValue[0],"");
                        }else{
                            baseInfo.put(lineValue[0],lineValue[1]);
                        }
                    }
                }
                if(string.contains("read start")){
                    flag=true;
                }
            }
            System.out.println ("hashMap::"+baseInfo);
            System.out.println ("readProAndSetMap::end:::::::::::::::::::::::::::::::::");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println (e.getMessage());
        }
        finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println (e.getMessage());
            }
        }
    }

    /**
     * 获取配置文件路径
     * 注 ：使用当前方法要确保ecology文件路径之前不存在ecology同名文件
     * @param fileName
     * @return
     */
   /* public static String getProPath(String fileName) {
        System.out.println ("getFilePath::");
        ClassLoader classLoader = ProperitesRead.class.getClassLoader();
        String resource = classLoader.getResource("/").getPath();
        System.out.println (resource);
       *//* int i = resource.indexOf("ecology");
        String path = resource.substring(0, i+6)+"\\WEB-INF\\prop\\"+fileName;
        System.out.println ("path::"+path);*//*
        resource = resource.replace("/Resin/","");
        String path = resource.substring(1,resource.length())+"\\ecology\\WEB-INF\\prop\\"+fileName+".properties";
        System.out.println ("path::"+path);
        return path;
    }*/
}
