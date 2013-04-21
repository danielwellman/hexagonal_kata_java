package test.endtoend.birthdaygreetings;

import com.danielwellman.birthdaygreetings.adapters.registry.filesystem.FileSystemPeopleSource;
import com.danielwellman.birthdaygreetings.adapters.registry.filesystem.SourceFilteringPersonRegistry;
import com.danielwellman.birthdaygreetings.domain.BirthdayService;
import com.danielwellman.birthdaygreetings.domain.EmailNotifier;

import java.nio.file.Path;

public class ApplicationRunner {
    private final Path path;
    private FakePostOffice fakePostOffice = new FakePostOffice();

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
