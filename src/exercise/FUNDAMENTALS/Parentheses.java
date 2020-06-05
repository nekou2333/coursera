package exercise.FUNDAMENTALS;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Parentheses {

    public static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char tmp;
            switch (c) {
                case '{':
                case '[':
                case '(':
                    stack.push(c);
                    break;
                case ')':
                    if (stack.isEmpty()) return false;
                    if (stack.pop() != '(') return false;
                case ']':
                    if (stack.isEmpty()) return false;
                    if (stack.pop() != '[') return false;
                case '}':
                    if (stack.isEmpty()) return false;
                    if (stack.pop() != '}') return false;
                default:
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        In in = new In();
        String s = in.readAll().trim();
        StdOut.println(isBalanced(s));
    }
}
