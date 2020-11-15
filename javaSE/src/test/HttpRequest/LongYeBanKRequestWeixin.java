package test.HttpRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import test.HttpRequest.Bean.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: slfang
 * @time: 2020/4/8 14:03
 */
public class LongYeBanKRequestWeixin {

    private static final String TOKEN_URL="https://qyapi.weixin.qq.com/cgi-bin/gettoken";

    public static void main(String[] args) throws Exception {

    /*    *//*公司di*//*
     *//*   String corpId="wwc13c1764abd824ff";
        String corpsecret="ckihvqQ_DLFl966sOXoGa2vZLFtWM6PhnRvbsCyZg1Q";
        String token1 = getToken(corpId, corpsecret, TOKEN_URL);
        TokenMsgBean returnInfoToken = JSONObject.parseObject(token1, TokenMsgBean.class);
        System.out.println("returnInfoToken::"+returnInfoToken);
        *//*
        //String token = returnInfoToken.getAccess_token();
        *//*String endTime = GetUnixStarp.Date2TimeStamp("2020-01-06 12:22:11","yyyy-MM-dd HH:mm:ss");
        String startTime = GetUnixStarp.Date2TimeStamp("2020-01-09 12:22:01","yyyy-MM-dd HH:mm:ss");
        String token="XocuxLTC4kM-WObHyOm49BGlhOhF_i67xUPtWWm9v_m9ruJ3ZgVQMDPlN4rLa9SlkUBu10P0IkCLWn-sjBt3igXdLwFbu0PsYPWNs6wuWXHy5KRyZ_1XKW4YWYeG7tMZkbrcQjL9mF1O-HUuwCp9lgJF6IUTbHoxImc-rTZIvnlFH-gLtid4J_mCoe0UVUdXc_hju3wKYMpg_iJyKU3E5A";
        boolean flag=true;
        Integer startCursor=0;
        SpNoReturnBean spNoReturnBean=null;
        spNoReturnBean = JSONObject.parseObject(getSpNo(token,startCursor, 100,startTime,endTime),SpNoReturnBean.class);
        while (spNoReturnBean.getNext_cursor()!=null){
            List<String> sp_no_list = spNoReturnBean.getSp_no_list();
            int size=sp_no_list.size();
            for (int i = 0;i <size; i++) {
                testDetail(token,sp_no_list.get(i));
            }
            spNoReturnBean=JSONObject.parseObject(getSpNo(token,++startCursor, 100,startTime,endTime),SpNoReturnBean.class);
        }

        while (flag){
            spNoReturnBean = JSONObject.parseObject(getSpNo(token,startCursor++, 100,startTime,endTime),SpNoReturnBean.class);
            List<String> sp_no_list = spNoReturnBean.getSp_no_list();
            int size=sp_no_list.size();
            for (int i = 0;i <size; i++) {
                ApproveReturnInfo approveReturnInfo = JSONObject.parseObject(testDetail(token,sp_no_list.get(i)),ApproveReturnInfo.class);
                if("ok".equals(approveReturnInfo.getErrmsg())){
                    if("出差".equals(approveReturnInfo.getInfo().getSp_name())){
                        if("202001060089".equals(approveReturnInfo.getInfo().getSp_no())){
                            writeToOa(approveReturnInfo,"main","detail");
                        }
                    }
                }
            }
            if(spNoReturnBean.getNext_cursor()==null){
                flag=false;
            }
        }*//*
        String token = "lGtjHkL2UoGV0MY7z8vszPtqmmmvie203L8lDPcvfaEsjklHJ4eY1Q6xza6PkNxTJ3UYG8bGp9_fCmX2gpgGjcOxWslqgXGkD7zpNQI5w5faiiOM0Lr-O_m4FBmD0vWfFxNDdz6qaRrRQx-rtw3zL9keYM6Qa2yhiEHGPPutBTfNh4cq8-aUo_yzz72gPwgp9CbSsplbanAYHLYXwTZHbA";
        ApproveReturnInfo approveReturnInfo = JSONObject.parseObject(testDetail(token,"202006290093"),ApproveReturnInfo.class);
        String sp_name = approveReturnInfo.getInfo().getSp_name();
        System.out.println("sp_anme:"+sp_name);
        System.out.println("eq"+("出差".equals(sp_name)));
        if("出差".equals(sp_name)){
            System.out.println("cao");
            writeToOa(approveReturnInfo,"main","detail");
        }else{
            writeToOa(approveReturnInfo,"main","detail");
        }*/
    }

    /**
     * 获取token信息
     * @param corpId
     * @param corpsecret
     */
    private static String getToken(String corpId, String corpsecret,String url) throws Exception {
        url=url+"?corpid="+corpId+"&corpsecret="+corpsecret;
        String s = sendPost(url, "");
        return s;
    }



