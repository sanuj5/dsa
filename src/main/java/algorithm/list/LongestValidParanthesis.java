package algorithm.list;

import java.util.Stack;

public class LongestValidParanthesis {
    public static void main(String[] args) {
        System.out.println(longestValidParentheses("())"));
    }
    public static int longestValidParentheses(String s) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for(int i =0;i<s.length();i++){
            if(s.charAt(i)=='('){
                stack.push(i);
            }
            else {
                stack.pop();
                if(stack.isEmpty()){
                    stack.push(i);
                }else{
                    max = Math.max(max, i-stack.peek());
                }
            }
        }
        return max;
    }
}
