package test2;

import org.dom4j.Document;
import org.dom4j.Element;
import test.IO.FileUtils;

import java.util.Iterator;

/**
 * @ClassName
 * @Description TODO
 * @Author fangshilei
 * @Date 2019/12/3 13:55
 * @Version 1.0
 **/
public class XmlRemoteTest {
    public static void main(String[] args) {
        /*String smbUrl="smb://administrator:" + 123 + "@127.0.0.1/xmlPushTest";
        String fileName="project_sendcontent20191120101010.xml";
        String xmldata = SmbFileUtil.getSmbXMLContent(smbUrl + "/" + fileName);*/
        String xmldata = FileUtils.readFileToString("C:\\Users\\19504\\Desktop\\project_sendcontent20191120101010.xml");
        System.out.println("" + xmldata);
        Document document;
        Element rootNode;
        Element currentElement;
        document = XMLUtil.parseXMLStr(xmldata);
        rootNode = XMLUtil.getRootNode(document);
        System.out.println(rootNode.attributes());
        Iterator<Element> iterator = rootNode.element("data_content").elementIterator("row");
        while (iterator.hasNext()) {
            Element next = (Element) iterator.next();
            System.out.println(next.element("SEND_CODE").getText());
            System.out.println(next.element("SEND_DATE").getText());
            System.out.println("-------------------------------------");
        }
    }
}