    /**
     * 获取审批单号
     * @param token
     * @param cursor
     * @param size
     * @return
     * @throws Exception
     */
    static String getSpNo(String token,Integer cursor,Integer size,String startTime,String endTime) throws Exception {
        System.out.println("getSpNo::start");
        String testUrl="https://qyapi.weixin.qq.com/cgi-bin/oa/getapprovalinfo?access_token="+token;
        Map<String,Object> returnMap = new HashMap<String,Object>();

        returnMap.put("starttime",startTime);
        returnMap.put("endtime",endTime);
        returnMap.put("cursor",cursor);
        returnMap.put("size",size);
        String jsonText = JSON.toJSONString(returnMap);
        System.out.println(jsonText);
        String s = sendPost(testUrl, jsonText);
        System.out.println("getSpNo::"+s);
        return s;
    }



    static String testDetail(String token,String spNo) throws Exception {
        String testUrl="https://qyapi.weixin.qq.com/cgi-bin/oa/getapprovaldetail?access_token="+token;
        Map<String,Object> returnMap = new HashMap<String,Object>();
        returnMap.put("sp_no",spNo);
        String jsonText = JSON.toJSONString(returnMap);
        String s = sendPost(testUrl, jsonText);
        System.out.println("testDetail::"+s);
        return s;
    }



    /**
     * 回写到本地oa
     * @param approveReturnInfo
     * @param MIAN_TABLE
     * @param DETIAL_TIBLE
     */
    private static void writeToOa(ApproveReturnInfo approveReturnInfo, String MIAN_TABLE, String DETIAL_TIBLE)throws Exception {
        //建模模块数据
        int formmodeid = 6;
        int modedatacreater = 1; //1-系统管理员
        int modedatacreatertype = 0;//创建人类型，默认为0
        ApproveInfo info = approveReturnInfo.getInfo();

        List<Contents> contents = info.getApply_data().getContents();
        StringBuilder insertMainSql=new StringBuilder("insert into "+MIAN_TABLE);
        insertMainSql.append("(");
        insertMainSql.append("spbh,");
        insertMainSql.append("spmbmc,");
        insertMainSql.append("sqdzt,");
        insertMainSql.append("spsqtjsj,");
        insertMainSql.append("sqr,");
        insertMainSql.append("sqrszbm,");
        insertMainSql.append("ccsy,");
        insertMainSql.append("ccdd,");

        insertMainSql.append("ccksrq,");
        insertMainSql.append("ccjsrq,");
        insertMainSql.append("kssjx,");
        insertMainSql.append("jssjx,");
       /* insertMainSql.append("kssj,");
        insertMainSql.append("jssj,");*/

        insertMainSql.append("ccsc,");
        insertMainSql.append("formmodeid,");
        insertMainSql.append("modedatacreater,");
        insertMainSql.append("modedatacreatertype,");
        insertMainSql.append("modedatacreatedate,");
        insertMainSql.append("modedatacreatetime");
        insertMainSql.append(")");
        insertMainSql.append("values(");
        insertMainSql.append("'"+info.getSp_no()+"',");
        insertMainSql.append("'"+info.getSp_name()+"',");
        insertMainSql.append("'"+info.getSp_status()+"',");
        insertMainSql.append("'"+info.getApply_time()+"',");
        insertMainSql.append("'"+1+"',");
        insertMainSql.append("'"+1+"',");
        insertMainSql.append("'"+contents.get(0).getValue().getText()+"',");
        insertMainSql.append("'"+contents.get(1).getValue().getText()+"',");

        String BEGIN = GetUnixStarp.TimeStamp2Date(contents.get(3).getValue().getAttendance().getDate_range().getNew_begin(), "yyyy-MM-dd HH:mm");
        String END = GetUnixStarp.TimeStamp2Date(contents.get(3).getValue().getAttendance().getDate_range().getNew_end(), "yyyy-MM-dd HH:mm");

        String s = timeSta(BEGIN.split(" ")[1]);
        String s1 = timeSta(END.split(" ")[1]);

        insertMainSql.append("'"+BEGIN.split(" ")[0]+"',");
        insertMainSql.append("'"+END.split(" ")[0]+"',");
        insertMainSql.append("'"+s+"',");
        insertMainSql.append("'"+s1+"',");

        insertMainSql.append("'"+GetUnixStarp.getDay(contents.get(3).getValue().getAttendance().getDate_range().getNew_duration())+"',");
        insertMainSql.append("'"+formmodeid+"',");
        insertMainSql.append("'"+modedatacreater+"',");
        insertMainSql.append("'"+modedatacreatertype+"',");
        insertMainSql.append("'"+1+"',");
        insertMainSql.append("'"+1+"'");
        insertMainSql.append(")");
        System.out.println("insertMainSql::"+insertMainSql.toString());
        //rs1.execute(insertMainSql.toString());

        List<SpRecord> sp_record = info.getSp_record();
        for (SpRecord spRecord : sp_record) {
            StringBuilder insertDetailSql = new StringBuilder("insert into "+DETIAL_TIBLE);
            insertDetailSql.append("(");
            insertDetailSql.append("spjdzt,");
            insertDetailSql.append("jdspfs,");
            insertDetailSql.append("fzspr,");
            insertDetailSql.append("spyj,");
            insertDetailSql.append("fzsprspzt,");
            insertDetailSql.append("jdfzsprspczsj,");
            insertDetailSql.append("mainid");
            insertDetailSql.append(")");
            insertDetailSql.append("values(");
            insertDetailSql.append("'"+spRecord.getSp_status()+"',");
            insertDetailSql.append("'"+spRecord.getApproverattr()+"',");
            List<RecordDetails> details = spRecord.getDetails();
            String lastname1 = "lastName";
            insertDetailSql.append("'"+lastname1+"',");
            insertDetailSql.append("'"+details.get(0).getSpeech()+"',");
            insertDetailSql.append("'"+details.get(0).getSp_status()+"',");
            insertDetailSql.append("'"+ GetUnixStarp.TimeStamp2Date(details.get(0).getSptime(),"yyyy-MM-dd HH:mm")+"',");
            insertDetailSql.append("'"+1+"'");
            insertDetailSql.append(")");
            System.out.println("insertDetailSql::"+insertDetailSql);
            //rs2.execute(insertDetailSql.toString());
        }


    }

