package test.EXCEL;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import util.DateUtils;
import util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description GL流程数据生成excel ACTION
 * @Author fangshilei
 * @Date 2019/12/20 13:23
 * @Version 1.0
 **/
public class DongZhiGLExcelExportAction {


    static HSSFRow contentRow = null;

    public static void main(String[] args) {
        Map<String, Object> baseInfo = new HashMap<>();
        OutputStream os=null;
        resetRow();
        HSSFWorkbook wb=new HSSFWorkbook();
        CellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        HSSFSheet sheet=wb.createSheet("GL数据");

        String head = "type,2017,2018,2019,2020";
        String nexthead ="核定合同额,到账金额,余款";
        String[] nextHeadSp = nexthead.split(",");
        String path = "E:/file/";
        String codes = "";
        String filePre = "各年度到账及余款情况表";

        String[] splitHead = head.split(",");
        renderHead(sheet,wb,splitHead,font,style);

        String[] splitCodes = codes.split(",");
        String sql="select "+codes+" from  GLLIST";
        int index=0;

        HSSFRow row = sheet.getRow(1);
        contentRow = sheet.createRow(1);
        setStyle(contentRow.createCell((int)0),"Arial","","RIGHT","到账时间",font,style);
        for (int i = 1; i <13 ; i++) {
            for (String s : nextHeadSp) {
                setStyle(contentRow.createCell((int)i),"Arial","","RIGHT",s,font,style);
            }
        }


       /* while (rs.next()){
            System.out.println("行遍历------------------------------");
            render(++index,sheet,style,font,splitCodes,rs);
        }*/
        String filepath=path+filePre+"-"+ DateUtils.getDate()+".xls";
        try {
            os = new FileOutputStream(new File(filepath));
            os.flush();
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    System.out.println();
                    e.printStackTrace();
                    System.out.println("系统报错：："+e.getMessage());
                }
            }
        }
    }



    /**
     * 渲染手机费
     * @param sheet
     * @param wb
     * @param head
     * @param font
     * @param style
     */
    private static   void renderHead(HSSFSheet sheet, HSSFWorkbook wb, String[] head, HSSFFont font, CellStyle style){
        contentRow=sheet.createRow(0);
        int index=1;
        for (int i = 0; i < head.length; i++) {
            String value = head[i];
           if(i==0){
               setStyle(contentRow.createCell((int)i),"Arial","1","CENTER",value,font,style);
           }else{
               setStyle(contentRow.createCell((int)index),"Arial","1","CENTER",value,font,style);
               // 合并单元格：参数：起始行, 终止行, 起始列, 终止列
               sheet.addMergedRegion(new CellRangeAddress(0,0,index,index+2));
               index = index+3;
           }
        }
    }


    private static void resetRow() {
        contentRow = null;
    }


    /**
     * 渲染excel表格
     * @param style
     * @param font
     */
    /*private  void render(int index, HSSFSheet sheet, CellStyle style, HSSFFont font, String[] splitCodes, RecordSet rs) {
        contentRow = sheet.createRow(index);
        for (int i = 0; i <splitCodes.length ; i++) {
            String codeName=splitCodes[i];
            System.out.println("codeName::"+codeName);
            if(!"SBU".equals(codeName)){
                String value = Util.null2String(rs.getString(splitCodes[i]));
                setStyle(contentRow.createCell((int)i),"Arial","","RIGHT",value,font,style);
            }else{
                RecordSet rs2 = new RecordSet();
                String centerVal = Util.null2String(rs.getString("CENTER"));
                System.out.println("centerVal::"+centerVal);
                String sql="select case substr('"+centerVal+"',1,2)\n" +
                        "when 'AG' THEN 'B30'\n" +
                        "WHEN 'AS' THEN 'B40'\n" +
                        "ELSE (SELECT CASE substr('"+centerVal+"',-1,1)\n" +
                        "WHEN 'G' then 'B50'\n" +
                        "ELSE 'B20'\n" +
                        "END\n" +
                        "FROM dual )\n" +
                        "END\n" +
                        "FROM dual";
                rs2.execute(sql);
                System.out.println("sql::"+sql);
                rs2.next();
                String val = rs2.getString(1);
                System.out.println("val::"+val);
                setStyle(contentRow.createCell((int)i),"Arial","","RIGHT",val,font,style);
            }
        }
    }*/

    public static void setStyle(HSSFCell cell, String fontName, String bgColor, String HorizontalWay , String value, HSSFFont font, CellStyle style) {//
        cell.setCellValue(value);
        //设置字体大小
        font.setFontHeightInPoints((short) 10);
        //字体
        if(StringUtils.isNotBlank(fontName)){
            font.setFontName(fontName);
        }
        style.setFont(font);
        if(StringUtils.isNotBlank(bgColor)){
            //4.设置单元格背景色
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);//填充单元格
            style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        }
        if(StringUtils.isNotBlank(HorizontalWay)){
            if("RIGHT".equals(HorizontalWay)){
                style.setAlignment(HorizontalAlignment.RIGHT);
            }
            if("LEFT".equals(HorizontalWay)){
                style.setAlignment(HorizontalAlignment.LEFT);
            }
            if("CENTER".equals(HorizontalWay)){
                style.setAlignment(HorizontalAlignment.CENTER);
            }
        }
        setBorderWight(style);
        cell.setCellStyle(style);
    }


    /**
     * 设置边框
     * @param style
     */
    public static  void  setBorderWight(CellStyle style){
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
    }
}
