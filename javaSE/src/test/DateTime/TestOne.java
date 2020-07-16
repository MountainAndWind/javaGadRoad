package test.DateTime;

import util.DateUtils;
import test.HttpRequest.GetUnixStarp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @description:
 * @author: slfang
 * @time: 2020/5/11 13:21
 */
public class TestOne {

    public static void main(String[] args) throws ParseException {
        System.out.println(getYesterday("2020-03-01"));

        String dateBefore = DateUtils.getDateBefore(new Date(), 29, "yyyy-MM-dd");
        String unixStartTime = GetUnixStarp.Date2TimeStamp(dateBefore, "yyyy-MM-dd");
        String newDate = DateUtils.getDateBefore(new Date(), 0, "yyyy-MM-dd");
        String unixEndTime = GetUnixStarp.Date2TimeStamp(newDate, "yyyy-MM-dd");
    }

    /**
     * 得到前一天
     *
     * @param value
     *            formate格式
     * @return 当前日期前一天
     * @author rongwang
     * @created 2013-10-22 上午10:45:46
     * @lastModified
     * @history
     */
    public static String getYesterday(String value) throws ParseException {
        // 取时间
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat1.parse(value);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        // 把日期往后增加一天.整数往后推,负数往前移动
        calendar.add(calendar.DATE, -1);
        // 这个时间就是日期往后推一天的结果
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 判断是否是周末
     * @return
     */
    public static String isWeekend(String bDate) throws ParseException {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date bdate = format1.parse(bDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(bdate);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return "OK";
        } else {
            return "NO";
        }
    }



}