    /**
     *
     * @param str
     * @return
     */
    private static String timeSta(String str) {
        if("00:00".equals(str)){
            return "0";
        }else if ("12:00".equals(str)){
            return "1";
        }else{
            return null;
        }
    }

    /**
     * GET请求
     * @param requestUrl 请求地址
     * @return
     */
    public static String get(String requestUrl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;

        try {
                /** 创建远程url连接对象 */
                URL url = new URL(requestUrl);

                /** 通过远程url对象打开一个连接，强制转换为HttpUrlConnection类型 */
                connection = (HttpURLConnection) url.openConnection();

                /** 设置连接方式：GET */
                connection.setRequestMethod("GET");
                /** 设置连接主机服务器超时时间：15000毫秒 */
                connection.setConnectTimeout(15000);
                /** 设置读取远程返回的数据时间：60000毫秒 */
                connection.setReadTimeout(60000);

                /** 设置通用的请求属性 */
                connection.setRequestProperty("accept", "*/*");
                connection.setRequestProperty("connection", "Keep-Alive");
                connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                /** 发送GET方式请求，使用connet方法建立和远程资源之间的实际连接即可 */
                connection.connect();

                /*-------------------------->*/
                /** 获取所有相应头字段 */
                Map<String, List<String>> map = connection.getHeaderFields();
                /** 遍历响应头字段 */
                for (String key : map.keySet()) {
                System.out.println(key + "---------->" + map.get(key));
                }
                /* <-------------------------- */

                /** 请求成功：返回码为200 */
                if (connection.getResponseCode() == 200) {
                    /** 通过connection连接，获取输入流 */
                    is = connection.getInputStream();
                    /** 封装输入流is，并指定字符集 */
                    br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                    /** 存放数据 */
                    StringBuffer sbf = new StringBuffer();
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sbf.append(line);
                        sbf.append("\r\n");
                    }
                    result = sbf.toString();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                /** 关闭资源 */
                try {

                    if (null != br) {
                        br.close();
                    }

                    if (null != is) {
                        is.close();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                /** 关闭远程连接 */
                // 断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
                // 固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些
                connection.disconnect();
                System.out.println("--------->>> GET request end <<<----------");
            }

            return result;
    }


    /**
     * 发起请求并获取响应数据
     * @param url
     * @param content
     * @return
     * @throws Exception
     */
    private static String sendPost(String url ,String content) throws Exception{
        String strResult="";
        InputStreamReader inSteam =null;
        PrintWriter out=null;
        URL httpurl = new URL(url);

        HttpURLConnection httpConn = (HttpURLConnection) httpurl.openConnection();
        httpConn.setRequestProperty("content-type", "application/json;charset=UTF-8");
        httpConn.setRequestMethod("POST");
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);

        out = new PrintWriter(new OutputStreamWriter(httpConn.getOutputStream(),"utf-8"));
        out.print(content);
        out.flush();
        out.close();//关闭输出流

        inSteam = new InputStreamReader(httpConn.getInputStream(),"utf-8");

        int nByte = 1024;
        StringBuffer strBuffer = new StringBuffer();
        if(inSteam!=null) {
            while((nByte = inSteam.read())!= -1){
                strBuffer.append((char) nByte);
            }
            strResult = strBuffer.toString();
            inSteam.close();//关闭输入流
        }

        return strResult;
    }
}
