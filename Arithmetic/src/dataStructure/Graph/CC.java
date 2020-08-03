package dataStructure.Graph;

import java.util.LinkedList;

/**
 * @description:连通分量api：概念地址：https://www.jianshu.com/p/f5a72b93db5d
 * @author: slfang
 * @time: 2020/7/29 21:35
 */
public class CC {


    private boolean isVisited[];

    private int[] ids;

    private int count;

    public static void main(String[] args) {

    }
    public CC(GraphByLinkedList graph){
        isVisited = new boolean[graph.getV()];
        ids = new int[graph.getV()];
        LinkedList[] adj = graph.getAdj();
        for (int i = 0; i < graph.getV(); i++) {
            count++;
            dfs(graph,i);
        }
    }
    private void dfs(GraphByLinkedList graph, int i) {
        isVisited[i] = true;
        ids[i]=count;
        LinkedList<Integer> linkedList = graph.getAdj()[i];
        for (Integer integer : linkedList) {
            if(!isVisited[integer]){
                dfs(graph,integer);
            }
        }

    }

    public boolean connectted(int v,int w){
        return ids[v]==ids[w];
    }

    public int count(){
        return count;
    }

    public int id(int v){
        return ids[v];
    }

    /**
     * 是否是无环图
     * 解析：
     * https://www.cnblogs.com/TenosDoIt/p/3644225.html
     * @param graph
     * @return
     */
    boolean isCycle(GraphByLinkedList graph){
        boolean[] marked = new boolean[graph.getV()];
        boolean haCycle = false;
        for (int i = 0; i <graph.getV() ; i++) {
            if(!marked[i]){
                dfsIsCycle(graph,marked,i,i,haCycle);
            }
        }
        return haCycle;
    }

    /**
     *
     * @param graph
     * @param marked
     * @param w
     * @param u 此处用作父节点使用
     * @param haCycle
     */
    private void dfsIsCycle(GraphByLinkedList graph, boolean[] marked, int w, int u, boolean haCycle) {
        marked[w] = true;
        for (Object o : graph.getAdj()[w]) {
            if(!marked[(int)o]){
                dfsIsCycle(graph,marked,(int)o,w, haCycle);
            }else{
                if(u!=(int)o){
                    haCycle = true;
                }
            }
        }
    }

    /**
     * 是否是二分图
     * 思路：相琳的两个点颜色不能是一样
     * @return
     */
    boolean isTwoColorGra(GraphByLinkedList graph){
        int v = graph.getV();
        boolean[] isVisited = new boolean[graph.getV()];
        boolean[] color = new boolean[graph.getV()];
        LinkedList[] adj = graph.getAdj();
        boolean temp = false;
        for (int i = 0; i < v; i++) {
            dfs(isVisited,i,adj,color,temp);
        }
        return temp;
    }

    private void dfs(boolean[] isVisited, int i, LinkedList<Integer>[] adj, boolean[] color,boolean temp) {
        isVisited[i] = true;
        for (int w : adj[i]) {
            if(!isVisited[w]){
                color[w] = !color[i];
                dfs(isVisited,w,adj,color,temp);
            }
            else if(color[w]==color[i]){
                temp = false;
            }
        }
    }


}
