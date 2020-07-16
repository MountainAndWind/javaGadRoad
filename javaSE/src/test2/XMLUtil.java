/**
 *
 * Copyright (c) 2001-2017 泛微软件.
 * 泛微协同商务系统,版权所有.
 * 
 */
package test2;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.*;

/**
 * @version 1.0
 * @author lv XML工具类
 */
public class XMLUtil {
    /**
     * 集成日志
     */

	/**
     * 编码方式：UTF-8
     */
	private static String XML_UTF8 = "UTF-8";
	
	/**
	 * 根据指定路径创建空的XML文件，默认为UTF-8编码
	 * @param xmlPath
	 * @return
	 */
	public static Document createEmptyXMLFile(String xmlPath) {
		if(xmlPath == null || "".equals(xmlPath)) {
			return null;
		}
		File xmlFile = new File(xmlPath);
        return createEmptyXMLFile(xmlFile, null);
	}
	
	/**
	 * 根据指定路径和编码格式创建空的XML文件
	 * @param xmlPath
	 * @param charset
	 * @return
	 */
	public static Document createEmptyXMLFile(String xmlPath, String charset) {
		if(xmlPath == null || "".equals(xmlPath)) {
			return null;
		}
		File xmlFile = new File(xmlPath);
        return createEmptyXMLFile(xmlFile, charset);
	}
	
	/**
	 * 根据指定文件创建空的XML文件，默认为UTF-8编码
	 * @param xmlFile
	 * @return
	 */
	public static Document createEmptyXMLFile(File xmlFile) {
		return createEmptyXMLFile(xmlFile, null);
	}
	
