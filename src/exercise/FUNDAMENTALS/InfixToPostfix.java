package exercise.FUNDAMENTALS;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class InfixToPostfix {
    public static String filter(String s) {
        Stack<Character> ops = new Stack<>();
        Stack<Character> vals = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') ;
            else if (c == '+') ops.push(c);
            else if (c == '-') ops.push(c);
            else if (c == '*') ops.push(c);
            else if (c == '/') ops.push(c);
            else {
                if (c == ')') {
                    char op = ops.pop();
                    vals.push(op);
                } else
                    vals.push(c);
            }
        }
        StringBuilder ans = new StringBuilder();
        for (Character c : vals) {
            ans.append(c);
        }
        return ans.reverse().toString();
    }

    public static void main(String[] args) {
        In in = new In();
        String s = in.readAll().trim();
        StdOut.println(filter(s));
    }
}
