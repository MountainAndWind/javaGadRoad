package test.EXCEL.git;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import util.DateUtils;
import test.EXCEL.ExcelProtUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * @description:
 * @author: slfang
 * @time: 2020/2/17 14:21
 */
public class TestOne {

    public static void main(String[] args) {
        List<BeanInfo> infos = new ArrayList();
        List<TestInfo> testInfos = new ArrayList<>();
        InitData(testInfos);
        int count=0;//计数器
        int counts=0;//封装一个完整对象需要遍历的个数
        int totalCounts=0;
        BeanInfo beanInfo = null;
        for (TestInfo testInfo : testInfos) {//
            ++count;
            if(count==1){//
                beanInfo = new BeanInfo(testInfo.getSqr());
            }
            reSetMonthByDate(beanInfo,testInfo);
            totalCounts+=Integer.valueOf(testInfo.getJe());
            String total = testInfo.getTotal();
            counts=Integer.valueOf(total);
            if(count==counts){//
                beanInfo.setTotal(""+totalCounts);
                infos.add(beanInfo);
                totalCounts=0;
                count=0;
                counts=0;
            }
        }
        ExcelUtils excelUtils = new ExcelUtils(infos, getHeaderInfo());

        HSSFWorkbook workbook = (HSSFWorkbook)excelUtils.getWorkbook();
        HSSFSheet sheet = workbook.getSheet("sheet0");

        reSetStyle(workbook,sheet);

        OutputStream os=null;
        //OutputStreamWriter op = null;
        String path="E:/file";
        try {
            String date = DateUtils.getDate();
            os = new FileOutputStream(new File(path+"\\"+date+".xls"));
            //op = new OutputStreamWriter(os, "utf-8");
            os.flush();
            workbook.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static void reSetStyle(HSSFWorkbook workbook, HSSFSheet sheet) {
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(10);
        HSSFRow row = sheet.getRow(0);
        //ExcelProtUtils.setStyle(row.getCell(0),"宋体","1","RIGHT",workbook);
        for (int i = 0; i <14 ; i++) {
           if(i==0){
               ExcelProtUtils.setStyle(row.getCell(i),"宋体","1","CENTER",workbook);
           }else{
               ExcelProtUtils.setStyle(row.getCell(i),"宋体","1","CENTER",workbook);
           }
        }
    }

    private static void reSetMonthByDate(BeanInfo beanInfo, TestInfo testInfo) {
        String date = testInfo.getDate();
        String month = date.split("-")[1];
        System.out.println("month月::"+month);
        if("01".equals(month)){
            beanInfo.setMoney_01(testInfo.getJe());
        }else if("02".equals(month)){
            beanInfo.setMoney_02(testInfo.getJe());
        }else if("03".equals(month)){
            beanInfo.setMoney_03(testInfo.getJe());
        }else if("04".equals(month)){
            beanInfo.setMoney_04(testInfo.getJe());
        }else if("05".equals(month)){
            beanInfo.setMoney_05(testInfo.getJe());
        }else if("06".equals(month)){
            beanInfo.setMoney_06(testInfo.getJe());
        }else if("07".equals(month)){
            beanInfo.setMoney_07(testInfo.getJe());
        }else if("08".equals(month)){
            beanInfo.setMoney_08(testInfo.getJe());
        }else if("09".equals(month)){
            beanInfo.setMoney_09(testInfo.getJe());
        }else if("10".equals(month)){
            beanInfo.setMoney_10(testInfo.getJe());
        }else if("11".equals(month)){
            beanInfo.setMoney_11(testInfo.getJe());
        }else if("12".equals(month)){
            beanInfo.setMoney_12(testInfo.getJe());
        }
    }

    private static void InitData(List<TestInfo> infos) {
        infos.add(new TestInfo("1","小王","2020-01-01","12","2"));
        infos.add(new TestInfo("2","小王","2020-03-28","32","2"));
        /*infos.add(new TestInfo("3","小王1","2019-09-27","45","3"));
        infos.add(new TestInfo("4","小王1","2019-11-12","56","3"));
        infos.add(new TestInfo("5","小王1","2019-12-23","50","3"));
        infos.add(new TestInfo("6","小王2","2019-06-09","20","2"));
        infos.add(new TestInfo("7","小王2","2019-05-19","21","2"));*/
    }

    // 获取表头信息
    private static   List<ExcelHeaderInfo> getHeaderInfo() {
        return Arrays.asList(
                new ExcelHeaderInfo(0, 0, 0, 0, "姓名"),
                new ExcelHeaderInfo(0, 0, 1, 1, "1月"),
                new ExcelHeaderInfo(0, 0, 2, 2, "2月"),
                new ExcelHeaderInfo(0, 0, 3, 3, "3月"),
                new ExcelHeaderInfo(0, 0, 4, 4, "4月"),
                new ExcelHeaderInfo(0, 0, 5, 5, "5月"),
                new ExcelHeaderInfo(0, 0, 6, 6, "6月"),
                new ExcelHeaderInfo(0, 0, 7, 7, "7月"),
                new ExcelHeaderInfo(0, 0, 8, 8, "8月"),
                new ExcelHeaderInfo(0, 0, 9, 9, "9月"),
                new ExcelHeaderInfo(0, 0, 10, 10, "10月"),
                new ExcelHeaderInfo(0, 0, 11, 11, "11月"),
                new ExcelHeaderInfo(0, 0, 12, 12, "12月"),
                new ExcelHeaderInfo(0, 0, 13, 13, "total")
        );
    }



}
