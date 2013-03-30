package test.endtoend.birthdaygreetings;

import org.junit.Before;
import org.junit.Test;

public class BirthdayGreetingsEndToEndTest {

    private final FakeBirthdayList birthdayList = new FakeBirthdayList();

    @Before
    public void resetBirthdayList() {
        birthdayList.clear();
    }

    @Test
    public void sends_mail_if_person_has_birthday_today() {
        FakeCalendar today = new FakeCalendar(2012, 1, 15);

        birthdayList.createContaining(entryFor("John", "Doe", "john.doe@foobar.com", 1970, 1, 15));

        ApplicationRunner application = new ApplicationRunner();
        application.runFor(today);

        application.hasDeliveredGreetingTo("john.doe@foobar.com");
    }

    private BirthdayEntryDetails entryFor(String firstName, String lastName, String email, int year, int month, int day) {
        return new BirthdayEntryBuilder().withFirstName(firstName).withLastName(lastName).withEmail(email).withBirthday(year, month, day).build();
    }
}
