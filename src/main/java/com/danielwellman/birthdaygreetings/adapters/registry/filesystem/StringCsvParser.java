package com.danielwellman.birthdaygreetings.adapters.registry.filesystem;

class StringCsvParser implements CsvParser<String> {
    @Override
    public String parse(String data) {
        return data;
    }

}
