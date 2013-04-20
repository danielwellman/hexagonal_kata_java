package com.danielwellman.birthdaygreetings;

import java.util.Collection;

public class FileSystemPersonRegistry implements PersonRegistry {

    private final PeopleSource peopleSource;

    public FileSystemPersonRegistry(FileSystemPeopleSource peopleSource) {
        this.peopleSource = peopleSource;
    }

    @Override
    public Collection<Person> allPeople() {
        return peopleSource.allPeople();
    }

}