package dataStructure.tree;

import org.omg.CORBA.ULongLongSeqHelper;

import java.util.TreeMap;

/**
 * @description:
 * @author: slfang
 * @time: 2020/7/14 21:15
 */
public class RedBlackTree<Key extends Comparable<Key>,Value> {

    //ƽ����������ϸ����������ģ�������������һ���ڵ�����������ĸ߶����ܴ��� 1 ,��������������������һ��ƽ���������(��ȫ������������������ʵ����ƽ�������)
    //��������ܺõĽ���˶���������˻�����������⣬�Ѳ��룬���ң�ɾ����ʱ�临�Ӷ���������������ά����O(logN)������Ƶ����ת��ʹ�����ɾ��������O(logN)���ҵ�ʱ�䣬
    // ������Զ����������˵��ʱ�����ȶ��˺ܶࡣ

    //ƽ�����������С�ƽ�⡱����˼����ʵ���������������ҿ������Ƚϡ��Գơ����Ƚϡ�ƽ�⡱����Ҫ�����������ܸߡ�
    // �������ܰ�������������������������ĸ߶������˵��һЩ����Ӧ�Ĳ��롢ɾ�������ҵȲ�����Ч�ʸ�һЩ��

    /*�������Ӣ���ǡ�Red-Black Tree������� R-B Tree������һ�ֲ��ϸ��ƽ��������������ǰ��˵�ˣ����Ķ����ǲ��ϸ����ƽ�����������Ķ���ġ�*/
    //������еĽڵ㣬һ�౻���Ϊ��ɫ��һ�౻���Ϊ��ɫ������֮�⣬һ�ú��������Ҫ������������Ҫ��
    //���ڵ��Ǻ�ɫ�ģ�
    // ÿ��Ҷ�ӽڵ㶼�Ǻ�ɫ�Ŀսڵ㣨NIL����Ҳ����˵��Ҷ�ӽڵ㲻�洢���ݣ�
    // �κ����ڵĽڵ㶼����ͬʱΪ��ɫ��Ҳ����˵����ɫ�ڵ��Ǳ���ɫ�ڵ�����ģ�
    // ÿ���ڵ㣬�Ӹýڵ㵽����ɴ�Ҷ�ӽڵ������·������������ͬ��Ŀ�ĺ�ɫ�ڵ㣻


    //һ�ü���ƽ��Ķ�������������������ȫ���������ĸ߶ȴ�Լ�� log2n���������Ҫ֤��������ǽ���ƽ��ģ�����ֻ��Ҫ������������ĸ߶��Ƿ�Ƚ��ȶ������� log2n �ͺ��ˡ�


    Node root;


    /**
     * ���� �ұߴ��ں�ɫ����
     * �ڲ����¼�ʱ���ǿ���ʹ����ת�����������Ǳ�֤23����������11��Ӧ��ϵ
     * ӦΪ��ת���Ա�֤���������������:������������ƽ����
     * @return
     */
    Node rotateLeft(Node node){
        Node rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;
        //��ɫ�任
        node.color = true;
        rightNode.color=node.color;
        rightNode.N = node.N;
        node.N = 1+size(node.left)+size(node.right);
        return rightNode;
    }

    /**
     * ����  ������ɫ���ӵ���
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
     * �任��ɫ
     * ����ת��һ���ڵ�������ɫ�ӽڵ����ɫ�����ӽڵ���ɫ��ڣ��������ڵ����ɫ���ֺڱ��
     * �˲�������תһ���Ǿֲ��任����Ӱ����������ƽ����
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
        Boolean color;//true  ��ɫ��false ��ɫ
        Node left,right;
        int N;//���Ľڵ���

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
        /*ÿ�α任֮��Ҫ�����ڵ�����Ϊ��ɫ*/
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
