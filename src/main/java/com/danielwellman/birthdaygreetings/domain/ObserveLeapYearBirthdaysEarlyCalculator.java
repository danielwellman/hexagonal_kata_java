package com.danielwellman.birthdaygreetings.domain;

import java.util.Arrays;
import java.util.List;

public class ObserveLeapYearBirthdaysEarlyCalculator implements BirthdaysEffectiveCalculator {
    @Override
    public List<MonthAndDay> birthdaysEffectiveOn(Date today) {
        return Arrays.asList(today.monthAndDate());
    }
}
