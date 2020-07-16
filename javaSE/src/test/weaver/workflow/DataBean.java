package test.weaver.workflow;

import java.util.HashMap;
import java.util.List;

/**
 * @description:数据准备实体
 * @author: slfang
 * @time: 2020/4/28 14:40
 */
public class DataBean {

    private HashMap<String,Object> baseInfo;

    private List<Object> values;

    public HashMap<String, Object> getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(HashMap<String, Object> baseInfo) {
        this.baseInfo = baseInfo;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }
}
