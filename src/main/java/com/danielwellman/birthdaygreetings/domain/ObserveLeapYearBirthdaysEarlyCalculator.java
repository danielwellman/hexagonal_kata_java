package com.danielwellman.birthdaygreetings.domain;

import com.danielwellman.birthdaygreetings.conveniences.Sets;

import java.util.Set;

public class ObserveLeapYearBirthdaysEarlyCalculator implements BirthdaysEffectiveCalculator {

    private static final MonthAndDay FEB_28 = new MonthAndDay(2, 28);
    private static final MonthAndDay FEB_29 = new MonthAndDay(2, 29);

    @Override
    public Set<MonthAndDay> birthdaysEffectiveOn(Date date) {
        if (!date.isInLeapYear() && date.sameMonthAndDayAs(FEB_28)) {
            return Sets.hashSet(FEB_28, FEB_29);
        } else return Sets.hashSet(date.monthAndDate());
    }

}
