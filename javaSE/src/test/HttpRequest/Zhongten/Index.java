package test.HttpRequest.Zhongten;

import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.xfire.client.Client;

/**
 * @description:
 * @author: slfang
 * @time: 2020/6/15 10:19
 */
public class Index {


    public static void main(String[] args) throws Exception {
        testCommonQueryService();
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
        System.out.println("返回报文："+o[0]);
    }
}
