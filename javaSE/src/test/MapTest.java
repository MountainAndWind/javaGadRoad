package test;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName map测试类
 * @Description TODO
 * @Author fangshilei
 * @Date 2019/11/20 10:02
 * @Version 1.0
 **/
public class MapTest {


    public static void main(String[] args) {
        HashMap<String,Object> target = new HashMap<>();
        target.put("a",121);
        target.put("a",222);
        System.out.println(target.get("a"));

        String remoteSql="select * from XXGFAOB_JOURNAL_V a  where a.LEDGER_NAME='T073311_TCE_IFRS' and CREATION_DATE='当前时间-1' and a.NATURAL_ACCOUNT in (FILE_VALUE0)";
        Map<String,Object> baseInfo = new HashMap<>();
        baseInfo.put("VALUES","1");
        baseInfo.put("FILE_VALUE0","12");

        if(remoteSql.contains("FILE_VALUE")){//判断是否存在替代值
            String valuestr = (String) baseInfo.get("VALUES");
            Integer values = Integer.valueOf(valuestr);
            for (Integer i = 0; i < values; i++) {
                String  fileValue =(String) baseInfo.get("FILE_VALUE" + i);
                remoteSql = remoteSql.replace("FILE_VALUE"+i,fileValue);
            }
        }
        System.out.println(remoteSql);

    }


}
