package dataStructure.Stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @description:  ջ��һ�֡��������ޡ������Ա�ջ�ȿ���ͨ������ʵ�֣�Ҳ����ͨ��������ʵ��
 * ����ʵ�ֵ�ջ�����ǽ���˳��ջ��������ʵ�ֵ�ջ�����ǽ�����ʽջ��
 * 1������ջʵ�������Ӽ��˳���������
 * 2��ջ������ƥ���е�Ӧ��
 * 3��ʵ���������ǰ�������˹���
 * @author: slfang
 * @time: 2020/7/1 16:25
 */
public class Dome1 {

    /*  1. ��ջʵ�ֶ���
        2. �ö���ʵ��ջ
        3. ��Сֵջ
        4. ��ջʵ������ƥ�� */


    /*1����ջʵ�ֶ��� ��һ����ջ��һ����ջ*/
    class MyQueue{
        int front;
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        void push(Integer integer){
           stack1.push(integer);
        }

       Integer pop(){
           while (stack2.isEmpty()){
               while (!stack1.isEmpty()){
                   stack2.push(stack1.pop());
               }
           }
           return stack2.pop();
       }

    }

    // 2. �ö���ʵ��ջ
    //
    class MyStack{
        Queue<Integer> queue;

        /** Initialize your data structure here. */
        public MyStack() {
            queue = new LinkedList<Integer>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            queue.add(x);
            for(int i = 1; i < queue.size(); i++)
                queue.add(queue.remove());//ÿ���Ƴ�first �¼ӵ�x���Ƴ�  ����ÿ���¼����ʱ��ͻὫ�¼ӵķŵ���ͷ��ȥ
        }

        public int pop(){
            return queue.poll();
        }

        /** Get the top element. */
        public int top() {
            return queue.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue.size() == 0;
        }


    }



    /*
    ջ����Сֵ
    ִ��push��pop��min������ʱ�临�Ӷȱ���ΪO(1)��

    private Stack<Integer> stack;
    private Stack<Integer> min_stack;
    public MinStack() {
        stack = new Stack<>();
        min_stack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if(min_stack.isEmpty() || x <= min_stack.peek())
            min_stack.push(x);
    }

    public void pop() {
        if(stack.pop().equals(min_stack.peek()))
            min_stack.pop(); m
    }



     */
    class MinStack {

        int top=-1;
        int size;
        int length;
        int[] arr;
        int min;

        /** initialize your data structure here. */
        public MinStack() {
            this.arr=new int[20];
            this.length=0;
            this.size=10;
            min = 0;
        }

        public void push(int x) throws Exception {
            if(length==size){
                throw new Exception("����");
            }
            arr[++top] = x;
            length++;
            if(arr[top]<arr[min]){
                min = top;
            }
        }

        public void pop() throws Exception {
            if(length==0){
                throw new Exception("����Ϊ��");
            }
            if(top==min){
                int minTemp = 0;
                for (int i = 1; i <length-1 ; i++) {
                    if(arr[minTemp]>arr[i]){
                        minTemp=i;
                    }
                }
                min = minTemp;
            }
            top--;
            length--;
        }

        public int top() throws Exception {
            if(length==0){
                throw new Exception("����Ϊ��");
            }
            if(top==min){
                int minTemp = 0;
                for (int i = 1; i <length-1 ; i++) {
                    if(arr[minTemp]>arr[i]){
                        minTemp=i;
                    }
                }
                min = minTemp;
            }
            length--;
            return arr[top--];
        }

        public int getMin() throws Exception {
            if(length==0){
                throw new Exception("����Ϊ��");
            }
            return arr[min];
        }
    }


    /*��ջʵ������ƥ��*/
    // '('��')'��'{'��'}'��'['��']' ���ַ������ж��ַ����Ƿ���Ч��
    //
    class Solution {
        public boolean isMatch(String s) {
            char[] charS = s.toCharArray();
            Stack<Character> stack1 = new Stack<>();
            for (char aChar : charS) {
                if(stack1.isEmpty()){
                    stack1.push(aChar);
                }else{
                    char peek = stack1.peek();
                    if('('==(peek)){
                        if(')'==(aChar)){
                            stack1.pop();
                        }else {
                            stack1.push(aChar);
                        }
                    }else if('{'==(peek)){
                        if('}'==(aChar)){
                            stack1.pop();
                        }else{
                            stack1.push(aChar);
                        }
                    }else if('['==(peek)){
                        if(']'==(aChar)){
                            stack1.pop();
                        }else{
                            stack1.push(aChar);
                        }
                    }
                }
            }
            return stack1.isEmpty();
        }
        //ѭ�������бȵ�ǰԪ�ش����һ��Ԫ��
        //����һ��ѭ�����飨���һ��Ԫ�ص���һ��Ԫ��������ĵ�һ��Ԫ�أ���
        // ���ÿ��Ԫ�ص���һ������Ԫ�ء����� x ����һ�������Ԫ���ǰ��������˳��
        // �������֮��ĵ�һ�������������������ζ����Ӧ��ѭ��������������һ�����������
        // ��������ڣ������ -1��
        //�� todo
        /*
        *
        * */
        public int[] nextGreaterElements(int[] nums) {
            int []arr = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                int falg=-1;
                for (int j = 0; j < nums.length; j++) {
                    if(nums[j]>nums[i]){
                        falg=nums[j];
                        break;
                    }
                }
                arr[i]=falg;
            }
            return arr;
        }



    }




    public static void main(String[] args) throws Exception {

        /*MinStack minStack = new Dome1().new MinStack();
        int[] datas = new int[]{12,3,12,4,67,44,96,1};
        for (int data : datas) {
            minStack.push(data);
        }
        int min = minStack.getMin();
        System.out.println(min);
        int top = minStack.top();
        System.out.println(top);
        System.out.println(minStack.getMin());*/

        Solution solution = new Dome1().new Solution();
        /*boolean match = solution.isMatch("(([][](){}{}))");
        System.out.println(match);
        System.out.println(solution.isMatch("()[]{}"));
        System.out.println(solution.isMatch("(]"));
        System.out.println(solution.isMatch("([)]"));*/
        int[] ints = solution.nextGreaterElements(new int[]{1, 2, 1});
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
