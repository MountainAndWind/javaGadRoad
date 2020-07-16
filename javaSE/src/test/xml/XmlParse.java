package test.xml;

import org.dom4j.Document;
import org.dom4j.Element;
import test.IO.FileUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @description:
 * @author: slfang
 * @time: 2020/6/8 14:36
 */
public class XmlParse {

    public static void main(String[] args) throws Exception {
        HashMap<String,Object> baseInfo = new HashMap<>();
        //String xmldata = String.valueOf(FileUtils.loadWithBomFile("C:\\Users\\19504\\Desktop\\project_sendcontent20200602114901240(2).xml"));
        String xmldata = "?<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<QUALITY_PROJECT><data_content><row id=\"1\"><PROJECT_CODE>20200054</PROJECT_CODE><PROJECTPLAN_CODE>���潨(2020)FC310000202000234</PROJECTPLAN_CODE><PROJECTPLANVICE_CODE></PROJECTPLANVICE_CODE><PROJECT_NAME>�����ͷ�֣���ɽ��·-����·���ܿ�����ع���</PROJECT_NAME><TRACECHECK_UNIT>�Ϻ��ǿ�����Ϣ���̼������޹�˾</TRACECHECK_UNIT><CONSTRUCTION_UNIT>�Ϻ�����Ϣ�������޹�˾</CONSTRUCTION_UNIT><TRACECHECK_PERSON>����</TRACECHECK_PERSON><TRACECHECK_TEL>13917706445</TRACECHECK_TEL><TRACECHECK_ADDRESS>��ɽ�϶�·777Ū1��¥415</TRACECHECK_ADDRESS><SEND_CODE>20200054_01</SEND_CODE><SEND_DATE>2020-04-29</SEND_DATE><SEND_PERSON>�ǿ��ڹ���</SEND_PERSON><SEND_TEL></SEND_TEL><SEND_TECH>1</SEND_TECH><SEND_TECHDESIGN>1</SEND_TECHDESIGN><SEND_TECHSUMMARY>1</SEND_TECHSUMMARY><SEND_TECHRECORD>0</SEND_TECHRECORD><SEND_TECHMATERIAL>0</SEND_TECHMATERIAL><SEND_RESULT>1</SEND_RESULT><SEND_RESULTCONTENT></SEND_RESULTCONTENT><SEND_DATA>1</SEND_DATA><SEND_DATACD>1</SEND_DATACD><SEND_DATADISK></SEND_DATADISK><SEND_ELSE>0</SEND_ELSE><SEND_MEMO></SEND_MEMO><INFORM_CODE></INFORM_CODE><SEND_PIPLELINE></SEND_PIPLELINE></row><row id=\"2\"><PROJECT_CODE>20200022</PROJECT_CODE><PROJECTPLAN_CODE></PROJECTPLAN_CODE><PROJECTPLANVICE_CODE></PROJECTPLANVICE_CODE><PROJECT_NAME>���Ϲ��繫˾10ǧ��ӣ22����ͨ����ӣ12�����������·Ǩ�Ĺ���</PROJECT_NAME><TRACECHECK_UNIT>�Ϻ��������Ƽ����޹�˾</TRACECHECK_UNIT><CONSTRUCTION_UNIT>�����Ϻ��е�����˾���Ϲ��繫˾</CONSTRUCTION_UNIT><TRACECHECK_PERSON>����</TRACECHECK_PERSON><TRACECHECK_TEL>64130429</TRACECHECK_TEL><TRACECHECK_ADDRESS></TRACECHECK_ADDRESS><SEND_CODE>20200022_01</SEND_CODE><SEND_DATE>2020-04-27</SEND_DATE><SEND_PERSON>����</SEND_PERSON><SEND_TEL></SEND_TEL><SEND_TECH>0</SEND_TECH><SEND_TECHDESIGN></SEND_TECHDESIGN><SEND_TECHSUMMARY></SEND_TECHSUMMARY><SEND_TECHRECORD></SEND_TECHRECORD><SEND_TECHMATERIAL></SEND_TECHMATERIAL><SEND_RESULT>0</SEND_RESULT><SEND_RESULTCONTENT></SEND_RESULTCONTENT><SEND_DATA>0</SEND_DATA><SEND_DATACD></SEND_DATACD><SEND_DATADISK></SEND_DATADISK><SEND_ELSE>0</SEND_ELSE><SEND_MEMO></SEND_MEMO><INFORM_CODE></INFORM_CODE><SEND_PIPLELINE></SEND_PIPLELINE></row></data_content><content_check><rowcontent>2</rowcontent></content_check></QUALITY_PROJECT>";

        if(xmldata.startsWith("?")){
            xmldata = xmldata.substring(1);
        }

        baseInfo.put("oa_table","table1");
        baseInfo.put("LOCAL_OA_TABLE_FIELDS","PROJECT_CODE,PROJECTPLAN_CODE,PROJECTPLANVICE_CODE,PROJECT_NAME,TRACECHECK_UNIT,CONSTRUCTION_UNIT,TRACECHECK_PERSON,TRACECHECK_TEL,TRACECHECK_ADDRESS,SEND_CODE,SEND_DATE,SEND_PERSON,SEND_TEL,SEND_TECH,SEND_TECHDESIGN,SEND_TECHSUMMARY,SEND_TECHRECORD,SEND_TECHMATERIAL,SEND_RESULT,SEND_RESULTCONTENT,SEND_DATA,SEND_DATACD,SEND_DATADISK,SEND_ELSE,SEND_MEMO,INFORM_CODE,SEND_PIPLELINE");
        baseInfo.put("READ_ROW_CHILD_XML_NAME","PROJECT_CODE,PROJECTPLAN_CODE,PROJECTPLANVICE_CODE,PROJECT_NAME,TRACECHECK_UNIT,CONSTRUCTION_UNIT,TRACECHECK_PERSON,TRACECHECK_TEL,TRACECHECK_ADDRESS,SEND_CODE,SEND_DATE,SEND_PERSON,SEND_TEL,SEND_TECH,SEND_TECHDESIGN,SEND_TECHSUMMARY,SEND_TECHRECORD,SEND_TECHMATERIAL,SEND_RESULT,SEND_RESULTCONTENT,SEND_DATA,SEND_DATACD,SEND_DATADISK,SEND_ELSE,SEND_MEMO,INFORM_CODE,SEND_PIPLELINE");
        baseInfo.put("xmldata",xmldata);
        paseXMLDataToTable(baseInfo);
    }


