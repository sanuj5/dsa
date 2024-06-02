package algorithm.list;

import java.util.LinkedList;
import java.util.Stack;

public class ReverseStringWithParentheses {

    public static void main(String[] args) {
        System.out.println(reverseParentheses("(u(love)i)"));
    }

    public static String reverseParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        boolean reverse = false;
        LinkedList<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length();i++){
            if(reverse){
                stack.push(s.charAt(i));
            }
            else{
                queue.add(s.charAt(i));
            }
            if(s.charAt(i) == '(' || s.charAt(i) == ')'){
                reverse = !reverse;
            }
        }
        System.out.println(queue);
        System.out.println(stack);
        reverse = false;
        while(!queue.isEmpty() || !stack.isEmpty()){
            Character pop;
            if(reverse){
                pop = stack.pop();
            }
            else{
                pop = queue.removeFirst();
            }
            Character nextChar = null;
            if(!stack.empty()){
                nextChar = stack.peek();
            }
            if(!queue.isEmpty()){
                nextChar = queue.getFirst();
            }
            if(nextChar == '(' || nextChar ==')'){
                reverse = !reverse;
            }
            else{
                sb.append(pop);
            }
            System.out.println(sb);

        }
        return sb.toString();
    }
}
