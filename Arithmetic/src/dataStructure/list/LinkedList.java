package dataStructure.list;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @description:�������������
 * 1��������ת
 * 2�������л��ļ��
 * 3���������������ϲ�
 * 4��ɾ���������� n �����
 * 5����������м���
 * ��ϰ��LeetCode��Ӧ��ţ�206��141��21��19��876
 * @author: slfang
 * @time: 2020/6/30 14:49
 */
public class LinkedList {

    //private ListNode head = new ListNode(-1);//���ڱ�
    private ListNode head = new ListNode(1);//�����ڱ�

    class ListNode{
        ListNode next;
        int data;
        public ListNode(int data) {
            this.data = data;
        }

        public ListNode() {
        }

        public ListNode(int data,ListNode next ) {
            this.next = next;
            this.data = data;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }

    public void createListNodes(int size){
        int i=2;//���ڱ���1��ʼ
        ListNode headTemp = head;
        while(i<size){
            ListNode node = new ListNode(i);
            headTemp.next=node;
            headTemp=node;
            i++;
        }
        headTemp.next=null;
    }

    //������ת

    /*����: 1->2->3->4->5->NULL
    ���: 5->4->3->2->1->NUL*/
    @Test
    public void test1(){
        createListNodes(6);
        //printLinkedList(head);
        //reverseLinkList(head);
        //reverseLinkList2(head);
        reverseListDg(head);
    }


    public ListNode reverseListDg(ListNode head) {
        //�ݹ���ֹ�����ǵ�ǰΪ�գ�������һ���ڵ�Ϊ��
        if(head==null || head.next==null) {
            return head;
        }
        //�����cur�������һ���ڵ�
        ListNode cur = reverseListDg(head.next);
        //��������϶�����ʾ���
        //��������� 1->2->3->4->5����ô��ʱ��cur����5
        //��head��4��head����һ����5������һ���ǿ�
        //����head.next.next ����5->4
        head.next.next = head;
        //��ֹ����ѭ������Ҫ��head.next����Ϊ��
        head.next = null;
        //ÿ��ݹ麯��������cur��Ҳ�������һ���ڵ�
        return cur;
    }

    /**
     * �����ڱ��ķ�ת����
     * @param head
     */
    private void reverseLinkList(ListNode head) {
        ListNode tempListNode = head;
        ListNode preListNode = head;
        while (tempListNode.next!=null){
            ListNode node = tempListNode.next;
            if(tempListNode==head){
                tempListNode = node;
            }else{
                if(tempListNode.data==1){
                    tempListNode.next=null;
                }else{
                    tempListNode.next=preListNode;
                }
                preListNode = tempListNode;
                tempListNode = node;
            }
        }
        tempListNode.next=preListNode;
        head.next=tempListNode;
        printLinkedList(head);
    }

    /**
     * �������ڱ��ķ�ת����
     * @param head
     */
    private void reverseLinkList2(ListNode head) {
        ListNode tempListNode = head;
        ListNode preListNode = head;
        while (tempListNode.next!=null){
            ListNode node = tempListNode.next;
            if(tempListNode.data==1){
                tempListNode.next=null;
            }else{
                tempListNode.next=preListNode;
            }
            preListNode = tempListNode;
            tempListNode = node;
        }
        tempListNode.next=preListNode;
        head=tempListNode;
        printLinkedList(head);
    }

    /**
     * ��ӡ�ڱ�ģʽ
     * @param node
     */
    void printLinkedList(ListNode node){
        while (node.next!=null){
            node = node.next;
            System.out.println(node.getData());
        }
    }

    /**
     * ��ӡ�������ڱ�
     * @param node
     */
    void printLinkedListWithShaoBin(ListNode node){
        while (node!=null){
            System.out.println(node.getData());
            node = node.next;
        }
    }


    //2�������л��ļ�� 141
    /*����һ�������ж��������Ƿ��л����ж��Ǵ�������*/
    @Test
    public void test2(){
        //����1 �����ǿ���ͨ�����һ������ǰ�Ƿ񱻷��ʹ����ж������Ƿ�Ϊ�����������õķ�����ʹ�ù�ϣ��
        ListNode node = new ListNode(1,new ListNode(2,new ListNode(4,null)));
        hasCycleByHash(node);
        //����2 ������ָ�뷨
        hasCycleBySlowAndFast(node);

    }

