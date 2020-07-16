package test.EXCEL;

import com.sun.javafx.scene.control.skin.NestedTableColumnHeader;

import java.util.List;
import java.util.Map;

/**
 * poi导出表格配置文件
 */
public class Conf {

    /**
     * 设置每段表头信息
     */
    private String blockTitle="1月,2月,3月,4月,5月,6月,7月,8月,9月,10月,11月,12月,Total";

    /**
     * 每一段的行头信息 String[名称]:Map<字体,背景></>
     */
    private Map<String,Map<String,String>> colHead= null;

    /**
     * 设置每段表头信息list
     */
    private List<String> blockTitleList = null;

    /**
     * 表格数据list  使用map 二维定位数据
     */
    private List<Map<String,String>> dataMap = null;

    public Conf(String blockTitle, Map<String, Map<String, String>> colHead, List<String> blockTitleList, List<Map<String, String>> dataMap) {
        this.blockTitle = blockTitle;
        this.colHead = colHead;
        this.blockTitleList = blockTitleList;
        this.dataMap = dataMap;
    }
}
