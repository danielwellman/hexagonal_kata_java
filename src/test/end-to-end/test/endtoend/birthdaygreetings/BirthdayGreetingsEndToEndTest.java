package test.endtoend.birthdaygreetings;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static test.endtoend.birthdaygreetings.BirthdayEntryDetails.entryFor;

public class BirthdayGreetingsEndToEndTest {

    private final FakeBirthdayList birthdayList = new FakeBirthdayList();

    private final FakeCalendar irrelevantDay = new FakeCalendar(2012, 1, 15);

    @Before
    public void resetBirthdayList() throws IOException {
        birthdayList.clear();
    }

    @Test
    public void sends_no_mail_if_no_birthdays_are_today() throws IOException {
        birthdayList.createContaining(entryFor("John", "Doe", "john.doe@foobar.com", 1970, 8, 22),
                entryFor("Sarah", "Vane", "sarah.vane@mail.com", 1980, 1, 29));

        ApplicationRunner application = new ApplicationRunner();
        application.runFor(irrelevantDay);

        application.hasDeliveredNoGreetings();
    }

    @Test
    public void sends_mail_to_all_people_in_list_whose_birthdays_are_today() throws IOException {
        birthdayList.createContaining(entryFor("John", "Doe", "john.doe@foobar.com", 1970, 8, 22),
                                      entryFor("Sarah", "Vane", "sarah.vane@mail.com", 1980, 1, 29),
                                      entryFor("Zae", "Smith", "zsmith@name.com", 1980, 8, 22),
                                      entryFor("Charles", "Kuro", "ckuro@email.com", 1980, 11, 3));

        ApplicationRunner application = new ApplicationRunner();
        application.runFor(new FakeCalendar(2013, 8, 22));

        application.hasDeliveredGreetingTo("john.doe@foobar.com", "zsmith@name.com");
        application.hasNotDeliveredGreetingTo("sarah.vane@mail.com", "ckuro@email.com");
    }

}
