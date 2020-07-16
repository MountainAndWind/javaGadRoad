package dataStructure.tree.prictice;

/**
 * @description:
 * @author: slfang
 * @time: 2020/7/13 17:43
 */
public class SearchBinaryTree {

    class Node{
        int data;
        Node leftNode;
        Node rightNode;
        Node parent;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 头节点
     */
    public Node root = null;


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
     *
     * 当前查找二叉树模型：：
     *                         1
     * @param args
     */
    public static void main(String[] args) {
        SearchBinaryTree tree = new SearchBinaryTree();
        int[] datas = new int[]{1,19,20,30,25,22,28,26,29,34,35};
        for (int data : datas) {
            tree.insert(data);
        }
        tree.midTrav(tree.root);
        System.out.println("-------------------------");
        Node node = tree.find(25);
        System.out.println(node.leftNode.data);
        System.out.println(node.rightNode.data);

        System.out.println("----------------");
        System.out.println(tree.findNextNode(tree.root).data);
        System.out.println(tree.find(29).data);
        System.out.println(tree.findNextNode(tree.find(29)).data);
        tree.delete(20);
        tree.midTrav(tree.root);
    }

    /**
     * 删除节点
     * @param i
     */
    private void delete(int i) {
        Node node = find(i);
        if(node==null){
            return;
        }
        //1、删除节点为叶子节点
        if(node.leftNode==null&&node.rightNode==null){
            if(node.parent.rightNode==node){
                 node.parent.rightNode = null;
                 return;
            }else{
                node.parent.leftNode = null;
                return;
            }
        }
        //2、删除节点有一个子节点
        if(node.leftNode!=null&&node.rightNode==null){
            node.data = node.leftNode.data;
            node.leftNode = null;
            return;
        }
        if(node.leftNode==null&&node.rightNode!=null){
            node.data = node.rightNode.data;
            node.rightNode = null;
            return;
        }
        if(node.leftNode!=null&&node.rightNode!=node){
            //3、删除节点有两个子节点,思想找到后继节点
            Node nextNode = findNextNode(node);
            delete(nextNode.data);
            node.data = nextNode.data;
        }
    }

    /**
     * 查找后继节点
     * @param node
     * @return
     */
    private Node findNextNode(Node node) {
        if(node==null){
            return null;
        }
        if(node.rightNode!=null){
           return getTreeMinNode(node.rightNode);
        }else{
           Node parent = node.parent;
           while (parent!=null&&parent.rightNode==node ){
               node = node.parent;
               parent = parent.parent;
           }
           return parent;
        }
    }

    /**
     * 获取数中最小的节点
     * @param node
     */
    private Node getTreeMinNode(Node node) {
       while (node.leftNode!=null){
           node = node.leftNode;
       }
       return node;
    }


    /**
     * 查找
     */
    public Node find(int data){
       Node node = root;
       while (node!=null){
           if(data>node.data){
                node = node.rightNode;
           }else if(data<node.data){
                node = node.leftNode;
           }else{
                return node;
           }
       }
       return null;
    }

    /**
     * 查找二叉树的插入
     * @param data
     */
    private void insert(int data) {
         if(root==null){
             root = new Node(data);
             return;
         }
         Node parent = null;
         Node node = root;
         while (node!=null){
             parent = node;
             if(data>node.data){
                 //右边
                 node=node.rightNode;
             }else if(data<node.data){
                 //左边
                 node = node.leftNode;
             }else{
                 return;
             }
         }

         Node newNode = new Node(data);
         if(data>parent.data){
             parent.rightNode = newNode;
         }else{
             parent.leftNode = newNode;
         }
         newNode.parent = parent;
    }


    public void midTrav(Node node){
        if(node==null){
            return;
        }
        if(node.leftNode!=null){
            midTrav(node.leftNode);
        }
        System.out.println(node.data);
        if(node.rightNode!=null){
            midTrav(node.rightNode);
        }
    }
}
