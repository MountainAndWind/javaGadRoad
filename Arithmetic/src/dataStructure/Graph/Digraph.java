package dataStructure.Graph;


import java.util.LinkedList;
import java.util.Stack;

/**
 * @description:有向图的实现
 * 基本构造与无向图一致，addEdge方法不存在相对性，且它还存在reverse()方法来返回图的反向图
 * @author: slfang
 * @time: 2020/8/1 20:46
 */
public class Digraph {

    public static void main(String[] args) {
        Digraph digraph = new Digraph(13);

        digraph.initOrder();
       /*
        digraph.addEdge(0,5);
        digraph.addEdge(3,5);
        digraph.addEdge(5,4);
        digraph.addEdge(4,3);
        boolean cycle = digraph.isCycle(digraph);
        System.out.println(cycle);*/
        DepthFirstOrder depthFirstOrder = new DepthFirstOrder(digraph);
        System.out.println(depthFirstOrder.getPre());
        System.out.println(depthFirstOrder.getPost());
        System.out.println(depthFirstOrder.getResversePost());
    }

    private void initOrder() {
        addEdge(0,5);
        addEdge(0,1);
        addEdge(0,6);
        addEdge(2,0);
        addEdge(2,3);
        addEdge(3,5);
        addEdge(5,4);
        addEdge(6,4);
        addEdge(7,6);
        addEdge(8,7);
        addEdge(6,9);
        addEdge(9,11);
        addEdge(9,12);
        addEdge(9,10);
        addEdge(11,12);
    }

    /**
     * 链表数组
     */
    private LinkedList<Integer>[] adj;

    /**
     * 边的个数
     */
    private int E;

    /**
     * 顶点个数
     */
    private int V;


    public LinkedList<Integer> adj(int v){
        return adj[v];
    }

    public Digraph(int size) {
        this.V = size;
        adj = new LinkedList[size];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList();
        }

    }

    public void addEdge(int s,int w){
        adj[s].add(w);
        E++;
    }

    public Digraph reverse(){
        Digraph r = new Digraph(V);
        for (int i = 0; i < adj.length; i++) {
            LinkedList<Integer> linkedList = adj[i];
            for (Integer n : linkedList) {
                r.addEdge(n,i);
            }
        }
        return r;
    }


    /**
     * 多点可达性
     * 垃圾回收机制的标记清除算法类似
     * @param G
     * @param sources
     * @return
     */
    public boolean[] DirectedDfs(Digraph G,Iterable<Integer> sources){
        boolean[] marked = new boolean[G.V];
        for (Integer source : sources) {
            if(!marked[source]){
                dfs(G,source,marked);
            }
        }
        return marked;
    }


    public boolean[]  dfs(Digraph G,int v,boolean[] marked){
        marked[v] = true;
        LinkedList<Integer> vlist = G.getAdj()[v];
        for (Integer n : vlist) {
            if(!marked[n]){
                 dfs(G,n,marked);
            }
        }
        return marked;
    }

    private Stack<Integer> cycle = null;

    private boolean isCycle;

    /**
     * 是否有环
     * @return
     */
    public boolean isCycle(Digraph digraph){
        int[] edgeTo = new int[digraph.getV()];
        boolean[] marked = new boolean[digraph.getV()];
        boolean[] onStack = new boolean[digraph.getV()];
        for (int i = 0; i < digraph.getV(); i++) {
            if(!marked[i]) isCycleDfs(digraph, marked, onStack, edgeTo, i);
        }
        System.out.println(cycle);
        return isCycle;
    }

    private void isCycleDfs(Digraph digraph, boolean[] marked, boolean[] onStack, int[] edgeTo,  int v) {
        marked[v] = true;
        onStack[v] = true;
        LinkedList<Integer> linkedList = digraph.getAdj()[v];
        for (Integer w : linkedList) {
            if(isCycle) return;
            if(!marked[w]){
                edgeTo[w] = v;
                isCycleDfs(digraph,marked,onStack,edgeTo,w);
            }else if(onStack[w]){
                cycle = new Stack<Integer>();
                isCycle = true;
                for (int x = v; x !=w ; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }


    public LinkedList[] getAdj() {
        return adj;
    }

    public void setAdj(LinkedList[] adj) {
        this.adj = adj;
    }

    public int getE() {
        return E;
    }

    public void setE(int e) {
        E = e;
    }

    public int getV() {
        return V;
    }

    public void setV(int v) {
        V = v;
    }

}