    private boolean hasCycleBySlowAndFast(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {//
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * hash��
     * @param node
     */
    private boolean hasCycleByHash(ListNode node) {
        Set<ListNode> set = new HashSet<>();
        while(node!=null){
            if(set.contains(node)){
                return true;
            }
            set.add(node);
            node = node.next;
        }
        return false;
    }



    //3���������������ϲ� 21
    /*���룺1->2->4, 1->3->4
    �����1->1->2->3->4->4*/
    @Test
    public void test3(){
        ListNode nodeA1 = new ListNode(1,new ListNode(2,new ListNode(4,null)));
        ListNode nodeB1 = new ListNode(1,new ListNode(3,new ListNode(4,null)));
        //ListNode listNode = mergeTwoLists(nodeA1, nodeB1);
        ListNode listNode = mergeTwoListsDg(nodeA1, nodeB1);
        printLinkedListWithShaoBin(listNode);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.getData() < l2.getData()) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        tail.next = l1 == null? l2: l1;

        return dummyHead.next;
    }

    public ListNode mergeTwoListsDg(ListNode l1, ListNode l2) {
        if(l1==null)
            return l2;
        if(l2==null)
            return l1;
        if(l1.getData()<l2.getData()){
            l1.next = mergeTwoListsDg(l1.next,l2);
            return l1;
        }else{
            l2.next = mergeTwoListsDg(l1,l2.next);
            return l2;
        }
    }

    /**
     * �ǵݹ�
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoListsByMe(ListNode l1, ListNode l2) {
        ListNode root = null;
        if(l1.getData()<l2.getData()){
            root = l1;
        }else{
            root = l2;
        }
        ListNode lastNode = root;
        while (l1!=null||l2!=null){
            if(l1==null){
                lastNode.next=l2;
                lastNode = l2;
                l2 = l2.next;
            }else if(l2==null){
                lastNode.next=l1;
                lastNode = l1;
                l1 = l1.next;
            }else{
                if(l1.getData()<l2.getData()){
                    if(root!=l1){
                        lastNode.next=l1;
                        lastNode = l1;
                    }
                    l1 = l1.next;
                }else{
                    if(root!=l2){
                        lastNode.next=l2;
                        lastNode = l2;
                    }
                    l2 = l2.next;
                }
            }
        }
        return root;
    }


    //4��ɾ���������� n �����   19
    /*���ף�

    ���ܳ���ʹ��һ��ɨ��ʵ����*/
    @Test
    public void test4(){
        ListNode node = new ListNode(1,new ListNode(2,new ListNode(4,new ListNode(5))));
        ListNode listNode = deleteNodeRerverse(node, 3);
        printLinkedListWithShaoBin(listNode);
    }

    private ListNode deleteNodeRerverse(ListNode node, int i) {
        int count=0;
        ListNode root = node;
        ListNode[] arr = new ListNode[100];
        while (node!=null){
            arr[count] = node;
            node = node.next;
            count++;
        }
        if(count-i>0){
            arr[count-i-1].next = arr[count-i].next;
            return root;
        }else{
            return root.next;
        }
    }

    /**
     * ��������м���  876
     * ����һ������ͷ��� head �ķǿյ���������������м��㡣
     * ����������м��㣬�򷵻صڶ����м��㡣
     */
    @Test
    public void test5(){
        ListNode node = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5,new ListNode(6))))));
        //����1��������
        //����2˫����
        //����3����ָ�뷨  ���Ž�
        getMidleNode(node);
    }

    /**
     * �㷨˼·1 2 3 4 5 6
     * ���ǿ��Լ����Ż���������������ָ�� slow �� fast һ���������
     * slow һ����һ����fast һ������������ô�� fast ���������ĩβʱ��slow ��Ȼλ���м䡣
     * @param node
     */
    private ListNode getMidleNode(ListNode node) {
        ListNode nodeSlow = node;
        ListNode nodeFast = node;
        while (nodeFast!=null&& nodeFast.next != null){
            nodeFast = nodeFast.next.next;
            nodeSlow = nodeSlow.next;
        }
        return nodeSlow;
    }

}
