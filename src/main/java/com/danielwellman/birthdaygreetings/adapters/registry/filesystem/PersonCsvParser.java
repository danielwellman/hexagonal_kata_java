package com.danielwellman.birthdaygreetings.adapters.registry.filesystem;

import com.danielwellman.birthdaygreetings.domain.Date;
import com.danielwellman.birthdaygreetings.domain.EmailAddress;
import com.danielwellman.birthdaygreetings.domain.Name;
import com.danielwellman.birthdaygreetings.domain.Person;

class PersonCsvParser implements CsvParser<Person> {
    private final CsvParser<String> firstNameParser;
    private final CsvParser<String> lastNameParser;
    private final CsvParser<EmailAddress> emailAddressParser;
    private final CsvParser<Date> dateCsvParser;

    PersonCsvParser(CsvParser<String> firstNameParser,
                    CsvParser<String> lastNameParser,
                    CsvParser<EmailAddress> emailAddressParser,
                    CsvParser<Date> dateCsvParser) {
        this.firstNameParser = firstNameParser;
        this.lastNameParser = lastNameParser;
        this.emailAddressParser = emailAddressParser;
        this.dateCsvParser = dateCsvParser;
    }

    @Override
    public Person parse(String data) {
        String[] tokens = data.split(",\\s?");
        return new Person(
                new Name(firstNameParser.parse(tokens[1]),
                        lastNameParser.parse(tokens[0])),
                emailAddressParser.parse(tokens[3]),
                dateCsvParser.parse(tokens[2]));
    }
}
