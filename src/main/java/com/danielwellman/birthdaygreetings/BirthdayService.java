package com.danielwellman.birthdaygreetings;

public class BirthdayService {
    private final Notifier notifier;
    private final PersonRegistry personRegistry;

    public BirthdayService(Notifier notifier, PersonRegistry personRegistry) {
        this.notifier = notifier;
        this.personRegistry = personRegistry;
    }

    public void sendGreetings(Date today) {
        Person person = personRegistry.firstPerson();
        notifier.notify(person);
    }
}
