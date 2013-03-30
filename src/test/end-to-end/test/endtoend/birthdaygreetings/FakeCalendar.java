package test.endtoend.birthdaygreetings;

public class FakeCalendar {

    private final Date today;

    public FakeCalendar(int year, int month, int day) {
        today = new Date(year, month, day);
    }
}
