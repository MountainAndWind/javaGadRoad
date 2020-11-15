package dataStructure.Graph;

import java.util.Arrays;

/**
 * @description:五环加权图有向图的最短路径算法
 * 1、比dijkstra算法更快跟简单
 * 2、线性时间内解决单点最短路径
 * 3、能够处理负权重边
 * 4、能够解决相关的问题，列如找出最长路径
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
