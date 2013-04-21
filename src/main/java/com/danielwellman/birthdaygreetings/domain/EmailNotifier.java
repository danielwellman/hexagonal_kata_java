package com.danielwellman.birthdaygreetings.domain;

public class EmailNotifier implements Notifier {
    private final PostOffice postOffice;

    public EmailNotifier(PostOffice postOffice) {
        this.postOffice = postOffice;
    }

    @Override
    public void notify(Person person) {
        Email email = new Email(person.emailAddress());
        email.subject("Happy birthday!");
        email.body("Happy birthday, dear " + person.firstName());
        
        postOffice.deliver(email);
    }
}
