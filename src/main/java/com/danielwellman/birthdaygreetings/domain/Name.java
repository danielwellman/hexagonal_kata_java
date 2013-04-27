package com.danielwellman.birthdaygreetings.domain;

public class Name {
    private final String firstName;
    private final String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // FUTURE Replace need for getter and instead use some other way to greet this person,
    //        such as Smalltalk's style of printOn: aStream
    public String getFirstName() {
        return firstName;
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Name name = (Name) o;

        if (firstName != null ? !firstName.equals(name.firstName) : name.firstName != null) return false;
        if (lastName != null ? !lastName.equals(name.lastName) : name.lastName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}
