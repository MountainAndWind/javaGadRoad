package test.HttpRequest.shizheng;

        import com.alibaba.fastjson.JSON;
        import com.alibaba.fastjson.JSONObject;
        import net.sf.json.JSONArray;
        import util.StringUtils;
        import test.HttpRequest.Bean.SpNoReturnBean;
        import test.HttpRequest.Request;
        import test.HttpRequest.shizheng.Bean.OrganizationReturnInfo;

        import java.util.Date;
        import java.util.HashMap;
        import java.util.Iterator;
        import java.util.Map;

        import static test.HttpRequest.Request.sendPost;
        import static test.xml.XmlParse.formatTime;

/**
 * @description:
 * @author: slfang
 * @time: 2020/4/16 15:14
 */
public class RequestTest {

    public static void main(String[] args) throws Exception {
        String url="http://180.167.220.36:777/api/Concurrent/GetConcurrentAll";
        String url2="http://180.167.220.36:777/api/Concurrentwithdraw/GetConcurrentwithdrawAll";

        RequestTest test = new RequestTest();
        HashMap<String,Object> baseInfo = new HashMap<>();
        baseInfo.put("RemoteFields","SysId,UserNameUserId,UserName,newCompanyId,newDepId,newPostId,jgzt");
        baseInfo.put("LocalSynchronousTable","table");
        baseInfo.put("TheOnlyCheck","MemberSysId");
        baseInfo.put("TheOnlyCheckRemoteFields","SysId");
        baseInfo.put("formmodeid","1005");
        baseInfo.put("LocalFields","MemberSysId,UserNameUserId,MemberName,NowCompanyId,NowDepId,NowPostJobId");
        baseInfo.put("UserName","WBS");
        baseInfo.put("PassWord","1002AA10000000002BNO");
        baseInfo.put("DeptCode","WBXT001");
        String mutPostUrl = url;
        String outPostUrl = url2;
        Map mutMap = test.getDataMap(mutPostUrl,baseInfo);
        Map outMap = test.getDataMap(outPostUrl,baseInfo);

        if("true".equals(outMap.get("Status").toString())){
            JSONArray outJsonArr = JSONArray.fromObject(outMap.get("Data").toString());
            if("true".equals(mutMap.get("Status").toString())){
                JSONArray json = JSONArray.fromObject(mutMap.get("Data").toString());
                int size = json.size();
                if(Integer.valueOf(size)>0){
                    test.insertLocalOaTable(baseInfo,json,outJsonArr);
                }
            }else{
            }
        }else{
        }
    }


    /**
     * 插入本地oa values为值数组与字段的位置应该保持一致
     * 全量插入每次插入先清空之前的数据
     * @param baseInfo 基础信息
     * @param json 传递过来的json数据
     * @param outArray
     * @throws Exception
     */
    public void insertLocalOaTable(Map<String, Object> baseInfo, JSONArray json, JSONArray outArray) throws Exception {
        System.out.println("insertLocalOaTable::execute");
        String remoteFileds=(String)baseInfo.get("RemoteFields");
        String table = (String) baseInfo.get("LocalSynchronousTable");
        for (int j = 0; j< json.size(); j++) {
            net.sf.json.JSONObject job = json.getJSONObject(j);
            String status;
            if(findIsExistOutMap(job,outArray)){//存在状态设置为1
                status="1";
            }else{
                status="0";
            }
            String theOnlyCheckRemoteFields = (String)baseInfo.get("TheOnlyCheckRemoteFields");
            String TheOnlyCheck = (String)baseInfo.get("TheOnlyCheck");
            //建模模块数据
            int formmodeid = Integer.valueOf((String)baseInfo.get("formmodeid"));
            int modedatacreater = 1; //1-系统管理员
            int modedatacreatertype = 0;//创建人类型，默认为0
            String modedatacreatedate = formatTime(new Date(), "yyyy-MM-dd");//模块创建日期
            String modedatacreatetime = formatTime(new Date(), "HH:mm:ss");//模块创建时间
            StringBuilder sqlInsert = new StringBuilder(" insert into "+table);//localSynchronousTable
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
            sqlInsert.append(",formmodeid,");
            sqlInsert.append("modedatacreater,");
            sqlInsert.append("modedatacreatertype,");
            sqlInsert.append("modedatacreatedate,");
            sqlInsert.append("modedatacreatetime");
            sqlInsert.append(") VALUES");
            String[] valuesFilds = remoteFileds.split(",");
            for (int i = 0; i < valuesFilds.length; i++) {
                Object value = job.get(valuesFilds[i]);
                if(i==0){
                    sqlInsert.append(" ('" + value + "',");
                }else if(i==valuesFilds.length-1){
                    sqlInsert.append(" '" + value + "'");
                }else{
                    sqlInsert.append(" '" +value+ "',");
                }
            }
            sqlInsert.append(",'"+status+"',");
            sqlInsert.append(",'"+formmodeid+"',");
            sqlInsert.append("'"+modedatacreater+"',");
            sqlInsert.append("'"+modedatacreatertype+"',");
            sqlInsert.append("'"+modedatacreatedate+"',");
            sqlInsert.append("'"+modedatacreatetime+"'");
            sqlInsert.append(")");
            System.out.println();
            System.out.println("插入sql::::::::::::::::::::::::::::::::::::::"+sqlInsert.toString());
            //插入并生成模块权限
            // }
        }
    }

    /**
     * 查找撤岗是否存在
     * @param job
     * @param outArr
     * @return
     */
    private boolean findIsExistOutMap(net.sf.json.JSONObject job, JSONArray outArr) {
        for (int j = 0; j< outArr.size(); j++) {
            net.sf.json.JSONObject outjob = outArr.getJSONObject(j);
            String value1 = (String)outjob.get("UserNameUserId");
            String value = (String)job.get("UserNameUserId");
            if(StringUtils.isNotBlank(value1)&&StringUtils.isNotBlank(value)&&value1.equals(value)){
                return true;
            }
        }
        return false;
    }

    private Map getDataMap(String mutPostUrl,HashMap<String,Object> baseInfo) throws Exception {
        String resultData = sendPost(mutPostUrl + "?UserName=" + baseInfo.get("UserName")
                + "&PassWord=" + baseInfo.get("PassWord") + "&DeptCode=" + baseInfo.get("DeptCode"), "");
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(resultData);
        Map map = new HashMap();
        for(Iterator iter = jsonObject.keys(); iter.hasNext();){
            String key = (String)iter.next();
            map.put(key, jsonObject.get(key));
        }
        return map;
    }

    private static void test1(String url) throws Exception {
        /*Map<String,Object> param = new HashMap<String,Object>();
        param.put("UserName","WBS");
        param.put("PassWord","1002AA10000000002BNO");
        param.put("DeptCode","WBXT001");
        String jsonText = JSON.toJSONString(param);
        System.out.println(jsonText);*/
        String s = sendPost(url+"?UserName=WBS&PassWord=1002AA10000000002BNO&DeptCode=WBXT001","");
        System.out.println(s);
    }
}
