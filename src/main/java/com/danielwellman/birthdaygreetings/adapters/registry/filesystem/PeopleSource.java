package com.danielwellman.birthdaygreetings.adapters.registry.filesystem;

import com.danielwellman.birthdaygreetings.domain.Person;

import java.util.Collection;

public interface PeopleSource {
    Collection<Person> allPeople();
}
