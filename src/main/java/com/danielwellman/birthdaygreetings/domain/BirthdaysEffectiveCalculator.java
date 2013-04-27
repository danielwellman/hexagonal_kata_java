package com.danielwellman.birthdaygreetings.domain;

import java.util.Collection;

public interface BirthdaysEffectiveCalculator {
    Collection<MonthAndDay> birthdaysEffectiveOn(Date today);
}
