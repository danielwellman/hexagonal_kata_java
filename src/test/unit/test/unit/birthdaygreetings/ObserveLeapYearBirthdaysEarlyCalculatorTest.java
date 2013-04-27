package test.unit.birthdaygreetings;

import com.danielwellman.birthdaygreetings.domain.Date;
import com.danielwellman.birthdaygreetings.domain.ObserveLeapYearBirthdaysEarlyCalculator;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

public class ObserveLeapYearBirthdaysEarlyCalculatorTest {

    private final ObserveLeapYearBirthdaysEarlyCalculator calculator = new ObserveLeapYearBirthdaysEarlyCalculator();

    @Test
    public void leapYearBirthdaysHaveSameEffectiveDate() {
        assertHasSameEffectiveBirthday(new Date(2004, 2, 29));
        assertHasSameEffectiveBirthday(new Date(2004, 2, 28));
        assertHasSameEffectiveBirthday(new Date(2004, 3, 1));
        assertHasSameEffectiveBirthday(new Date(2004, 1, 1));
    }

    private void assertHasSameEffectiveBirthday(Date date) {
        assertThat(calculator.birthdaysEffectiveOn(date), hasItems(date.monthAndDate()));
    }
}
