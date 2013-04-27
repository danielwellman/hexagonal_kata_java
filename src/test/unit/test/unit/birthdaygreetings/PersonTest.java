package test.unit.birthdaygreetings;

import com.danielwellman.birthdaygreetings.domain.Date;
import com.danielwellman.birthdaygreetings.domain.EmailAddress;
import com.danielwellman.birthdaygreetings.domain.Name;
import com.danielwellman.birthdaygreetings.domain.Person;
import org.junit.Test;

public class PersonTest {

    public static final EmailAddress VALID_EMAIL_ADDRESS = new EmailAddress("irrelevant@email.com");
    public static final Date VALID_BIRTHDAY = new Date(2002, 3, 4);
    public static final Name VALID_NAME = new Name("first", "last");

    @Test(expected = IllegalArgumentException.class)
    public void forbidsConstructionWithNullName() {
        new Person(null, VALID_EMAIL_ADDRESS, VALID_BIRTHDAY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void forbidsConstructionWithNullEmail() {
        new Person(VALID_NAME, null, VALID_BIRTHDAY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void forbidsConstructionWithNullBirthday() {
        new Person(VALID_NAME, VALID_EMAIL_ADDRESS, null);
    }
}
