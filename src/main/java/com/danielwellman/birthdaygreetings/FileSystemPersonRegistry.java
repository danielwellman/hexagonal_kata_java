package com.danielwellman.birthdaygreetings;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class FileSystemPersonRegistry implements PersonRegistry {

    public static final Path FILE_PATH = Paths.get("birthdays.txt");

    @Override
    public Person firstPerson() {
        List<String> strings = allBirthdayEntries();
        String firstLine = strings.get(1);
        return parse(firstLine);
    }

    List<String> allBirthdayEntries() {
        try {
            return Files.readAllLines(FILE_PATH, Charset.defaultCharset());
        } catch (IOException e) {
            throw new BirthdayListUnavailableException();
        }
    }

    Person parse(String line) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(",\\s?");
        String firstName = scanner.next();
        scanner.next(); // last name
        scanner.next(); // birthday
        String email = scanner.next();

        return new Person(firstName, new EmailAddress(email));
    }
}