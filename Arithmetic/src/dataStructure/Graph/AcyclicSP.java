package dataStructure.Graph;

import java.util.Arrays;

/**
 * @description:�廷��Ȩͼ����ͼ�����·���㷨
 * 1����dijkstra�㷨�������
 * 2������ʱ���ڽ���������·��
 * 3���ܹ�����Ȩ�ر�
 * 4���ܹ������ص����⣬�����ҳ��·��
 * @author: slfang
 * @time: 2020/8/11 14:24
 */
public class AcyclicSP {

    private EdgeWeightedDiGraph.DirectedEdge[] edgeTo;

    private double[] distTo;


    public AcyclicSP(EdgeWeightedDiGraph g,int s) {
        edgeTo = new EdgeWeightedDiGraph.DirectedEdge[g.getV()];
        distTo = new double[g.getV()];
        Arrays.fill(distTo,Double.POSITIVE_INFINITY);
        distTo[s] = 0;
        Iterable<Integer> integers = Topological.topologicalByInDragee(g);
        for (Integer integer : integers) {
            relax(g,integer);
        }
    }

    private void relax(EdgeWeightedDiGraph G, int v){
        for (EdgeWeightedDiGraph.DirectedEdge edge : G.adj(v)) {
            int w = edge.to();
            if(distTo[w]>distTo[v]+edge.getWeight()){
                distTo[w] = distTo[v]+ edge.getWeight();
                edgeTo[w] = edge;
            }
        }
    }
}
