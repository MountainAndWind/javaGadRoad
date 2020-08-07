package dataStructure.Graph;

import jdk.nashorn.internal.ir.ReturnNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @description:��������
 * ������ֻػ���������������
 *
 * ʵ��1��������ȱ�������DepthFirstOrder ��getResversePostʵ��
 * ʵ��2���������
 * @author: slfang
 * @time: 2020/8/3 14:54
 */
public class Topological {

    private Iterable<Integer> order;
    public Topological(Digraph digraph){
        if(!digraph.isCycle(digraph)){
            DepthFirstOrder  depthFirstOrder = new DepthFirstOrder(digraph);
            order = depthFirstOrder.getResversePost();//
        }
    }

    public Iterable<Integer> getOrder() {
        return order;
    }

    public boolean isDaG(){
        return order !=null;
    }

    public Iterable<Integer>  topologicalByInDragee(Digraph digraph){
        int[] arrInDegree = new int[digraph.getV()];
        Stack<Integer> stack = new Stack<>();

        List<Integer> outList = new LinkedList<>();
        for (int i = 0; i <digraph.getV() ; i++) {
            arrInDegree[i] = digraph.getIn(i);
            if(digraph.getIn(i)==0){
                stack.push(i);
            }
        }
        while (!stack.isEmpty()){
            Integer pop = stack.pop();
            outList.add(pop);
            LinkedList<Integer> linkedList = digraph.getAdj()[pop];
            for (Integer w : linkedList) {
                arrInDegree[w] = arrInDegree[w]-1;
                if(arrInDegree[w]==0){
                    stack.push(w);
                }
            }
        }
        return outList;
    }











}
