package test.endtoend.birthdaygreetings;

import com.danielwellman.birthdaygreetings.BirthdayService;
import com.danielwellman.birthdaygreetings.EmailNotifier;
import com.danielwellman.birthdaygreetings.FileSystemPeopleSource;
import com.danielwellman.birthdaygreetings.SourceFilteringPersonRegistry;

import java.nio.file.Path;

public class ApplicationRunner {
    private final Path path;
    FakePostOffice fakePostOffice = new FakePostOffice();

    public ApplicationRunner(Path path) {
        this.path = path;
    }

    public void runFor(FakeCalendar calendar) {
        BirthdayService birthdayService = new BirthdayService(new EmailNotifier(fakePostOffice), new SourceFilteringPersonRegistry(new FileSystemPeopleSource(path)));
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
