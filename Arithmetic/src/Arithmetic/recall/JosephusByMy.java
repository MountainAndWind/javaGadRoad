package Arithmetic.recall;

/**
 * @description: 约瑟夫杀人算法
 * 约瑟夫问题（有时也称为约瑟夫斯置换，
 * 是一个出现在计算机科学和数学中的问题。在计算机编程的算法中，
 * 类似问题又称为约瑟夫环。又称“丢手绢问题”.）
 * @author: slfang
 * @time: 2020/5/31 20:03
 */
public class JosephusByMy {

    public static void main(String[] args) {
        JosephusByMy josephusByMy = new JosephusByMy();
        josephusByMy.josephusExcute(10,4);//10数量   4间隔值
    }

    class Node{
        int i;
        Node next;

        public Node(int i) {
            this.i = i;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private void josephusExcute(int n, int interval) {//
        //第一步构造一个 循环链表
        Node top = new Node(1);
        Node indexNode = top;
        for (int i = 2; i <= n; i++) {
            Node node = new Node(i);
            indexNode.next = node;
            indexNode=node;
        }
        indexNode.next = top;
        while (indexNode.next!=indexNode){//  3 8 9 5 2 6 7  1 4 0
            for (int i = 1; i <interval ; i++) {
                indexNode = indexNode.next;
            }
            System.out.println("删除："+indexNode.next.getI());
            indexNode.next=indexNode.next.next;
        }
        System.out.println("幸运儿：："+indexNode.getI());
    }
}
