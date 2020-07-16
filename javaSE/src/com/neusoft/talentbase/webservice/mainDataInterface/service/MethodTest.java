package com.neusoft.talentbase.webservice.mainDataInterface.service;


import java.util.HashMap;

/**
 * @description:
 * @author: slfang
 * @time: 2020/5/26 15:07
 */
public class MethodTest {

    public static void main(String[] args) throws Exception {
        MDMWebServiceProxy webServiceProxy = new MDMWebServiceProxy();
        //MDMWebService mdmWebService = webServiceProxy.getMDMWebService();
      /*  String key = webServiceProxy.getKey("KGOA", DESUtil.encrypt("sG18Yy6v01ZoNq9x1hk1Vg=="));
        System.out.println("key::"+key);
        String hrInfo = webServiceProxy.getHrInfo("KGOA", key, DESUtil.encrypt("UNIT"),
               "","", DESUtil.encrypt("1"));
        String info = DESUtil.decrypt(hrInfo);
        System.out.println("hrInfo:"+DESUtil.decrypt(hrInfo));*/
        String key = webServiceProxy.getKey("MSOA", DESUtil.encrypt("gIqj3oK532QTI4pmIa0zNw=="));
        System.out.println("key::"+key);
        String hrInfo = webServiceProxy.getHrInfo("MSOA", key, DESUtil.encrypt("UNIT"),
               "","", DESUtil.encrypt("1"));
        System.out.println("hrInfo:"+DESUtil.decrypt(hrInfo));
    }

    /**
     * 获取集成数据         systemName:KGOAKYY    KGOAKYY    TYPE:gaIt0k/udo8=   mmtZDfAVhSM=     isall:1  1
     * @param baseInfo
     * @return
     */
    /*private String getInterData(HashMap<String, Object> baseInfo) throws Exception {
        MDMWebServiceProxy webServiceProxy = new MDMWebServiceProxy();
        String key = webServiceProxy.getKey((String)baseInfo.get("systemName"), DESUtil.encrypt((String)baseInfo.get("systemCode") ));
        baseBean.writeLog("systemName"+baseInfo.get("systemName"));
        baseBean.writeLog("TYPE"+DESUtil.encrypt((String)baseInfo.get("TYPE")));
        baseBean.writeLog("isall"+ baseInfo.get("isall"));
        String hrInfo = webServiceProxy.getHrInfo((String)baseInfo.get("systemName"), key, DESUtil.encrypt((String)baseInfo.get("TYPE")),
                "", "", DESUtil.encrypt((String)baseInfo.get("isall")));
        return DESUtil.decrypt(hrInfo);
    }*/

}
