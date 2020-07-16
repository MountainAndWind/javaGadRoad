package test.date;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description:
 * @author: slfang
 * @time: 2020/7/15 10:20
 */
public class DataTest3 {


    @Test
    public void Test1() throws ParseException {
        String rq = "2002-10-02";
        System.out.println(rq.substring(0,rq.length()-2));
        System.out.println(Integer.valueOf(rq.substring(8,10)));
        System.out.println(getWeekedDays("2020-07-10","2020-07-15"));
    }

    /**
     * ������ĩ��
     * @return
     */
    public  int getWeekedDays(String v1, String v2) throws ParseException {
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
