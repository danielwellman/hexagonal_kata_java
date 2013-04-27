package com.danielwellman.birthdaygreetings.adapters.registry.filesystem;

import com.danielwellman.birthdaygreetings.domain.EmailAddress;

class EmailAddressCsvParser implements CsvParser<EmailAddress> {
    @Override
    public EmailAddress parse(String data) {
        return new EmailAddress(data);
    }
}
