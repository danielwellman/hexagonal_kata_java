package test.endtoend.birthdaygreetings;

import com.danielwellman.birthdaygreetings.BirthdayService;
import com.danielwellman.birthdaygreetings.EmailNotifier;
import com.danielwellman.birthdaygreetings.FileSystemPeopleSource;
import com.danielwellman.birthdaygreetings.FileSystemPersonRegistry;

import java.nio.file.Paths;

public class ApplicationRunner {
    FakePostOffice fakePostOffice = new FakePostOffice();

    public void runFor(FakeCalendar calendar) {
        BirthdayService birthdayService = new BirthdayService(new EmailNotifier(fakePostOffice), new FileSystemPersonRegistry(new FileSystemPeopleSource(Paths.get("birthdays.txt"))));
        birthdayService.sendGreetings(calendar.today());
    }

    public void hasDeliveredGreetingTo(String... emails) {
        for (String email : emails) {
            fakePostOffice.hasSentAMessageTo(email);
        }
    }

    public void hasDeliveredNoGreetings() {
        fakePostOffice.hasSentNoMessages();
    }

    public void hasNotDeliveredGreetingTo(String... emails) {
        for (String email : emails) {
            fakePostOffice.hasNotSentAMessageTo(email);
        }
    }
}
