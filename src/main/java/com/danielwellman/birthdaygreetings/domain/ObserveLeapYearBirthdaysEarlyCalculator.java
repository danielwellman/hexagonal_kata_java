package com.danielwellman.birthdaygreetings.domain;

import java.util.Arrays;
import java.util.List;

public class ObserveLeapYearBirthdaysEarlyCalculator implements BirthdaysEffectiveCalculator {

    public static final MonthAndDay LAST_NON_LEAP_DAY_OF_FEBRUARY = new MonthAndDay(2, 28);
    public static final MonthAndDay LEAP_DAY_IN_FEBRUARY = new MonthAndDay(2, 29);

    @Override
    public List<MonthAndDay> birthdaysEffectiveOn(Date today) {
        if (today.isLeapYear() || !today.sameMonthAndDayAs(LAST_NON_LEAP_DAY_OF_FEBRUARY)) {
            return Arrays.asList(today.monthAndDate());
        } else if (today.sameMonthAndDayAs(LAST_NON_LEAP_DAY_OF_FEBRUARY)) {
            return Arrays.asList(LAST_NON_LEAP_DAY_OF_FEBRUARY, LEAP_DAY_IN_FEBRUARY);
        } else {
            throw new ProgrammerDefect("Failed to suggest a matching date for " + today);
        }
    }
}
