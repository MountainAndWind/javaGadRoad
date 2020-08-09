package dataStructure.Graph;

import sun.security.provider.MD2;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description:懒加载prim 算法
 * @author: slfang
 * @time: 2020/8/5 16:31
 */
public class LazyPrimMsT {

    private boolean marked[];//最小生成树的顶点
    private Queue<EdgeWeightedGraph.Edge> mst;//最小生成数的边
    private PriorityQueue<EdgeWeightedGraph.Edge> pq;//横切边


    public LazyPrimMsT(EdgeWeightedGraph graph) throws Exception {
        marked = new boolean[graph.getV()];
        pq = new PriorityQueue();
        mst = new LinkedList<>();
        visit(graph,0);
        while (!pq.isEmpty()){
            EdgeWeightedGraph.Edge min = pq.poll();
            int v = min.either();
            int w = min.other(v);
            if(marked[v]&&marked[w]) continue;
            mst.add(min);
            if(!marked[v])visit(graph,v);
            if(!marked[w])visit(graph,w);
        }
    }

    private void visit(EdgeWeightedGraph graph, int v) throws Exception {
        marked[v] = true;
        for (EdgeWeightedGraph.Edge edge : graph.adj(v)) {
            if(!marked[edge.other(v)]){
                pq.add(edge);
            }
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
        LazyPrimMsT lazyPrimMsT = new LazyPrimMsT(graph);
        LinkedList<EdgeWeightedGraph.Edge> edges = (LinkedList<EdgeWeightedGraph.Edge>)lazyPrimMsT.edges();
        for (EdgeWeightedGraph.Edge edge : edges) {
            System.out.println(edge.either()+"----->"+edge.other(edge.either()));
        }
        System.out.println(lazyPrimMsT.weight());
    }


}
