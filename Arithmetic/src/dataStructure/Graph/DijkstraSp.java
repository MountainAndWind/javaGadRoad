package dataStructure.Graph;

import dataStructure.Queue.IndexMinPQ;

import java.util.Arrays;
import java.util.Stack;

/**
 * @description:空间与顶点v成正比，时间与ElogV成正比
 * 前提条件：权重不为负数
 * @author: slfang
 * @time: 2020/8/10 22:29
 */
public class DijkstraSp {
    private double[] disto;
    private EdgeWeightedDiGraph.DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> minPQ;

    public static void main(String[] args) {
        EdgeWeightedDiGraph diGraph = new EdgeWeightedDiGraph(8);
        diGraph.createData();
        DijkstraSp dijkstraSp = new DijkstraSp(diGraph,0);
        System.out.println(Arrays.toString(dijkstraSp.disto));
    }

    public DijkstraSp(EdgeWeightedDiGraph diGraph,int s) {
        disto = new double[diGraph.getV()];
        edgeTo = new EdgeWeightedDiGraph.DirectedEdge[diGraph.getV()];
        minPQ = new IndexMinPQ<Double>(diGraph.getV());

        Arrays.fill(disto, Double.POSITIVE_INFINITY);
        disto[s] = 0;
        minPQ.insert(s,0.0);
        while (!minPQ.isEmpty()){
            relax(minPQ.delMin(),diGraph);
        }
    }

    private void relax(int i, EdgeWeightedDiGraph diGraph) {
        for (EdgeWeightedDiGraph.DirectedEdge edge : diGraph.adj(i)) {
            int w = edge.to();
            int v = edge.from();
            if(disto[w]>disto[v]+edge.getWeight()){
                disto[w] = disto[v]+edge.getWeight();
                edgeTo[w] = edge;
                if(minPQ.contains(w)){
                    minPQ.changeKey(w,disto[w]);
                }else{
                    minPQ.insert(w,disto[w]);
                }
            }
        }
    }


    public double disto(int v){
        return disto[v];
    }

    public boolean hasPathTo(int v){
        return disto[v]<Double.POSITIVE_INFINITY;
    }

    public Iterable<EdgeWeightedDiGraph.DirectedEdge> pathTo(int v){
        if(!hasPathTo(v))
            return null;
        Stack<EdgeWeightedDiGraph.DirectedEdge> stack = new Stack<>();
        for (EdgeWeightedDiGraph.DirectedEdge edge = edgeTo[v];edge!=null;edge = edgeTo[edge.from()]) {
            stack.push(edge);
        }
        return stack;
    }
}

/**
 * 解决任意两点的最短路径
 */
class DijkstraAllPairsSp{
    private DijkstraSp[] sps;

    public DijkstraAllPairsSp(EdgeWeightedDiGraph diGraph) {
        sps = new DijkstraSp[diGraph.getV()];
        for (int i = 0; i < diGraph.getV(); i++) {
            sps[i] = new DijkstraSp(diGraph,i);
        }
    }

    Iterable<EdgeWeightedDiGraph.DirectedEdge> pathTo(int s,int w){
        return sps[s].pathTo(w);
    }

    double dist(int s,int t){
        return sps[s].disto(t);
    }
}