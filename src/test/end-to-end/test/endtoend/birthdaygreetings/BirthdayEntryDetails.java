package test.endtoend.birthdaygreetings;

import com.danielwellman.birthdaygreetings.domain.Date;

public class BirthdayEntryDetails {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Date birthday;

    public BirthdayEntryDetails(String firstName, String lastName, String email, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public static BirthdayEntryDetails entryFor(String firstName, String lastName, String email, int year, int month, int day) {
        return new BirthdayEntryBuilder().withFirstName(firstName).withLastName(lastName).withEmail(email).withBirthday(year, month, day).build();
    }
}
