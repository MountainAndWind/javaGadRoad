package test.EXCEL;

import org.apache.poi.hssf.usermodel.*;
import util.DateUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Test3 {


    static HSSFRow contentRow = null;
    public static void main(String[] args) {
        Test3 t = new Test3();

        //创建工作簿
        HSSFWorkbook wb=new HSSFWorkbook();
        //创建一个sheet
        HSSFSheet sheet=wb.createSheet("手机费");
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(10);
        mobileRenderHead(sheet,wb);
        mobileRender2(1,sheet,wb);
        for (int i = 5; i <=12 ; i++) {

            if(i==0){
                sheet.getRow(1).getCell(i).setCellValue("张三");
            }else{
                sheet.getRow(1).getCell(i).setCellValue("1");
            }
        }
        mobileRender2(2,sheet,wb);
        for (int i = 0; i <=12 ; i++) {
            if(i==0){
                sheet.getRow(2).getCell(i).setCellValue("张三2");
            }else{
                sheet.getRow(2).getCell(i).setCellValue("2");
            }
        }
        mobileRender2(3,sheet,wb);
        for (int i = 0; i <=12 ; i++) {
            if(i==0){
                sheet.getRow(3).getCell(i).setCellValue("张三3");
            }else{
                sheet.getRow(3).getCell(i).setCellValue("3");
            }
        }
        packageData(sheet);

        OutputStream os=null;
        //OutputStreamWriter op = null;
        String path="E:/file";
        try {
            String date = DateUtils.getDate();
            os = new FileOutputStream(new File(path+"\\"+date+".xls"));
            //op = new OutputStreamWriter(os, "utf-8");
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

    private static void mobileRenderHead( HSSFSheet sheet, HSSFWorkbook wb){
        System.out.println("设置头部信息");
        contentRow=sheet.createRow(0);
        for (int i = 0; i <= 13; i++) {
            if(i==0){
                ExcelProtUtils.setStyle(contentRow.createCell((int)i),"宋体","","","Name","BOLD",wb);
            }else if(i==13){
                ExcelProtUtils.setStyle(contentRow.createCell((int)i),"Arial","","RIGHT","Total",wb);
            }else{
                ExcelProtUtils.setStyle(contentRow.createCell((int)i),"Arial","","RIGHT",i+"月",wb);
            }
        }
    }



    /**
     * 渲染excel表格
     * @param sheet
     * @param wb
     */
    private static void mobileRender2(int index, HSSFSheet sheet, HSSFWorkbook wb) {
        System.out.println("mobileRender::");
        System.out.println("total:"+index);
        contentRow = sheet.createRow(index);
        System.out.print("设置数据");
        for (int i = 0; i <=13 ; i++) {
            if(i==0){
                ExcelProtUtils.setStyle(contentRow.createCell((int)i),"宋体","","RIGHT",wb);
            }else{
                ExcelProtUtils.setStyle(contentRow.createCell((int)i),"Arial","","RIGHT",wb);
            }
        }
    }

    /**
     * 渲染excel表格
     * @param total 记录总数
     * @param sheet
     * @param wb
     */
    private static void mobileRender(int total, HSSFSheet sheet, HSSFWorkbook wb) {
        System.out.println("mobileRender::");
        System.out.println("total:"+total);
        for (int j = 0; j <=total ; j++) {
            contentRow = sheet.createRow(j);
            if(j==0){
                System.out.println("设置头部信息");
                for (int i = 0; i <= 13; i++) {
                    if(i==0){
                        ExcelProtUtils.setStyle(contentRow.createCell((int)i),"宋体","","","Name","BOLD",wb);
                    }else if(i==13){
                        ExcelProtUtils.setStyle(contentRow.createCell((int)i),"Arial","","RIGHT","Total",wb);
                    }else{
                        ExcelProtUtils.setStyle(contentRow.createCell((int)i),"Arial","","RIGHT",i+"月",wb);
                    }
                }
            }else{
                System.out.print("设置数据");
                for (int i = 0; i <=13 ; i++) {
                    if(i==0){
                        ExcelProtUtils.setStyle(contentRow.createCell((int)i),"宋体","","RIGHT",wb);
                    }else{
                        ExcelProtUtils.setStyle(contentRow.createCell((int)i),"Arial","","RIGHT",wb);
                    }
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
     * @param sheet
     */
    private static void packageData( HSSFSheet sheet) {
        /*遍历*/
        for(int rowNum = 1; rowNum<=sheet.getLastRowNum();rowNum++){
            //获取每一行
            HSSFRow row = sheet.getRow(rowNum);
            if(row == null){
                continue;
            }
            Integer total =0;
            //遍历列cell
            for(int cellNum = 1; cellNum<=row.getLastCellNum()-2;cellNum++){
                //获取每一列
                HSSFCell cell = row.getCell(cellNum);
                if(cell == null){
                    continue;
                }
                System.out.println(cell.getCellType().getCode());
                System.out.println(cell.getCellType());
                System.out.println(cell.getStringCellValue());
                Integer cellValue = withStringNullBindTo0(cell.getStringCellValue());
                total+=cellValue;
                if(cellNum==12){
                    row.getCell(13).setCellValue(total);
                }

            }
        }
    }




}
