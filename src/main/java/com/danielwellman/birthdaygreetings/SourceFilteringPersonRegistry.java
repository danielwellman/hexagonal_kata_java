package com.danielwellman.birthdaygreetings;

import java.util.Collection;
import java.util.Collections;

public class SourceFilteringPersonRegistry implements PersonRegistry {

    private final PeopleSource peopleSource;

    public SourceFilteringPersonRegistry(PeopleSource peopleSource) {
        this.peopleSource = peopleSource;
    }

    @Override
    public Collection<Person> allPeople() {
        return peopleSource.allPeople();
    }

    @Override
    public Collection<Person> birthdaysOn(Date date) {
        return Collections.emptyList();
    }

}