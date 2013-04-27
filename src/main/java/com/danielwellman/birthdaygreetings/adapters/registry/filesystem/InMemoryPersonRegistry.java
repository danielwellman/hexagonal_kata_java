package com.danielwellman.birthdaygreetings.adapters.registry.filesystem;

import com.danielwellman.birthdaygreetings.domain.MonthAndDay;
import com.danielwellman.birthdaygreetings.domain.Person;
import com.danielwellman.birthdaygreetings.domain.PersonRegistry;

import java.util.Collection;
import java.util.HashSet;

public class InMemoryPersonRegistry implements PersonRegistry {

    private final PeopleSource peopleSource;

    public InMemoryPersonRegistry(PeopleSource peopleSource) {
        this.peopleSource = peopleSource;
    }

    @Override
    public Collection<Person> birthdaysOn(MonthAndDay monthAndDay) {
        Collection<Person> filtered = new HashSet<>();

        Collection<Person> all = peopleSource.allPeople();
        for (Person person : all) {
            if (person.isBirthday(monthAndDay)) {
                filtered.add(person);
            }
        }
        return filtered;
    }

}