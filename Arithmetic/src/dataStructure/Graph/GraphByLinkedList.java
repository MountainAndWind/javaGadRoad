package dataStructure.Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description:����ͼ���ڽӱ�ʵ��
 * @author: slfang
 * @time: 2020/7/21 16:06
 *
 * ��ǰ���ʹ�õ�������ͼ���ڽӱ�ʵ�ֵ�
 *     ��������������������������ͼ�ϵ�������á�������������㷨���������߼��������㷨��
 *     ���� A*��IDA* �ȣ�Ҫ�򵥴ֱ���û��ʲô�Ż������ԣ�Ҳ���������������㷨��
 *     ���ԣ������������㷨��������״̬�ռ䲻��Ҳ����˵ͼ�����������
 *     ������Ⱥ͹������������ʱ�临�Ӷȶ��� O(E)��E����ߡ����ռ临�Ӷ��� O(V)��
 */
public class GraphByLinkedList {

    private int v; // ����ĸ���
    private LinkedList adj[]; // �ڽӱ�

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

    public void addEdge(int s, int t) { // ����ͼһ���ߴ�����
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
     * �������������BFS��
     *     ������������Breadth-First-Search��������ƽ������� BFS��ֱ�۵ؽ�������ʵ����һ�֡���̺ʽ��
     *     ����ƽ����������ԣ����Ȳ�������ʼ��������ģ�Ȼ���Ǵν��ģ�������������
     *
     * visited ��������¼�Ѿ������ʵĶ��㣬�������ⶥ�㱻�ظ����ʡ�������� q �����ʣ�����Ӧ�� visited[q]�ᱻ����Ϊ true��
     * queue ��һ�����У������洢�Ѿ������ʡ��������Ķ��㻹û�б����ʵĶ��㡣��Ϊ������������������ʵģ�
     *       Ҳ����˵������ֻ�аѵ� k ��Ķ��㶼�������֮�󣬲��ܷ��ʵ� k+1 ��Ķ��㡣�����Ƿ��ʵ��� k ��Ķ����ʱ��
     *       ������Ҫ�ѵ� k ��Ķ����¼�������Ժ����ͨ���� k ��Ķ������ҵ� k+1 ��Ķ��㡣���ԣ����������������ʵ�ּ�¼�Ĺ��ܡ�
     * prev ������¼����·���������ǴӶ��� s ��ʼ������������������� t ��prev �����д洢�ľ���������·����
     *       ���������·���Ƿ���洢�ġ�prev[w]�洢���ǣ����� w �Ǵ��ĸ�ǰ��������������ġ�
     *       ���磬����ͨ������ 2 ���ڽӱ���ʵ����� 3���� prev[3]�͵��� 2��
     *
     * @param s
     * @param t
     *
     * ���� s ��ʾ��ʼ���㣬t ��ʾ��ֹ���㡣��������һ���� s �� t ��·����ʵ���ϣ�������õ�·�����Ǵ� s �� t �����·����
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

    private void print(int[] prev, int s, int t) { // �ݹ��ӡs->t��·��
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }


    boolean found = false; // ȫ�ֱ����������Ա����

    /**
     * �������������Depth-First-Search������� DFS����ֱ�۵����Ӿ��ǡ����Թ�����
     * ������վ���Թ���ĳ����·�ڣ�Ȼ�����ҵ����ڡ�������ѡ��һ����·�����ߣ��������ŷ����߲�ͨ��ʱ��
     *     ��ͻ��˵���һ����·�ڣ�����ѡ��һ��·�����ߣ�ֱ�������ҵ����ڡ������߷�����һ����������������ԡ�
     * ������������ҳ�����·���������Ƕ��� s ������ t �����·����ʵ���ϣ�������������õ���һ�ֱȽ��������㷨˼�룬
     *     ����˼�롣����˼��������Ĺ��̣��ǳ��ʺ��õݹ���ʵ��
     * @param s
     * @param t
     *
     * prev��visited �������Ǹ����������������ʵ�����������һ����
     *      ���������������������ʵ����и��Ƚ�����ı��� found��
     *      ���������ǣ��������Ѿ��ҵ���ֹ���� t ֮�����ǾͲ��ٵݹ�ؼ��������ˡ�
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
