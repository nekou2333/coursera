package exercise.FUNDAMENTALS;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Josephus {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);

        int counter = 1;
        Queue<Integer> queue = new Queue<>();

        for (int i = 0; i < N; ++i) {
            queue.enqueue(i);
        }

        while (!queue.isEmpty()) {
            if (counter++ % M != 0) {
                queue.enqueue(queue.dequeue());
            } else {
                StdOut.print(queue.dequeue() + " ");
            }
        }
        StdOut.println();
    }
}
