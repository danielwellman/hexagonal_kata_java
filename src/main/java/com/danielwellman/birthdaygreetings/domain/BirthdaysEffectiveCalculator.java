package com.danielwellman.birthdaygreetings.domain;

import java.util.List;

public interface BirthdaysEffectiveCalculator {
    List<MonthAndDay> birthdaysEffectiveOn(Date today);
}
