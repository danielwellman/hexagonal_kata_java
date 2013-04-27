package com.danielwellman.birthdaygreetings.conveniences;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Sets {
    @SafeVarargs
    public static <T> Set<T> hashSet(T... items) {
        HashSet<T> set = new HashSet<>();
        Collections.addAll(set, items);
        return set;
    }
}
