/*
 * Copyright @ 2015 com.iflysse.trains
 * manager2 上午11:32:17
 * All right reserved.
 *
 */
package test.EXCEL;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.List;

/**
 * @desc: excel工具
 * @author: bingye@com.com
 * @createTime: 2015-4-21 上午11:32:17
 * @version: v1.0
 */
public class ExcelUtils {
	
	/**
	 * 
	 *  ｛说明该函数的含义和作用，如果函数较为复杂，请详细说明｝
	 *  @param codes  字段code
	 *  @param fileName  excel表格名称
	 *  @param list
	 *  @author xzran
	 *  @created 2017年7月19日 下午2:11:32
	 *  @lastModified       
	 *  @history
	 */
	public static <E> void exportExcel(String codes, String fileName, List<E> list) {
		
		//String [] header = headers.split(",");
		String [] fileNames = codes.split(",");
		//创建工作簿
		HSSFWorkbook wb=new HSSFWorkbook();
		//创建一个sheet
		HSSFSheet sheet=wb.createSheet(fileName);
		
		HSSFRow contentRow=null;
		
		/*//设置标题
		HSSFRow headerRow=sheet.createRow(0);
		for(int i=0;i<header.length;i++){
			headerRow.createCell(i).setCellValue(header[i]);
		}*/
		try {
			for(int i=0;i<list.size();i++){
				contentRow=sheet.createRow(i*6);
				//获取每一个对象
				E o=list.get(i);
				Class cls=o.getClass();
				for(int j=0;j<fileNames.length;j++){
					/*设置行数据*/
					//contentRow.createCell(j).setCellValue(value.toString());
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream("E:\\");
			wb.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(outputStream!=null){
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}



}
