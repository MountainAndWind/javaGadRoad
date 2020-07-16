package test.String;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * @description:
 * @author: slfang
 * @time: 2020/2/24 14:43
 */
public class ConvertTest {

    public static void main(String[] args) throws ParseException {
       /* String je="194.90";
        String[] split = je.split(".");
        Float aDouble = Float.valueOf(je);
        System.out.println(aDouble);*/
       /* String format = String.format("%03d", "1");
        System.out.println(format);
        String format2 = String.format("%03d", 80);
        System.out.println(format2);
        String format3 = String.format("%03d", 1020);
        System.out.println(format3);*/
        String s="00012";
        System.out.println(Integer.valueOf(s));
        // ָ��һ������

        //���������������Ƿ� ȥ�� ˫����
        System.out.println(computeHolidays("2020-04-29","2020-05-11"));
    }

    public static int computeHolidays(String v1, String v2) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date t1 = dateFormat.parse(v1);
        Date t2 = dateFormat.parse(v2);
        //��ʼ����һ������
        Calendar cal1 = Calendar.getInstance();
        //��ʼ���ڶ������ڣ����������������������
        Calendar cal2 = Calendar.getInstance();


        // ָ��һ������
        Date date1 = dateFormat.parse(dateFormat.format(t1));
        Date date2 = dateFormat.parse(dateFormat.format(t2));
        // �� calendar ����Ϊ date ����������
        cal1.setTime(date1);
        cal2.setTime(date2);

        int holidays = 0;
        //ȷ��һ�� ������
        if(cal1.compareTo(cal2) > 0){
            Calendar temp = cal1;
            cal1 = cal2;
            cal2 = temp;
            temp = null;
        }
        while(cal1.compareTo(cal2)<=0){
            if(cal1.get(Calendar.DAY_OF_WEEK)==1||cal1.get(Calendar.DAY_OF_WEEK)==7){
                holidays++;
                System.out.println("��ĩ��"+new SimpleDateFormat("yyyy-MM-dd").format(cal1.getTime()));
            }
            cal1.add(Calendar.DAY_OF_YEAR,1);

        }
        return holidays;
    }
}
