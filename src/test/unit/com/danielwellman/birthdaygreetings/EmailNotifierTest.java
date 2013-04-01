package com.danielwellman.birthdaygreetings;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;

public class EmailNotifierTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void sendsAnEmailGreetingToPerson() {
        final PostOffice postOffice = context.mock(PostOffice.class);
        context.checking(new Expectations() {{
            oneOf(postOffice).deliver(with(anEmailTo("somebody@email.com")));
        }});

        Person person = new Person(new EmailAddress("somebody@email.com"));
        EmailNotifier notifier = new EmailNotifier(postOffice);
        notifier.notify(person);
    }

    private Matcher<Email> anEmailTo(String address) {
        return new FeatureMatcher<Email, EmailAddress>(equalTo(new EmailAddress(address)), "an email sent to", "to") {

            @Override
            protected EmailAddress featureValueOf(Email actual) {
                return actual.to();
            }
        };
    }
}
