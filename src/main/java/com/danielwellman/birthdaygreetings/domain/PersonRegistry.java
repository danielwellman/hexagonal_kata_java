package com.danielwellman.birthdaygreetings.domain;

import java.util.Collection;

public interface PersonRegistry {
    Collection<Person> birthdaysOn(Collection<MonthAndDay> monthAndDay);
}
