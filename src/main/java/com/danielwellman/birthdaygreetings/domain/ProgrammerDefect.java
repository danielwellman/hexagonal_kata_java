package com.danielwellman.birthdaygreetings.domain;

public class ProgrammerDefect extends RuntimeException {
    public ProgrammerDefect(String message) {
        super(message);
    }
}
