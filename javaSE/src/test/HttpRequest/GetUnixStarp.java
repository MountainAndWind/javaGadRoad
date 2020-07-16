package test.HttpRequest;

import org.apache.http.util.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @description:
 * @author: slfang
 * @time: 2020/4/9 12:18
 */
public class GetUnixStarp {

    public static void main(String[] args) {
        String dateStr = Long.toString(System.currentTimeMillis()/1000L);
        System.out.println("当前时间:"+dateStr);
        String startTime = Date2TimeStamp("2020-01-06 12:22:11","yyyy-MM-dd HH:mm:ss");
        String endTime = Date2TimeStamp("2020-01-09 12:22:01","yyyy-MM-dd HH:mm:ss");
        System.out.println(startTime);
        System.out.println(endTime);
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param dateStr 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String Date2TimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Java将Unix时间戳转换成指定格式日期字符串
     * @param timestampString 时间戳 如："1473048265";
     * @param formats 要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
     *
     * @return 返回结果 如："2016-09-05 16:06:42";
     */
    public static String TimeStamp2Date(String timestampString, String formats) {
        if (TextUtils.isEmpty(formats))
            formats = "yyyy-MM-dd HH:mm:ss";
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        return date;
    }

    /**
     * 取得当前时间戳（精确到秒）
     *
     * @return nowTimeStamp
     */
    public static String getNowTimeStamp() {
        long time = System.currentTimeMillis();
        String nowTimeStamp = String.valueOf(time / 1000);
        return nowTimeStamp;
    }

    /**
     * 根据时间
     * @return
     */
    public static double getDay(String timestampString){
        BigDecimal b1 = new BigDecimal(Double.toString(Double.valueOf(timestampString)));
        BigDecimal b2 = new BigDecimal(Double.toString(60*60*24));
        return b1.divide(b2, 4, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
