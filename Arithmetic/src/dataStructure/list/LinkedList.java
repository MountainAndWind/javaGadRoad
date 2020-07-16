package dataStructure.list;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @description:链表操作测试类
 * 1、单链表反转
 * 2、链表中环的检测
 * 3、两个有序的链表合并
 * 4、删除链表倒数第 n 个结点
 * 5、求链表的中间结点
 * 练习题LeetCode对应编号：206，141，21，19，876
 * @author: slfang
 * @time: 2020/6/30 14:49
 */
public class LinkedList {

    //private ListNode head = new ListNode(-1);//带哨兵
    private ListNode head = new ListNode(1);//不带哨兵

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
        int i=2;//带哨兵从1开始
        ListNode headTemp = head;
        while(i<size){
            ListNode node = new ListNode(i);
            headTemp.next=node;
            headTemp=node;
            i++;
        }
        headTemp.next=null;
    }

    //单链表反转

    /*输入: 1->2->3->4->5->NULL
    输出: 5->4->3->2->1->NUL*/
    @Test
    public void test1(){
        createListNodes(6);
        //printLinkedList(head);
        //reverseLinkList(head);
        //reverseLinkList2(head);
        reverseListDg(head);
    }


    public ListNode reverseListDg(ListNode head) {
        //递归终止条件是当前为空，或者下一个节点为空
        if(head==null || head.next==null) {
            return head;
        }
        //这里的cur就是最后一个节点
        ListNode cur = reverseListDg(head.next);
        //这里请配合动画演示理解
        //如果链表是 1->2->3->4->5，那么此时的cur就是5
        //而head是4，head的下一个是5，下下一个是空
        //所以head.next.next 就是5->4
        head.next.next = head;
        //防止链表循环，需要将head.next设置为空
        head.next = null;
        //每层递归函数都返回cur，也就是最后一个节点
        return cur;
    }

    /**
     * 带有哨兵的反转方法
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
     * 不带有哨兵的反转方法
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
     * 打印哨兵模式
     * @param node
     */
    void printLinkedList(ListNode node){
        while (node.next!=null){
            node = node.next;
            System.out.println(node.getData());
        }
    }

    /**
     * 打印链表无哨兵
     * @param node
     */
    void printLinkedListWithShaoBin(ListNode node){
        while (node!=null){
            System.out.println(node.getData());
            node = node.next;
        }
    }


    //2、链表中环的检测 141
    /*给定一个链表，判断链表中是否有环。判断是带环链表*/
    @Test
    public void test2(){
        //方法1 ：我们可以通过检查一个结点此前是否被访问过来判断链表是否为环形链表。常用的方法是使用哈希表。
        ListNode node = new ListNode(1,new ListNode(2,new ListNode(4,null)));
        hasCycleByHash(node);
        //方法2 ：快慢指针法
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
     * hash法
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



    //3、两个有序的链表合并 21
    /*输入：1->2->4, 1->3->4
    输出：1->1->2->3->4->4*/
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
     * 非递归
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


    //4、删除链表倒数第 n 个结点   19
    /*进阶：

    你能尝试使用一趟扫描实现吗*/
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
     * 求链表的中间结点  876
     * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
     * 如果有两个中间结点，则返回第二个中间结点。
     */
    @Test
    public void test5(){
        ListNode node = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5,new ListNode(6))))));
        //方法1利用数组
        //方法2双遍历
        //方法3快慢指针法  最优解
        getMidleNode(node);
    }

    /**
     * 算法思路1 2 3 4 5 6
     * 我们可以继续优化方法二，用两个指针 slow 与 fast 一起遍历链表。
     * slow 一次走一步，fast 一次走两步。那么当 fast 到达链表的末尾时，slow 必然位于中间。
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
