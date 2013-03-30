package test.endtoend.birthdaygreetings;

public class BirthdayEntryBuilder {
    private String firstName;
    private String lastName;
    private String email;
    private Date birthdate;

    public BirthdayEntryBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public BirthdayEntryBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public BirthdayEntryBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public BirthdayEntryBuilder withBirthday(int year, int month, int day) {
        this.birthdate = new Date(year, month, day);
        return this;
    }

    public BirthdayEntryDetails build() {
        return new BirthdayEntryDetails(firstName, lastName, email, birthdate);
    }
}
