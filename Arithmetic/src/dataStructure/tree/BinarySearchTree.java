package dataStructure.tree;

/**
 * @description:
 * @author: slfang
 * @time: 2020/7/13 17:19
 */
public class BinarySearchTree {
    private Node tree;

    public Node find(int data) {
        Node p = tree;
        while (p != null) {
            if (data < p.data) p = p.left;
            else if (data > p.data) p = p.right;
            else return p;
        }
        return null;
    }

    public void insert(int data) {
        if (tree == null) {
            tree = new Node(data);
            return;
        }

        Node p = tree;
        while (p != null) {
            if (data > p.data) {
                if (p.right == null) {
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            } else { // data < p.data
                if (p.left == null) {
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            }
        }
    }

    public void delete(int data) {
        Node p = tree; // pָ��Ҫɾ���Ľڵ㣬��ʼ��ָ����ڵ�
        Node pp = null; // pp��¼����p�ĸ��ڵ�
        while (p != null && p.data != data) {
            pp = p;
            if (data > p.data) p = p.right;
            else p = p.left;
        }
        if (p == null) return; // û���ҵ�

        // Ҫɾ���Ľڵ��������ӽڵ�
        if (p.left != null && p.right != null) { // ��������������С�ڵ�
            Node minP = p.right;
            Node minPP = p; // minPP��ʾminP�ĸ��ڵ�
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.data = minP.data; // ��minP�������滻��p��
            p = minP; // ����ͱ����ɾ��minP��
            pp = minPP;
    }

        // ɾ���ڵ���Ҷ�ӽڵ���߽���һ���ӽڵ�
        Node child; // p���ӽڵ�
        if (p.left != null) child = p.left;
        else if (p.right != null) child = p.right;
        else child = null;

        if (pp == null) tree = child; // ɾ�����Ǹ��ڵ�
        else if (pp.left == p) pp.left = child;
        else pp.right = child;
    }

    public Node findMin() {
        if (tree == null) return null;
        Node p = tree;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    public Node findMax() {
        if (tree == null) return null;
        Node p = tree;
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}