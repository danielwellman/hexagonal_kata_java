package test.endtoend.birthdaygreetings;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FakeBirthdayList {

    public static final Path BIRTHDAYS_PATH = Paths.get("birthdays.txt");

    public void createContaining(BirthdayEntryDetails entry) throws IOException {
        // TODO Possibly use a visitor or something if we want to simplify this code without encoding format in the details?
        //      ... or that might be overkill for this test.
        String header = "last_name, first_name, date_of_birth, email";
        String line = entry.getLastName() + ", " +
                entry.getFirstName() + ", " +
                entry.getBirthday() + ", " +
                entry.getEmail();
        List<String> contents = Arrays.asList(header, line);

        Files.write(BIRTHDAYS_PATH, contents, Charset.defaultCharset());
    }

    public void clear() throws IOException {
        Files.deleteIfExists(BIRTHDAYS_PATH);
    }
}
