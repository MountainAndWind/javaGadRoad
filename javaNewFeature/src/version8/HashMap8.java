package version8;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:  hashMap 1.8������
 * @author: slfang
 * @time: 2020/6/17 16:14
 */
public class HashMap8 {


    //1.8�м����˺����  ��ǰ�����д���8 ��map������������64  ת���ɺ������ת���ɺ����֮����������������Ч�ʶ�����������
    //ӦΪ1.7��ͷ�巨���룬1.8֮�����β�巨
    //ConCurrentHashMap  ����cas���ײ����ϵͳ֧�ֵ�Ч�ʸߣ�   putVal  /// no lock when adding to empty bin
    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

    }
}