	/**
	 * 根据指定文件和编码格式创建空的XML文件
	 * @param xmlFile
	 * @param charset
	 * @return
	 */
	public static Document createEmptyXMLFile(File xmlFile, String charset) {
		if (xmlFile == null) {
			return null;
		}
		Document doc = DocumentHelper.createDocument();
		XMLWriter output = null;
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			if (charset == null || "".equals(charset)) {
				charset = XML_UTF8;
			}
			format.setEncoding(charset);
			output = new XMLWriter(new OutputStreamWriter(new FileOutputStream(xmlFile), charset), format);
			output.write(doc);
			output.close();
		} catch (IOException e) {
			System.out.println("创建XML文件！"+e);
			return null;
		}
        return doc;
	}
	
	/**
	 * 根据路径加载XML文件
	 * @param xmlPath
	 * @return
	 */
	public static Document loadXMLFile(String xmlPath) {
		if(xmlPath == null || "".equals(xmlPath)) {
        	return null;
        }
		File file = new File(xmlPath);
		return loadXMLFile(file);
    }
	
	/**
	 * 根据文件加载
	 * @param xmlFile
	 * @return
	 */
	public static Document loadXMLFile(File xmlFile) {
		Document doc = null;
		if (xmlFile.exists() && xmlFile.isFile()) {
			SAXReader reader = new SAXReader();
			try {
				// 传入文件流，不要直接传入文件，不然中文乱码
				doc = reader.read(new FileInputStream(xmlFile));
			} catch (DocumentException e) {
				System.out.println("加载XML文件失败，XML文件中存在特殊字符或者编码格式错误！"+e.getMessage());
				return null;
			} catch (FileNotFoundException e) {
				System.out.println("加载XML文件失败，XML文件中存在特殊字符或者编码格式错误！"+e.getMessage());
				return null;
			}
		}
        return doc;
	}
	
	/**
	 * 保存XML文件
	 * @param doc
	 * @param xmlPath
	 */
	public static void saveXMLFile(Document doc, String xmlPath) {
		if(xmlPath == null || "".equals(xmlPath)) {
			System.out.println("xmlPath can not be null.");
			throw new NullPointerException("xmlPath can not be null.");
		}
		File xmlFile = new File(xmlPath);
		saveXMLFile(doc, xmlFile, null);
	}
	
	/**
	 * 以指定的编码格式保存XML文件
	 * @param doc
	 * @param xmlPath
	 * @param charset
	 */
	public static void saveXMLFile(Document doc, String xmlPath, String charset) {
		if(xmlPath == null || "".equals(xmlPath)) {
			System.out.println("xmlPath can not be null.");
			throw new NullPointerException("xmlPath can not be null.");
		}
		File xmlFile = new File(xmlPath);
		saveXMLFile(doc, xmlFile, charset);
	}
	
	/**
	 * 保存XML文件
	 * @param doc
	 * @param xmlFile
	 */
	public static void saveXMLFile(Document doc, File xmlFile) {
		saveXMLFile(doc, xmlFile, null);
	}
	
	/**
	 * 以指定的编码格式保存XML文件
	 * @param doc
	 * @param xmlFile
	 * @param charset
	 */
	public static void saveXMLFile(Document doc, File xmlFile, String charset) {
		if (doc == null) {
			System.out.println("document can not be null.");
			throw new NullPointerException("document can not be null.");
		}
		if (xmlFile.exists() && xmlFile.isFile()) {
			XMLWriter output = null;
			try {
				OutputFormat format = OutputFormat.createPrettyPrint();
				if (charset == null || "".equals(charset)) {
					charset = XML_UTF8;
				}
				format.setEncoding(charset);
				output = new XMLWriter(new OutputStreamWriter(new FileOutputStream(xmlFile), charset), format);
				output.write(doc);
				output.close();
			} catch (IOException e) {
				System.out.println("保存XML文件失败！");
			}
		}
	}
	
	/**
	 * 解析XML字符串，获取document对象。
	 * @param xmlStr XML字符串
	 * @return
	 */
	public static Document parseXMLStr(String xmlStr) {
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xmlStr);
		} catch (DocumentException e) {
			System.out.println("解析XML字符串失败！"+e);
		}
		return doc;
	}
	
	/**
	 * 以UTF-8编码格式返回XML字符串
	 * @param doc
	 * @return
	 */
	public static String xmltoString(Document doc) {
		return xmltoString(doc, null);
	}
	
	/**
	 * 以指定编码格式返回XML字符串
	 * @param doc
	 * @param charset 编码
	 * @return
	 */
	public static String xmltoString(Document doc, String charset) {
		if (doc == null) {
            return "";
        }
        if (charset == null || "".equals(charset)) {
            //return doc.asXML();// 转换成以UTF-8编码的字符串
        	charset = XML_UTF8;
        }
        
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding(charset);
        StringWriter strWriter = new StringWriter();
        XMLWriter xmlWriter = new XMLWriter(strWriter, format);
        try {
            xmlWriter.write(doc);
            xmlWriter.close();
        } catch (IOException e) {
        	System.out.println("XML转换成字符串失败！"+e);
        }
        return strWriter.toString();
	}
	
	/**
	 * 读取XML文件，返回XML字符串
	 * @param xmlPath
	 * @return
	 */
	public static String xmltoString(String xmlPath) {
		return xmltoString(xmlPath, XML_UTF8);
	}
	
	/**
	 * 读取XML文件，以指定编码格式返回XML字符串
	 * @param xmlPath XML文件路径
	 * @param charset 编码
	 * @return 
	 */
	public static String xmltoString(String xmlPath, String charset) {
		StringBuilder sb = new StringBuilder();
		
		File file = new File(xmlPath);
		BufferedReader reader = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(fis, charset));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("XML转换成字符串失败！"+e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ioe) {
					System.out.println("XML转换成字符串失败！"+ioe);
				}
			}
		}
		
		return sb.toString();
	}
	
	
	
	//<============================================================节点和属性操作============================================================>//
	/**
	 * 增加根节点
	 * @param doc
	 * @param rootNodeName 根节点名
	 * @return 根节点
	 */
	public static Element addRootNode(Document doc, String rootNodeName) {
		if (doc == null) {
			return null;
		}
		if (rootNodeName == null || "".equals(rootNodeName)) {
			return null;
		}
		return doc.addElement(rootNodeName);
	}
	
	/**
	 * 增加节点，除根节点外
	 * @param element
	 * @param nodeName 新增的节点名
	 * @return 子节点
	 */
	public static Element addNode(Element element, String nodeName) {
		if (element == null) {
			return null;
		}
		if (nodeName == null || "".equals(nodeName)) {
			return null;
		}
		return element.addElement(nodeName);
	}
	
	/**
	 * 增加节点，除根节点外，并指定内容
	 * @param element
	 * @param nodeName 新增的节点名
	 * @param text
	 * @return 子节点
	 */
	public static Element addNode(Element element, String nodeName, String text) {
		if (element == null) {
			return null;
		}
		if (nodeName == null || "".equals(nodeName)) {
			return null;
		}
		if (text == null) {
			return null;
		}
		Element ele = element.addElement(nodeName);
		ele.setText(text);
		return ele;
	}
	
	/**
	 * 添加注释，如果comment包含中文，请注意编码格式
	 * @param element
	 * @param comment 注释
	 * @return 当前节点
	 */
	public static Element addComment(Element element, String comment) {
		if (element == null) {
			return null;
		}
		if (comment == null || "".equals(comment)) {
			return null;
		}
		return element.addComment(comment);
	}
	
	/**
	 * 添加节点文本内容，已经存在则修改
	 * @param element
	 * @param text 文本内容
	 * @return 当前节点
	 */
	public static Element addText(Element element, String text) {
		if (element == null) {
			return null;
		}
		if (text == null || "".equals(text)) {
			text = "";
		}
		element.setText(text);
		return element;
	}
	
	/**
	 * 添加节点属性以及属性值，如果属性已经存在则修改属性值
	 * @param element
	 * @param attrName 属性名
	 * @param attrValue 属性值
	 * @return 当前节点
	 */
	public static Element addAttribute(Element element, String attrName, String attrValue) {
		if (element == null) {
			return null;
		}
		if (attrName == null || "".equals(attrName)) {
			return null;
		}
		if (attrValue == null) {
			attrValue = "";
		}
		return element.addAttribute(attrName, attrValue);
	}
	
	/**
	 * 获取根节点
	 * @param doc
	 * @return 根节点
	 */
	public static Element getRootNode(Document doc) {
		if (doc == null) {
			return null;
		}
		Element rootNode = doc.getRootElement();
		return rootNode;
	}
	
	/**
	 * 获得所有名称为childName的节点
	 * @param doc
	 * @param childName
	 * @return
	 */
	public static List<Element> getNodesByName(Document doc, String childName) {
		if(doc == null) {
			return null;
		}
		if(childName == null || "".equals(childName)) {
			return null;
		}
		List<Element> list = doc.selectNodes("//"+childName);
		return list;
	}
	
	/**
	 * 获得element节点下的名称为childName的所有子节点
	 * @param element
	 * @param childName
	 * @return
	 */
	public static List<Element> getChildrenNodesByName(Element element, String childName) {
		if(element == null) {
			return null;
		}
		if(childName == null || "".equals(childName)) {
			return null;
		}
		List<Element> list = element.elements(childName);
		return list;
	}
	
	/**
	 * 获取element节点下名称为childName的子节点的数量
	 * @param element
	 * @param childName
	 * @return
	 */
	public static int getNodeSizeByName(Element element, String childName) {
		if(element == null) {
			return 0;
		}
		if(childName == null || "".equals(childName)) {
			return 0;
		}
		return element.elements(childName).size();
	}
	
	/**
	 * 获得element节点下名称为childName的所有子节点中的第order个，order从0开始
	 * @param childName
	 * @param order
	 * @return
	 */
	public static Element getNodeByNameAndOrder(Element element, String childName, int order) {
		if(element == null) {
			return null;
		}
		if(childName == null || "".equals(childName)) {
			return null;
		}
		
		int size = element.elements(childName).size();
		System.out.println("当前节点下，名称为"+childName+"的子节点共有"+size+"个！");
		if (order > (size - 1) || order < 0) {
			System.out.println("order应该在0到"+(size - 1)+"之间！");
			return null;
		}
		return (Element)element.elements(childName).get(order);
	}
	
	/**
	 * 迭代element节点的所有子节点
	 * @param element
	 * @return
	 */
	public static Iterator<Element> getIterator(Element element) {
		if(element == null) {
			return null;
		}
		Iterator<Element> iterator = element.elementIterator();
		return iterator;
	}
	
	/**
	 * 获取节点内容
	 * @param element
	 * @return
	 */
	public static String getText(Element element) {
		return element.getText();
	}
	
	/**
	 * 获取节点名称
	 * @param element
	 * @return
	 */
	public static String getNodeName(Element element) {
		return element.getName();
	}
	
	/**
	 * 获得element节点的属性迭代器
	 * @param element
	 * @return
	 */
	public static Iterator<Attribute> getAttributeIterator(Element element) {
		if(element == null) {
			return null;
		}
		Iterator<Attribute> attrIterator = element.attributeIterator();
		return attrIterator;
	}
	
	/**
	 * 遍历element节点的所有属性
	 * @param element
	 * @return
	 */
	public static List<Attribute> getAttributeList(Element element) {
		if(element == null) {
			return null;
		}
		Iterator<Attribute> iterator = getAttributeIterator(element);
		if (iterator == null) {
			return null;
		}
		List<Attribute> list = new ArrayList<Attribute>();
		while (iterator.hasNext()) {
            Attribute attribute = iterator.next();
            list.add(attribute);
        }
		return list;
	}
	
	/**
	 * 根据属性获取属性名称
	 * @param attr
	 * @return
	 */
	public static String getAttrName(Attribute attr) {
		if (attr == null) {
			return null;
		}
		return attr.getName();
	}
	
	/**
	 * 根据属性获取属性值
	 * @param attr
	 * @return
	 */
	public static String getAttrValue(Attribute attr) {
		if (attr == null) {
			return null;
		}
		return attr.getValue();
	}
	
	/**
	 * 获得element节点attrName属性的值
	 * @param element
	 * @param attrName
	 * @return
	 */
	public static String getAttributeValue(Element element, String attrName) {
		attrName = attrName.trim();
		if(element == null) {
			return null;
		}
		if (attrName == null || "".equals(attrName)) {
			return null;
		}
		return element.attributeValue(attrName);
	}
	
	/**
	 * 获得element节点的所有属性及属性值
	 * @param element
	 * @return
	 */
	public static Map<String, String> getAttributeMap(Element element) {
		Map<String, String> attrMap = new HashMap<String, String>(); 
		if (element == null) {
			return null;
		}
		List<Attribute> attributeList = getAttributeList(element);
		if (attributeList == null) {
			return null;
		}
		for (Attribute attribute : attributeList) {
			String value = getAttributeValue(element, attribute.getName());
			attrMap.put(attribute.getName(), value);
		}
		return attrMap;
	}
	
	/**
	 * 判读element节点是否存在子节点
	 * @param element
	 * @return
	 */
	public static boolean hasChildren(Element element) {
		if (element == null) {
			return false;
		}
		return element.hasContent();
	}
	
	/**
	 * 删除element节点下所有名称为nodeName的子节点
	 * @param element
	 * @param nodeName
	 * @return
	 */
	public static boolean removeNodesByName(Element element, String nodeName) {
		if (element == null) {
			return false;
		}
		if (nodeName == null || "".equals(nodeName)) {
			return false;
		}
		Iterator<Element> iter = element.elementIterator(nodeName);
		while (iter.hasNext()) {
			Element ele = (Element) iter.next();
			element.remove(ele);
		}
		return true;
	}
	
	/**
	 * 删除element节点下名称为nodeName并且内容为text的子节点
	 * @param element
	 * @param nodeName
	 * @param text
	 * @return
	 */
	public static boolean removeNodeByNameAndText(Element element, String nodeName, String text) {
		if (element == null) {
			return false;
		}
		if (nodeName == null || "".equals(nodeName)) {
			return false;
		}
		if (text == null) {
			return false;
		}
		Iterator<Element> iter = element.elementIterator(nodeName);
		while (iter.hasNext()) {
			Element ele = (Element) iter.next();
			if (ele.getText().equals(text)) {
				element.remove(ele);
			}
		}
		return true;
	}
	
	/**
	 * 删除element节点的attrName属性
	 * @param element
	 * @param attrName
	 * @return
	 */
	public static boolean removeAttribute(Element element, String attrName) {
		if (element == null) {
			return false;
		}
		if (attrName == null || "".equals(attrName)) {
			return false;
		}
		Attribute attr = element.attribute(attrName);
		return element.remove(attr);
	}
	
	
	
	//<============================================================XPATH操作============================================================>//
	/**
	 * 通过Document和xpath获取节点列表(整个doc下)，xpath形式："/根节点"，"/根节点/一级子节点"，"/根节点/一级子节点/二级子节点"，......
	 * @param doc
	 * @param xpath
	 * @return
	 */
	public static List<Element> getNodesByXPath(Document doc, String xpath) {
		if (doc == null) {
			System.out.println("Document不能为空！");
			return null;
		}
		List<Element> list = null;
		try {
			list = doc.selectNodes(xpath);
		} catch (Exception e) {
			System.out.println("xpath获取节点失败！"+e);
		}
		return list;
	}
	
	/**
	 * 通过Element和xpath获取节点列表(element节点下)，xpath形式："element的一级子节点"，"element的一级子节点/element的二级子节点"，......
	 * @param element
	 * @param xpath
	 * @return
	 */
	public static List<Element> getNodesByXPath(Element element, String xpath) {
		if (element == null) {
			System.out.println("Element不能为空！");
			return null;
		}
		List<Element> list = null;
		try {
			list = element.selectNodes(xpath);
		} catch (Exception e) {
			System.out.println("xpath获取节点失败！"+e);
		}
		return list;
	}
	
	/**
	 * 通过Document和xpath获取第一个节点，xpath形式："/根节点"，"/根节点/一级子节点"，"/根节点/一级子节点/二级子节点"，......
	 * @param doc
	 * @param xpath
	 * @return
	 */
	public static Element getFirstNodeByXPath(Document doc, String xpath) {
		if (doc == null) {
			System.out.println("Document不能为空！");
			return null;
		}
		Element element = null;
		try {
			element = (Element) doc.selectSingleNode(xpath);
		} catch (Exception e) {
			System.out.println("xpath获取节点失败！"+e);
		}
		return element;
	}
	
	/**
	 * 通过Element和xpath获取节点列表(element节点下)，xpath形式："element的一级子节点"，"element的一级子节点/element的二级子节点"，......
	 * @param element
	 * @param xpath
	 * @return
	 */
	public static Element getFirstNodeByXPath(Element element, String xpath) {
		if (element == null) {
			System.out.println("Element不能为空！");
			return null;
		}
		Element ele = null;
		try {
			ele = (Element) element.selectSingleNode(xpath);
		} catch (Exception e) {
			System.out.println("xpath获取节点失败！"+e);
		}
		return ele;
	}
	
	/**
	 * 通过Document和xpath获取属性列表(整个doc下)，xpath形式："/根节点@attrName"，"/根节点/一级子节点@attrName"，"/根节点/一级子节点/二级子节点@attrName"，......，
	 * 其中attrName是属性名称
	 * @param doc
	 * @param xpath
	 * @return
	 */
	public static List<Attribute> getAttributesByXPath(Document doc, String xpath) {
		if (doc == null) {
			System.out.println("Document不能为空！");
			return null;
		}
		List<Attribute> list = null;
		try {
			list = doc.selectNodes(xpath);
		} catch (Exception e) {
			System.out.println("xpath获取属性失败！"+e);
		}
		return list;
	}
	
	/**
	 * 通过Element和xpath获取属性列表(element节点下)，xpath形式："element的一级子节点@attrName"，"element的一级子节点/element的二级子节点@attrName"，......，
	 * 其中attrName是属性名称
	 * @param element
	 * @param xpath
	 * @return
	 */
	public static List<Attribute> getAttributesByXPath(Element element, String xpath) {
		if (element == null) {
			System.out.println("Element不能为空！");
			return null;
		}
		List<Attribute> list = null;
		try {
			list = element.selectNodes(xpath);
		} catch (Exception e) {
			System.out.println("xpath获取属性失败！"+e);
		}
		return list;
	}
	
	
	
}
