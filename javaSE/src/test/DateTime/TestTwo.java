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

     * ���㹤����

     * ������հ�����Щ,������HolidayMap���޸�

     * @param src ����(Դ)

     * @param adddays Ҫ�ӵ�����

     * @exception throws [Υ������] [Υ��˵��]

     * @version  [s001, 2010-11-19]

     * @author  shenjunjie

     */

    public static Calendar addDateByWorkDay(Calendar src,int adddays)

    {

//        Calendar result = null;

        holidayFlag = false;

        for (int i = 0; i < adddays; i++)

        {

            //��Դ���ڼ�һ��

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

     * У��ָ���������Ƿ��ڽ����б���

     * ������հ�����Щ,������HolidayMap���޸�

     * @param src ҪУ�������(Դ)

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

        //�ȼ���Ƿ�����������(��Щ��������������)

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

     * ��ʼ������List,�����Ҫ�����µĽ���,�����������

     * �ӵ�ʱ���뾡��ʹ��Calendar�Դ��ĳ���������ħ������

     * ע:��ݿ������д,��Ϊ�ȵ�ʱ��ֻ���·ݺ���

     * @version  [s001, 2010-11-19]

     * @author  shenjunjie

     */

    private static void initHolidayList()

    {

        holidayList = new ArrayList();



        //��һ�Ͷ���

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



        //�й�ĸ�׽ڣ����µĵڶ���������

        Calendar may5 = Calendar.getInstance();

        //�����·�Ϊ5��

        may5.set(Calendar.MONTH,Calendar.MAY);

        //��������:��2������

        may5.set(Calendar.DAY_OF_WEEK_IN_MONTH,2);

        //������

        may5.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);

//        System.out.println(may5.getTime());



        holidayList.add(may5);

    }
}
