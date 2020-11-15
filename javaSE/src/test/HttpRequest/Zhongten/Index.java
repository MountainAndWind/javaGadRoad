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
     * GET����
     * @param requestUrl �����ַ
     * @return
     */
    public static String sentGet(String requestUrl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;

        try {
            /** ����Զ��url���Ӷ��� */
            URL url = new URL(requestUrl);

            /** ͨ��Զ��url�����һ�����ӣ�ǿ��ת��ΪHttpUrlConnection���� */
            connection = (HttpURLConnection) url.openConnection();

            /** �������ӷ�ʽ��GET */
            connection.setRequestMethod("GET");
            /** ��������������������ʱʱ�䣺15000���� */
            connection.setConnectTimeout(15000);
            /** ���ö�ȡԶ�̷��ص�����ʱ�䣺60000���� */
            connection.setReadTimeout(60000);

            /** ����ͨ�õ��������� */
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // ���ô�������ĸ�ʽ:�������Ӧ���� name1=value1&name2=value2 ����ʽ
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            /** ����GET��ʽ����ʹ��connet����������Զ����Դ֮���ʵ�����Ӽ��� */
            connection.connect();

            /*-------------------------->*/
            /** ��ȡ������Ӧͷ�ֶ� */
            Map<String, List<String>> map = connection.getHeaderFields();
            /** ������Ӧͷ�ֶ� */
            for (String key : map.keySet()) {
                //System.out.println(key + "---------->" + map.get(key));
            }
            /* <-------------------------- */

            /** ����ɹ���������Ϊ200 */
            if (connection.getResponseCode() == 200) {
                /** ͨ��connection���ӣ���ȡ������ */
                is = connection.getInputStream();
                /** ��װ������is����ָ���ַ��� */
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                /** ������� */
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
            /** �ر���Դ */
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

            /** �ر�Զ������ */
            // �Ͽ����ӣ����д�ϣ�disconnect���ڵײ�tcp socket���ӿ���ʱ���жϡ�������ڱ������߳�ʹ�þͲ��жϡ�
            // �̶����̵߳Ļ��������disconnect�����ӻ����ֱ࣬���շ�������Ϣ��д��disconnect������һЩ
            connection.disconnect();
            //System.out.println("--------->>> GET request end <<<----------");
        }

        return result;
    }

    /**
     * ������������ѯ
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
                "<PURPOSE>����</PURPOSE>" +
                "<OURORGCODE>1010101001-0001</OURORGCODE>" +
                "<OURBANKACCOUNTNUMBER>121912205710501</OURBANKACCOUNTNUMBER>" +
                "<OURAMOUNT>200</OURAMOUNT>" +
                "<OURCURCODE>CNY</OURCURCODE>" +
                "<OPPOBJECTNAME>С������Ƽ������ڣ����޹�˾</OPPOBJECTNAME>" +
                "<OPPBANKLOCATIONCODE>104584001645</OPPBANKLOCATIONCODE>" +
                "<OPPBANKLOCATIONS>�й����йɷ����޹�˾����ǰ��֧��</OPPBANKLOCATIONS>" +
                "<OPPBANKACCOUNTNUMBER>121912205710501</OPPBANKACCOUNTNUMBER>" +
                "<OPPBANKACCOUNTNAME>С������Ƽ������ڣ����޹�˾</OPPBANKACCOUNTNAME>" +
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
        //System.out.println("���ر��ģ�"+o[0]);
    }
}
