package dataStructure.Graph;

import java.util.HashMap;

/**
 * @description:  ����ͼ��ʵ�֣��˴�Ϊ����ͼ��ʵ�֣�����ͼ��ʵ��graph �滻��Digraph
 * @author: slfang
 * @time: 2020/7/30 21:30
 */
public class StringGraph {

    private HashMap<String,Integer> keys = new HashMap<>();

    private String[] values;

    private GraphByLinkedList graph;

    public StringGraph(String stream,String spiltChar) {
        String[] streamArr = stream.split(spiltChar);
        for (int i = 0; i <streamArr.length ; i++) {
            keys.put(streamArr[i],keys.size());
        }
        values = new String[keys.size()];
    }

}
