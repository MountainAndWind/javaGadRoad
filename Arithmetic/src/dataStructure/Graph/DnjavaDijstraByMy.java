package dataStructure.Graph;

/**
 * @description:�Ͻ�˹�����㷨ʵ��
 * @author: slfang
 * @time: 2020/4/6 13:06
 */
public class DnjavaDijstraByMy {

    private int vertexSize;/*��������*/
    /**
     * ��ȡ���·��
     * @param graph
     */
    public static void getMinPath(Graph graph){
        int minIndex=0;//��¼�±�
        boolean []isMin = new boolean[graph.getVertexSize()];
        int []minPathTemp = new int[graph.getVertexSize()];
        for (int i = 0; i <9 ; i++) {
            minPathTemp[i]=graph.getMatrix()[0][i];
        }
        isMin[0]=true;
        minPathTemp[0]=0;
        for (int i = 1; i < graph.getVertexSize(); i++) {
            int min=graph.getMaxWeight();
            for (int w = 1; w <graph.getVertexSize() ; w++) {
                if(!isMin[w]&&minPathTemp[w]<min){
                    minIndex=w;
                    min=minPathTemp[w];
                }
            }
            isMin[minIndex]=true;
            for (int j = 0; j <graph.getVertexSize() ; j++) {
                if(!isMin[j]&&graph.getMatrix()[minIndex][j]+min<minPathTemp[j]){
                    minPathTemp[j]=graph.getMatrix()[minIndex][j]+min;
                }
            }
        }
        for (int i = 0; i <minPathTemp.length ; i++) {
            /*0,1,4,7,5,8,10,12,16*/
            System.out.println("V0��V"+i+"�����·��Ϊ:"+minPathTemp[i]+"\n");
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(9);
        graph.createGraph();
        getMinPath(graph);
    }

}
