package test.EXCEL.update;

import java.util.ArrayList;

/**
 * @description:
 * @author: slfang
 * @time: 2020/3/30 14:52
 */
public class MainTest {

    public static void main(String[] args) {
        ArrayList<Bean> list = new ArrayList<>();
        list.add(new Bean("张三","1","1"));
        list.add(new Bean("张三2","1","1"));
        list.add(new Bean("张三3","1","1"));
        list.add(new Bean("张三4","1","1"));
        list.add(new Bean("张三5","1","1"));
        list.add(new Bean("张三6","1","1"));


        ExcelUsedUtils.exportExcel("E:\\file","id,姓名,年龄","id,name,age","test",list);
    }
}
