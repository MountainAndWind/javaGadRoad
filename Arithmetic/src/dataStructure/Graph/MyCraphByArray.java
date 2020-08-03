package dataStructure.Graph;

import java.util.LinkedList;

/**
 * @description:图  存储结构一维数组（存储顶点信息）加二维数组（存储图中 边的信息）
 * 基本概念
 * 顶点：图中的元素
 * 边：顶点与顶点之间建立的关系
 * 度：顶点相连的边数（无向图称之为度，有向图称之为入度与出度）
 * 带权图：每条边上带权重
 * 1、存储方法
 *    1.1、图最直观的一种存储方法就是，邻接矩阵二维数组表示图，一个数组存储图的顶点信息，一个数组存储图中边信息】
 *         但是用邻接矩阵来表示一个图，虽然简单、直观，但是比较浪费存储空间。为什么这么说呢？
 *           A:对于无向图来说，如果 A[i][j]等于 1，那 A[j][i]也肯定等于 1。实际上，我们只需要存储一个就可以了。
 *           也就是说，无向图的二维数组中，如果我们将其用对角线划分为上下两部分，那我们只需要利用上面或者
 *           下面这样一半的空间就足够了，另外一半白白浪费掉了。
 *           B:如果我们存储的是稀疏图（Sparse Matrix），也就是说，顶点很多，但每个顶点的边并不多，那邻接矩阵
 *           的存储方法就更加浪费空间了。比如微信有好几亿的用户，对应到图上就是好几亿的顶点。但是每个用户的
 *           好友并不会很多，一般也就三五百个而已。如果我们用邻接矩阵来存储，那绝大部分的存储空间都被浪费了。
 *
 *   1.2、邻接表
 *            它的表示有点像散列表的表示方法，每个顶点对应一张链表，链表中存储的是与这个顶点连接的其他顶点
 *   邻接矩阵与邻接表的可以让我们联想到空间复杂度互换的设计思想，邻接矩阵存储起来比较浪费空间，但是使用起来比较节省时间。
 *   相反，邻接表存储起来比较节省空间，但是使用起来就比较耗时间。
 *
 * 2、邻接矩阵实现代码  获取出入度
 *
 * 3、图的遍历：a 深度优先算法（似于树的前序遍历） b 广度算法（树的层级遍历）
 * @author: slfang
 * @time: 2020/3/29 14:02
 */
public class MyCraphByArray {

    /**
     * GRAPH_MAX表示无法到达
     */
    private static final int GRAPH_MAX=10000;

    private int vertexSize;
    private int[] vertex;
    private int[][] arc;
    boolean[] isVisted ;

    public MyCraphByArray(int vertexSize) {
        this.vertexSize = vertexSize;
        this.vertex=new int[vertexSize];
        this.arc=new int[vertexSize][vertexSize];
        isVisted=new boolean[vertexSize];
    }

    /**
     * 代码实现模型见有道云笔记
     * @param args
     */
    public static void main(String[] args) {
        MyCraphByArray graph = new MyCraphByArray(9);
        /*创建数组*/
        graph.createDate(graph);
        //graph.createDate2(graph);
        int degreeByVertexIN = graph.getDegreeByVertex(3, GraphEnum.IN, graph);
        int degreeByVertexOut = graph.getDegreeByVertex(3, GraphEnum.OUT, graph);
        System.out.println(degreeByVertexIN);
        System.out.println(degreeByVertexOut);
        /*遍历*/
        //graph.depthFirstSearch();
        //graph.bfs(0);

    }

    private void createDate2(MyCraphByArray graph) {

        graph.vertex=new int[]{0,1,2,3,4,5,6,7,8};
        int [] a1 = new int[]{0,10,GRAPH_MAX,GRAPH_MAX,GRAPH_MAX,11,GRAPH_MAX,GRAPH_MAX,GRAPH_MAX};
        int [] a2 = new int[]{10,0,18,GRAPH_MAX,GRAPH_MAX,GRAPH_MAX,16,GRAPH_MAX,12};
        int [] a3 = new int[]{GRAPH_MAX,GRAPH_MAX,0,22,GRAPH_MAX,GRAPH_MAX,GRAPH_MAX,GRAPH_MAX,8};
        int [] a4 = new int[]{GRAPH_MAX,GRAPH_MAX,22,0,20,GRAPH_MAX,GRAPH_MAX,16,21};
        int [] a5 = new int[]{GRAPH_MAX,GRAPH_MAX,GRAPH_MAX,20,0,26,GRAPH_MAX,7,GRAPH_MAX};
        int [] a6 = new int[]{11,GRAPH_MAX,GRAPH_MAX,GRAPH_MAX,26,0,17,GRAPH_MAX,GRAPH_MAX};
        int [] a7 = new int[]{GRAPH_MAX,16,GRAPH_MAX,GRAPH_MAX,GRAPH_MAX,17,0,19,GRAPH_MAX};
        int [] a8 = new int[]{GRAPH_MAX,GRAPH_MAX,GRAPH_MAX,16,7,GRAPH_MAX,19,0,GRAPH_MAX};
        int [] a9 = new int[]{GRAPH_MAX,12,8,21,GRAPH_MAX,GRAPH_MAX,GRAPH_MAX,GRAPH_MAX,0};

        graph.arc[0] = a1;
        graph.arc[1] = a2;
        graph.arc[2] = a3;
        graph.arc[3] = a4;
        graph.arc[4] = a5;
        graph.arc[5] = a6;
        graph.arc[6] = a7;
        graph.arc[7] = a8;
        graph.arc[8] = a9;
    }

