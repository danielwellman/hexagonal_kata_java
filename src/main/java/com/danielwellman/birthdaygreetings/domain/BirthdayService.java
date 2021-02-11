package com.danielwellman.birthdaygreetings.domain;

import com.danielwellman.birthdaygreetings.conveniences.Sets;

import java.util.Set;

public class BirthdayService {
    private static final Set<MonthAndDay> FEB_28_AND_29 = Sets.hashSet(MonthAndDay.FEB_28, MonthAndDay.FEB_29);

    private final Notifier notifier;
    private final People people;
    private final String bad1;
    private final String bad2;
    private final String bad3;
    private final String bad4;

    public BirthdayService(Notifier notifier, People people, String bad1, String bad2, String bad3, String bad4) {
        this.notifier = notifier;
        this.people = people;
        this.bad1 = bad1;
        this.bad2 = bad2;
        this.bad3 = bad3;
        this.bad4 = bad4;
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
