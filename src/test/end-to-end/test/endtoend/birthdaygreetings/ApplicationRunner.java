package test.endtoend.birthdaygreetings;

import com.danielwellman.birthdaygreetings.BirthdayService;

public class ApplicationRunner {
    FakeNotifier fakeNotifier = new FakeNotifier();

    public void runFor(FakeCalendar calendar) {
        BirthdayService birthdayService = new BirthdayService(fakeNotifier);
        birthdayService.sendGreetings(calendar.today());
    }

    public void hasDeliveredGreetingTo(String email) {
        fakeNotifier.hasSentAMessageTo(email);
    }
}
