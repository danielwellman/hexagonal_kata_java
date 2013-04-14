package test.endtoend.birthdaygreetings;

import com.danielwellman.birthdaygreetings.BirthdayService;
import com.danielwellman.birthdaygreetings.EmailNotifier;
import com.danielwellman.birthdaygreetings.FileSystemPersonRegistry;

public class ApplicationRunner {
    FakePostOffice fakePostOffice = new FakePostOffice();

    public void runFor(FakeCalendar calendar) {
        BirthdayService birthdayService = new BirthdayService(new EmailNotifier(fakePostOffice), new FileSystemPersonRegistry());
        birthdayService.sendGreetings(calendar.today());
    }

    public void hasDeliveredGreetingTo(String... emails) {
        for (String email : emails) {
            fakePostOffice.hasSentAMessageTo(email);
        }
    }

}
