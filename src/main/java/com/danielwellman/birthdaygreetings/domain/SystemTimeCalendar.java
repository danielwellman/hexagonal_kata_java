package com.danielwellman.birthdaygreetings.domain;

import org.joda.time.LocalDate;

public class SystemTimeCalendar implements Calendar {
    @Override
    public Date today() {
        return new Date(LocalDate.now());
    }
}
