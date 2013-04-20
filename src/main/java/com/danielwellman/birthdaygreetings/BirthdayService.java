package com.danielwellman.birthdaygreetings;

import java.util.Collection;

public class BirthdayService {
    private final Notifier notifier;
    private final PersonRegistry personRegistry;

    public BirthdayService(Notifier notifier, PersonRegistry personRegistry) {
        this.notifier = notifier;
        this.personRegistry = personRegistry;
    }

    public void sendGreetings(Date today) {
        Collection<Person> people = personRegistry.birthdaysOn(today);
        for (Person person : people) {
            notifier.notify(person);
        }
    }
}
