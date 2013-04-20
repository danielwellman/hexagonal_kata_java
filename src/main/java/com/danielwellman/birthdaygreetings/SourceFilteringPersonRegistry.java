package com.danielwellman.birthdaygreetings;

import java.util.Collection;
import java.util.HashSet;

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
    public Collection<Person> birthdaysOn(Date targetDate) {
        Collection<Person> filtered = new HashSet<>();

        Collection<Person> all = peopleSource.allPeople();
        for (Person person : all) {
            if (person.birthday().sameMonthAndDayAs(targetDate)) {
                filtered.add(person);
            }
        }
        return filtered;
    }

}