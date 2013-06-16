package test.unit.birthdaygreetings;

import com.danielwellman.birthdaygreetings.conveniences.Sets;
import com.danielwellman.birthdaygreetings.domain.*;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.Set;

public class BirthdayServiceTest {
    private static final int LEAP_YEAR = 2004;
    private static final int REGULAR_YEAR = 2013;

    private static final MonthAndDay FEB_13 = new MonthAndDay(2, 13);
    private static final MonthAndDay FEB_28 = new MonthAndDay(2, 28);
    private static final MonthAndDay FEB_29 = new MonthAndDay(2, 29);

    private static final Set<Person> SOME_PEOPLE = Sets.hashSet(new Person(new Name("a", "person"), new EmailAddress("some@mail.com"), new Date(1970, 1, 1)));

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private final People people = context.mock(People.class);
    private final Notifier notifier = context.mock(Notifier.class);
    private final BirthdayService birthdayService = new BirthdayService(notifier, people);

    @Test
    public void notifiesAllPeopleWithBirthdaysOnTheGivenDate() {
        context.checking(new Expectations() {
            {
                Person person1 = new Person(new Name("person", "one"), new EmailAddress("person_1@mail.com"), FEB_13.inYear(2001));
                Person person2 = new Person(new Name("person", "two"), new EmailAddress("person_2@mail.com"), FEB_13.inYear(2004));
                allowing(people).withBirthdaysOn(Sets.hashSet(FEB_13)); will(returnValue(Sets.hashSet(person1, person2)));

                oneOf(notifier).notify(person1);
                oneOf(notifier).notify(person2);
            }
        });
        birthdayService.sendGreetings(FEB_13.inYear(REGULAR_YEAR));
    }

    @Test
    public void looksForFeb29BirthdaysInYearWhenItWouldBeMissed() {
        context.checking(new Expectations() {
            {
                // Note the allowing from the previous tests is now an expect since the parameters are the important bit
                oneOf(people).withBirthdaysOn(Sets.hashSet(FEB_28, FEB_29)); will(returnValue(SOME_PEOPLE));

                ignoring(notifier);
            }
        });
        birthdayService.sendGreetings(FEB_28.inYear(REGULAR_YEAR));
    }

    @Test
    public void looksOnlyForFeb28BirthdaysInLeapYears() {
        context.checking(new Expectations() {
            {
                oneOf(people).withBirthdaysOn(Sets.hashSet(FEB_28)); will(returnValue(SOME_PEOPLE));

                ignoring(notifier);
            }
        });
        birthdayService.sendGreetings(FEB_28.inYear(LEAP_YEAR));
    }

}
