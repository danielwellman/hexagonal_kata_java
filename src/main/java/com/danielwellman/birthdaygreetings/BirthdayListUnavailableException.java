package com.danielwellman.birthdaygreetings;

public class BirthdayListUnavailableException extends RuntimeException {
    public BirthdayListUnavailableException() {
        super("Could not access birthday list");
    }
}
