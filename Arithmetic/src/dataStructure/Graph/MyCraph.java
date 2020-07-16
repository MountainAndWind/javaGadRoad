package dataStructure.Graph;

import java.util.LinkedList;

/**
 * @description:图  存储结构一维数组（存储顶点信息）加二维数组（存储图中 边的信息）
 * 1、获取一个顶点的度
 * 2、图的遍历：a 深度优先算法（似于树的前序遍历） b 广度算法（树的层级遍历）
 * @author: slfang
 * @time: 2020/3/29 14:02
 */
public class MyCraph {

    private static final int GRAPH_MAX=10000;

    private int vertexSize;
    private int[] vertex;
    private int[][] arc;
    boolean[] isVisted ;

    public MyCraph(int vertexSize) {
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
        MyCraph graph = new MyCraph(9);
        /*创建数组*/
        //graph.createDate(graph);
        graph.createDate2(graph);
        /*int degreeByVertexIN = graph.getDegreeByVertex(4, GraphEnum.IN, graph);
        int degreeByVertexOut = graph.getDegreeByVertex(4, GraphEnum.OUT, graph);
        System.out.println(degreeByVertexIN);
        System.out.println(degreeByVertexOut);*/
        /*遍历*/
        //graph.depthFirstSearch();
        graph.bfs(0);

    }

    private void createDate2(MyCraph graph) {

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
    private void createDate(MyCraph graph) {
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
     * 获取一个顶点的度
     * @return
     */
    public int getDegreeByVertex(int vertexIndex, GraphEnum type, MyCraph graph) {
        int count=0;
        if(0==(type.getCode())){//出度
            for (int i = 0; i <graph.arc[vertexIndex].length ; i++) {
                if(graph.arc[vertexIndex][i]!=0&&graph.arc[vertexIndex][i]!=GRAPH_MAX){
                    count++;
                }
            }
        }else if(1==type.getCode()){
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
