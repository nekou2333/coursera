package exercise.FUNDAMENTALS;

public class Transaction {
    private final String who;
    private final Date when;
    private final double amount;

    public Transaction(String who, Date when, double amount) {
        if (Double.isNaN(amount) || Double.isInfinite(amount))
            throw new IllegalArgumentException("Amount cannot be NaN or infinite");
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    public String who() {
        return who;
    }

    public Date when() {
        return when;
    }

    public double amount() {
        return amount;
    }

    public String toString() {
        return String.format("%-10s %10s %8.2f", who, when, amount);
    }

    public boolean equals(Object x) {
        if (this == x)  return true;
        if (x == null)  return false;
        if (this.getClass() != x.getClass())    return false;
        Transaction that = (Transaction) x;
        return (this.amount == that.amount)
                && (this.when.equals(that.when))
                &&(this.who.equals(that.who));
    }
}
