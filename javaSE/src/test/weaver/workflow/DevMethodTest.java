package test.weaver.workflow;

import util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @description:泛微开发bean测试类
 * @author: slfang
 * @time: 2020/4/28 14:38
 */
public class DevMethodTest {
    public static void main(String[] args) throws Exception {
        DataBean insertInfo = DatePre.getInsertInfo("name,age,sex,id,code");
        insert(insertInfo.getBaseInfo(),insertInfo.getValues());
    }


    /**
     * 插入本地oa values为值数组与字段的位置应该保持一致
     * @param baseInfo 基础信息
     * @throws Exception
     */
    public static void insert(Map<String,Object> baseInfo, List<Object> values) throws Exception {
        StringBuilder sqlInsert = new StringBuilder(" insert into "+baseInfo.get("INS_TABLE"));//localSynchronousTable
        String localFields = (String)baseInfo.get("INS_FIELDS");
        if(StringUtils.isBlank(localFields)){
            throw new Exception("本地字段读取不到");
        }
        sqlInsert.append("(");
        String[] split_localFields = localFields.split(",");
        for (int i = 0; i < split_localFields.length; i++) {
            if(i==0){
                sqlInsert.append(split_localFields[i]);
            }else{
                sqlInsert.append(","+split_localFields[i]);
            }
        }
        sqlInsert.append(") VALUES(");
        Object[] objects = values.toArray();
        for (int i = 0; i < objects.length; i++) {
            if(i==0){
                sqlInsert.append(" "+objects[i]);
            }else{
                sqlInsert.append(","+objects[i]);
            }
        }
        sqlInsert.append(")");
        System.out.println("插入sql::::::::::::::::::::::::::::::::::::::"+sqlInsert.toString());
    }
}
