package test.unit.birthdaygreetings;

import com.danielwellman.birthdaygreetings.domain.Date;
import com.danielwellman.birthdaygreetings.domain.MonthAndDay;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DateTest {

    @Test
    public void sameMonthAndDayIgnoresYears() {
        Date march13_2001 = new Date(2001, 3, 13);

        assertThat(march13_2001.sameMonthAndDayAs(new MonthAndDay(2, 13)), is(false));
        assertThat(march13_2001.sameMonthAndDayAs(new MonthAndDay(3, 13)), is(true));
    }

    @Test
    public void reportsLeapYears() {
        assertThat(year(2000).isLeapYear(), is(true));
        assertThat(year(2001).isLeapYear(), is(false));
        assertThat(year(2002).isLeapYear(), is(false));
        assertThat(year(2003).isLeapYear(), is(false));
        assertThat(year(2004).isLeapYear(), is(true));
    }

    private Date year(int year) {
        return new Date(year, 1, 1);
    }
}
