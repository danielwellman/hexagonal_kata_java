package com.danielwellman.birthdaygreetings.domain;

import java.util.Set;

public interface BirthdaysEffectiveCalculator {
    Set<MonthAndDay> birthdaysEffectiveOn(Date today);
}
