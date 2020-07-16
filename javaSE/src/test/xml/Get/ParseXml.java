package test.xml.Get;

import org.dom4j.Document;
import org.dom4j.Element;
import test.IO.FileCharsetConvert;
import test.IO.FileUtils;
import test.xml.XMLUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Iterator;
import java.util.Map;

/**
 * @description:
 * @author: slfang
 * @time: 2020/5/9 11:09
 */
public class ParseXml {

    public static void main(String[] args) throws Exception {
        String path="C:\\Users\\19504\\Desktop\\project_sendcontent20191120101010.xml";
        FileCharsetConvert.convert(path,
                "UTF-8", "GBK", new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.endsWith("xml");
                    }
                });
        String xmldata = String.valueOf(FileUtils.loadWithBomFile(path));
        System.out.println(xmldata);
        paseXMLDataToTable(xmldata);

      /*  String path="F:\\ftpTest\\ftpTest\\a.xml";
        FileCharsetConvert.convert(path,
                "UTF-8", "GBK", new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.endsWith("xml");
                    }
                });
        String xmldata = String.valueOf(FileUtils.loadWithBomFile(path));
        System.out.println(xmldata);*/

    }

    private static void paseXMLDataToTable(String xmldata) throws Exception{
        if (!"".equals(xmldata)) {
            Document document;
            Element rootNode;
            document = XMLUtil.parseXMLStr(xmldata);
            rootNode = XMLUtil.getRootNode(document);
            Iterator<Element> iteratorRow = rootNode.element("data_content").elementIterator("row");
            while (iteratorRow.hasNext()) {
                Element next = (Element) iteratorRow.next();
                //String READ_ROW_CHILD_XML_NAME = (String) baseInfo.get("READ_ROW_CHILD_XML_NAME");
               // String[] tagNames = READ_ROW_CHILD_XML_NAME.split(",");
            }
        }else{
        }
    }
}
