package com.danielwellman.birthdaygreetings;

public class Person {
    private EmailAddress emailAddress;

    public Person(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    public EmailAddress emailAddress() {
        return emailAddress;
    }
}
