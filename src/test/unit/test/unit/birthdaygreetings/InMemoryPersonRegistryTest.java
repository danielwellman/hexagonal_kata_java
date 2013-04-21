package test.unit.birthdaygreetings;

import com.danielwellman.birthdaygreetings.adapters.registry.filesystem.InMemoryPersonRegistry;
import com.danielwellman.birthdaygreetings.adapters.registry.filesystem.PeopleSource;
import com.danielwellman.birthdaygreetings.domain.Date;
import com.danielwellman.birthdaygreetings.domain.EmailAddress;
import com.danielwellman.birthdaygreetings.domain.Name;
import com.danielwellman.birthdaygreetings.domain.Person;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.emptyCollectionOf;

public class InMemoryPersonRegistryTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private PeopleSource peopleSource = context.mock(PeopleSource.class);
    private InMemoryPersonRegistry registry = new InMemoryPersonRegistry(peopleSource);

    @Test
    public void returnsEmptyCollectionIfNoBirthdaysToday() {
        context.checking(new Expectations() {{
            allowing(peopleSource).allPeople();
            will(returnValue(Arrays.asList(createPersonWithBirthdayOn(new Date(2011, 1, 1)))));
        }});
        assertThat(registry.birthdaysOn(new Date(2030, 12, 31)), emptyCollectionOf(Person.class));
    }


    @Test
    public void returnsAllPeopleWithBirthdayToday() {
        final Person christmasBirthday2011 = createPersonWithBirthdayOn(new Date(2011, 12, 25));
        final Person summerBirthday = createPersonWithBirthdayOn(new Date(2011, 8, 1));
        final Person christmasBirthday2009 = createPersonWithBirthdayOn(new Date(2009, 12, 25));

        context.checking(new Expectations() {{
            allowing(peopleSource).allPeople();
            will(returnValue(Arrays.asList(christmasBirthday2011, summerBirthday, christmasBirthday2009)));
        }});

        //noinspection unchecked
        assertThat(registry.birthdaysOn(new Date(2013, 12, 25)), allOf(
                containsInAnyOrder(christmasBirthday2009, christmasBirthday2011)
        ));
    }

    private Person createPersonWithBirthdayOn(Date birthday) {
        return new Person(new Name("firstName", "lastName"), new EmailAddress("whatever@email.com"), birthday);
    }
}
