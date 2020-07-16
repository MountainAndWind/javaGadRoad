package dataStructure.Stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @description:  栈是一种“操作受限”的线性表，栈既可以通过数组实现，也可以通过链表来实现
 * 数组实现的栈，我们叫作顺序栈，用链表实现的栈，我们叫作链式栈。
 * 1、利用栈实现算术加减乘除四则运算
 * 2、栈在括号匹配中的应用
 * 3、实现浏览器的前进、后退功能
 * @author: slfang
 * @time: 2020/7/1 16:25
 */
public class Dome1 {

    /*  1. 用栈实现队列
        2. 用队列实现栈
        3. 最小值栈
        4. 用栈实现括号匹配 */


    /*1、用栈实现队列 ：一个入栈，一个出栈*/
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

    // 2. 用队列实现栈
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
                queue.add(queue.remove());//每次移除first 新加的x不移除  所以每次新加入的时候就会将新加的放到队头上去
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
    栈的最小值
    执行push、pop和min操作的时间复杂度必须为O(1)。

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
                throw new Exception("超容");
            }
            arr[++top] = x;
            length++;
            if(arr[top]<arr[min]){
                min = top;
            }
        }

        public void pop() throws Exception {
            if(length==0){
                throw new Exception("容器为空");
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
                throw new Exception("容器为空");
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
                throw new Exception("容器为空");
            }
            return arr[min];
        }
    }


    /*用栈实现括号匹配*/
    // '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
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
        //循环数组中比当前元素大的下一个元素
        //给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），
        // 输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，
        // 这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。
        // 如果不存在，则输出 -1。
        //答案 todo
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
