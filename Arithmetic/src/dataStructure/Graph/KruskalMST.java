package dataStructure.Graph;


import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @description: 思想：讲边排序，找到n-1边加入树中，其中加入的边不能构成回路
 * @author: slfang
 * @time: 2020/8/7 15:27
 */
public class KruskalMST {

    private PriorityQueue<EdgeWeightedGraph.Edge> priorityQueue;

    private List<EdgeWeightedGraph.Edge> mst;

    public KruskalMST(EdgeWeightedGraph graph) throws Exception {
        mst = new LinkedList<>();
        priorityQueue = new PriorityQueue();
        for (EdgeWeightedGraph.Edge edge : graph.Edgs()) {
            priorityQueue.offer(edge);
        }
        WeightedQuickUnionUF uf =new WeightedQuickUnionUF(graph.getV());
        while (!priorityQueue.isEmpty()&&mst.size()<graph.getV()-1){
            EdgeWeightedGraph.Edge min = priorityQueue.poll();
            int v = min.either();
            int w = min.other(v);
            if(uf.connected(v,w)) continue;
            uf.union(v,w);
            mst.add(min);
        }
    }

    public Iterable<EdgeWeightedGraph.Edge> edges(){
        return mst;
    }

    public double weight(){
        double weight =0 ;
        for (EdgeWeightedGraph.Edge e:mst)
            weight+=e.weight();
        return weight;
    }

    public static void main(String[] args) throws Exception {
        EdgeWeightedGraph graph = new EdgeWeightedGraph(8);
        graph.createData();
        KruskalMST mst = new KruskalMST(graph);
        System.out.println(mst.weight());
    }
}
