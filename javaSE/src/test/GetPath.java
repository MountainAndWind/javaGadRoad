package test;

/**
 * @description:
 * @author: slfang
 * @time: 2020/1/25 22:08
 */
public class GetPath {

    private String path;

    public GetPath(String path) {
        this.path = path;
    }

    private String print(){
        return path;
    }

    public static void main(String[] args) {
        GetPath getPath = new GetPath("/aaa");
        String print = getPath.print();
        System.out.println("print::"+print);
        /*String resource="/F:/wearwer/Resin/";
        resource = resource.replace("/Resin/","");
        String path = resource.substring(1,resource.length())+"\\ecology\\WEB-INF\\prop\\";
        System.out.println("path::"+path);*/

    }
    /**
     * 获取配置文件路径
     * 注 ：使用当前方法要确保ecology文件路径之前不存在ecology同名文件
     * @param fileName
     * @return
     */
    /*public static String getProPath(String fileName) {
        System.out.println("getFilePath::");
        ClassLoader classLoader = GetPath.class.getClassLoader();
        String resource = classLoader.getResource("/").getPath();
        System.out.println(resource);
        int i = resource.indexOf("ecology");
        String path = resource.substring(0, i+6)+"\\WEB-INF\\prop\\"+fileName;
        System.out.println("path::"+path);
        return path;
    }*/
}
