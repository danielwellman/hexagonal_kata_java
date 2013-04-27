package com.danielwellman.birthdaygreetings.domain;

import com.danielwellman.birthdaygreetings.conveniences.StringUtils;

public class EmailAddress {
    private final String email;

    public EmailAddress(String email) {
        if (null == email || StringUtils.isBlank(email)) throw new IllegalArgumentException("Email cannot be null");
        this.email = email;
    }

    @Override
    public String toString() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailAddress that = (EmailAddress) o;

        //noinspection RedundantIfStatement
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }
}
