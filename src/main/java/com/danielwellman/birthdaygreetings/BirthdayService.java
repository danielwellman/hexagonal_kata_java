package com.danielwellman.birthdaygreetings;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class BirthdayService {
    private final Notifier notifier;

    public BirthdayService(Notifier notifier) {
        this.notifier = notifier;
    }

    public void sendGreetings(Date today) {
        Path path = Paths.get("birthdays.txt");
        List<String> strings = allBirthdayEntries(path);
        String firstLine = strings.get(1);
        Scanner scanner = new Scanner(firstLine);
        scanner.useDelimiter(",\\s?");
        scanner.next(); // first name
        scanner.next(); // last name
        scanner.next(); // birthday
        String email = scanner.next();

        notifier.notify(email);
    }

    private List<String> allBirthdayEntries(Path path) {
        try {
            return Files.readAllLines(path, Charset.defaultCharset());
        } catch (IOException e) {
            throw new BirthdayListUnavailableException();
        }
    }
}
