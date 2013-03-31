# Implementation Notes, Thoughts, and Questions


## Thoughts

* While implementing the first end-to-end test, I wondered some questions:
    * How shall I write the dates?  Numbers?  Create some enums?
    * Shall the dates use my own date class?  A third-party Jodatime date?
    * Shall I name the part that knows today's date the "FakeCalendar", or "FakeDateSource" or something else?
    *
* I made an effort to name the thing that creates the file list the BirthdayList, *not* encoding the name of the
  file system in it.  I figured that the test didn't *really* want to know that this was using the file system,
  but instead keep things named in terms of the domain.  One day we might swap out the file system with a
  database implementation and this test feels like it need not know these details.
  * The same went for the greetings delivered bit -- I chose not to put anything about e-mail in there, and
    instead kept it in terms of greetings delivered.
* I stated with a test that proves that no emails were sent.  I thought this would help me drive out some of the
  details of the file system and domain without having to dive into the email component.  However, I think that
  this means I could make this test pass by not implementing *anything*.  That feels funny to me.  I weigh this against
  building a first test that would send an e-mail, and I thought -- hmm, this could be too much of a step - in fact,
  it would build out the whole application!    ... I think I'll resolve it by having only one entry in the
  birthday list and make sure it sends it to that person.  That way I can add the logic for multiple steps entries
  and skipped people later.

## Guiding Principles

* End to end tests should avoid being too coupled to production abstractions.  We want to avoid situations where
  the test behaves correctly using the production code, except the production code is actually doing the wrong thing,
  causing the test to incorrectly pass.  There seem to be some fuzzy barriers on this though...
  * For example, the `Date` class, which is a wrapper around the third-party Jodatime library.  The tests do need
    to invoke the mailer given a specific date, hence this class must be used.  Theory: Sharing simple value objects
    is not as risky.