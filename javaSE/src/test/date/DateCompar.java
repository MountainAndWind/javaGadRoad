package test.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: slfang
 * @time: 2020/3/11 13:16
 */
public class DateCompar{

        public static boolean compare(String time1,String time2) throws ParseException
        {
            //如果想比较日期则写成"yyyy-MM-dd"就可以了
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            //将字符串形式的时间转化为Date类型的时间
            Date a=sdf.parse(time1);
            Date b=sdf.parse(time2);
            //Date类的一个方法，如果a早于b返回true，否则返回false
            if(a.before(b))
                return true;
            else
                return false;
		/*
		 * 如果你不喜欢用上面这个太流氓的方法，也可以根据将Date转换成毫秒
		if(a.getTime()-b.getTime()<0)
			return true;
		else
			return false;
		*/
        }
        public static void main(String[] args) throws Exception
        {
            boolean result=compare("2020-07-01", "2020-06-30");
            System.out.println(result);
            System.out.println(compare("2020-06-30", "2020-07-01"));
        }
}
