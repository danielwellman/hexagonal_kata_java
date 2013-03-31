package com.danielwellman.birthdaygreetings;

public class BirthdayService {
    private final Mailer mailer;

    public BirthdayService(Mailer mailer) {
        this.mailer = mailer;
    }

    public void sendGreetings(Date today) {

    }
}
