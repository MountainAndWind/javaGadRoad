package dataStructure.Graph;


import java.util.LinkedList;

/**
 * @description:加权无向图
 * @author: slfang
 * @time: 2020/8/5 14:57
 */
public class EdgeWeightedGraph {

    private int V;
    private int E;
    private LinkedList<Edge>[] adj;

    public EdgeWeightedGraph(int size) {
        this.V = size;
        adj = new LinkedList[size];
        E = 0;
        for (int i = 0; i <size ; i++) {
            adj[i] = new LinkedList<Edge>();
        }
    }



    public void addEdge(Edge e) throws Exception {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v){
        return adj[v];
    }

    public EdgeWeightedGraph(LinkedList<Edge>[] adj) {
        this.adj = adj;
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public class Edge implements Comparable<Edge>{
        private int v;//一个顶点
        private int w;//另一个顶点
        private double weight;

        public Edge(int v, int w, double weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public double weight(){
            return weight;
        }

        public int either(){
            return v;
        }

        public int other(int vertex) throws Exception {
            if(vertex==v){
                return w;
            }else if(vertex==w){
                return v;
            }else{
                throw new Exception("error vertex");
            }
        }

        @Override
        public int compareTo(Edge o) {
            if(o.weight()==this.weight()){
               return 0;
            }
            return this.weight()-o.weight()>0?1:-1;
        }
    }

    void createData() throws Exception {
        Edge e = new Edge(0,7,0.16);
        Edge e1 = new Edge(0,2,0.26);
        Edge e2 = new Edge(0,6,0.58);
        Edge e3 = new Edge(0,4,0.36);
        Edge e4 = new Edge(1,2,0.36);
        Edge e5 = new Edge(1,3,0.29);
        Edge e6 = new Edge(1,5,0.32);
        Edge e7 = new Edge(1,7,0.19);
        Edge e8 = new Edge(2,3,0.17);
        Edge e9 = new Edge(2,6,0.40);
        Edge e10 = new Edge(2,7,0.34);
        Edge e11 = new Edge(4,5,0.35);
        Edge e12 = new Edge(4,7,0.37);
        Edge e13 = new Edge(4,6,0.93);
        Edge e14 = new Edge(5,7,0.28);
        addEdge(e);
        addEdge(e1);
        addEdge(e2);
        addEdge(e3);
        addEdge(e4);
        addEdge(e5);
        addEdge(e6);
        addEdge(e7);
        addEdge(e8);
        addEdge(e9);
        addEdge(e11);
        addEdge(e12);
        addEdge(e13);
        addEdge(e14);
    }
}
