package com.danielwellman.birthdaygreetings.domain;

import java.util.Collection;
import java.util.List;

public interface PersonRegistry {
    Collection<Person> birthdaysOn(List<MonthAndDay> monthAndDay);
}
