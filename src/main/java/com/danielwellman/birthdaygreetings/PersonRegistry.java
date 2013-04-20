package com.danielwellman.birthdaygreetings;

import java.util.Collection;

public interface PersonRegistry {
    Collection<Person> allPeople();

    Collection<Person> birthdaysOn(Date date);
}
