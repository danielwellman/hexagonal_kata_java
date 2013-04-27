package com.danielwellman.birthdaygreetings.adapters.registry.filesystem;

import com.danielwellman.birthdaygreetings.domain.MonthAndDay;
import com.danielwellman.birthdaygreetings.domain.Person;
import com.danielwellman.birthdaygreetings.domain.PersonRegistry;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class InMemoryPersonRegistry implements PersonRegistry {

    private final PeopleSource peopleSource;

    public InMemoryPersonRegistry(PeopleSource peopleSource) {
        this.peopleSource = peopleSource;
    }

    @Override
    public Set<Person> birthdaysOn(Collection<MonthAndDay> monthAndDays) {
        Set<Person> filtered = new HashSet<>();

        Collection<Person> all = peopleSource.allPeople();
        for (Person person : all) {
            for (MonthAndDay monthAndDay : monthAndDays) {
                if (person.isBirthday(monthAndDay)) {
                    filtered.add(person);
                }
            }
        }
        return filtered;
    }

}