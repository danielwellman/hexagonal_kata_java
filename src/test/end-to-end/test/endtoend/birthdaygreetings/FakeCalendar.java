package test.endtoend.birthdaygreetings;

import com.danielwellman.birthdaygreetings.domain.Calendar;
import com.danielwellman.birthdaygreetings.domain.Date;

public class FakeCalendar implements Calendar {

    private final Date today;

    public FakeCalendar(int year, int month, int day) {
        today = new Date(year, month, day);
    }

    @Override
    public Date today() {
        return today;
    }
}
