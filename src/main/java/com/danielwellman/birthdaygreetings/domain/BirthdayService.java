package com.danielwellman.birthdaygreetings.domain;

import java.util.Set;

public class BirthdayService {
    private final Notifier notifier;
    private final PersonRegistry personRegistry;
    // FUTURE I'm not certain whether this strategy belongs as an internal or injected collaborator.
    private final BirthdaysEffectiveCalculator birthdaysEffectiveCalculator;

    public BirthdayService(Notifier notifier, PersonRegistry personRegistry, BirthdaysEffectiveCalculator calculator) {
        this.notifier = notifier;
        this.personRegistry = personRegistry;
        this.birthdaysEffectiveCalculator = calculator;
    }

    public void sendGreetings(Date today) {
        Set<MonthAndDay> effectiveDates = birthdaysEffectiveCalculator.birthdaysEffectiveOn(today);
        Set<Person> people = personRegistry.birthdaysOn(effectiveDates);
        for (Person person : people) {
            notifier.notify(person);
        }
    }

}
