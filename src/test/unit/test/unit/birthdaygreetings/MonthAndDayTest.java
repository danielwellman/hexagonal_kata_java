package test.unit.birthdaygreetings;

import com.danielwellman.birthdaygreetings.domain.MonthAndDay;
import org.junit.Test;

public class MonthAndDayTest {
    @Test(expected = IllegalArgumentException.class)
    public void forbidsConstructionWithMonthZero() {
        new MonthAndDay(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void forbidsConstructionWithDayZero() {
        new MonthAndDay(1, 0);
    }
}
