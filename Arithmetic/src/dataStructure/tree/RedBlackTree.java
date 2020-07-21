package dataStructure.tree;

import org.omg.CORBA.ULongLongSeqHelper;

import java.util.TreeMap;

/**
 * @description:
 * @author: slfang
 * @time: 2020/7/14 21:15
 */
public class RedBlackTree<Key extends Comparable<Key>,Value> {

    //平衡二叉树的严格定义是这样的：二叉树中任意一个节点的左右子树的高度相差不能大于 1 ,并且左右两个子树都是一棵平衡二叉树。(完全二叉树、满二叉树其实都是平衡二叉树)
    //这个方案很好的解决了二叉查找树退化成链表的问题，把插入，查找，删除的时间复杂度最好情况和最坏情况都维持在O(logN)。但是频繁旋转会使插入和删除牺牲掉O(logN)左右的时间，
    // 不过相对二叉查找树来说，时间上稳定了很多。

    //平衡二叉查找树中“平衡”的意思，其实就是让整棵树左右看起来比较“对称”、比较“平衡”，不要出现左子树很高、
    // 右子树很矮的情况。这样就能让整棵树的高度相对来说低一些，相应的插入、删除、查找等操作的效率高一些。

    /*红黑树的英文是“Red-Black Tree”，简称 R-B Tree。它是一种不严格的平衡二叉查找树，我前面说了，它的定义是不严格符合平衡二叉查找树的定义的。*/
    //红黑树中的节点，一类被标记为黑色，一类被标记为红色。除此之外，一棵红黑树还需要满足这样几个要求：
    //根节点是黑色的；
    // 每个叶子节点都是黑色的空节点（NIL），也就是说，叶子节点不存储数据；
    // 任何相邻的节点都不能同时为红色，也就是说，红色节点是被黑色节点隔开的；
    // 每个节点，从该节点到达其可达叶子节点的所有路径，都包含相同数目的黑色节点；


    //一棵极其平衡的二叉树（满二叉树或完全二叉树）的高度大约是 log2n，所以如果要证明红黑树是近似平衡的，我们只需要分析，红黑树的高度是否比较稳定地趋近 log2n 就好了。


    Node root;


    /**
     * 左旋 右边存在红色连接
     * 在插入新键时我们可以使用旋转操作帮我我们保证23树与红黑树的11对应关系
     * 应为旋转可以保证红黑树的两个性质:有序性与完美平衡性
     * @return
     */
    Node rotateLeft(Node node){
        Node rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;
        //颜色变换
        node.color = true;
        rightNode.color=node.color;
        rightNode.N = node.N;
        node.N = 1+size(node.left)+size(node.right);
        return rightNode;
    }

    /**
     * 右旋  两个红色连接导致
     * @return
     */
    Node rotateRight(Node node){
        Node leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;
        node.color = true;
        leftNode.color = node.color;
        leftNode.N = node.N;
        node.N = 1+size(node.left)+size(node.right);
        return leftNode;
    }

    private int size(Node node) {
        if(node==null){
            return 0;
        }
        if(node.left==null&&node.right==null){
            return 1;
        }else {
            return 1+size(node.left)+size(node.right);
        }
    }

    /**
     * 变换颜色
     * 用来转换一个节点两个红色子节点的颜色，将子节点颜色变黑，还将父节点的颜色变又黑变红
     * 此操作和旋转一样是局部变换不会影响整个树的平衡性
     * @param node
     */
    void flipColors(Node node){
        node.color = true;
        node.left.color = false;
        node.right.color = false;
    }

    class Node{
        Key key;
        Value val;
        Boolean color;//true  红色，false 黑色
        Node left,right;
        int N;//树的节点数

        public Node(Key key, Value val, Boolean color, int n) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.N = n;
        }


    }

    boolean isRed(Node node){
        return node!=null?node.color:false;
    }

    public void put(Key key,Value value){
        root  = put(root,key,value);
        /*每次变换之后都要讲根节点设置为黑色*/
        root.color = false;
    }

    private Node put(Node root, Key key, Value value) {
        if(root==null){
           return new Node(key,value,true,1);
        }
        int i = key.compareTo(root.key);
        if(i<0)
            root.left = put(root.left,key,value);
        else if(i>0)
            root.right = put(root.right,key,value);
        else root.val = value;
        if(isRed(root.right)==true&&isRed(root.left)==false)
           root = rotateLeft(root);
        if(isRed(root.left)==true&&isRed(root.left.left)==true)
           root = rotateRight(root);
        if(isRed(root.left)==true&&isRed(root.right)==true)
            flipColors(root);
        root.N = size(root.left)+size(root.right)+1;
        return root;
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        for (int i = 0; i <10 ; i++) {
            tree.put(i,i);
        }

    }


}
