package test.endtoend.birthdaygreetings;

import com.danielwellman.birthdaygreetings.Date;

public class FakeCalendar {

    private final Date today;

    public FakeCalendar(int year, int month, int day) {
        today = new Date(year, month, day);
    }

    public Date today() {
        return today;
    }
}
