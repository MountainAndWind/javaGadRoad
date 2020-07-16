package dataStructure.tree;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @description:此处采用链式存储法，如果某棵二叉树是一棵完全二叉树，那用数组存储无疑是最节省内存的一种方式。
 * 二叉树既可以用链式存储，也可以用数组顺序存储。数组顺序存储的方式比较适合完全二叉树，
 * 其他类型的二叉树用数组存储会比较浪费存储空间。
 * 除此之外，二叉树里非常重要的操作就是前、中、后序遍历操作，遍历的时间复杂度是 O(n)
 *
 *
 * 1、二叉树的建立：链式存储法，完全二叉树可采用数组存储方式
 * 2、二叉树的遍历
 * 3、通过前序后还原二叉树 todo
 *
 * @author: slfang
 * @time: 2020/3/20 18:39
 */
public class BinaryTree {
    private TreeNode root = null;

    public BinaryTree() {
        root = new TreeNode(1, "A");
    }

    /**
     * 创建二叉树
     *        A     ；
     *     B     C   ；
     *   D   E  F  G ；
     *
     *
     */
    public void createBinaryTree() {
        TreeNode nodeB = new TreeNode(2, "B");
        TreeNode nodeC = new TreeNode(3, "C");
        TreeNode nodeD = new TreeNode(4, "D");
        TreeNode nodeE = new TreeNode(5, "E");
        TreeNode nodeF = new TreeNode(6, "F");
        TreeNode nodeG = new TreeNode(7, "G");

        root.leftChild = nodeB;
        root.rightChild = nodeC;
        nodeB.leftChild = nodeD;
        nodeB.rightChild = nodeE;
        nodeC.leftChild = nodeF;
        nodeC.rightChild = nodeG;

    }

    /**
     * 获得二叉树的深度
     *
     * @author my
     *
     */
    public int getHeight() {
        return getHeight(root);
    }

