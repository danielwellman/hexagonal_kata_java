package com.danielwellman.birthdaygreetings;

public class Email {
    private final EmailAddress to;
    private String subject;

    public Email(EmailAddress to) {
        this.to = to;
    }

    public EmailAddress to() {
        return to;
    }

    public String subject() {
        return subject;
    }

    public void subject(String newSubject) {
        this.subject = newSubject;
    }
}
