package test.String;

/**
 * @description:
 * @author: slfang
 * @time: 2020/2/13 16:27
 */
public class JsonPinjie {

    public static void main(String[] args) {
        StringBuilder s= new StringBuilder("dashhads dajsjhdahsd a,");
        boolean b = s.toString().endsWith(",");
        if(b){
            String substring = s.toString().substring(0, s.length() - 1);
            System.out.println(substring);
            s = new StringBuilder(substring);
            System.out.println(s.toString());
        }

    }
}
