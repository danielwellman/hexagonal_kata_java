package test.endtoend.birthdaygreetings;

public class Email {
    private final String to;

    public Email(String to) {
        this.to = to;
    }

    public String to() {
        return to;
    }
}
