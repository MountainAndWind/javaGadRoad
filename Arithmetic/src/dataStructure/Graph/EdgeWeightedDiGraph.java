package dataStructure.Graph;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @description:有向加权图
 * @author: slfang
 * @time: 2020/8/10 17:16
 */
public class EdgeWeightedDiGraph {

    private int v;
    private int E;
    private LinkedList<DirectedEdge>[] adj;

    public EdgeWeightedDiGraph(int v) {
        this.v = v;
        this.E = 0;
        adj = new LinkedList[v];
        for (int i = 0; i <v ; i++) {
            adj[i] = new LinkedList<DirectedEdge>();
        }
    }

    void addEdge(DirectedEdge edge){
        int start = edge.from();
        adj[start].add(edge);
        E++;
    }


    public Iterable<DirectedEdge> edges(){
        Set<DirectedEdge> edgeSet = new HashSet<>();
        for (LinkedList<DirectedEdge> edges : adj) {
            for (DirectedEdge edge : edges) {
                edgeSet.add(edge);
            }
        }
        return edgeSet;
    }


    public int getV() {
        return v;
    }

    public int getE() {
        return E;
    }

    public LinkedList<DirectedEdge>[] getAdj() {
        return adj;
    }

    public LinkedList<DirectedEdge> adj(int i){
        return adj[i];
    }

    public int getIn(int i) {
        int count=0;
        for (LinkedList<DirectedEdge> directedEdges : adj) {
            for (DirectedEdge directedEdge : directedEdges) {
                if(directedEdge.to()==i){
                    count++;
                }
            }
        }
        return count;
    }

    public class DirectedEdge implements Comparable<DirectedEdge>{
        private int v;//起点
        private int w;//终点
        private double weight;//权重

        public DirectedEdge(int v, int w, double weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        @Override
        public int compareTo(DirectedEdge o) {
            return 0;
        }

        public int from() {
            return v;
        }

        public int to() {
            return w;
        }


        public double getWeight() {
            return weight;
        }

    }

    void createData(){
        DirectedEdge e1 = new DirectedEdge(0,2,0.26);
        DirectedEdge e3 = new DirectedEdge(0,4,0.38);

        DirectedEdge e5 = new DirectedEdge(1,3,0.29);

        DirectedEdge e10 = new DirectedEdge(2,7,0.34);

        DirectedEdge e2 = new DirectedEdge(3,6,0.52);

        DirectedEdge e11 = new DirectedEdge(4,5,0.35);
        DirectedEdge e112 = new DirectedEdge(4,7,0.37);

        DirectedEdge e4 = new DirectedEdge(5,4,0.35);
        DirectedEdge e6 = new DirectedEdge(5,1,0.32);
        DirectedEdge e7 = new DirectedEdge(5,7,0.28);

        DirectedEdge e8 = new DirectedEdge(6,4,0.93);
        DirectedEdge e9= new DirectedEdge(6,2,0.40);
        DirectedEdge e111 = new DirectedEdge(6,0,0.58);

        DirectedEdge e14 = new DirectedEdge(7,5,0.28);
        DirectedEdge e15 = new DirectedEdge(7,3,0.39);
        addEdge(e1);
        addEdge(e3);
        addEdge(e5);
        addEdge(e10);
        addEdge(e2);
        addEdge(e11);
        addEdge(e112);
        addEdge(e4);
        addEdge(e6);
        addEdge(e7);
        addEdge(e8);
        addEdge(e9);
        addEdge(e111);
        addEdge(e14);
        addEdge(e15);
    }

}
