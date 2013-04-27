package test.unit.birthdaygreetings;

import com.danielwellman.birthdaygreetings.domain.EmailAddress;
import org.junit.Test;

public class EmailAddressTest {

    @Test(expected = IllegalArgumentException.class)
    public void forbidsConstructionWithNullAddress() {
        new EmailAddress(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void forbidsConstructionWithEmptyAddress() {
        new EmailAddress("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void forbidsConstructionWithBlankAddress() {
        new EmailAddress(" ");
    }
}
