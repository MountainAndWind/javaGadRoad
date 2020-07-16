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
     * ͷ�ڵ�
     */
    public Node root = null;


    /**
     * ����˳��  1 19 20  30  25  22 28  26 29  34 35
     * ð������� 1  19  20  22 25 26 28 29  34 35
     * ������ģ�ͣ�
     *                          1
     *                              19
     *                                   20
     *                                        30
     *                                    25      34
     *                                22      28      35
     *                                      26  29
     *
     *
     * ��ǰ���Ҷ�����ģ�ͣ���
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
     * ɾ���ڵ�
     * @param i
     */
    private void delete(int i) {
        Node node = find(i);
        if(node==null){
            return;
        }
        //1��ɾ���ڵ�ΪҶ�ӽڵ�
        if(node.leftNode==null&&node.rightNode==null){
            if(node.parent.rightNode==node){
                 node.parent.rightNode = null;
                 return;
            }else{
                node.parent.leftNode = null;
                return;
            }
        }
        //2��ɾ���ڵ���һ���ӽڵ�
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
            //3��ɾ���ڵ��������ӽڵ�,˼���ҵ���̽ڵ�
            Node nextNode = findNextNode(node);
            delete(nextNode.data);
            node.data = nextNode.data;
        }
    }

    /**
     * ���Һ�̽ڵ�
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
     * ��ȡ������С�Ľڵ�
     * @param node
     */
    private Node getTreeMinNode(Node node) {
       while (node.leftNode!=null){
           node = node.leftNode;
       }
       return node;
    }


    /**
     * ����
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
     * ���Ҷ������Ĳ���
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
                 //�ұ�
                 node=node.rightNode;
             }else if(data<node.data){
                 //���
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
