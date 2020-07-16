package test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName
 * @Description TODO
 * @Author fangshilei
 * @Date 2019/11/8 11:35
 * @Version 1.0
 **/
public class ObjectEquals {


    /*用于存储同行重复字段*/
    private static List<String> currrntRepList = new ArrayList<String>();

    public static void main(String[] args) {

    }

    @Test
    public void test1(){
        List<Dt1Node> list = new ArrayList<>();
        int count=0;
        Dt1Node node = new Dt1Node("1","1","1","1");
        ListCompare(list, node,count);
        list.add(node);
        count++;

        Dt1Node node2 = new Dt1Node("1","1","1","1");
        ListCompare(list, node2,count);
        list.add(node2);
        count++;

        Dt1Node node3 = new Dt1Node("22","2","2","2");
        ListCompare(list, node3,count);
        list.add(node3);
        count++;

        Dt1Node node4 = new Dt1Node("2","2","2","2");
        ListCompare(list, node4,count);
        list.add(node4);
        count++;
        System.out.println(currrntRepList);
    }

    /**
     * 当前提交数据校验
     * @param list
     * @param node
     * @param count
     */
    private void ListCompare(List<Dt1Node> list, Dt1Node node, int count) {
        for (int i = 0; i < list.size(); i++) {
            Dt1Node dt1Node = list.get(i);
            if(dt1Node.equals(node)){
                int index2=count+1;
                int index1=i+1;
                currrntRepList.add(index1+","+index2);
            }
        }
    }
    /**
     * 此处创建内部类用于封装明细1结果集
     */
    class Dt1Node{
        private String cbzx;
        private String mt;
        private String qj;
        private String dls;

        public Dt1Node(String cbzx, String mt, String qj, String dls) {
            this.cbzx = cbzx;
            this.mt = mt;
            this.qj = qj;
            this.dls = dls;
        }

        @Override
        public boolean equals(Object o) {
            Dt1Node dt1Node = (Dt1Node) o;
            return Objects.equals(cbzx, dt1Node.cbzx) &&
                    Objects.equals(mt, dt1Node.mt) &&
                    Objects.equals(qj, dt1Node.qj) &&
                    Objects.equals(dls, dt1Node.dls);
        }
    }

}
