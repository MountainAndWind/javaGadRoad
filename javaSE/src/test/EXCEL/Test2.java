package test.EXCEL;

import org.apache.poi.hssf.usermodel.*;
import util.DateUtils;
import test.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Test2 {


    static HSSFRow contentRow0 = null;
    static HSSFRow contentRow1 = null;
    static HSSFRow contentRow2 = null;
    static HSSFRow contentRow3 = null;
    static HSSFRow contentRow4 = null;
    public static void main(String[] args) {
        Test2 t = new Test2();

       //创建工作簿
        HSSFWorkbook wb=new HSSFWorkbook();
        //创建一个sheet
        HSSFSheet sheet=wb.createSheet("汽油费");
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(10);

        HSSFRow headerRow=sheet.createRow(0);

        HSSFRow contentRow=null;

       t.renderCell(sheet,wb,0,"张三112");
        for (int i = 1; i <12 ; i++) {
            contentRow1.getCell(i).setCellValue("1");
        }
        for (int i = 5; i <12 ; i++) {
            contentRow2.getCell(i).setCellValue("22");
        }
        for (int i = 5; i <12 ; i++) {
            contentRow3.getCell(i).setCellValue("33");
        }

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        packageData(list1,list2,list3,0,sheet);
        for (int i = 1; i < 13; i++) {
            int total = withStringNullBindTo0(list1.get(i-1))+ withStringNullBindTo0(list2.get(i-1))+ withStringNullBindTo0(list3.get(i-1));
            System.out.println(total);
            contentRow4.getCell(i).setCellValue(total);
        }


        t.renderCell(sheet,wb,1,"王五");
        for (int i = 1; i <12 ; i++) {
            contentRow1.getCell(i).setCellValue("44");
        }
        for (int i = 1; i <12 ; i++) {
            contentRow2.getCell(i).setCellValue("55");
        }
        for (int i = 1; i <12 ; i++) {
            contentRow3.getCell(i).setCellValue("66");
        }

        List<String> list4 = new ArrayList<>();
        List<String> list5 = new ArrayList<>();
        List<String> list6 = new ArrayList<>();
        packageData(list4,list5,list6,1,sheet);
        for (int i = 1; i < 13; i++) {
            int total = withStringNullBindTo0(list4.get(i-1))+ withStringNullBindTo0(list5.get(i-1))+ withStringNullBindTo0(list6.get(i-1));
            System.out.println(total);
            contentRow4.getCell(i).setCellValue(total);
        }

        HSSFCell cell = sheet.getRow(1).getCell(1);
        String stringCellValue = cell.getStringCellValue();
        if(StringUtils.isNotBlank(stringCellValue)){
            Integer integer = withStringNullBindTo0(cell.getStringCellValue());
            cell.setCellValue(integer+1);
        }

        OutputStream os=null;
        //OutputStreamWriter op = null;
        String path="E:/file/";
        try {
            String date = DateUtils.getDate();
            System.out.println(path+"三项统计报表-"+date+".xls");
            os = new FileOutputStream(new File(path+""+"三项统计报表"+".xls"));
            os.flush();
            wb.write(os);
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

    /**
     * 处理字符串转换成数字
     * @param value
     * @return
     */
    static Integer withStringNullBindTo0(String value){
        if ("".equals(value)||value==null) {
              return 0;
        }else{
            return Integer.valueOf(value);
        }
    }


    /**
     * 封装求和数据
     * @param list1
     * @param list2
     * @param list3
     * @param i
     * @param sheet
     */
    private static void packageData(List<String> list1, List<String> list2, List<String> list3, int i, HSSFSheet sheet) {
        i=i*6;
        /*遍历*/
        for(int rowNum = i+1; rowNum<=sheet.getLastRowNum()-1;rowNum++){
            //获取每一行
            HSSFRow row = sheet.getRow(rowNum);
            if(row == null){
                continue;
            }
            Integer total=0;
            //遍历列cell
            for(int cellNum = 1; cellNum<=row.getLastCellNum()-2;cellNum++){
                //获取每一列
                HSSFCell cell = row.getCell(cellNum);
                if(cell == null){
                    continue;
                }

                System.out.println("开始横向求和");
                Integer cellValue = withStringNullBindTo0(cell.getStringCellValue());
                total+=cellValue;
                if(cellNum==12){
                    row.getCell(13).setCellValue(total);
                    System.out.println("结束横向求和");
                }

                System.out.println("开始封装纵向求和list");
                System.out.println(" "+cell.getStringCellValue());
                if(rowNum==i+1){
                    list1.add(cell.getStringCellValue());
                }
                if(rowNum==i+2){
                    list2.add(cell.getStringCellValue());
                }
                if(rowNum==i+3){
                    list3.add(cell.getStringCellValue());
                }
            }
        }
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list3);
    }



    private void renderCell(HSSFSheet sheet, HSSFWorkbook wb, int i,String sqr) {
        System.out.println("renderCell::");
        i = i * 6;
        System.out.println("当前申请人："+sqr);
        System.out.println("i:"+i);
        contentRow0 = sheet.createRow(i);
        HSSFCell cell0 = contentRow0.createCell((short)0);
        ExcelProtUtils.setStyle(cell0,"宋体","1","",sqr,wb);
        for (int j = 1; j < 14; j++) {
            if (j == 13) {//Arial
                ExcelProtUtils.setStyle(contentRow0.createCell(j),"Arial","","","Total",wb);
            } else {
                ExcelProtUtils.setStyle(contentRow0.createCell((int)j),"Arial","","",j+"月",wb);
            }
        }
        contentRow1 = sheet.createRow(i + 1);
        ExcelProtUtils.setStyle(contentRow1.createCell((int)0),"宋体","","","停车费",wb);
        contentRow2 = sheet.createRow(i + 2);
        ExcelProtUtils.setStyle(contentRow2.createCell((int)0),"宋体","","","过路费",wb);
        contentRow3 = sheet.createRow(i + 3);
        ExcelProtUtils.setStyle(contentRow3.createCell((int)0),"宋体","","","汽油费",wb);
        contentRow4 = sheet.createRow(i + 4);
        ExcelProtUtils.setStyle(contentRow4.createCell((int)0),"宋体","Yellow","","合计2000",wb);

        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 13; k++) {
                if(j==0){
                    ExcelProtUtils.setStyle(contentRow1.createCell((int)k+1),"Arial","","RIGHT",wb);
                }
                if(j==1){
                    ExcelProtUtils.setStyle(contentRow2.createCell((int)k+1),"Arial","","RIGHT",wb);
                }
                if(j==2){
                    ExcelProtUtils.setStyle(contentRow3.createCell((int)k+1),"Arial","","RIGHT",wb);
                }
                if(j==3){
                    ExcelProtUtils.setStyle(contentRow4.createCell((int)k+1),"Arial","","RIGHT",wb);
                }
            }
        }
    }

}
