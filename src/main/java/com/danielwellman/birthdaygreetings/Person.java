package com.danielwellman.birthdaygreetings;

public class Person {
    private final String firstName;
    private EmailAddress emailAddress;

    public Person(String firstName, EmailAddress emailAddress) {
        this.firstName = firstName;
        this.emailAddress = emailAddress;
    }

    public EmailAddress emailAddress() {
        return emailAddress;
    }

    public String firstName() {
        return firstName;
    }
}
