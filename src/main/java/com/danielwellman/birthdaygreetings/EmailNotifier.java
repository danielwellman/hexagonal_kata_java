package com.danielwellman.birthdaygreetings;

public class EmailNotifier implements Notifier {
    private final PostOffice postOffice;

    public EmailNotifier(PostOffice postOffice) {
        this.postOffice = postOffice;
    }

    @Override
    public void notify(Person person) {
        Email email = new Email(person.emailAddress());
        postOffice.deliver(email);
    }
}
