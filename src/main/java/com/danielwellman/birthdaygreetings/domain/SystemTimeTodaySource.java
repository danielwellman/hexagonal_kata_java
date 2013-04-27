package com.danielwellman.birthdaygreetings.domain;

import org.joda.time.LocalDate;

public class SystemTimeTodaySource implements TodaySource {
    @Override
    public Date today() {
        return new Date(LocalDate.now());
    }
}
