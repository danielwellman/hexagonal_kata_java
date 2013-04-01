package com.danielwellman.birthdaygreetings;

public class Email {
    private final EmailAddress to;

    public Email(EmailAddress to) {
        this.to = to;
    }

    public EmailAddress to() {
        return to;
    }
}
