package dataStructure.Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description:无向图的邻接表实现
 * @author: slfang
 * @time: 2020/7/21 16:06
 *
 * 当前类的使用的是无向图的邻接表实现的
 *     广度优先搜索和深度优先搜索是图上的两种最常用、最基本的搜索算法比起其他高级的搜索算法，
 *     比如 A*、IDA* 等，要简单粗暴，没有什么优化，所以，也被叫作暴力搜索算法。
 *     所以，这两种搜索算法仅适用于状态空间不大，也就是说图不大的搜索。
 *     深度优先和广度优先搜索的时间复杂度都是 O(E)【E代表边】，空间复杂度是 O(V)。
 */
public class GraphByLinkedList {

    private int v; // 顶点的个数
    private LinkedList adj[]; // 邻接表

    public int getV() {
        return v;
    }

    public LinkedList[] getAdj() {
        return adj;
    }

    public boolean isFound() {
        return found;
    }

    public GraphByLinkedList(int size){
        this.v = size;
        this.adj = new LinkedList[size];
        for (int i = 0; i <size ; i++) {
            adj[i] = new LinkedList();
        }
    }

    public void addEdge(int s, int t) { // 无向图一条边存两次
        adj[s].add(t);
        adj[t].add(s);
    }

    public static void main(String[] args) {
        GraphByLinkedList graph = new GraphByLinkedList(7);
        graph.creatEdge();
        graph.dfs(0,3);
    }

    private void creatEdge() {
        addEdge(0,1);
        addEdge(0,2);
        addEdge(0,6);
        addEdge(0,5);
        addEdge(5,3);
        addEdge(4,3);
        addEdge(4,5);
        addEdge(4,6);
    }

    /**
     * 广度优先搜索（BFS）
     *     度优先搜索（Breadth-First-Search），我们平常都简称 BFS。直观地讲，它其实就是一种“地毯式”
     *     层层推进的搜索策略，即先查找离起始顶点最近的，然后是次近的，依次往外搜索
     *
     * visited 是用来记录已经被访问的顶点，用来避免顶点被重复访问。如果顶点 q 被访问，那相应的 visited[q]会被设置为 true。
     * queue 是一个队列，用来存储已经被访问、但相连的顶点还没有被访问的顶点。因为广度优先搜索是逐层访问的，
     *       也就是说，我们只有把第 k 层的顶点都访问完成之后，才能访问第 k+1 层的顶点。当我们访问到第 k 层的顶点的时候，
     *       我们需要把第 k 层的顶点记录下来，稍后才能通过第 k 层的顶点来找第 k+1 层的顶点。所以，我们用这个队列来实现记录的功能。
     * prev 用来记录搜索路径。当我们从顶点 s 开始，广度优先搜索到顶点 t 后，prev 数组中存储的就是搜索的路径。
     *       不过，这个路径是反向存储的。prev[w]存储的是，顶点 w 是从哪个前驱顶点遍历过来的。
     *       比如，我们通过顶点 2 的邻接表访问到顶点 3，那 prev[3]就等于 2。
     *
     * @param s
     * @param t
     *
     * 其中 s 表示起始顶点，t 表示终止顶点。我们搜索一条从 s 到 t 的路径。实际上，这样求得的路径就是从 s 到 t 的最短路径。
     */
    public void bfs(int s, int t) {
        if (s == t) return;
        boolean[] visited = new boolean[v];
        visited[s]=true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }
        while (queue.size() != 0) {
            int w = queue.poll();
            for (int i = 0; i < adj[w].size(); ++i) {
                int q = (int) adj[w].get(i);
                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }

    private void print(int[] prev, int s, int t) { // 递归打印s->t的路径
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }


    boolean found = false; // 全局变量或者类成员变量

    /**
     * 深度优先搜索（Depth-First-Search），简称 DFS。最直观的例子就是“走迷宫”。
     * 假设你站在迷宫的某个岔路口，然后想找到出口。你随意选择一个岔路口来走，走着走着发现走不通的时候，
     *     你就回退到上一个岔路口，重新选择一条路继续走，直到最终找到出口。这种走法就是一种深度优先搜索策略。
     * 深度优先搜索找出来的路径，并不是顶点 s 到顶点 t 的最短路径。实际上，深度优先搜索用的是一种比较著名的算法思想，
     *     回溯思想。这种思想解决问题的过程，非常适合用递归来实现
     * @param s
     * @param t
     *
     * prev、visited 变量它们跟广度优先搜索代码实现里的作用是一样的
     *      不过，深度优先搜索代码实现里，有个比较特殊的变量 found，
     *      它的作用是，当我们已经找到终止顶点 t 之后，我们就不再递归地继续查找了。
     */
    public void dfs(int s, int t) {
        found = false;
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }
        recurDfs(s, t, visited, prev);
        print(prev, s, t);
    }

    private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
        if (found == true) return;
        visited[w] = true;
        if (w == t) {
            found = true;
            return;
        }
        for (int i = 0; i < adj[w].size(); ++i) {
            int q = (int) adj[w].get(i);
            if (!visited[q]) {
                prev[q] = w;
                recurDfs(q, t, visited, prev);
            }
        }
    }
}
