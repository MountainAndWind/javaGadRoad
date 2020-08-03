package dataStructure.Graph;

import java.util.*;

/**
 * @description:基于深度优先搜搜的顶点排序
 * @author: slfang
 * @time: 2020/8/3 14:55
 */
public class DepthFirstOrder {

    private boolean[] marked;
    private Queue<Integer> pre;  //前序顶点的前序排序

    private Queue<Integer> post;  //前序顶点的后续排序

    private Stack<Integer> resversePost;  //前序顶点的逆后续排序

    public DepthFirstOrder(Digraph digraph) {
        marked = new boolean[digraph.getV()];
        pre = new LinkedList<>();
        post = new LinkedList<>();
        resversePost = new Stack<>();
        for (int i = 0; i <digraph.getV() ; i++) {
            if(!marked[i]){
                dfs(digraph,i);
            }
        }
    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        pre.add(v);
        for (Integer w : digraph.adj(v)) {
            if(!marked[w]){
                dfs(digraph,w);
            }
        }
        post.add(v);
        resversePost.push(v);
    }

    public Queue<Integer> getPre() {
        return pre;
    }

    public Queue<Integer> getPost() {
        return post;
    }

    public Stack<Integer> getResversePost() {
        return resversePost;
    }
}
