package test.integration.birthdaygreetings;

import com.danielwellman.birthdaygreetings.Date;
import com.danielwellman.birthdaygreetings.EmailAddress;
import com.danielwellman.birthdaygreetings.FileSystemPersonRegistry;
import com.danielwellman.birthdaygreetings.Person;
import org.junit.Before;
import org.junit.Test;
import test.endtoend.birthdaygreetings.FakeBirthdayList;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
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
    public void returnsTheFirstPerson() throws IOException {
        birthdayList.createContaining(entryFor("Jenny", "Saskatoon", "jenny@saskato.on", 2001, 1, 14));
        Person person = registry.firstPerson();

        Person expectedPerson = new Person("Jenny", "Saskatoon", new EmailAddress("jenny@saskato.on"), new Date(2001, 1, 14));
        assertThat(person, equalTo(expectedPerson));
    }

}
