package test.unit.birthdaygreetings;

import com.danielwellman.birthdaygreetings.domain.*;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;

public class EmailNotifierTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private final PostOffice postOffice = context.mock(PostOffice.class);

    @Test
    public void sendsAnEmailGreetingToPerson() {
        context.checking(new Expectations() {{
            oneOf(postOffice).deliver(with(anEmail(to("somebody@email.com"),
                                        subject(containsString("Happy birthday")),
                                        body(containsString("Shannon")))));
        }});

        Person person = new Person(new Name("Shannon", "Lastname"), new EmailAddress("somebody@email.com"), new Date(2009, 12, 1));
        EmailNotifier notifier = new EmailNotifier(postOffice);
        notifier.notify(person);
    }

    @SafeVarargs
    private final Matcher<Email> anEmail(Matcher<Email>... matcher) {
        return allOf(matcher);
    }

    private Matcher<Email> to(String address) {
        return new FeatureMatcher<Email, EmailAddress>(equalTo(new EmailAddress(address)), "sent to", "to") {

            @Override
            protected EmailAddress featureValueOf(Email actual) {
                return actual.to();
            }
        };
    }

    private Matcher<Email> subject(Matcher<String> matcher) {
        return new FeatureMatcher<Email, String>(matcher, "a subject matching", "the subject") {

            @Override
            protected String featureValueOf(Email actual) {
                return actual.subject();
            }
        };
    }

    private Matcher<Email> body(Matcher<String> matcher) {
        return new FeatureMatcher<Email, String>(matcher, "body matching", "the body") {

            @Override
            protected String featureValueOf(Email actual) {
                return actual.body();
            }
        };

    }
}
