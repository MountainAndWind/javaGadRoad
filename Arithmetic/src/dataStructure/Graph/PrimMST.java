package dataStructure.Graph;

import dataStructure.Queue.IndexMinPQ;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

/**
 * @description:prim算法的即时版本
 * @author: slfang
 * @time: 2020/8/6 9:54
 */
public class PrimMST {

    private EdgeWeightedGraph.Edge[] edgeTo;//距离树最近的边
    private double[] distTo;//distTo[w] = edgeTo[w].weight();
    private boolean[] marked;
    private IndexMinPQ<Double> pq;//有效的横切边

    public PrimMST(EdgeWeightedGraph graph) throws Exception {
        edgeTo = new EdgeWeightedGraph.Edge[graph.getV()];
        distTo = new double[graph.getV()];
        marked = new boolean[graph.getV()];
        for (int i = 0; i <graph.getV() ; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(graph.getV());
        distTo[0] = 0.0;
        pq.insert(0,0.0);
        while (!pq.isEmpty()){
             visit(graph,pq.delMin());
        }

    }

    private void visit(EdgeWeightedGraph graph, int v) throws Exception {
        marked[v] = true;
        for (EdgeWeightedGraph.Edge edge : graph.adj(v)) {
            int w = edge.other(v);
            if(marked[w]) continue;
            if(edge.weight()<distTo[w]){
                edgeTo[w] = edge;
                distTo[w] = edge.weight();
                if(pq.contains(w)){
                    pq.changeKey(w,distTo[w]);
                }else{
                    pq.insert(w,distTo[w]);
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        EdgeWeightedGraph  graph = new EdgeWeightedGraph(8);
        graph.createData();
        PrimMST mst = new PrimMST(graph);
    }

}
