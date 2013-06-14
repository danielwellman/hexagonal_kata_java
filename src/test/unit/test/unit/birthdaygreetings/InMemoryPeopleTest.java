package test.unit.birthdaygreetings;

import com.danielwellman.birthdaygreetings.adapters.registry.filesystem.InMemoryPeople;
import com.danielwellman.birthdaygreetings.adapters.registry.filesystem.PeopleSource;
import com.danielwellman.birthdaygreetings.domain.*;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.emptyCollectionOf;

public class InMemoryPeopleTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private final PeopleSource peopleSource = context.mock(PeopleSource.class);
    private final InMemoryPeople people = new InMemoryPeople(peopleSource);

    @Test
    public void returnsEmptyCollectionIfNoBirthdaysMatchMonthAndDate() {
        context.checking(new Expectations() {{
            allowing(peopleSource).allPeople();
            will(returnValue(Arrays.asList(createPersonWithBirthdayOn(new Date(2011, 1, 1)))));
        }});
        assertThat(people.withBirthdaysOn(Arrays.asList(new MonthAndDay(12, 31))), emptyCollectionOf(Person.class));
    }


    @Test
    public void returnsAllPeopleWithBirthdaysThatMatchInListArguments() {
        final Person christmasBirthday2011 = createPersonWithBirthdayOn(new Date(2011, 12, 25));
        final Person summerBirthday = createPersonWithBirthdayOn(new Date(2011, 8, 1));
        final Person christmasBirthday2009 = createPersonWithBirthdayOn(new Date(2009, 12, 25));
        final Person halloween2008 = createPersonWithBirthdayOn(new Date(2008, 10, 31));

        context.checking(new Expectations() {{
            allowing(peopleSource).allPeople();
            will(returnValue(Arrays.asList(christmasBirthday2011, summerBirthday, christmasBirthday2009, halloween2008)));
        }});

        //noinspection unchecked
        assertThat(people.withBirthdaysOn(Arrays.asList(new MonthAndDay(12, 25), new MonthAndDay(10, 31))),
                containsInAnyOrder(christmasBirthday2009, christmasBirthday2011, halloween2008));
    }

    private Person createPersonWithBirthdayOn(Date birthday) {
        return new Person(new Name("firstName", "lastName"), new EmailAddress("whatever@email.com"), birthday);
    }
}
