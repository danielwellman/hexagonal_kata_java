package com.danielwellman.birthdaygreetings.domain;

public class MonthAndDay {
    private final int monthOfYear;
    private final int dayOfMonth;

    public MonthAndDay(int monthOfYear, int dayOfMonth) {
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
