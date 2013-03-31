package com.danielwellman.birthdaygreetings;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class BirthdayService {
    private final Notifier notifier;

    public BirthdayService(Notifier notifier) {
        this.notifier = notifier;
    }

    public void sendGreetings(Date today) {
        List<String> strings = allBirthdayEntries();
        String firstLine = strings.get(1);
        Person person = parse(firstLine);

        notifier.notify(person);
    }

    private List<String> allBirthdayEntries() {
        try {
            return Files.readAllLines(Paths.get("birthdays.txt"), Charset.defaultCharset());
        } catch (IOException e) {
            throw new BirthdayListUnavailableException();
        }
    }

    private Person parse(String line) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(",\\s?");
        scanner.next(); // first name
        scanner.next(); // last name
        scanner.next(); // birthday
        String email = scanner.next();

        return new Person(new EmailAddress(email));
    }
}
