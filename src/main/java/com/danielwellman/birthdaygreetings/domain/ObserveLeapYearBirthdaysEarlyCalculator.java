package com.danielwellman.birthdaygreetings.domain;

import com.danielwellman.birthdaygreetings.conveniences.Sets;

import java.util.Set;

public class ObserveLeapYearBirthdaysEarlyCalculator implements BirthdaysEffectiveCalculator {

    @Override
    public Set<MonthAndDay> birthdaysEffectiveOn(Date date) {
        return date.wouldMissLeapYear() ? Sets.hashSet(MonthAndDay.FEB_28, MonthAndDay.FEB_29) : Sets.hashSet(date.monthAndDate());
    }

}
