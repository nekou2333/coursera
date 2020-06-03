package exercise.FUNDAMENTALS;

import edu.princeton.cs.algs4.StdOut;

public class Rational implements Comparable<Rational> {
    private static Rational zero = new Rational(0, 1);
    private long num;
    private long den;

    public Rational(long numerator, long denominator) {
        if (denominator == 0)
            throw new ArithmeticException("denominator is zero");

        long g = gcd(numerator, denominator);
        den = denominator / g;
        num = numerator / g;

        if (den < 0) {
            den = -den;
            num = -num;
        }
    }

    public long numerator() {
        return num;
    }

    public long denominator() {
        return den;
    }

    public double toDouble() {
        return (double) num / den;
    }

    public String toString() {
        if (den == 1) return num + "";
        else return num + "/" + den;
    }

    // return {-1, 0, 1} when this < that, this = that, this > that
    public int compareTo(Rational that) {
        // 交叉相乘
        long lhs = this.num * that.den;
        long rhs = this.den * that.num;
        if (lhs < rhs) return -1;
        if (lhs > rhs) return 1;
        return 0;
    }

    public boolean equals(Object other) {
        if (other == null) return false;
        if (this.getClass() != other.getClass()) return false;
        Rational that = (Rational) other;
        return this.compareTo(that) == 0;
    }

    // 求|m|和|n|的最小公约数
    private static long gcd(long m, long n) {
        if (m < 0) m = -m;
        if (n < 0) n = -n;
        if (0 == n) return m;
        else return gcd(n, m % n);
    }

    // 求最大公倍数
    private static long lcm(long m, long n) {
        if (m < 0) m = -m;
        if (n < 0) n = -n;
        return m * n / (gcd(m, n));
    }

    // return this + that
    public Rational plus(Rational that) {
        if (this.compareTo(zero) == 0) return that;
        if (that.compareTo(zero) == 0) return this;

        long f = gcd(this.num, that.num);
        long g = gcd(this.den, that.den);
        Rational s = new Rational((this.num / f) * (that.den / g) + (that.num /f) * (this.den/g),
                this.den * (that.den / g));
        s.num *= f;
        return s;
    }

    // return -this
    public Rational negate() {
        return new Rational(-num, den);
    }

    // return |this|
    public Rational abs() {
        if (num >= 0) return this;
        else return negate();
    }

    // return this - that
    public Rational minus(Rational that) {
        return this.plus(that.negate());
    }

    public Rational reciprocal() {return new Rational(den, num);}

    // return this * that
    public Rational times(Rational b) {
        Rational c = new Rational(this.num, b.den);
        Rational d = new Rational(b.num, this.den);
        return new Rational(c.num * d.num, c.den * d.den);
    }

    // return this / that
    public Rational divides(Rational b) {
        return this.times(b.reciprocal());
    }

    public static void main(String[] args) {
        Rational x, y, z;

        // 1/2 + 1/3 = 5/6
        x = new Rational(1, 2);
        y = new Rational(1, 3);
        z = x.plus(y);
        StdOut.println(z);

        // 8/9 + 1/9 = 1
        x = new Rational(8, 9);
        y = new Rational(1, 9);
        z = x.plus(y);
        StdOut.println(z);

        // 1/200000000 + 1/300000000 = 1/120000000
        x = new Rational(1, 200000000);
        y = new Rational(1, 300000000);
        z = x.plus(y);
        StdOut.println(z);

        // 1073741789/20 + 1073741789/30 = 1073741789/12
        x = new Rational(1073741789, 20);
        y = new Rational(1073741789, 30);
        z = x.plus(y);
        StdOut.println(z);

        //  4/17 * 17/4 = 1
        x = new Rational(4, 17);
        y = new Rational(17, 4);
        z = x.times(y);
        StdOut.println(z);

        // 3037141/3247033 * 3037547/3246599 = 841/961
        x = new Rational(3037141, 3247033);
        y = new Rational(3037547, 3246599);
        z = x.times(y);
        StdOut.println(z);

        // 1/6 - -4/-8 = -1/3
        x = new Rational(1, 6);
        y = new Rational(-4, -8);
        z = x.minus(y);
        StdOut.println(z);
    }
}
