package com.danielwellman.birthdaygreetings.adapters.notifiers.inmemory;

import com.danielwellman.birthdaygreetings.domain.Email;
import com.danielwellman.birthdaygreetings.domain.PostOffice;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * An in-memory implementation of a PostOffice.
 *
 * Note that this class uses the Hamcrest matchers and asserts - this is because this class was extracted from
 * the tests and moved into the production implementation.
 */
public class InMemoryPostOffice implements PostOffice {
    private final List<Email> sentMessages = new ArrayList<>();

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
