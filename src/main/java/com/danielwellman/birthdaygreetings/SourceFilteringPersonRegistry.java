package com.danielwellman.birthdaygreetings;

import java.util.Collection;

public class SourceFilteringPersonRegistry implements PersonRegistry {

    private final PeopleSource peopleSource;

    public SourceFilteringPersonRegistry(PeopleSource peopleSource) {
        this.peopleSource = peopleSource;
    }

    @Override
    public Collection<Person> allPeople() {
        return peopleSource.allPeople();
    }

}