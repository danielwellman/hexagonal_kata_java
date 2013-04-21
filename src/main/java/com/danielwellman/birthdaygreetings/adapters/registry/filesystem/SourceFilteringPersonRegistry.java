package com.danielwellman.birthdaygreetings.adapters.registry.filesystem;

import com.danielwellman.birthdaygreetings.domain.Date;
import com.danielwellman.birthdaygreetings.domain.Person;
import com.danielwellman.birthdaygreetings.domain.PersonRegistry;

import java.util.Collection;
import java.util.HashSet;

public class SourceFilteringPersonRegistry implements PersonRegistry {

    private final PeopleSource peopleSource;

    public SourceFilteringPersonRegistry(PeopleSource peopleSource) {
        this.peopleSource = peopleSource;
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