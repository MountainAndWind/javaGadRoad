package dataStructure.tree;

/**
 * @description:简单二叉树的实现
 * @author: slfang
 * @time: 2020/3/20 18:39
 */
public class BinaryTreeByMy {

    class Node<T>{
        Node leftNode;
        Node rightNode;
        T t;
        Integer index;

        public Node( T t, Integer index) {
            this.leftNode = null;
            this.rightNode = null;
            this.t = t;
            this.index = index;
        }
    }

    private Node<String> root=null;

    public BinaryTreeByMy() {
        root = new Node<String>("A", 1);
    }

    /**
     * 创建二叉树
     *        A     ；
     *     B     C   ；
     *   D   E  F  G ；
     *
     *
     */
    public void creatNode(){
         Node nodeB = new Node("B",1);
         Node nodeC = new Node("C",1);
         Node nodeD = new Node("D",1);
         Node nodeE = new Node("E",1);
         Node nodeF = new Node("F",1);
         Node nodeG = new Node("G",1);
         root.leftNode = nodeB;
         root.rightNode = nodeC;
         nodeB.leftNode = nodeD;
         nodeB.rightNode = nodeE;
         nodeC.leftNode = nodeF;
         nodeC.rightNode = nodeG;
    }

    /**
     * 获取高度
     * @return
     */
    public int getHeight(Node node){
         if(node==null){
            return 0;
         }
        int lHeight = getHeight(node.leftNode);
        int rHeight = getHeight(node.rightNode);
        return lHeight>rHeight?lHeight+1:rHeight+1;
    }

    /**
     * 非迭代  使用堆栈的方式完成
     * @param root
     */
    private void preNoRecTraverse(Node<String> root) {


    }

    /**
     * 获取节点数
     * @return
     */
    public int getSize(Node node){
        if(node==null){
           return 0;
        }
        return getSize(node.leftNode)+getSize(node.rightNode)+1;
    }

    /**
     * 前序遍历迭代方式
     * @param node
     */
    public void preTraverse(Node node){
         if(node!=null){
             System.out.println(node.t);
             preTraverse(node.leftNode);
             preTraverse(node.rightNode);
         }
    }


    public static void main(String[] args) {
        BinaryTreeByMy tree = new BinaryTreeByMy();
        tree.creatNode();
        /*System.out.println(tree.getSize(tree.root.leftNode));
        System.out.println(tree.getHeight(tree.root.leftNode));*/
        //tree.preTraverse(tree.root);
        tree.preNoRecTraverse(tree.root);
    }

}