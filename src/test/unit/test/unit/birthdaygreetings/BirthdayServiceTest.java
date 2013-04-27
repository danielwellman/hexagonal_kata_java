package test.unit.birthdaygreetings;

import com.danielwellman.birthdaygreetings.domain.*;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BirthdayServiceTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private PersonRegistry registry = context.mock(PersonRegistry.class);
    private Notifier notifier = context.mock(Notifier.class);
    private BirthdaysEffectiveCalculator calculator = context.mock(BirthdaysEffectiveCalculator.class);

    @Test
    public void notifiesAllPeopleCalculatedForToday() {
        final Date anyDate = new Date(2003, 8, 22);
        final Person aPerson = aUniquePerson();
        final Person anotherPerson = aUniquePerson();

        context.checking(new Expectations() {
            {
                // SMELL Two allowings chained together by parameters - the calculator's return value is passed to a argument
                // for an allowing on the registry call to birthdaysOn.  Something feels funny here... It's like
                // I'm describing an algorithm *in the test*, but that's not really the point of this test -- I
                // want to check that all people returned from the registry are notified.
                List<MonthAndDay> effectiveDates = Arrays.asList(new MonthAndDay(8, 22), new MonthAndDay(8, 23));
                allowing(calculator).birthdaysEffectiveOn(anyDate); will(returnValue(effectiveDates));
                allowing(registry).birthdaysOn(effectiveDates); will(returnValue(Arrays.asList(aPerson, anotherPerson)));

                oneOf(notifier).notify(aPerson);
                oneOf(notifier).notify(anotherPerson);
            }
        });
        BirthdayService birthdayService = new BirthdayService(notifier, registry, calculator);
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
