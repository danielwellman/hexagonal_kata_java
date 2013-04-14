package test.integration.birthdaygreetings;

import com.danielwellman.birthdaygreetings.Date;
import com.danielwellman.birthdaygreetings.EmailAddress;
import com.danielwellman.birthdaygreetings.FileSystemPersonRegistry;
import com.danielwellman.birthdaygreetings.Person;
import org.hamcrest.FeatureMatcher;
import org.junit.Before;
import org.junit.Test;
import test.endtoend.birthdaygreetings.BirthdayEntryDetails;
import test.endtoend.birthdaygreetings.FakeBirthdayList;

import java.io.IOException;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsEqual.equalTo;
import static test.endtoend.birthdaygreetings.BirthdayEntryDetails.entryFor;

public class FileSystemPersonRegistryTest {

    private final FakeBirthdayList birthdayList = new FakeBirthdayList();
    private final FileSystemPersonRegistry registry = new FileSystemPersonRegistry();

    @Before
    public void resetBirhdaysFile() throws IOException {
        birthdayList.clear();
    }

    @Test
    public void correctlyParsesAPerson() throws IOException {
        birthdayList.createContaining(entryFor("Jenny", "Saskatoon", "jenny@saskato.on", 2001, 1, 14));

        Person expectedPerson = new Person("Jenny", "Saskatoon", new EmailAddress("jenny@saskato.on"), new Date(2001, 1, 14));
        assertThat(registry.allPeople(), hasItem(expectedPerson));
    }

    @Test
    public void returnsAllPeople() throws IOException {
        birthdayList.createContaining(
                anEntryForAPersonWithEmail("jenny@saskato.on"),
                anEntryForAPersonWithEmail("frank@somebody.com"),
                anEntryForAPersonWithEmail("me@dev.com")
        );

        Collection<Person> people = registry.allPeople();
        assertThat(people, hasItem(personWithEmail("jenny@saskato.on")));
        // STRANGE The JUnit/Hamcrest failure message here is strange to me:
        //        Expected: a collection containing a person with email <frank@somebody.com>
        //        but: email was <jenny@saskato.on>
        // Sounds like it matched a person but it had the wrong e-mail, not that it wasn't in the collection.
        assertThat(people, hasItem(personWithEmail("frank@somebody.com")));
        assertThat(people, hasItem(personWithEmail("me@dev.com")));
    }

    private FeatureMatcher<Person, EmailAddress> personWithEmail(final String email) {
        return new FeatureMatcher<Person, EmailAddress>(equalTo(new EmailAddress(email)),
                "a person with email", "email") {

            @Override
            protected EmailAddress featureValueOf(Person person) {
                return person.emailAddress();
            }
        };
    }

    private BirthdayEntryDetails anEntryForAPersonWithEmail(String email) {
        return entryFor("Jenny", "Saskatoon", email, 2001, 1, 14);
    }
}
