package com.neusoft.talentbase.webservice.mainDataInterface.service;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import javax.xml.rpc.ParameterMode;

public class caClient {
    public static void main(String[] args) {

        try {
            String endpoint = "http://ehr.chinawanxiang.com:10086/service/MDMWebService?wsdl";
            // ֱ������Զ�̵�wsdl�ļ�  
            // ���¶�����·  
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setOperationName("getKey");// WSDL���������Ľӿ�����
            call.addParameter("systemName",
                    org.apache.axis.encoding.XMLType.XSD_DATE,
                    ParameterMode.IN);// �ӿڵĲ���
            call.addParameter("systemCode",
                    org.apache.axis.encoding.XMLType.XSD_DATE,
                    ParameterMode.IN);// �ӿڵĲ���
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// ���÷�������
            String temp = "MSOA";
            String key=DESUtil.encrypt("gIqj3oK532QTI4pmIa0zNw==");
            String result = (String) call.invoke(new Object[] { temp,key});
            // ���������ݲ��������ҵ��÷���  
            System.out.println("result is " + result);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}  