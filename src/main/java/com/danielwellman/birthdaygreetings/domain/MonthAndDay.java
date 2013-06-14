package com.danielwellman.birthdaygreetings.domain;

public class MonthAndDay {
    public static final MonthAndDay FEB_28 = new MonthAndDay(2, 28);
    public static final MonthAndDay FEB_29 = new MonthAndDay(2, 29);

    private final int monthOfYear;
    private final int dayOfMonth;

    public MonthAndDay(int monthOfYear, int dayOfMonth) {
        if (monthOfYear <= 0) throw new IllegalArgumentException("month must be greater than zero");
        if (dayOfMonth <= 0) throw new IllegalArgumentException("day must be greater than zero");
        // FUTURE Validate that month <= 12, and day <= 29, but I've reached the point of boredom now...

        this.monthOfYear = monthOfYear;
        this.dayOfMonth = dayOfMonth;
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonthAndDay that = (MonthAndDay) o;

        if (dayOfMonth != that.dayOfMonth) return false;
        if (monthOfYear != that.monthOfYear) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dayOfMonth;
        result = 31 * result + monthOfYear;
        return result;
    }

    @Override
    public String toString() {
        return monthOfYear + "/" + dayOfMonth;
    }
}
