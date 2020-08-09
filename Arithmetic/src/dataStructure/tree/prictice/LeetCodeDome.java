package dataStructure.tree.prictice;

import dataStructure.tree.BinaryTree;
import dataStructure.tree.BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @description:
 * @author: slfang
 * @time: 2020/7/28 16:24
 */
public class LeetCodeDome {

    public static void main(String[] args) {
        LeetCodeDome dome = new LeetCodeDome();
        BinaryTree tree = new BinaryTree();
        tree.createBinaryTree();
       /* System.out.println(dome.getTreesHight(tree.root));
        System.out.println(dome.isBlance(tree.root));
        System.out.println(dome.diameterOfBinaryTree(tree.root));*/
        /*tree.midOrder(tree.root);
        System.out.println("------");
        tree.midOrder(dome.invertTree(tree.root));*/

        Node node = dome.mergeTrees(dome.createNode1(), dome.createNode2());
        List<List<Integer>> lists = dome.levelOrder(node);
        System.out.println(lists);
    }

    public List<List<Integer>> levelOrder(Node root) {
        if(root == null){
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int count = queue.size();
            List<Integer> list = new ArrayList<Integer>();
            while(count>0){
                Node node = queue.poll();
                list.add(node.val);
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
                count--;
            }
            res.add(list);
        }
        return res;
    }


    Node createNode1(){
        Node root = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(2);
        Node node4 = new Node(5);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        return root;
    }

    Node createNode2(){
        Node root = new Node(2);
        Node node2 = new Node(1);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(7);
        root.left = node2;
        root.right = node3;
        node2.right = node4;
        node3.right = node5;
        return root;
    }



    /**
     * 获取树的高度
     * @param node
     * @return
     */
    int getTreesHight(BinaryTree.TreeNode node){
        if(node==null){
           return 0;
        }
        return getTreesHight(node.leftChild)>getTreesHight(node.rightChild)
                ?getTreesHight(node.leftChild)+1:getTreesHight(node.rightChild)+1;
    }


    private boolean isBlance = true;

    /**
     * 判断是否是平衡树 ：平衡树左右子树高度差都小于等于 1
     * @param node
     * @return
     */
    boolean isBlance(BinaryTree.TreeNode node){
        maxDepth(node);
        return isBlance;
    }

    private int maxDepth(BinaryTree.TreeNode node) {
        if(node==null){
            return 0;
        }
        int l = maxDepth(node.leftChild);
        int r = maxDepth(node.rightChild);
        if (Math.abs(l - r) > 1) isBlance = false;
        return 1 + Math.max(l, r);
    }

    /**
     * 两节点的最长路径
     * @return
     */
    int diameterOfBinaryTree(BinaryTree.TreeNode root){
        int leftHeight = getTreesHight(root.leftChild);
        int rightHight = getTreesHight(root.rightChild);
        return leftHeight+rightHight;
    }

    /**
     * 翻转一棵二叉树。
     */
    public TreeNode invertTree(TreeNode root) {
        if(root==null){
           return null;
        }
        TreeNode treeNodeL = invertTree(root.leftChild);
        TreeNode treeNodeR = invertTree(root.rightChild);
        root.leftChild = treeNodeR ;
        root.rightChild = treeNodeL;
        return root;
    }


    /**
     * 合并二插树
     * @param t1
     * @param t2
     * @return
     */
    public Node mergeTrees(Node t1, Node t2){
        if (t1 == null)
            return t2;
        if (t2 == null)
            return t1;
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left,t2.left);
        t1.right = mergeTrees(t1.right,t2.right);
        return t1;
    }



    /**
     * 二叉树的元素 ：结点 具有左右结点和父结点；
     *
     * @author my
     *
     */
    public class Node {
        public int val;
        public Node left;
        public Node right;
        public Node(int val) {
            this.val=val;
        }
    }
}
