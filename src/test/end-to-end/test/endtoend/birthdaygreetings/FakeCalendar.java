package test.endtoend.birthdaygreetings;

import com.danielwellman.birthdaygreetings.domain.Date;
import com.danielwellman.birthdaygreetings.domain.TodaySource;

public class FakeCalendar implements TodaySource {

    private final Date today;

    public FakeCalendar(int year, int month, int day) {
        today = new Date(year, month, day);
    }

    @Override
    public Date today() {
        return today;
    }
}
