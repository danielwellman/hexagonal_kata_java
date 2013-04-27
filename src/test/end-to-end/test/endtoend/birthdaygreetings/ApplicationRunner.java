package test.endtoend.birthdaygreetings;

import com.danielwellman.birthdaygreetings.Main;
import com.danielwellman.birthdaygreetings.adapters.notifiers.inmemory.InMemoryPostOffice;
import com.danielwellman.birthdaygreetings.domain.TodaySource;

import java.nio.file.Path;

public class ApplicationRunner {
    private final Path path;
    private final InMemoryPostOffice inMemoryPostOffice = new InMemoryPostOffice();

    public ApplicationRunner(Path path) {
        this.path = path;
    }

    public void runFor(TodaySource calendar) {
        new Main(calendar, inMemoryPostOffice, path).run();
    }

    public void hasDeliveredGreetingTo(String... emails) {
        for (String email : emails) {
            inMemoryPostOffice.hasSentAMessageTo(email);
        }
    }

    public void hasDeliveredNoGreetings() {
        inMemoryPostOffice.hasSentNoMessages();
    }

    public void hasNotDeliveredGreetingTo(String... emails) {
        for (String email : emails) {
            inMemoryPostOffice.hasNotSentAMessageTo(email);
        }
    }
}
