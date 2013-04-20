package com.danielwellman.birthdaygreetings;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

public class Date {

    private static final String COMMON_DATE_FORMAT = "yyyy/MM/dd";
    private final LocalDate date;

    public Date(int year, int month, int day) {
        date = new LocalDate(year, month, day);
    }

    public Date(LocalDate date) {
        this.date = date;
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Date date1 = (Date) o;

        if (!date.equals(date1.date)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    @Override
    public String toString() {
        return date.toString(COMMON_DATE_FORMAT);
    }

    public static Date fromCommonFormat(String commonDateFormatString) {
        LocalDate jodaDate = LocalDate.parse(commonDateFormatString, DateTimeFormat.forPattern(COMMON_DATE_FORMAT));
        return new Date(jodaDate);
    }

    public boolean sameMonthAndDayAs(Date other) {
        // FUTURE Consider an implementation that does not rely on peeking into private fields
        boolean sameMonthOfYear = this.date.getMonthOfYear() == other.date.getMonthOfYear();
        boolean sameDayOfMonth = this.date.getDayOfMonth() == other.date.getDayOfMonth();

        return sameMonthOfYear && sameDayOfMonth;
    }
}
