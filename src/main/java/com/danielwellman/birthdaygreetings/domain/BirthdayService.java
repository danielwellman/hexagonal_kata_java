package com.danielwellman.birthdaygreetings.domain;

import com.danielwellman.birthdaygreetings.conveniences.Sets;

import java.util.Set;

public class BirthdayService {
    private static final Set<MonthAndDay> FEB_28_AND_29 = Sets.hashSet(MonthAndDay.FEB_28, MonthAndDay.FEB_29);

    private final Notifier notifier;
    private final People people;

    public BirthdayService(Notifier notifier, People people) {
        this.notifier = notifier;
        this.people = people;
    }

    public void sendGreetings(Date today) {
        for (Person person : people.withBirthdaysOn(birthdaysEffectiveOn(today))) {
            notifier.notify(person);
        }
    }

    private Set<MonthAndDay> birthdaysEffectiveOn(Date date) {
        return date.wouldMissLeapYear() ? FEB_28_AND_29 : Sets.hashSet(date.monthAndDate());
    }

}
