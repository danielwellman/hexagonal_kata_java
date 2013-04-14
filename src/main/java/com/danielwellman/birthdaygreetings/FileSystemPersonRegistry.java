package com.danielwellman.birthdaygreetings;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class FileSystemPersonRegistry implements PersonRegistry {

    public static final Path FILE_PATH = Paths.get("birthdays.txt");

    @Override
    public Collection<Person> allPeople() {
        Collection<Person> people = new HashSet<>();
        List<String> strings = allBirthdayEntries();
        List<String> rowsWithoutHeader = strings.subList(1, strings.size());
        for (String row : rowsWithoutHeader) {
            Person parsed = parse(row);
            people.add(parsed);
        }
        return people;
    }

    private List<String> allBirthdayEntries() {
        try {
            return Files.readAllLines(FILE_PATH, Charset.defaultCharset());
        } catch (IOException e) {
            throw new BirthdayListUnavailableException();
        }
    }

    private Person parse(String line) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(",\\s?");
        String lastName = scanner.next();
        String firstName = scanner.next();
        String birthday = scanner.next();
        String email = scanner.next();

        return new Person(firstName, lastName, new EmailAddress(email), Date.fromCommonFormat(birthday));
    }
}