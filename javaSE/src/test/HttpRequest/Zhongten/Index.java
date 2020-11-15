package test.HttpRequest.Zhongten;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.codehaus.xfire.client.Client;

/**
 * @description:
 * @author: slfang
 * @time: 2020/6/15 10:19
 */
public class Index {


    public static void main(String[] args) throws Exception {
        String s = sentGet("http://localhost:8081/aes/gettoken.jsp");
        //System.out.println("-----------------------------");
        System.out.println(s);
    }


    /**
     * GET请求
     * @param requestUrl 请求地址
     * @return
     */
    public static String sentGet(String requestUrl) {
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
                //System.out.println(key + "---------->" + map.get(key));
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
            //System.out.println("--------->>> GET request end <<<----------");
        }

        return result;
    }

    /**
     * 批量付款结果查询
     * @throws Exception
     */
    private static void testCommonQueryService()throws MalformedURLException, Exception {

        String xml="<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<MBS>" +
                "<pub>" +
                "<SRCBATCHNO>ZJDF20200700026</SRCBATCHNO>" +
                "<SRCOUTSYSTEMCODE>FWOA</SRCOUTSYSTEMCODE>" +
                "<TRANSCODE>PMSQ01</TRANSCODE>" +
                "<TRANSDATETIME>2020-07-01 10:40:59</TRANSDATETIME>" +
                "<MD5>a9b22799719fcf5db383c33d49b608d2</MD5>" +
                "</pub>" +
                "<req>" +
                "<head>" +
                "<ALLAMOUNT>200</ALLAMOUNT>" +
                "<ALLCOUNT>1</ALLCOUNT>" +
                "</head>" +
                "<list><detail><SRCSERIALNO>ZJDF20200700026</SRCSERIALNO>" +
                "<SRCNOTECODE>ZJDF20200700026</SRCNOTECODE>" +
                "<ORGCODE>1010101001-0001</ORGCODE>" +
                "<APPLYORGCODE>1010101001-0001</APPLYORGCODE>" +
                "<PAYDATE>2020-07-01</PAYDATE>" +
                "<PAYTYPECODE>zjdf</PAYTYPECODE>" +
                "<SETTLEMENTMODECODE>101</SETTLEMENTMODECODE>" +
                "<PURPOSE>测试</PURPOSE>" +
                "<OURORGCODE>1010101001-0001</OURORGCODE>" +
                "<OURBANKACCOUNTNUMBER>121912205710501</OURBANKACCOUNTNUMBER>" +
                "<OURAMOUNT>200</OURAMOUNT>" +
                "<OURCURCODE>CNY</OURCURCODE>" +
                "<OPPOBJECTNAME>小花网络科技（深圳）有限公司</OPPOBJECTNAME>" +
                "<OPPBANKLOCATIONCODE>104584001645</OPPBANKLOCATIONCODE>" +
                "<OPPBANKLOCATIONS>中国银行股份有限公司深圳前海支行</OPPBANKLOCATIONS>" +
                "<OPPBANKACCOUNTNUMBER>121912205710501</OPPBANKACCOUNTNUMBER>" +
                "<OPPBANKACCOUNTNAME>小花网络科技（深圳）有限公司</OPPBANKACCOUNTNAME>" +
                "<OPPDIRECTCURCODE>CNY</OPPDIRECTCURCODE>" +
                "<OPPPRIVATEFLAG>1</OPPPRIVATEFLAG>" +
                "<CREATEDBY>002095</CREATEDBY>" +
                "<LASTMODIFIEDBY>002095</LASTMODIFIEDBY>" +
                "<CAPITALCATEGORYCODE></CAPITALCATEGORYCODE>" +
                "<ISURGENT>0</ISURGENT>"+
                "</detail>" +
                "</list>" +
                "</req></MBS>";

        Client c=new Client(new URL("http://10.1.15.152:8083/ws/outService.ws?wsdl"));
        Object[] o=c.invoke("outSystemWS",new String[]{xml});
        //System.out.println("返回报文："+o[0]);
    }
}