    /**
     * 对外公开的实现广度优先遍历
     */

    public void broadFirstSearch(){
        isVisted = new boolean[vertexSize];
        for(int i = 0;i<vertexSize;i++){
            if(!isVisted[i]){
                System.out.println("访问到了："+i+"顶点");
                bfs(i);
            }
        }
        isVisted = new boolean[vertexSize];
    }

    /**
     * 广度优先算法 实现原理利用的了队列的原理
     * @param i
     */
    private void bfs(int i) {
        LinkedList<Integer> queue = new LinkedList();
        queue.add(i);
        isVisted[i]=true;
        System.out.println("访问到："+i+"顶点");
        while (!queue.isEmpty()){
            Integer integer = queue.removeFirst();
            isVisted[integer]=true;
            int[] arcIndex = arc[integer];
            for (int j = 0; j <arcIndex.length ; j++) {
                if(arcIndex[j]!=0&&arcIndex[j]!=GRAPH_MAX){
                    if(!isVisted[j]){
                        System.out.println("访问到了："+j+"顶点");
                        isVisted[j] = true;
                        queue.add(j);
                    }
                }
            }
        }
    }


    /**
     * 对外公开的深度优先遍历
     */

    public void depthFirstSearch(){
        isVisted = new boolean[vertexSize];
        for(int i = 0;i<vertexSize;i++){
            if(!isVisted[i]){
                System.out.println("访问到了："+i+"顶点");
                dfs(i);
            }
        }
        isVisted = new boolean[vertexSize];
    }

    /**
     * 深度优先遍历
     */
    private void dfs(int index) {
        isVisted[index]=true;
        int fristNeighbor = findFristNeighbor(index);
        while (fristNeighbor!=-1){
            if(isVisted[fristNeighbor]!=true){
                System.out.println("遍历节点：："+fristNeighbor);
                dfs(fristNeighbor);
            }
            int next = findFristNe(index, fristNeighbor);
            fristNeighbor=next;
        }


    }

    /**
     * 查找当前当前index的邻节点
     * @param vertex
     * @param index
     */
    private int findFristNe(int vertex, int index) {
        int[] arcIndex= arc[vertex];
        for (int i = index+1; i < arcIndex.length; i++) {
            if(arcIndex[i]!=0&&arcIndex[i]!=GRAPH_MAX){
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找第一个邻节点
     * @param index
     */
    private int findFristNeighbor(int index) {
        int[] arcIndex= arc[index];
        for (int i = 0; i < arcIndex.length; i++) {
            if(arcIndex[i]!=0&&arcIndex[i]!=GRAPH_MAX){
                return i;
            }
        }
        return -1;
    }

    /**
     * 创建图
     * @param graph
     */
    private void createDate(MyCraphByArray graph) {
        graph.vertex=new int[]{0,1,2,3,4};
        int[] a0 = new int[]{0,GRAPH_MAX,GRAPH_MAX,GRAPH_MAX,6};
        int[] a1 = new int[]{9,0,3,GRAPH_MAX,GRAPH_MAX};
        int[] a2 = new int[]{2,GRAPH_MAX,0,5,GRAPH_MAX};
        int[] a3 = new int[]{GRAPH_MAX,GRAPH_MAX,GRAPH_MAX,0,1};
        int[] a4 = new int[]{GRAPH_MAX,GRAPH_MAX,GRAPH_MAX,GRAPH_MAX,0};
        graph.arc[0]=a0;
        graph.arc[1]=a1;
        graph.arc[2]=a2;
        graph.arc[3]=a3;
        graph.arc[4]=a4;
    }


    /**
     * 横向看出度，纵向看入度
     * 获取一个顶点的度
     * @return
     */
    public int getDegreeByVertex(int vertexIndex, GraphEnum type, MyCraphByArray graph) {
        int count=0;
        if(0==(type.getCode())){//出度
            for (int i = 0; i <graph.arc[vertexIndex].length ; i++) {
                if(graph.arc[vertexIndex][i]!=0&&graph.arc[vertexIndex][i]!=GRAPH_MAX){
                    count++;
                }
            }
        }else if(1==type.getCode()){//入度
            for (int i = 0; i < graph.vertexSize; i++) {
                if(graph.arc[i][vertexIndex]!=0&&graph.arc[i][vertexIndex]!=GRAPH_MAX){
                    count++;
                }
            }
        }else{
            return -1;
        }
        return count;
    }


}
