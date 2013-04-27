package com.danielwellman.birthdaygreetings.adapters.registry.filesystem;

import com.danielwellman.birthdaygreetings.domain.Date;

class DateCsvParser implements CsvParser<Date> {
    @Override
    public Date parse(String data) {
        return Date.fromCommonFormat(data);
    }
}
