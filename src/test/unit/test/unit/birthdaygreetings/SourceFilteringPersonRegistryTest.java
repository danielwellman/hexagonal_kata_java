package test.unit.birthdaygreetings;

import com.danielwellman.birthdaygreetings.*;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

public class SourceFilteringPersonRegistryTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private PeopleSource peopleSource = context.mock(PeopleSource.class);
    private SourceFilteringPersonRegistry registry = new SourceFilteringPersonRegistry(peopleSource);

    @Test
    public void returnsEmptyCollectionIfNoBirthdaysToday() {
        context.checking(new Expectations() {{
            allowing(peopleSource).allPeople();
            will(returnValue(createPersonWithBirthdayOn(new Date(2011, 1, 1))));
        }});
        assertThat(registry.birthdaysOn(new Date(2030, 12, 31)), emptyCollectionOf(Person.class));
    }


    @Test
    public void returnsSingleElementCollectionIfOnePersonHasBirthdayToday() {
        final Person christmasBirthday = createPersonWithBirthdayOn(new Date(2011, 12, 31));
        context.checking(new Expectations() {{
            allowing(peopleSource).allPeople();
            will(returnValue(christmasBirthday));
        }});
        assertThat(registry.birthdaysOn(new Date(2013, 12, 31)), contains(christmasBirthday));
    }

    // TODO - Test with multiple birthdays returned?
    // TODO - Test on same birthday and year?


    private Person createPersonWithBirthdayOn(Date birthday) {
        return new Person("firstName", "lastName", new EmailAddress("whatever@email.com"), birthday);
    }
}
