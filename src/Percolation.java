import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF uf_without_bottom;
    private boolean[] sites;
    private final int n;
    private int count;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n:" + n + "is illegal.");
        }
        uf = new WeightedQuickUnionUF(n * n + 2);
        uf_without_bottom = new WeightedQuickUnionUF(n * n + 1);
        sites = new boolean[n * n];
        for (int i = 0; i < n * n; i++) {
            sites[i] = false;
        }
        this.n = n;
        count = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);

        if (isOpen(row, col)) {
            return;
        }

        int key = (row - 1) * n + (col - 1);
        sites[key] = true;
        count++;
        if (row == 1) {
            union(key, n * n);
        }
        if (row > 1 && sites[key - n]) {
            union(key, key - n);
        }
        if (row < n && sites[key + n]) {
            union(key, key + n);
        }
        if (row == n) {
            uf.union(key, n * n + 1);
        }
        if (col > 1 && sites[key - 1]) {
            union(key, key - 1);
        }
        if (col < n && sites[key + 1]) {
            union(key, key + 1);
        }
    }

    private void union(int p, int q) {
        uf.union(p, q);
        uf_without_bottom.union(p, q);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        int key = (row - 1) * n + (col - 1);
        return sites[key];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int key = (row - 1) * n + (col - 1);
        return (isOpen(row, col) && (uf_without_bottom.find(key) == uf_without_bottom.find(n * n)));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(n * n) == uf.find(n * n + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(1, 3);
        p.open(2, 3);
        p.open(3, 3);
        p.open(3, 1);
        System.out.println(p.isFull(3, 1));
        System.out.println(p.isFull(3, 3));
    }

    // helper
    private void validate(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("row:" + row + " or col:" + col + " is invalid.");
        }
    }
}
