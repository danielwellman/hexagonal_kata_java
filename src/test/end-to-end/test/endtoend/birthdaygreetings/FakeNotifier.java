package test.endtoend.birthdaygreetings;

import com.danielwellman.birthdaygreetings.Notifier;
import com.danielwellman.birthdaygreetings.Person;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

public class FakeNotifier implements Notifier {
    private List<Email> messagesSentTo = new ArrayList<>();

    public void hasSentAMessageTo(String address) {
        assertThat(messagesSentTo, hasItem(emailAddressedTo(address)));
    }

    private Matcher<Email> emailAddressedTo(final String email) {
        return new FeatureMatcher<Email, String>(equalTo(email), "an email addressed to", "to") {
            @Override
            protected String featureValueOf(Email actual) {
                return actual.to();
            }
        };
    }

    @Override
    public void notify(Person person) {
        Email email = new Email(person.emailAddress().toString());
        messagesSentTo.add(email);
    }
}
