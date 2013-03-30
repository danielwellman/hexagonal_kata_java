package test.endtoend.birthdaygreetings;

public class ApplicationRunner {
    FakeMailer fakeMailer = new FakeMailer();

    public void runFor(FakeCalendar today) {
    }

    public void hasDeliveredGreetingTo(String email) {
        fakeMailer.hasSentAMessageTo(email);
    }
}
