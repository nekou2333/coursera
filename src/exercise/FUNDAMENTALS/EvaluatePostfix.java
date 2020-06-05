package exercise.FUNDAMENTALS;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class EvaluatePostfix {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("+")) stack.push(stack.pop() + stack.pop());
            else if (s.equals("*")) stack.push(stack.pop() * stack.pop());
            else stack.push(Integer.parseInt(s));
        }
        StdOut.println(stack.pop());
    }
}