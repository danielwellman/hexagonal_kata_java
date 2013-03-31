package test.endtoend.birthdaygreetings;

import com.danielwellman.birthdaygreetings.BirthdayService;

public class ApplicationRunner {
    FakeMailer fakeMailer = new FakeMailer();

    public void runFor(FakeCalendar calendar) {
        BirthdayService birthdayService = new BirthdayService(fakeMailer);
        birthdayService.sendGreetings(calendar.today());
    }

    public void hasDeliveredGreetingTo(String email) {
        fakeMailer.hasSentAMessageTo(email);
    }
}
