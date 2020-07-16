package test.Ftp;

import org.dom4j.Document;
import org.dom4j.Element;
import test.IO.FileUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @description:
 * @author: slfang
 * @time: 2020/4/3 11:26
 */
public class XmlParse {

    public static void main(String[] args) throws IOException {
        System.out.println("测试");
        /*String path="F:\\localFtpTest\\project_sendcontent20200403112109989.xml";*/
        String path="F:\\localFtpTest\\testGBK.txt";
        String xmlNames="PROJECT_CODE,PROJECTPLAN_CODE,PROJECTPLANVICE_CODE,PROJECT_NAME,TRACECHECK_UNIT,CONSTRUCTION_UNIT,TRACECHECK_PERSON,TRACECHECK_TEL,TRACECHECK_ADDRESS,SEND_CODE,SEND_DATE,SEND_PERSON,SEND_TEL,SEND_TECH,SEND_TECHDESIGN,SEND_TECHSUMMARY,SEND_TECHRECORD,SEND_TECHMATERIAL,SEND_RESULT,SEND_RESULTCONTENT,SEND_DATA,SEND_DATACD,SEND_DATADISK,SEND_ELSE,SEND_MEMO,INFORM_CODE,SEND_PIPLELINE";
        String xmldata = String.valueOf(FileUtils.loadWithBomFile(path));
        HashMap<String,Object> baseInfo = new HashMap<>();
        baseInfo.put("READ_ROW_CHILD_XML_NAME",xmlNames);
        Document document;
        Element rootNode;
        document = XMLUtil.parseXMLStr(xmldata);
        rootNode = XMLUtil.getRootNode(document);
        Iterator<Element> iteratorRow = rootNode.element("data_content").elementIterator("row");
        while (iteratorRow.hasNext()) {
            System.out.println("#####################遍历xml数据#####################");
            Element next = (Element) iteratorRow.next();
            String READ_ROW_CHILD_XML_NAME = (String) baseInfo.get("READ_ROW_CHILD_XML_NAME");
            String[] tagNames = READ_ROW_CHILD_XML_NAME.split(",");
            /*StringBuilder sql = new StringBuilder("insert into "+table+"(");

            for (int i = 0; i < tableFields.length; i++) {
                if(i==0){
                    sql.append(tableFields[i]);
                }else{
                    sql.append(","+tableFields[i]);
                }
            }
            sql.append(") values(");*/
            for (String tagName : tagNames) {
                String tagValue = next.element(tagName).getText();
                String name=next.element(tagName).getName();
                System.out.println("tag::"+name+"--value::"+tagValue);
                //sql.append("'"+tagValue+"',");
            }
            System.out.println("######################准备执行sql将xml数据转oa table#####################");
           /* sql.deleteCharAt(sql.length()-1);
            sql.append(")");
            System.out.println("sql::"+sql.toString());
            boolean execute = rs.execute(sql.toString());
            System.out.println("execute  res：："+execute);
            if(execute){
                System.out.println("######################"+sql.toString()+"执行成功######################");
            }else{
                System.out.println("删除失败请检查接口");
                throw new Exception("paseXMLDataToTable   xml数据转换成oa数据失败");
            }*/
        }
    }
}
