package com.danielwellman.birthdaygreetings;

/**
 * An email message contents.
 *
 * Trying out a slightly Smalltalk-esque style instead of JavaBean getters and setters.  Why?
 * To see what it looks like, to see if it works, and to see if it's perhaps crazy.
 */
public class Email {
    private final EmailAddress to;
    private String subject;
    private String body;

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

    public String body() {
        return body;
    }

    public void body(String newBody) {
        this.body = newBody;
    }

    @Override
    public String toString() {
        return "Email{" +
                "to=" + to +
                ", subject='" + subject + '\'' +
                '}';
    }
}
