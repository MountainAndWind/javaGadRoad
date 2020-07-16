package test;

import net.sf.json.JSONObject;
import util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: slfang
 * @time: 2020/4/22 11:00
 */
public class ActionTest {

    public static void main(String[] args) throws Exception {

        /*HashMap<String,Object> baseInfo = new HashMap();
        baseInfo.put("localSynchronousTable","uf_gwbd");
        baseInfo.put("LocalFields","SysId,PostJobId,STEC_Code,JobCode,JobName");
        baseInfo.put("RemoteFields","SysId,PostJobId,STEC_Code,JobCode,JobName");
        baseInfo.put("requestUrl","http://180.167.220.36:777/api/Post/GetPostAll?UserName=WBS&PassWord=1002AA10000000002BNO&DeptCode=WBXT001");
        insertLocalOaTableWithNoColHandler(baseInfo);
        Object o = null;
        String a= isNull(o);
        System.out.println(a);*/

        test2("-1");


    }

    static void test2(String value){
        if("0".equals(value)||"0.00".equals(value)||"0.0".equals(value)||"-1".equals(value)){
        }else{
            String updateSql="update "+" set fmisjpryfjf="+0+",fmisjpqsf="+0+" where requestid=";
        }
    }

    private static String isNull(Object o) {
        if(o==null){
            return "";
        }else{
            return (String) o;
        }
    }



    public static void insertLocalOaTableWithNoColHandler(Map<String,Object> baseInfo) throws Exception {
        String remoteFileds=(String)baseInfo.get("RemoteFields");
        for (int j = 0; j< 10; j++) {
            StringBuilder sqlInsert = new StringBuilder(" insert into "+baseInfo.get("localSynchronousTable"));//localSynchronousTable
            String localFields = (String)baseInfo.get("LocalFields");
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
            sqlInsert.append(") VALUES");
            //JSONObject job = json.getJSONObject(j);
            String[] valuesFilds = remoteFileds.split(",");
            for (int i = 0; i < valuesFilds.length; i++) {
                String value = "test";
                //baseBean.writeLog("获取字段值：："+value);
                if(i==0){
                    sqlInsert.append(" ('" + value + "',");
                }else if(i==valuesFilds.length-1){
                    sqlInsert.append(" '" + value + "')");
                }else{
                    sqlInsert.append(" '" +value+ "',");
                }
            }
            System.out.println("插入sql::::::::::::::::::::::::::::::::::::::"+sqlInsert.toString());
            //rs.execute(sqlInsert.toString());
        }
    }
}
