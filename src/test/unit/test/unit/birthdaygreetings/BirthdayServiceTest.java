package test.unit.birthdaygreetings;

import com.danielwellman.birthdaygreetings.domain.*;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class BirthdayServiceTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private PersonRegistry registry = context.mock(PersonRegistry.class);
    private Notifier notifier = context.mock(Notifier.class);

    @Test
    public void notifiesAllPeopleReturnedForToday() {
        final Date anyDate = new Date(2003, 8, 22);
        final Person aPerson = aUniquePerson();
        final Person anotherPerson = aUniquePerson();

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

    private static int uniqueCounter = 0;

    private Person aUniquePerson() {
        int uniqueSuffix = uniqueCounter++;
        Random randomGenerator = new Random();
        int randomMonth = randomGenerator.nextInt(11) + 1;
        int randomDay = randomGenerator.nextInt(27) + 1;
        return new Person(new Name("firstName", "lastName" + uniqueSuffix), new EmailAddress("whatever" + uniqueSuffix + "@foo.com"), new Date(2013, randomMonth, randomDay));
    }

}
