package com.danielwellman.birthdaygreetings;

public class Person {
    private final String firstName;
    private final String lastName;
    private final EmailAddress emailAddress;
    private final Date birthday;

    public Person(String firstName, String lastName, EmailAddress emailAddress, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.birthday = birthday;
    }

    public EmailAddress emailAddress() {
        return emailAddress;
    }

    public String firstName() {
        return firstName;
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

        if (birthday != null ? !birthday.equals(person.birthday) : person.birthday != null) return false;
        if (emailAddress != null ? !emailAddress.equals(person.emailAddress) : person.emailAddress != null)
            return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress=" + emailAddress +
                ", birthday=" + birthday +
                '}';
    }
}
