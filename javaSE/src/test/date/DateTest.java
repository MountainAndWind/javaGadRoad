package test.date;

import util.DateUtils;
import test.HttpRequest.GetUnixStarp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: slfang
 * @time: 2020/3/10 14:26
 */
public class DateTest {

    public static void main(String[] args) throws ParseException {
        //设置转换的日期格式
       /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        //开始时间
        Date startDate = sdf.parse("2018-05-10 23:11");
        //结束时间
        Date endDate = sdf.parse("2018-05-10 12:21");
        String yyyy = DateUtils.getDate("yyyy");
        System.out.println("YYYY"+yyyy);

        System.out.println(endDate.getTime());
        System.out.println(startDate.getTime());
        //得到相差的天数 betweenDate
        long betweenDate = (endDate.getTime() - startDate.getTime())/(60*60*24*1000);

        //打印控制台相差的天数
        System.out.println(betweenDate);*/
      /* String a="12122.12";
       String b="23323.33";
        System.out.println(Float.valueOf(a)-Float.valueOf(a));
        System.out.println((Float.valueOf(a)-Float.valueOf(a))<=0.0);*/
        String dateBefore = DateUtils.getDateBefore(new Date(), 1, "yyyy-MM-dd");
        String s = GetUnixStarp.Date2TimeStamp(dateBefore, "yyyy-MM-dd");
        System.out.println(s);
        String newDate = DateUtils.getDateBefore(new Date(), 0, "yyyy-MM-dd");
        String s2 = GetUnixStarp.Date2TimeStamp(newDate, "yyyy-MM-dd");
        System.out.println(s2);

    }
}
