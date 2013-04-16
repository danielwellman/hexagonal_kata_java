package test.endtoend.birthdaygreetings;

import com.danielwellman.birthdaygreetings.Email;
import com.danielwellman.birthdaygreetings.PostOffice;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

class FakePostOffice implements PostOffice {
    private List<Email> sentMessages = new ArrayList<>();

    @Override
    public void deliver(Email email) {
        sentMessages.add(email);
    }

    public void hasSentAMessageTo(String address) {
        assertThat(sentMessages, hasItem(emailAddressedTo(address)));
    }

    private Matcher<Email> emailAddressedTo(final String email) {
        return new FeatureMatcher<Email, String>(equalTo(email), "an email addressed to", "to") {
            @Override
            protected String featureValueOf(Email actual) {
                return actual.to().toString();
            }
        };
    }

    public void hasSentNoMessages() {
        assertThat(sentMessages, empty());
    }

    public void hasNotSentAMessageTo(String email) {
        assertThat(sentMessages, not(hasItem(emailAddressedTo(email))));

    }
}
