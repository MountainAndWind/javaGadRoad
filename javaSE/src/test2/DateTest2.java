package test2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @ClassName
 * @Description TODO
 * @Author fangshilei
 * @Date 2019/12/6 10:06
 * @Version 1.0
 **/
public class DateTest2 {

    /**
     * 	指定天数天前的时间
     *  @param formate,dayNums指定天数
     *  @return
     *  @author fangshilei
     *  @created 2019年12月6日 上午10:54:07
     *  @lastModified
     *  @history
     */
    public static String get45DayAgo(String formate,int dayNums){
        Calendar calendar = Calendar.getInstance();//得到日历的实例
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DATE)-dayNums);
        return DateUtils.getDate(calendar.getTime(),formate);
    }

    public static void  t45(){
        Calendar cal = Calendar.getInstance();
        cal.set(2019,10,11);
        Date time = cal.getTime();
        DateUtils.getNowDate();
        System.out.println("当前指定日期是："+new SimpleDateFormat("yyyy-MM-dd").format(time));
        cal.add(Calendar.DAY_OF_MONTH,-45);
        cal.getTime();
        System.out.println("前两天的日期是 "+new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
    }


    public static void main(String[] args) {
    }
}
