package test.unit.birthdaygreetings;

import com.danielwellman.birthdaygreetings.domain.Name;
import org.junit.Test;

public class NameTest {
    @Test
    public void allowsOnlyOneNameToBeBlank() {
        new Name("first", null);
        new Name(null, "last");
    }

    @Test(expected = IllegalArgumentException.class)
    public void forbidsConstructionWithBothNamesNull() {
        new Name(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void forbidsConstructionWithBothNamesBlank() {
        new Name("", "");
    }
}