    /**
     * 获取二叉树的高度
     * @param node
     * @return
     */
    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            int i = getHeight(node.leftChild);
            int j = getHeight(node.rightChild);
            return (i < j) ? j + 1 : i + 1;
        }
    }

    /**
     * 获取二叉树的节点数
     *
     */
    public int getSize() {
        return getSize(root);
    }

    private int getSize(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + getSize(node.leftChild) + getSize(node.rightChild);
        }
    }

    /**
     * 迭代方式获取 --前序遍历
     */
    public void preOrder(TreeNode node) {
        if (node == null) {
            return;
        } else {
            System.out.println("preOrder data :" + node.data);
            if (node.leftChild != null) {
                preOrder(node.leftChild);
            }
            if (node.rightChild != null) {
                preOrder(node.rightChild);
            }
        }
    }

    /**
     * 非迭代方式获取 --前序遍历  利用栈的数据接口
     */
    public void preNonRecOrder(TreeNode node) {
        LinkedList<TreeNode> linkNode = new LinkedList<TreeNode>();
        if (node == null) {
            return;
        } else {
            linkNode.push(node);
        }
        while(!linkNode.isEmpty()) {
            TreeNode tn = linkNode.pop();
            System.out.println("preNonRecOrder : "+tn.data);
            if (tn.rightChild != null) {
                linkNode.push(tn.rightChild);
            }
            if (tn.leftChild != null) {
                linkNode.push(tn.leftChild);
            }
        }

    }

    /**
     * 迭代方式获取 --后序遍历
     */
    public void postOrder(TreeNode node) {
        if (node == null) {
            return;
        } else {
            if (node.leftChild != null) {
                postOrder(node.leftChild);
            }
            if (node.rightChild != null) {
                postOrder(node.rightChild);
            }
            System.out.println("postOrder data :" + node.data);
        }
    }
    /**
     * 非迭代方式获取 --后序遍历DEBFGCA
     *        A     ；
     *     B     C   ；
     *   D   E  F  G ；
     */
    public void postNonRecOrder(TreeNode node) {
        LinkedList<TreeNode> linkNode = new LinkedList<TreeNode>();
        if (node == null) {
            return;
        } else {
            linkNode.push(node);
            if (node.rightChild != null) {
                linkNode.push(node.rightChild);
            }
            if (node.leftChild != null) {
                linkNode.push(node.leftChild);
            }
            node.visited=1;
        }
        while(!linkNode.isEmpty()) {
            TreeNode tn = linkNode.pop();
            if(tn.rightChild == null && tn.leftChild == null) {
                tn.visited = 1;
                System.out.println("midNonRecOrder : "+tn.data);
                continue;
            }
            if(tn.visited != 1) {
                linkNode.push(tn);
                if (tn.rightChild != null) {
                    linkNode.push(tn.rightChild);
                }
                if (tn.leftChild != null) {
                    linkNode.push(tn.leftChild);
                }
                tn.visited = 1;
            }else {
                System.out.println("midNonRecOrder : "+tn.data);
            }
        }

    }
    /**
     *        A     ；
     *     B     C   ；
     *   D   E  F  G ；
     *
     * 迭代方式获取 --中序遍历
     */
    public void midOrder(TreeNode node) {
        if (node == null) {
            return;
        } else {
            if (node.leftChild != null) {
                midOrder(node.leftChild);
            }
            System.out.println("midOrder data :" + node.data);
            if (node.rightChild != null) {
                midOrder(node.rightChild);
            }
        }
    }
    /**
     * 非迭代方式获取 --中序遍历   DEBAFGC
     *        A     ；
     *     B     C   ；
     *   D   E  F  G ；
     */
    public void midNonRecOrder(TreeNode node) {
        LinkedList<TreeNode> linkNode = new LinkedList<TreeNode>();
        if (node == null) {
            return;
        } else {
            if (node.rightChild != null) {
                linkNode.push(node.rightChild);
            }
            linkNode.push(node);
            node.visited=1;
            if (node.leftChild != null) {
                linkNode.push(node.leftChild);
            }
        }
        while(!linkNode.isEmpty()) {
            TreeNode tn = linkNode.pop();
            if(tn.rightChild == null && tn.leftChild == null) {
                tn.visited = 1;
                System.out.println("midNonRecOrder : "+tn.data);
                continue;
            }
            if(tn.visited != 1) {
                if (tn.rightChild != null) {
                    linkNode.push(tn.rightChild);
                }
                linkNode.push(tn);
                if (tn.leftChild != null) {
                    linkNode.push(tn.leftChild);
                }
                tn.visited = 1;
            }else {
                System.out.println("midNonRecOrder : "+tn.data);
            }
        }

    }

    /**
     * main 方法 测试结果
     */
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.createBinaryTree();
        System.out.println("BinaryTree 深度：" + bt.getHeight());
//      System.out.println("BinaryTree 深度：" + bt.getHeight());
//      System.out.println("BinaryTree 节点数：" + bt.getSize());
//      bt.preOrder(bt.root);
//      bt.midOrder(bt.root);
//      bt.postOrder(bt.root);
//      bt.preNonRecOrder(bt.root);
//      bt.midNonRecOrder(bt.root);
        //bt.postNonRecOrder(bt.root);


    }

    /*
     * 通过前序排序反向构造二叉树
     *     abd##f##cf##
     * */
    public TreeNode createTreePreByOrder(ArrayList<String> list){
        return createTree(list.size(), list);
    }
    public TreeNode createTree(int size,ArrayList<String> Data){
        if(Data.size()==0){
            return null;
        }
        String data = Data.get(0);
        TreeNode node=null;
        int index = size-Data.size();
        if("#".equals(data)){
            node = null;
            Data.remove(data);
            return node;
        }
        node = new TreeNode(index+1,data);
        if(index==0){
            root = node;
        }
        Data.remove(data);
        node.leftChild = createTree(size,Data);
        node.rightChild = createTree(size,Data);
        return node;
    }


    /**
     * 二叉树的元素 ：结点 具有左右结点和父结点；
     *
     * @author my
     *
     */
    class TreeNode {
        private int index;
        private String data;
        private TreeNode leftChild;
        private TreeNode rightChild;
        private int visited;

        public TreeNode(int index, String data) {
            super();
            this.index = index;
            this.data = data;
            this.visited = 0;
            this.leftChild = null;
            this.rightChild = null;
        }

        public int getVisited() {
            return visited;
        }

        public void setVisited(int visited) {
            this.visited = visited;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

    }
}