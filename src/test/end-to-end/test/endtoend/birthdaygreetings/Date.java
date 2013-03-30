package test.endtoend.birthdaygreetings;

import org.joda.time.LocalDate;

public class Date {

    private final LocalDate date;

    public Date(int year, int month, int day) {
        date = new LocalDate(year, month, day);
    }

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
        return date.toString("yyyy/MM/dd");
    }
}
