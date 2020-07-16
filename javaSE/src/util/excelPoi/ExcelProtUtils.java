package util.excelPoi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import util.StringUtils;

/**
 * excel导出工具类
 */
public class ExcelProtUtils {


     static void setStyle(HSSFCell cell,String fontName,String bgColor,String HorizontalWay ,String value, HSSFWorkbook wb) {//
        cell.setCellValue(value);
        // 设置字体
        CellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
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
     * 设置边框加粗
     * @param style
     */
    static void setBorderWight(CellStyle style){
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
    }

    /**
     * 设置样式
     * @param cell
     * @param fontName
     * @param bgColor
     * @param HorizontalWay
     * @param wb
     */
    static void setStyle(HSSFCell cell,String fontName,String bgColor,String HorizontalWay , HSSFWorkbook wb) {//
        // 设置字体
        CellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
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
}
