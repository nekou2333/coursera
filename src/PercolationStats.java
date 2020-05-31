import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    // perform independent trials on an n-by-n grid;
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException("arguments must be positive");

        Percolation grid;
        int[] p = new int[trials];
        double[] threshold = new double[trials];

        for (int i = 0; i < trials; i++) {
            grid = new Percolation(n);
            while (!grid.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                grid.open(row, col);
            }
            p[i] = grid.numberOfOpenSites();
            threshold[i] = ((double) p[i]) / n / n;
        }

        mean = StdStats.mean(threshold);
        stddev = StdStats.stddev(threshold);
        confidenceLo = (mean() - 1.96 * stddev() / Math.sqrt(trials));
        confidenceHi = (mean() + 1.96 * stddev() / Math.sqrt(trials));
    }

    // sample mean of percolation threshold
    public double mean() {
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.confidenceHi;
    }

    // test client (see below)
    public static void main(String[] args) {
        int n[] = new int[2];

        for (int i = 0; i < args.length; i++) {
            n[i] = Integer.parseInt(args[i]);
        }


        PercolationStats test = new PercolationStats(n[0], n[1]);
        System.out.println("mean\t\t\t\t\t = " + test.mean());
        System.out.println("stddev\t\t\t\t\t = " + test.stddev());
        System.out.println("95% confidence interval\t = [" + test.confidenceLo() + ", " + test.confidenceHi() + "]");
    }
}
