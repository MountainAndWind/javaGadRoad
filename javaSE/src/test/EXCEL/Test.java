package test.EXCEL;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import util.DateUtils;

import java.io.*;

public class Test {

    public static void main(String[] args) {
      /*  HashMap<String,HashMap<String,HashMap<String,String>>> Onemap = new HashMap<>();
        HashMap<String,HashMap<String,String>> Twomap1 = new HashMap<>();
        HashMap<String,HashMap<String,String>> Twomap2 = new HashMap<>();
        HashMap<String,String> threeMap1 = new HashMap<>();
        HashMap<String,String> threeMap2 = new HashMap<>();
        threeMap1.put("1","123");
        threeMap1.put("2","543");
        threeMap1.put("3","5");
        threeMap1.put("11","31");
        threeMap1.put("8","66");

        threeMap2.put("1","123");
        threeMap2.put("2","543");
        threeMap2.put("3","5");
        threeMap2.put("11","31");
        threeMap2.put("8","66");
        Twomap1.put("zhangsan",threeMap1);
        Twomap2.put("lisi",threeMap2);

        Onemap.put("")
*/

        exportExcel("ceshi312311");

    }



    public static <E> void exportExcel(String fileName) {
        String[] header = "学习支出（元）,娱乐支出（元）,伙食支出（元）".split(",");
        //创建工作簿
        HSSFWorkbook wb=new HSSFWorkbook();
        //创建一个sheet
        HSSFSheet sheet=wb.createSheet(fileName);
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(10);

       /* HSSFRow headerRow=sheet.createRow(0);*/
        HSSFRow contentRow=null;

        //设置标题
       /* for(int i=0;i<header.length;i++){
            headerRow.createCell(i).setCellValue(header[i]);
        }*/
        try {
           /* for(int j=0;j<3;j++){
                contentRow=sheet.createRow(j+1);
                for (int i = 0; i <3 ; i++) {
                    contentRow.createCell(i).setCellValue("value"+j+i);
                }
            }*/

           /* for(int i=0;i<list.size();i++){
                contentRow=sheet.createRow(i*6);
                //获取每一个对象
                E o=list.get(i);
                Class cls=o.getClass();
                for(int j=0;j<fileNames.length;j++){
                    设置行数据
                    contentRow.createCell(j).setCellValue(value.toString());
                }
            }*/
            contentRow=sheet.createRow(0);
            HSSFCell cell0 = contentRow.createCell(0);
            setHeadStyle(cell0,"周勇",wb);
            for (int i = 1; i <13 ; i++) {
                HSSFCell cell = contentRow.createCell(i);
                setStyleAndVal(cell,i+"月",wb);
            }

            contentRow=sheet.createRow(1);
            HSSFCell cell2 = contentRow.createCell(0);
            setStyleAndVal(cell2,"汽油费",wb);

            for (int i = 0; i<3 ; i++) {
                setStyleData(contentRow,i+1,"22",wb);
            }
            HSSFCell cell = contentRow.createCell(10);
            cell.setCellValue("10");


            contentRow=sheet.createRow(2);
            HSSFCell cell3 = contentRow.createCell(0);
            setStyleAndVal(cell3,"停车费",wb);


            for (int i = 0; i<3 ; i++) {
                setStyleData(contentRow,i+1,"22",wb);
            }
            contentRow=sheet.createRow(3);
            HSSFCell cellTotal = contentRow.createCell(0);
            setStyleTotal(cellTotal,"合计2000",wb);

            for (int i = 0; i<3 ; i++) {
                /*setStyleData(contentRow,i+1,"22",wb);*/
                HSSFCell totalContent = contentRow.createCell(i);
                String colString = CellReference.convertNumToColString(i);//长度转成ABC列
                String sumstring = "SUM(" + colString + "1:" + colString + 3 + ")";//求和公式
                sheet.getRow(3).getCell(i).setCellFormula(sumstring);
            }
            //contentRow=sheet.createRow(4);

            contentRow=sheet.createRow(5);
            HSSFCell cell6 = contentRow.createCell(0);
            cell6.setCellValue("张三");
            setHeadStyle(cell6,"张三",wb);

            for (int i = 1; i <13 ; i++) {
                HSSFCell cellMonth = contentRow.createCell(i);
                setStyleAndVal(cellMonth,i+"月",wb);
            }
            contentRow=sheet.createRow(6);
            setStyleAndVal(contentRow.createCell(0),"汽油费",wb);
            for (int i = 0; i<3 ; i++) {
                setStyleData(contentRow,i+1,"22",wb);
            }
            contentRow=sheet.createRow(7);
            HSSFCell cell1 = contentRow.createCell(0);
            setStyleAndVal(cell1,"停车费",wb);
            for (int i = 0; i<3 ; i++) {
                setStyleData(contentRow,i+1,"22",wb);
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        OutputStream os=null;
        //OutputStreamWriter op = null;
        try {
            String date = DateUtils.getDate();
            os = new FileOutputStream(new File("E:\\"+date+".xls"));
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

    private static void setStyleTotal(HSSFCell cellTotal, String value, HSSFWorkbook wb) {
        cellTotal.setCellValue(value);
        // 设置字体
        CellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 10);
        //字体
        font.setFontName("宋体");
        style.setFont(font);
        //4.设置单元格背景色
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);//填充单元格
        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        setBorderWight(style);
        cellTotal.setCellStyle(style);

    }

    private static void setStyleData(HSSFRow contentRow, int i, String s, HSSFWorkbook wb) {
        HSSFCell cell = contentRow.createCell(i);
        cell.setCellValue(s);
        // 设置字体
        CellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 10);
        //字体
        font.setFontName("Arial");
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.RIGHT);
        setBorderWight(style);
        cell.setCellStyle(style);
    }

    private static void setHeadStyle(HSSFCell cell, String value, HSSFWorkbook wb) {//
        cell.setCellValue(value);
        // 设置字体
        CellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 10);
        //字体
        font.setFontName("宋体");
        style.setFont(font);
        //4.设置单元格背景色
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);//填充单元格
        style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        setBorderWight(style);
        cell.setCellStyle(style);
    }

    private static void setStyleAndVal(HSSFCell cell1, String s, HSSFWorkbook wb) {
        cell1.setCellValue(s);
        // 设置字体
        CellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();

        //设置字体大小
        font.setFontHeightInPoints((short) 10);
        //字体
        if(!"停车费".equals(s)&&!"汽油费".equals(s)&&!"过路费".equals(s)){
            font.setFontName("Arial");
        }
        font.setFontName("宋体");
        style.setFont(font);
        setBorderWight(style);
        cell1.setCellStyle(style);
    }


    /**
     * 设置边框加粗
     * @param style
     */
    static void setBorderWight(CellStyle style){
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
    }


}
