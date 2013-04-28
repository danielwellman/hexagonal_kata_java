package com.danielwellman.birthdaygreetings.domain;

public class Person {
    private final Name name;
    private final EmailAddress emailAddress;
    private final Date birthday;

    public Person(Name name, EmailAddress emailAddress, Date birthday) {
        if (null == name) throw new IllegalArgumentException("Name cannot be null");
        if (null == emailAddress) throw new IllegalArgumentException("Email cannot be null");
        if (null == birthday) throw new IllegalArgumentException("Birthday cannot be null");

        this.name = name;
        this.emailAddress = emailAddress;
        this.birthday = birthday;
    }

    public Name name() {
        return this.name;
    }

    public EmailAddress emailAddress() {
        return emailAddress;
    }

    public boolean isBirthday(MonthAndDay monthAndDay) {
        return birthday.sameMonthAndDayAs(monthAndDay);
    }

    // These equals and hash code implementations compare all values; I'm thinking in this case that
    // a Person isn't really an entity (if so, what would uniquely identify them, given this data?)
    // In this case, we treat the Person object like a value object, and these are equal only if all
    // the fields are equal.

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!birthday.equals(person.birthday)) return false;
        if (!emailAddress.equals(person.emailAddress)) return false;
        if (!name.equals(person.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + emailAddress.hashCode();
        result = 31 * result + birthday.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                ", birthday=" + birthday +
                ", emailAddress=" + emailAddress +
                '}';
    }
}
