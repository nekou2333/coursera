package exercise.FUNDAMENTALS;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFindUF {
    private int[] id;
    private int count;

    public QuickFindUF(int n) {
        id = new int[n];
        count = n;
        for (int i = 0; i < n; ++i) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    // 返回所在分量的标志符
    public int find(int p) {
        validate(p);
        return id[p];
    }

    private void validate(int p) {
        int n = id.length;
        if (p < 0 || p >= n)
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
    }

    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        validate(p);
        validate(q);
        int pid = id[p];
        int qid = id[q];
        if (pid == qid) return;
        for (int i = 0; i < id.length; ++i) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
        count--;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.find(p) == uf.find(q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}
