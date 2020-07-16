package com.neusoft.talentbase.webservice.mainDataInterface.service;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import javax.xml.rpc.ParameterMode;

public class caClient {
    public static void main(String[] args) {

        try {
            String endpoint = "http://ehr.chinawanxiang.com:10086/service/MDMWebService?wsdl";
            // 直接引用远程的wsdl文件  
            // 以下都是套路  
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setOperationName("getKey");// WSDL里面描述的接口名称
            call.addParameter("systemName",
                    org.apache.axis.encoding.XMLType.XSD_DATE,
                    ParameterMode.IN);// 接口的参数
            call.addParameter("systemCode",
                    org.apache.axis.encoding.XMLType.XSD_DATE,
                    ParameterMode.IN);// 接口的参数
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
            String temp = "MSOA";
            String key=DESUtil.encrypt("gIqj3oK532QTI4pmIa0zNw==");
            String result = (String) call.invoke(new Object[] { temp,key});
            // 给方法传递参数，并且调用方法  
            System.out.println("result is " + result);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}  