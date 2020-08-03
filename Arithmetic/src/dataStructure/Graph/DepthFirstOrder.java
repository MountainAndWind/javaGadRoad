package dataStructure.Graph;

import java.util.*;

/**
 * @description:��������������ѵĶ�������
 * @author: slfang
 * @time: 2020/8/3 14:55
 */
public class DepthFirstOrder {

    private boolean[] marked;
    private Queue<Integer> pre;  //ǰ�򶥵��ǰ������

    private Queue<Integer> post;  //ǰ�򶥵�ĺ�������

    private Stack<Integer> resversePost;  //ǰ�򶥵�����������

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
