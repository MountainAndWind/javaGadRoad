package test.weaver.workflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @description:数据准备
 * @author: slfang
 * @time: 2020/4/28 14:39
 */
public class DatePre {


    /**
     * 测试DevBaseBean  获取测试数据
     * @return
     */
    public static DataBean getInsertInfo(String fields){
        DataBean bean = new DataBean();
        String[] split = fields.split(",");
        List<Object> values = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            values.add(i);
        }

        HashMap<String,Object> baseInfo = new HashMap<>();
        baseInfo.put("INS_FIELDS",fields);
        baseInfo.put("INS_TABLE","tableName");
        bean.setBaseInfo(baseInfo);
        bean.setValues(values);
        return bean;
    }
}
