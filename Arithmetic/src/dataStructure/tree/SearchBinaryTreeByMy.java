package dataStructure.tree;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * 二叉查找树是二叉树中最常用的一种类型，也叫二叉搜索树。顾名思义，二叉查找树是为了实现快速查找而生的。
 * 不过，它不仅仅支持快速查找一个数据，还支持快速插入、删除一个数据。它是怎么做到这些的呢？
 *
 * 这些都依赖于二叉查找树的特殊结构。二叉查找树要求，在树中的任意一个节点，其左子树中的每个节点的值，
 * 都要小于这个节点的值，而右子树节点的值都大于这个节点的值。
 *
 * 中序遍历二叉查找树，可以输出有序的数据序列
 *
 * 在二叉查找树中，查找、插入、删除等很多操作的时间复杂度都跟树的高度成正比。
 * 两个极端情况的时间复杂度分别是 O(n) 和 O(logn)，分别对应二叉树退化成链表的情况和完全二叉树。
 * Created by zlk on 2018/7/6.
 */
public class SearchBinaryTreeByMy {

    private Node root=null;

    private List list = new ArrayList<Integer>();


    class Node{
        public int data;
        public Node lNode;
        public Node rNode;
        public Node parent;

        public Node(int data) {
            this.data = data;
            this.lNode = null;
            this.rNode = null;
            this.parent = null;
        }


    }


    /**
     * 数组顺序  1 19 20  30  25  22 28  26 29  34 35
     * 冒泡排序后： 1  19  20  22 25 26 28 29  34 35
     * 二叉树模型：
     *                          1
     *                              19
     *                                   20
     *                                        30
     *                                    25      34
     *                                22      28      35
     *                                      26  29
     *
     * 各个操作的时间复杂度跟树的高度成正比,理想情况下，时间复杂度是 O(logn)。
     * 二叉查找树在频繁的动态更新过程中，可能会出现树的高度远大于 log2n 的情况，
     * 从而导致各个操作的效率下降。极端情况下，二叉树会退化为链表，时间复杂度会退化到 O(n)。
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        SearchBinaryTreeByMy tree=new SearchBinaryTreeByMy();
        tree.createSearchTree();
        List<Integer> list=new ArrayList<>();
        tree.middlePaixu(tree.root,list);
        System.out.println(list);
        System.out.println("----------------------");
        List<Integer> list2=new ArrayList<>();
        tree.deleteNode(25);
        tree.middlePaixu(tree.root,list2);
        System.out.println(list2);

        System.out.println("--------------------");
        List<Integer> list3=new ArrayList<>();
        tree.deleteNode(30);
        tree.middlePaixu(tree.root,list3);
        System.out.println(list3);
}


    private Node getRoot(){
        return root;
    }

    /**
     * 删除节点
     *
     * 数组顺序  1 19 20  30  25  22 28  26 29  34 35
     * 冒泡排序后： 1  19  20  22 25 26 28 29  34 35
     * 二叉树模型：
     *                          1
     *                              19
     *                                   20
     *                                        30
     *                                    25      34
     *                                22      28      35
     *                                      26  29
     * 向二叉树删除一个元素
     *  三种情况来处理。
     *  1、如果要删除的节点没有子节点，直接删除
     *  2、如果要删除的节点只有一个子节点（只有左子节点或者右子节点），删除节点的父节点直接指向删除节点子节点
     *  3、删除的节点有两个子节点
     *         我们需要找到这个节点的右子树中的最小节点，把它替换到要删除的节点上。然后再删除掉这个最小节点，因为最小节点肯定没有左子节点
     *         （如果有左子结点，那就不是最小节点了），所以，我们可以应用上面两条规则来删除这个最小节点
     *
     *
     * @param i
     * @throws Exception
     */
    private void deleteNode(int i) throws Exception {
        Node node = getNodeByData(i);
        if(isEmpty(node)){
            throw new Exception("该参数的值无法找到！！！！");
        }
        //左右孩子都有
        if(node.lNode!=null&&node.rNode!=null){
            /*差找后继节点*/
            Node nextNode = findNextNode(node);
            deleteNode(nextNode.data);
            node.data=nextNode.data;
        }else{
            //1、删除节点只有一个子节点的，或者没有
            Node parent = node.parent;//查找父节点
            Node child =null;
            if(node.lNode!=null){
                child = node.lNode;
            }else if(node.rNode!=null){
                child = node.rNode;
            }

            if(parent==null){//删除节点为root 节点
                root = child;
            }else{
                if(node==parent.lNode){
                    parent.lNode = child;
                }else{
                    parent.rNode = child;
                }
            }
        }
    }
    /**
     * 数组顺序  1 19 20  30  25  22 28  26 29  34 35
     * 冒泡排序后： 1  19  20  22 25 26 28 29  34 35
     * 二叉树模型：
     *                          1
     *                              19
     *                                   20
     *                                        30
     *                                    25      34
     *                                22      28      35
     *                                      26  29
     * A 此节点，没有右节点的时候
     * B 此节点有右结点
     *
     * 查找后继节点
     * @param node
     * @return
     */
    private Node findNextNode(Node node) {
        if(isEmpty(node)){
            return null;
        }
        if(node.rNode!=null){//存在右节点时  if包含的代码可以抽象成查找一颗数的最小元素
            Node currNode = node.rNode;
            if(currNode.lNode==null){
                return currNode;
            }else{
                while (currNode.lNode!=null){
                    currNode=currNode.lNode;
                }
                return currNode;
            }

        }else{//右节点不存时  eg29
            Node parent = node.parent;
            while (parent!=null&&parent.rNode==node){//应为左子树都小于顶点
                node=node.parent;
                parent= parent.parent;
            }
            return parent;
        }
    }

