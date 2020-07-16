package test.String;

/**
 * @description:
 * @author: slfang
 * @time: 2020/2/11 13:15
 */
public class SpiltTest {

    public static void main(String[] args) {

       /* String aa="dasdas";
        System.out.println(aa.indexOf("=")+"--");
        String s = "xmlHeader=<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        System.out.println(s);
        int i = s.indexOf("=");
        System.out.println(i);
        System.out.println(s.substring(10,s.length()));*/

       /* String sql="select   a,b,c,d  from table";
        String resql = sql.replace("select","");
        String res = resql.substring(0,resql.indexOf("from"));
        System.out.println(res);
        System.out.println(res.length());
        String trim = res.trim();
        System.out.println(trim.length());

         System.out.println("sql::"+sql);*/
      /* String string = "/D:/WEAVER/ecology/classbean/";
        System.out.println(string.substring(0,string.indexOf("ecology")));
        String col="a.namenamea,";
        System.out.println(col);
        System.out.println(col.substring(0,col.length()-1));*/
        String str="";
        String[] split = str.split(",");
        for (String s : split) {
            System.out.println(s);
        }

        str = str.substring(0,str.length()-1);

    }
}
