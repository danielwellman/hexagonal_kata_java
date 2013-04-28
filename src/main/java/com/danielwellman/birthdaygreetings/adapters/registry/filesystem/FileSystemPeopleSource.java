package com.danielwellman.birthdaygreetings.adapters.registry.filesystem;

import com.danielwellman.birthdaygreetings.domain.BirthdayListUnavailableException;
import com.danielwellman.birthdaygreetings.domain.Person;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class FileSystemPeopleSource implements PeopleSource {
    private final Path path;
    private final CsvParser<Person> personParser = new PersonCsvParser(
            new StringCsvParser(), new StringCsvParser(), new EmailAddressCsvParser(), new DateCsvParser());

    public FileSystemPeopleSource(Path path) {
        this.path = path;
    }

    @Override
    public Collection<Person> allPeople() {
        Collection<Person> people = new HashSet<>();
        for (String row : allEntriesInFile()) {
            // FUTURE To handle invalid rows, we might choose to ignore them and notify some sort of listener that
            // the row was skipped.  We might do this either by catching exceptions like IllegalArgumentException,
            // or perhaps change this PersonCsvParser to return a Maybe<Person>.
            people.add(personParser.parse(row));
        }
        return people;
    }

    private List<String> allEntriesInFile() {
        List<String> strings = allBirthdayEntries();
        // FUTURE Remove this obtuse encoded knowledge that the header is the first line and can be ignored
        return strings.subList(1, strings.size());
    }

    private List<String> allBirthdayEntries() {
        try {
            return Files.readAllLines(path, Charset.defaultCharset());
        } catch (IOException e) {
            throw new BirthdayListUnavailableException();
        }
    }


}