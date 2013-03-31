package test.endtoend.birthdaygreetings;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class BirthdayGreetingsEndToEndTest {

    private final FakeBirthdayList birthdayList = new FakeBirthdayList();

    @Before
    public void resetBirthdayList() throws IOException {
        birthdayList.clear();
    }

    @Test
    public void sends_mail_if_person_has_birthday_today() throws IOException {
        FakeCalendar today = new FakeCalendar(2012, 1, 15);

        birthdayList.createContaining(entryFor("John", "Doe", "john.doe@foobar.com", 1970, 1, 15));

        ApplicationRunner application = new ApplicationRunner();
        application.runFor(today);

        // Note we may want to check with a 'real' SMTP server, or fake or something if we want to be more end-to-end.
        application.hasDeliveredGreetingTo("john.doe@foobar.com"); // Note: We may want to check more of the mail details, including body?  Or a
    }

    private BirthdayEntryDetails entryFor(String firstName, String lastName, String email, int year, int month, int day) {
        return new BirthdayEntryBuilder().withFirstName(firstName).withLastName(lastName).withEmail(email).withBirthday(year, month, day).build();
    }
}
