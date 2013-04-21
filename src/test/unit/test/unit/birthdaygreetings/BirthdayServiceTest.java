package test.unit.birthdaygreetings;

import com.danielwellman.birthdaygreetings.domain.*;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

public class BirthdayServiceTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private PersonRegistry registry = context.mock(PersonRegistry.class);
    private Notifier notifier = context.mock(Notifier.class);

    @Test
    public void notifiesAllPeopleReturnedForToday() {
        final Date anyDate = new Date(2003, 8, 22);
        final Person aPerson = new Person("someName", "lastNames", new EmailAddress("whatever@foo.com"), new Date(2013, 9, 1));
        final Person anotherPerson = new Person("another", "someLastName", new EmailAddress("whoever@foo.com"), new Date(2013, 9, 2));

        context.checking(new Expectations() {
            {
                allowing(registry).birthdaysOn(anyDate); will(returnValue(Arrays.asList(aPerson, anotherPerson)));
                oneOf(notifier).notify(aPerson);
                oneOf(notifier).notify(anotherPerson);
            }
        });
        BirthdayService birthdayService = new BirthdayService(notifier, registry);
        birthdayService.sendGreetings(anyDate);
    }

}