    /**
     * 根据data获取节点
     * @param data
     * @return
     */
    private Node getNodeByData(int data) {
        Node node =root;
        if(root==null){
            return null;
        }
        while (node!=null&&data!=node.data){
            if(data>node.data){
                 node=node.rNode;
            }else if(data<node.data){
                 node=node.lNode;
            }
        }
        return node;
    }


    /**
     * 中序遍历
     * @param root
     */
    private void middlePaixu(Node root,List<Integer> list) {
        if(isEmpty(root)){
            return ;
        }else{
            middlePaixu(root.lNode,list);
            //System.out.println(root.data);
            list.add(root.data);
            middlePaixu(root.rNode,list);
        }
    }

    /**
     * 创建查找树
     */
    private void createSearchTree() {// 1 19 20  30  25  22 28  26 29  34 35
        int[] datas = new int[]{1,19,20,30,25,22,28,26,29,34,35};
        //orderNumber(datas);
        for (int data : datas) {
            putTree(data);
        }
    }

    /**
     * 添加查找二叉树
     * @param data
     */
    private void putTree(int data) {
        if(root==null){
            Node node = new Node(data);
            root = node;
            return;
        }
        Node node = root;
        Node parent = null;
        while(node!=null){
            parent=node;
            if(data>node.data){
                //右边
                node=node.rNode;
            }else if(data<node.data){
                //左边
                node = node.lNode;
            }else{
                return;
            }
        }
        Node newNode = new Node(data);
        if(data>parent.data){
            parent.rNode =newNode;
        }else{
            parent.lNode = newNode;
        }
        newNode.parent=parent;
    }

    /**
     * 打印数组
     * @param datas
     */
    private void printArr(int[] datas) {
        for (int data : datas) {
            System.out.println(data);
        }
    }

    /**
     * 冒泡排序
     * @param datas
     */
    private void orderNumber(int[] datas) {
        for (int i = 0; i < datas.length; i++) {
            for (int j = i+1; j <datas.length; j++) {
                if(datas[i]>datas[j]){
                    int temp = datas[i];
                    datas[i] = datas[j];
                    datas[j] = temp;
                }
            }
        }
    }

    /**
     * 树判空
     */
    public boolean isEmpty(Node node){
        return node==null?true:false;
    }
}
