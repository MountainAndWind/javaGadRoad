package test.EXCEL;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import util.DateUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Test5 {


    static HSSFRow contentRow = null;
    public static void main(String[] args) {
        Test5 t = new Test5();

        //����������
        HSSFWorkbook wb=new HSSFWorkbook();
        //����һ��sheet
        HSSFSheet sheet=wb.createSheet("�ֻ���");
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(10);
        mobileRenderHead(sheet,wb);
        String nameFlag = "";
        int index = 0;
        int totalCount=1;
        int mobileIndex=0;
        List<Mobile> list = new ArrayList();
        list.add(new Mobile("����1","12.0","0","2"));
        list.add(new Mobile("����1","13.0","11","2"));
        list.add(new Mobile("����2","12.5","11","2"));
        list.add(new Mobile("����2","12","0","2"));
        list.add(new Mobile("����3","15","0","2"));
        list.add(new Mobile("����3","15","11","2"));

        for (Mobile mobile : list) {
            //�ֻ���
            System.out.println("��ʼ��Ⱦ�ֻ���");
            if (!nameFlag.equals(mobile.getSqr())) {//��һ�α���ͬ����Ա
                //setColHead(sqr, index++, contentRow0, contentRow1, contentRow2, contentRow3, contentRow4);
                System.out.println("cell:mobileIndex"+mobileIndex);
                mobileRender2(++mobileIndex,sheet,wb);
                nameFlag = mobile.getSqr();
            }
            sheet.getRow(mobileIndex).getCell(0).setCellValue(mobile.getSqr());
            sheet.getRow(mobileIndex).getCell(Integer.valueOf(mobile.getBxyf())+1).setCellValue(mobile.getJe());
            packageMobileData(sheet);

        }

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


    /**
     * ��װ�������
     * @param sheet
     */
    private  static void  packageMobileData( HSSFSheet sheet) {
        System.out.println("packageMobileData");
        /*����*/
        for(int rowNum = 1; rowNum<=sheet.getLastRowNum();rowNum++){
            //��ȡÿһ��
            HSSFRow row = sheet.getRow(rowNum);
            if(row == null){
                continue;
            }
            double total =0;
            //������cell
            for(int cellNum = 1; cellNum<=row.getLastCellNum()-2;cellNum++){
                //��ȡÿһ��
                HSSFCell cell = row.getCell(cellNum);
                if(cell == null){
                    continue;
                }
                System.out.println("packageMobileData::��ʼ�������");
                double cellValue = withStringNullBindTo0Double(cell.getStringCellValue());
                total+=cellValue;
                if(cellNum==12){
                    row.getCell(13).setCellValue(total);
                    System.out.println("�����������");
                }
            }
        }
    }


    private static void mobileRenderHead( HSSFSheet sheet, HSSFWorkbook wb){
        System.out.println("����ͷ����Ϣ");
        contentRow=sheet.createRow(0);
        for (int i = 0; i <= 13; i++) {
            if(i==0){
                ExcelProtUtils.setStyle(contentRow.createCell((int)i),"����","","","Name","BOLD",wb);
            }else if(i==13){
                ExcelProtUtils.setStyle(contentRow.createCell((int)i),"Arial","","RIGHT","Total",wb);
            }else{
                ExcelProtUtils.setStyle(contentRow.createCell((int)i),"Arial","","RIGHT",i+"��",wb);
            }
        }
    }



    /**
     * ��Ⱦexcel���
     * @param sheet
     * @param wb
     */
    private static void mobileRender2(int index, HSSFSheet sheet, HSSFWorkbook wb) {
        System.out.println("mobileRender::");
        System.out.println("total:"+index);
        contentRow = sheet.createRow(index);
        System.out.print("��������");
        for (int i = 0; i <=13 ; i++) {
            if(i==0){
                ExcelProtUtils.setStyle(contentRow.createCell((int)i),"����","","RIGHT",wb);
            }else{
                ExcelProtUtils.setStyle(contentRow.createCell((int)i),"Arial","","RIGHT",wb);
            }
        }
    }

    /**
     * �����ַ���ת��������
     * @param value
     * @return
     */
    static double withStringNullBindTo0Double(String value){
        if ("".equals(value)||value==null) {
            return 0;
        }else{
            return Double.valueOf(value);
        }
    }




}
