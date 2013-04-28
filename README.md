# Hexagonal Kata

This is my implementation of [Matteo Vaccari's Hexagonal Kata](http://matteo.vaccari.name/blog/archives/154),
otherwise known as the birthday greetings kata.  I welcome any feedback you may have!

## Starting Points for Readers

For an overview of how the system works, look at the end-to-end test in the test/endtoend folder, see the class
`test.endtoend.birthdaygreetings.BirthdayGreetingsEndToEndTest`.

To start exploring the production code, look at the entry point Main in src/main/java at the class
`com.danielwellman.birthdaygreetings.Main`.  This class wires all the dependencies together and launches the
application.  Most of the action takes place in `BirthdayService#sendGreetings`.


## Requirements

* Java 7
* IntelliJ to load the project .iml and test run configuration

I built and tested this project in IntelliJ 12, use the included .iml and the "All Tests" run configuration to run.
I haven't yet created a build script since I did everything in IntelliJ for now.

Note that I specifically chose not to use Guava's collections and predicate library for this exercise, attempting to
see how [the Guava team's advice](https://code.google.com/p/guava-libraries/wiki/FunctionalExplained) plays out:

> Excessive use of Guava's functional programming idioms can lead to verbose, confusing, unreadable, and inefficient
> code. These are by far the most easily (and most commonly) abused parts of Guava, and when you go to preposterous
> lengths to make your code "a one-liner," the Guava team weeps.

Some bits in the `InMemoryPersonRegistry` and `PeopleSource` might benefit from some of the collections and
predicate operations, in retrospect.