    /**
     * ����xmlͬ������oa����
     * @param baseInfo
     * @throws Exception
     */
    private static void paseXMLDataToTable( Map<String, Object> baseInfo) throws Exception{
        String table = (String)baseInfo.get("oa_table");
        String LOCAL_OA_TABLE_FIELDS = (String)baseInfo.get("LOCAL_OA_TABLE_FIELDS");
        String[] tableFields = LOCAL_OA_TABLE_FIELDS.split(",");

        String xmldata =(String) baseInfo.get("xmldata");
        if (!"".equals(xmldata)) {
            //��ģģ������
            int formmodeid = 30;
            int modedatacreater = 1; //1-ϵͳ����Ա
            int modedatacreatertype = 0;//���������ͣ�Ĭ��Ϊ0
            String modedatacreatedate = formatTime(new Date(), "yyyy-MM-dd");//ģ�鴴������
            String modedatacreatetime = formatTime(new Date(), "HH:mm:ss");//ģ�鴴��ʱ��

            Document document;
            Element rootNode;
            document = XMLUtil.parseXMLStr(xmldata);
            rootNode = XMLUtil.getRootNode(document);
            Iterator<Element> iteratorRow = rootNode.element("data_content").elementIterator("row");
            while (iteratorRow.hasNext()) {
                Element next = (Element) iteratorRow.next();
                String READ_ROW_CHILD_XML_NAME = (String) baseInfo.get("READ_ROW_CHILD_XML_NAME");
                String[] tagNames = READ_ROW_CHILD_XML_NAME.split(",");
                StringBuilder sql = new StringBuilder("insert into "+table+"(");

                for (int i = 0; i < tableFields.length; i++) {
                    if(i==0){
                        sql.append(tableFields[i]);
                    }else{
                        sql.append(","+tableFields[i]);
                    }
                }
                sql.append(",formmodeid");
                sql.append(",modedatacreater");
                sql.append(",modedatacreatertype");
                sql.append(",modedatacreatedate");
                sql.append(",modedatacreatetime");
                sql.append(") values(");

                String PROJECT_CODE="";
                String SEND_CODE="";
                String SEND_DATE="";
                for (String tagName : tagNames) {
                    String tagValue = next.element(tagName).getText();
                    String name=next.element(tagName).getName();
                    if("PROJECT_CODE".equals(name)){
                        PROJECT_CODE=tagValue;
                    }
                    if("SEND_CODE".equals(name)){
                        SEND_CODE=tagValue;
                    }
                    if("SEND_DATE".equals(name)){
                        SEND_DATE=tagValue;
                    }
                    sql.append("'"+tagValue+"',");
                }
                sql.append("'"+formmodeid+"',");
                sql.append("'"+modedatacreater+"',");
                sql.append("'"+modedatacreatertype+"',");
                sql.append("'"+modedatacreatedate+"',");
                sql.append("'"+modedatacreatetime+"'");
                //sql.deleteCharAt(sql.length()-1);
                sql.append(")");
                System.out.println(sql.toString());
            }
        }
    }


    /**
     * ��ʽ��ʱ��
     * @param date
     * @param targetPattern
     * @return
     */
    public static String formatTime(Date date,String targetPattern){
        SimpleDateFormat sdf=new SimpleDateFormat(targetPattern);
        return sdf.format(date);
    }
}
