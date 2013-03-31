package com.danielwellman.birthdaygreetings;

public class BirthdayService {
    private final Notifier notifier;
    private final PersonRegistry personRegistry;

    public BirthdayService(Notifier notifier) {
        this.notifier = notifier;
        personRegistry = new FileSystemPersonRegistry();
    }

    public void sendGreetings(Date today) {
        Person person = personRegistry.firstPerson();
        notifier.notify(person);
    }
}
