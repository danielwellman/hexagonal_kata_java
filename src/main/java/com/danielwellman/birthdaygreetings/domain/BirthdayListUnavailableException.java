package com.danielwellman.birthdaygreetings.domain;

public class BirthdayListUnavailableException extends RuntimeException {
    public BirthdayListUnavailableException() {
        super("Could not access birthday list");
    }
}
