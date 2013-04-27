package test.endtoend.birthdaygreetings;

import com.danielwellman.birthdaygreetings.domain.TodaySource;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static test.endtoend.birthdaygreetings.BirthdayEntryDetails.entryFor;

public class BirthdayGreetingsEndToEndTest {

    private final Path sourceFile = Paths.get("birthdays.txt");
    private final FakeBirthdayList birthdayList = new FakeBirthdayList(sourceFile);
    private final ApplicationRunner application = new ApplicationRunner(sourceFile);

    private final TodaySource irrelevantDay = new FakeCalendar(2012, 1, 15);

    @Before
    public void resetBirthdayList() throws IOException {
        birthdayList.clear();
    }

    @Test
    public void sends_no_mail_if_no_birthdays_are_today() throws IOException {
        birthdayList.createContaining(entryFor("John", "Doe", "john.doe@foobar.com", 1970, 8, 22),
                entryFor("Sarah", "Vane", "sarah.vane@mail.com", 1980, 1, 29));

        application.runFor(irrelevantDay);

        application.hasDeliveredNoGreetings();
    }

    @Test
    public void sends_mail_to_all_people_in_list_whose_birthdays_are_today() throws IOException {
        birthdayList.createContaining(entryFor("John", "Doe", "john.doe@foobar.com", 1970, 8, 22),
                                      entryFor("Sarah", "Vane", "sarah.vane@mail.com", 1980, 1, 29),
                                      entryFor("Zae", "Smith", "zsmith@name.com", 1980, 8, 22),
                                      entryFor("Charles", "Kuro", "ckuro@email.com", 1980, 11, 3));

        application.runFor(new FakeCalendar(2013, 8, 22));

        application.hasDeliveredGreetingTo("john.doe@foobar.com", "zsmith@name.com");
        application.hasNotDeliveredGreetingTo("sarah.vane@mail.com", "ckuro@email.com");
    }

    @Test
    public void sends_mail_to_leap_year_birthdays_on_non_leap_years() throws IOException {
        birthdayList.createContaining(entryFor("Sam", "Smith", "feb28@mail.com", 2000, 2, 28),
                                      entryFor("Lucille", "Ball", "feb29@mail.com", 2004, 2, 29));

        application.runFor(new FakeCalendar(2005, 2, 28));

        application.hasDeliveredGreetingTo("feb28@mail.com", "feb29@mail.com");
    }

}
