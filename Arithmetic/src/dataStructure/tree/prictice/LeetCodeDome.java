package dataStructure.tree.prictice;

import dataStructure.tree.BinaryTree;
import dataStructure.tree.BinaryTree.TreeNode;
import sun.reflect.generics.tree.Tree;
import sun.security.x509.EDIPartyName;

import java.util.*;

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
        System.out.println(dome.getTreesHight(tree.root));
        /*System.out.println(dome.isBlance(tree.root));
        System.out.println(dome.diameterOfBinaryTree(tree.root));
        tree.midOrder(tree.root);
        System.out.println("------");
        tree.midOrder(dome.invertTree(tree.root));

        Node node = dome.mergeTrees(dome.createNode1(), dome.createNode2());
        List<List<Integer>> lists = dome.levelOrder(node);
        System.out.println(lists);*/

        Node node3 = dome.createNode3();
       /* boolean symmetric = dome.isSymmetric2(node3);
        System.out.println(symmetric);*/
       //dome.minDepth(node3);
        //int i = dome.longestUnivaluePath(node3);
        //int i = dome.getSameCount(node3,node3.val);
        int i = dome.kthSmallest(node3, 1);
        System.out.println(i);
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





    private boolean isBlance = true;

    /**
     * �ж��Ƿ���ƽ���� ��ƽ�������������߶ȲС�ڵ��� 1
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
     * ���ڵ���·��
     * @return
     */
    int diameterOfBinaryTree(BinaryTree.TreeNode root){
        int leftHeight = getTreesHight(root.leftChild);
        int rightHight = getTreesHight(root.rightChild);
        return leftHeight+rightHight;
    }

    /**
     * ��תһ�ö�������
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
     * �ϲ�������
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
     * ��������Ԫ�� ����� �������ҽ��͸���㣻
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


    /**
     * 112���ж�·�����Ƿ����һ����
     * ˼��bfs Ҳ�������Ĳ������
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(Node root, int sum) {
        Queue<Node> queue = new LinkedList<Node>();
        Queue<Integer> queueVal = new LinkedList<Integer>();
        queue.add(root);
        queueVal.add(root.val);
        while (!queue.isEmpty()){
            Node parent = queue.poll();
            Integer poll = queueVal.poll();
            if(poll==sum){
                return true;
            }
            if(parent.left!=null){
                queue.add(parent.left);
                queueVal.add(parent.left.val + poll);
            }
            if(parent.right!=null){
                queue.add(parent.right);
                queueVal.add(parent.right.val + poll);
            }
        }
        return false;
    }

    /**
     * ͳ��·���͵���һ������·������
     * @param root
     * @param sum
     * @return
     */
    public int pathSum1(Node root, int sum) {
        if(root==null){
            return 0;
        }
        int oneRes = getOneRes(root, sum);
        return oneRes+pathSum1(root.left,sum)+pathSum1(root.right,sum);
    }

    private int getOneRes(Node root, int sum) {
        if(root==null){
           return 0;
        }
        int count = 0;
        if(root.val==sum) count++;
        return count+getOneRes(root.left,sum-root.val)+getOneRes(root.right,sum-root.val);
    }


    /***
     * todo ǰ׺��˼��
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        // key��ǰ׺��, value�Ǵ�СΪkey��ǰ׺�ͳ��ֵĴ���
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        // ǰ׺��Ϊ0��һ��·��
        prefixSumCount.put(0, 1);
        // ǰ׺�͵ĵݹ����˼·
        return recursionPathSum(root, prefixSumCount, sum, 0);
    }

    /**
     * ǰ׺�͵ĵݹ����˼·
     * �ӵ�ǰ�ڵ㷴�Ƶ����ڵ�(���ƱȽϺ���⣬������ʵҲֻ��һ��)�����ҽ���һ��·������Ϊ����һ����
     * �����ǰ�к�ΪcurrSum-target,����ǰ�ĺ���ΪcurrSum,���ߵĲ�Ϳ϶�Ϊtarget��
     * ����ǰ׺�Ͷ��ڵ�ǰ·����˵��Ψһ�ģ���ǰ��¼��ǰ׺�ͣ��ڻ��ݽ������ص�����ʱȥ������֤�䲻Ӱ��������֧�Ľ��
     * @param node ���ڵ�
     * @param prefixSumCount ǰ׺��Map
     * @param target Ŀ��ֵ
     * @param currSum ��ǰ·����
     * @return ��������Ľ�
     */
    private int recursionPathSum(TreeNode node, Map<Integer, Integer> prefixSumCount, int target, int currSum) {
        // 1.�ݹ���ֹ����
        if (node == null) {
            return 0;
        }
        // 2.����Ҫ��������
        int res = 0;
        // ��ǰ·���ϵĺ�
        currSum += node.val;

        //---���Ĵ���
        // ����root����ǰ�ڵ�����·���Ƿ���ڽڵ�ǰ׺�ͼ�targetΪcurrSum��·��
        // ��ǰ�ڵ�->root�ڵ㷴�ƣ����ҽ���һ��·���������ǰ�к�ΪcurrSum-target,����ǰ�ĺ���ΪcurrSum,���ߵĲ�Ϳ϶�Ϊtarget��
        // currSum-target�൱����·������㣬����sum+target=currSum����ǰ�㵽���ľ������target
        res += prefixSumCount.getOrDefault(currSum - target, 0);
        // ����·���ϵ�ǰ�ڵ�ǰ׺�͵ĸ���
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);
        //---���Ĵ���

        // 3.������һ��
        res += recursionPathSum(node.left, prefixSumCount, target, currSum);
        res += recursionPathSum(node.right, prefixSumCount, target, currSum);

        // 4.�ص����㣬�ָ�״̬��ȥ����ǰ�ڵ��ǰ׺������
        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
        return res;
    }

    /**
     * 572����һ����������
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree1(TreeNode s, TreeNode t) {
        if(s==null||t==null){
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()){
            if(isLeagle(queue.poll(),t))
                return true;
            queue.add(s.left);
            queue.add(s.right);
        }
        return false;
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s==null){
            return false;
        }
        if(t==null){
            return true;
        }
        return isLeagle(s,t)||isSubtree(s.left,t)||isSubtree(s.right,t);
    }

    private boolean isLeagle(TreeNode s, TreeNode t) {
        if(s==null&&t==null){
           return true;
        }
        if((s==null&&t!=null)||(s!=null&&t==null)||(s.val!=t.val)){
           return false;
        }
        boolean leagle1 = isLeagle(s.left, t.left);
        boolean leagle2 = isLeagle(s.right, t.right);
        return leagle1&&leagle2;
    }


    Node createNode3(){
        /*Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(2);
        Node node4 = new Node(3);
        Node node5 = new Node(4);
        Node node6 = new Node(4);
        Node node7 = new Node(3);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        return root;*/
        /*Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3= new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        root.right = node5;*/
       /* Node root = new Node(5);
        Node node2 = new Node(4);
        Node node3= new Node(5);
        Node node4 = new Node(1);
        Node node5 = new Node(1);
        Node node6 = new Node(5);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;*/
        Node root = new Node(3);

        Node node2= new Node(1);

        Node node3 = new Node(4);
        Node node4 = new Node(2);

        root.left = node2;
        root.right = node3;
        node2.right = node4;
        return root;
    }

    public boolean isSymmetric(Node root) {
        int treesHight = getTreesHight(root);
        Node arr[] = new Node[(1<<treesHight)-1];
        builderAbTree(root,arr,0);
        for (int i = treesHight; i >1 ; i--) {
            int start = (1<<(i-1))-1;
            int end = (1<<i)-1-1;
            while (start<end){
                if((arr[start]!=null&&arr[end]==null)||(arr[start]==null&&arr[end]!=null)){
                    return false;
                }
                if(arr[start]==null&&arr[end]==null){
                    continue;
                }
                if(arr[start].val!=arr[end].val){
                    return false;
                }
                start++;
                end--;
            }
        }
        return true;
    }


    /**
     * �Ƿ��ǶԳ���
     * @param root
     * @return
     */
    public boolean isSymmetric2(Node root) {
        return check(root, root);
    }

    public boolean check(Node p, Node q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

    /**
     * ��ȡ���ĸ߶�
     * @param node
     * @return
     */
    int getTreesHight(Node node){
        if(node==null){
            return 0;
        }
        return getTreesHight(node.left)>getTreesHight(node.right)
                ?getTreesHight(node.left)+1:getTreesHight(node.right)+1;
    }

    /**
     * ��ȡ���ĸ߶�
     * @param node
     * @return
     */
    int getTreesHight(TreeNode node){
        if(node==null){
            return 0;
        }
        return getTreesHight(node.left)>getTreesHight(node.right)
                ?getTreesHight(node.left)+1:getTreesHight(node.right)+1;
    }


    private void builderAbTree(Node root, Node[] arr, int i) {
        arr[i] = root;
        if(root.left!=null){
            builderAbTree(root.left,arr,2*i+1);
        }
        if(root.right!=null){
            builderAbTree(root.right,arr,2*i+2);
        }
    }

    /**
     * 111������һ�����������ҳ�����С��ȡ�
     * @param root
     * @return
     */
    public int minDepth(Node root) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        setQueue(root,priorityQueue,0);
        return priorityQueue.poll()==null?0:priorityQueue.poll();
    }

    private void setQueue(Node root, PriorityQueue<Integer> priorityQueue, int i) {
        if(root!=null){
           i++;
            if(root.left==null&&root.right==null){
                priorityQueue.offer(i);
            }
            if(root.left!=null){
                setQueue(root.left,priorityQueue,i);
            }
            if(root.right==null){
                setQueue(root.right,priorityQueue,i);
            }
        }
    }

    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int m1 = minDepth(root.left);
        int m2 = minDepth(root.right);
        //1.������Ӻ��Һ�����Ϊ�յ������ֱ�ӷ���m1+m2+1
        //2.�������Ϊ�գ����ؽ�С���+1
        return root.left == null || root.right == null ? m1 + m2 + 1 : Math.min(m1,m2) + 1;
    }

    /**
     * ���������������������Ҷ��֮�͡�
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(Node root) {
        if(root==null){
           return 0;
        }
        int count = 0;
        if(root.left!=null&&root.left.left==null&&root.left.right==null){
            count+=root.left.val;
        }
        int countL = sumOfLeftLeaves(root.left);
        int countR = sumOfLeftLeaves(root.right);
        return count+countL+countR;
    }

    /**
     * 687����ͬ�ڵ�ֵ�����·������
     * @param root
     * @return
     */
    public int longestUnivaluePath(Node root) {
        if(root==null){
            return 0;
        }
        int l = longestUnivaluePath(root.left);
        int r = longestUnivaluePath(root.right);
        int max = Math.max(l, r);

        return Math.max(getSameCount(root,root.val),max);
    }

    int getSameCount(Node root, int val){
        if(root==null){
            return 0;
        }
        int count = 0;
        if(root.left!=null){
            if(root.left.val==val){
                count++;
                //getSameCount(root.left);
            }
        }
        if(root.right!=null){
            if(root.right.val==root.val){
                count++;
                //getSameCount(root.right);
            }
        }
        if(isLeafNode(root.left)&&isLeafNode(root.right)){
            return count+getSameCount(root.left,val)+getSameCount(root.right,val);
        }else{
            return count+Math.max(getSameCount(root.left,val),getSameCount(root.right,val));
        }
    }

    boolean isLeafNode(Node node){
        if(node==null){
           return false;
        }
        if(node.left==null&&node.right==null){
            return true;
        }else{
            return false;
        }
    }


    int ans;
    public int longestUnivaluePath(TreeNode root) {
        ans = 0;
        longestPath(root);
        return ans;
    }
    //�ݹ麯�����ܣ���Ѱ��nodeΪ�����ֵͬ·��:Ҫô����nodeΪ������������Ҫô����nodeΪ����������
    public int longestPath(TreeNode node) {
        if (node == null) return 0;
        int maxLorRres=0;
        int left = longestPath(node.left); //node���������ֵͬ·��
        int right = longestPath(node.right);//node���������ֵͬ·��
        //�����������Ѱ���ֵͬ·�����а���������������rootΪ·����ʼ����·��û�а���
        if (node.left != null && node.left.val == node.val&&node.right != null && node.right.val == node.val) {
            ans=Math.max(ans, left + right+2);
        }
        //������������ѡ�����ֵͬ·��
        if(node.left!=null&&node.left.val == node.val){
            maxLorRres=left+1;
        }
        if(node.right!=null&&node.right.val==node.val){
            maxLorRres=Math.max(maxLorRres,right+1);
        }
        //��ans��maxLorRres�и������ֵ
        ans=Math.max(ans,maxLorRres);
        return maxLorRres; //��������֪��Ϊʲô�������ֵ����
    }

    /**
     * 337���������
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (root == null) return 0;
        int val1 = root.val;
        if (root.left != null) val1 += rob(root.left.left) + rob(root.left.right);
        if (root.right != null) val1 += rob(root.right.left) + rob(root.right.right);
        int val2 = rob(root.left) + rob(root.right);
        return Math.max(val1, val2);
    }

    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.right != null) queue.add(root.right);
            if (root.left != null) queue.add(root.left);
        }
        return root.val;
    }


    /**
     * 669���޼�����������
     * @param root
     * @param L
     * @param R
     * @return
     */
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root==null) return null;
        int val = root.val;
        if(val<L) return trimBST(root.right,L,R);
        if(val>R) return trimBST(root.left,L,R);
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }


    int count = 1;
    int val = -1;
    /**
     * �������������ҳ���KС��Ԫ��
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(Node root, int k) {
        if(root==null) return 0;
        if(val==-1) kthSmallest(root.left,k);
        if(count==k)  val = root.val;
        count++;
        if(val==-1) kthSmallest(root.right,k);
        return val;
    }


    /**
     * 538���Ѷ�����ת�����ۼ���
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        int count = 0;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.right;
            }
            TreeNode node = stack.pop();
            //ret.add(node);
            count+=node.val;
            node.val = count;
            cur = node.left;
        }
        return root;
    }


    /**
     * 235�������������������������
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorSearch(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val<root.val&&q.val<root.val){
           return lowestCommonAncestorSearch(root.left,p,q);
        }

        if(p.val>root.val&&q.val>root.val){
           return lowestCommonAncestorSearch(root.right,p,q);
        }
        return root;
    }

    /**
     * 236���������������������
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }

    Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
    Set<Integer> visited = new HashSet<Integer>();

    public void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

    /*public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
    }*/
    /**
     * 108�������������й�����������
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {//  0,1,2, 3,4,5,
        if(nums==null||nums.length==0) return null;
        return builderTree(nums,0,nums.length-1);
    }

    private TreeNode builderTree(int[] nums, int start, int end) {
        if(start>end){
            return null;
        }
        int mid = (start+end)/2;
        TreeNode root  = new TreeNode(nums[mid]);
        root.left = builderTree(nums,start,mid-1);
        root.right = builderTree(nums,mid+1, end);
        return root;
    }

      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    /**
     * 109����������������ƽ��Ķ��������
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if(head==null) return null;
        return builderTreeByList(head,null);
    }

    private TreeNode builderTreeByList(ListNode head, ListNode end) {
        if (head == end) {
            return null;
        }
        ListNode listMid = getListMid(head, end);
        TreeNode root = new TreeNode(listMid.val);
        root.left = builderTreeByList(head,listMid);
        root.right = builderTreeByList(listMid.next,end);
        return root;
    }

    /**
     * ��ȡ�������λ�����ÿ���ָ�뷽ʽ
     * @param head
     * @param end
     */
    private ListNode getListMid(ListNode head, ListNode end) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast!=end&&fast!=null&&fast.next!=null&&fast.next!=end){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }


}
