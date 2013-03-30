package test.endtoend.birthdaygreetings;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

public class FakeBirthdayList {
    public void createContaining(BirthdayEntryDetails entry) {
        // TODO Possibly use a visitor or something if we want to simplify this code without encoding format in the details?
        //      ... or that might be overkill for this test.
        String contents = "last_name, first_name, date_of_birth, email" + System.lineSeparator() +
                entry.getLastName() + ", " +
                entry.getFirstName() + ", " +
                entry.getBirthday() + ", " +
                entry.getEmail() + System.lineSeparator();

        try {
            Files.write(contents, new File("birthdays.txt"), Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
