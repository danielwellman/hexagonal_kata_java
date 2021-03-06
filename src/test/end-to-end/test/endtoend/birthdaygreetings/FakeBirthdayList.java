package test.endtoend.birthdaygreetings;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FakeBirthdayList {

    private final Path path;

    public FakeBirthdayList(Path path) {
        this.path = path;
    }

    public void createContaining(BirthdayEntryDetails... entries) throws IOException {
        String header = "last_name, first_name, date_of_birth, email";

        List<String> contents = new ArrayList<>();
        contents.add(header);
        contents.addAll(toCsvRows(entries));

        Files.write(path, contents, Charset.defaultCharset());
    }

    private List<String> toCsvRows(BirthdayEntryDetails[] entries) {
        List<String> result = new ArrayList<>();
        for (BirthdayEntryDetails entry : entries) {
            result.add(toCsvLine(entry));
        }
        return result;
    }

    // FUTURE This seems like it could live on BirthdayEntryDetails if we wanted...
    private String toCsvLine(BirthdayEntryDetails entry) {
        return entry.getLastName() + ", " +
                        entry.getFirstName() + ", " +
                        entry.getBirthday() + ", " +
                        entry.getEmail();
    }

    public void clear() throws IOException {
        Files.deleteIfExists(path);
    }
}
