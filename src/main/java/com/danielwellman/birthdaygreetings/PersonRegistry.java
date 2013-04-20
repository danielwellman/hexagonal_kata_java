package com.danielwellman.birthdaygreetings;

import java.util.Collection;

public interface PersonRegistry {
    Collection<Person> birthdaysOn(Date date);
}
