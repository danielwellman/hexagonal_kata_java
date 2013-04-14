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
    public void always_sends_mail_to_the_first_person_in_list_regardless_of_date() throws IOException {
        birthdayList.createContaining(entryFor("John", "Doe", "john.doe@foobar.com", 1970, 8, 22));

        ApplicationRunner application = new ApplicationRunner();
        application.runFor(irrelevantDay);

        application.hasDeliveredGreetingTo("john.doe@foobar.com"); // Note: We may want to check more of the mail details, including body?  Or a
    }

    @Test
    public void always_sends_mail_to_all_people_in_the_list_regardless_of_date() throws IOException {
        birthdayList.createContaining(entryFor("John", "Doe", "john.doe@foobar.com", 1970, 8, 22),
                                      entryFor("Sarah", "Vane", "sarah.vane@mail.com", 1980, 1, 29));

        ApplicationRunner application = new ApplicationRunner();
        application.runFor(irrelevantDay);

        application.hasDeliveredGreetingTo("john.doe@foobar.com", "sarah.vane@mail.com");

    }

}
