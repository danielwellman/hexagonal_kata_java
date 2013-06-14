package com.danielwellman.birthdaygreetings.domain;

import java.util.Collection;
import java.util.Set;

public interface People {
    Set<Person> withBirthdaysOn(Collection<MonthAndDay> monthAndDay);
}
