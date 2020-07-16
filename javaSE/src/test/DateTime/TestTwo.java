package test.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @description:
 * @author: slfang
 * @time: 2020/5/11 13:54
 */
public class TestTwo {

    private static List<Calendar> holidayList;

    private static boolean holidayFlag;



    /**

     * 计算工作日

     * 具体节日包含哪些,可以在HolidayMap中修改

     * @param src 日期(源)

     * @param adddays 要加的天数

     * @exception throws [违例类型] [违例说明]

     * @version  [s001, 2010-11-19]

     * @author  shenjunjie

     */

    public static Calendar addDateByWorkDay(Calendar src,int adddays)

    {

//        Calendar result = null;

        holidayFlag = false;

        for (int i = 0; i < adddays; i++)

        {

            //把源日期加一天

            src.add(Calendar.DAY_OF_MONTH, 1);

            holidayFlag =checkHoliday(src);

            if(holidayFlag)

            {

                i--;

            }

            System.out.println(src.getTime());

        }

        System.out.println("Final Result:"+src.getTime());

        return src;

    }



    /**

     * 校验指定的日期是否在节日列表中

     * 具体节日包含哪些,可以在HolidayMap中修改

     * @param src 要校验的日期(源)

     * @version  [s001, 2010-11-19]

     * @author  shenjunjie

     */

    public static boolean checkHoliday(Calendar src)

    {

        boolean result = false;

        if (holidayList == null)

        {

            initHolidayList();

        }

        //先检查是否是周六周日(有些国家是周五周六)

        if (src.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY

                || src.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)

        {

            return true;

        }

        for (Calendar c : holidayList)

        {

            if (src.get(Calendar.MONTH) == c.get(Calendar.MONTH)

                    && src.get(Calendar.DAY_OF_MONTH) == c.get(Calendar.DAY_OF_MONTH))

            {

                result = true;

            }

        }

        return result;

    }



    /**

     * 初始化节日List,如果需要加入新的节日,请在这里添加

     * 加的时候请尽量使用Calendar自带的常量而不是魔鬼数字

     * 注:年份可以随便写,因为比的时候只比月份和天

     * @version  [s001, 2010-11-19]

     * @author  shenjunjie

     */

    private static void initHolidayList()

    {

        holidayList = new ArrayList();



        //五一劳动节

        Calendar may1 = Calendar.getInstance();

        may1.set(Calendar.MONTH,Calendar.MAY);

        may1.set(Calendar.DAY_OF_MONTH,1);

        holidayList.add(may1);



        Calendar may2 = Calendar.getInstance();

        may2.set(Calendar.MONTH,Calendar.MAY);

        may2.set(Calendar.DAY_OF_MONTH,2);

        holidayList.add(may2);



        Calendar may3 = Calendar.getInstance();

        may3.set(Calendar.MONTH,Calendar.MAY);

        may3.set(Calendar.DAY_OF_MONTH,3);

        holidayList.add(may3);



        Calendar h3 = Calendar.getInstance();

        h3.set(2000, 1, 1);

        holidayList.add(h3);



        Calendar h4 = Calendar.getInstance();

        h4.set(2000, 12, 25);

        holidayList.add(h4);



        //中国母亲节：五月的第二个星期日

        Calendar may5 = Calendar.getInstance();

        //设置月份为5月

        may5.set(Calendar.MONTH,Calendar.MAY);

        //设置星期:第2个星期

        may5.set(Calendar.DAY_OF_WEEK_IN_MONTH,2);

        //星期日

        may5.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);

//        System.out.println(may5.getTime());



        holidayList.add(may5);

    }
}
