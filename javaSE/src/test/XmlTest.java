package test;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName
 * @Description 解析xml类
 * @Author fangshilei
 * @Date 2019/12/3 13:33
 * @Version 1.0
 **/

public class XmlTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        try {
            // 实例化一个类用于添加xml文件
            List list = new ArrayList();

            SAXReader reader = new SAXReader();
            Document doc = reader.read(new File("C:\\Users\\19504\\Desktop\\project_sendcontent20200403112109989.xml"));
            // 读取指定标签
            Iterator<Element> eleit = doc.getRootElement().elementIterator("row");

            ArrayList newlist = new ArrayList(); // 创建新集合
            while (eleit.hasNext()) {
                Element ele = eleit.next();
                System.out.println(ele.attributeValue("id"));
                System.out.println(ele.elementText("PROJECT_CODE"));
                System.out.println(ele.elementText("PROJECTPLAN_CODE"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}
