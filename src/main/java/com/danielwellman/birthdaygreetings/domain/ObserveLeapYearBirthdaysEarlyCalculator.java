package com.danielwellman.birthdaygreetings.domain;

import com.danielwellman.birthdaygreetings.conveniences.Sets;

import java.util.Set;

public class ObserveLeapYearBirthdaysEarlyCalculator implements BirthdaysEffectiveCalculator {

    private static final MonthAndDay LAST_NON_LEAP_DAY_OF_FEBRUARY = new MonthAndDay(2, 28);
    private static final MonthAndDay LEAP_DAY_IN_FEBRUARY = new MonthAndDay(2, 29);

    @Override
    public Set<MonthAndDay> birthdaysEffectiveOn(Date today) {
        if (today.isLeapYear() || !today.sameMonthAndDayAs(LAST_NON_LEAP_DAY_OF_FEBRUARY)) {
            return Sets.hashSet(today.monthAndDate());
        } else if (today.sameMonthAndDayAs(LAST_NON_LEAP_DAY_OF_FEBRUARY)) {
            return Sets.hashSet(LAST_NON_LEAP_DAY_OF_FEBRUARY, LEAP_DAY_IN_FEBRUARY);
        } else {
            throw new ProgrammerDefect("Failed to suggest a matching date for " + today);
        }
    }

}
