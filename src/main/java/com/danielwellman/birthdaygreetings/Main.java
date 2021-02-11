package com.danielwellman.birthdaygreetings;

import com.danielwellman.birthdaygreetings.adapters.notifiers.inmemory.InMemoryPostOffice;
import com.danielwellman.birthdaygreetings.adapters.registry.filesystem.FileSystemPeopleSource;
import com.danielwellman.birthdaygreetings.adapters.registry.filesystem.InMemoryPeople;
import com.danielwellman.birthdaygreetings.domain.*;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    private final Calendar calendar;
    private final PostOffice postOffice;
    private final Path path;

    public Main(Calendar calendar, PostOffice postOffice, Path path) {
        this.calendar = calendar;
        this.postOffice = postOffice;
        this.path = path;
    }

    public void run() {
        BirthdayService birthdayService = new BirthdayService(new EmailNotifier(this.postOffice),
                new InMemoryPeople(new FileSystemPeopleSource(this.path)), "Bad 1", "Bad 2", "Bad 3", "bad4"
        );
        birthdayService.sendGreetings(this.calendar.today());
    }

    public static void main(String[] args) {
        // FUTURE This could accept an argument for the filename to use
        new Main(new SystemTimeCalendar(), new InMemoryPostOffice(), Paths.get("birthdays.txt")).run();
    }

}