package exercise.FUNDAMENTALS;

import edu.princeton.cs.algs4.StdOut;

public class Date implements Comparable<Date> {
    private static final int[] DAYS = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private final int month;
    private final int day;
    private final int year;

    public Date(int m, int d, int y) {
        if (!isValid(m, d, y)) throw new IllegalArgumentException("Invalid date");
        month = m;
        day = d;
        year = y;
    }

    public Date(String date) {
        String[] fields = date.split("/");
        if (fields.length != 3) {
            throw new IllegalArgumentException("Invalid date");
        }
        month = Integer.parseInt(fields[0]);
        day = Integer.parseInt(fields[1]);
        year = Integer.parseInt(fields[2]);
        if (!isValid(month, day, year)) throw new IllegalArgumentException("Invalid date");
    }

    public int month() {
        return month;
    }

    public int day() {
        return day;
    }

    public int year() {
        return year;
    }

    private static boolean isValid(int month, int day, int year) {
        if (month < 1 || month > 12) return false;
        if (day < 1 || day > DAYS[month]) return false;
        if (month == 2 && day == 29 && !isLeapYear(year)) return false;
        return true;
    }

    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) return true;
        if (year % 100 == 0) return false;
        return year % 4 == 0;
    }

    public Date next() {
        if (isValid(month, day + 1, year)) return new Date(month, day + 1, year);
        else if (isValid(month + 1, 1, year)) return new Date(month + 1, 1, year);
        else return new Date(1, 1, year + 1);
    }

    public boolean isAfter(Date that) {
        return this.compareTo(that) > 0;
    }

    public boolean isBefore(Date that) {
        return this.compareTo(that) < 0;
    }

    public int compareTo(Date that) {
        if (this.year < that.year) return -1;
        if (this.year > that.year) return 1;
        if (this.month < that.month) return -1;
        if (this.month > that.month) return 1;
        if (this.day < that.day) return -1;
        if (this.day > that.day) return 1;
        return 0;
    }

    public String toString() {
        return month() + "/" + day() + "/" + year();
    }

    public boolean equals(Object other) {
        if (other == null) return false;
        if (this.getClass() != other.getClass()) return false;
        Date that = (Date) other;
        return this.compareTo(that) == 0;
    }

    public int hashCode() {
        return day + month * 31 + year * 31 * 12;
    }

    /**
     * Unit tests the {@code Date} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Date today = new Date(2, 25, 2004);
        StdOut.println(today);
        for (int i = 0; i < 10; i++) {
            today = today.next();
            StdOut.println(today);
        }

        StdOut.println(today.isAfter(today.next()));
        StdOut.println(today.isAfter(today));
        StdOut.println(today.next().isAfter(today));


        Date birthday = new Date(10, 16, 1971);
        StdOut.println(birthday);
        for (int i = 0; i < 10; i++) {
            birthday = birthday.next();
            StdOut.println(birthday);
        }
    }
}
