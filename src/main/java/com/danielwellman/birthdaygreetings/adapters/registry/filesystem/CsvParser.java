package com.danielwellman.birthdaygreetings.adapters.registry.filesystem;

interface CsvParser<T> {
    T parse(String data);
}
