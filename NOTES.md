# Implementation Notes, Thoughts, and Questions


## Thoughts

* While implementing the first end-to-end test, I wondered some questions:
    * How shall I write the dates?  Numbers?  Create some enums?
    * Shall the dates use my own date class?  A third-party Jodatime date?
    * Shall I name the part that knows today's date the "FakeCalendar", or "FakeDateSource" or something else?
* I made an effort to name the thing that creates the file list the BirthdayList, *not* encoding the name of the
  file system in it.  I figured that the test didn't *really* want to know that this was using the file system,
  but instead keep things named in terms of the domain.  One day we might swap out the file system with a
  database implementation and this test feels like it need not know these details.
  * The same went for the greetings delivered bit -- I chose not to put anything about e-mail in there, and
    instead kept it in terms of greetings delivered.
* I started with a test that proves that no emails were sent.  I thought this would help me drive out some of the
  details of the file system and domain without having to dive into the email component.  However, I think that
  this means I could make this test pass by not implementing *anything*.  That feels funny to me.  I weigh this against
  building a first test that would send an e-mail, and I thought -- hmm, this could be too much of a step - in fact,
  it would build out the whole application!    ... I think I'll resolve it by having only one entry in the
  birthday list and make sure it sends it to that person.  That way I can add the logic for multiple steps entries
  and skipped people later.
* I switched the Notifier from taking a String emailAddress to a Person.  This coupling means that the notifiers
  now have full access to the new Person object.  I could have had the Notifier take an Address (email, or in the
  future, some other social networking address) and a Name.  That is another direction of decoupling that may be
  worth exploring.
* When implementing the EmailNotifier, I wasn't sure whether to implement it and use an integration test or a unit test.
  If I picked unit, I thought that I could defer the actual SMTP internals to a collaborator.  Then I thought,
  "Would that SMTP gateway be a peer or an internal?"  If it was a peer, I'd probably pass in a mock and
  write a unit test.  If it were an internal, then there would be no need to write a separate test for that object,
  and the EmailNotifierTest would be an integration test that would talk to some form of SMTP server.
  I decided on going the unit route, but then I thought "What will the adapter interface look like - written in
  terms of the application domain?"  Such an interface probably would not have SMTP details expressed in the
  interface, and would be written in terms of the domain.  I am puzzled, but am trying to start here with a
  PostOffice interface and an SMTPPostOffice implementation for production.
* The Email - it's a class of values.  Might that want a deliver(PostOffice) method instead?
* I switched the end to end test to use a real EmailNotifier but instead use a fake PostOffice, rather than a fake
  notifier.  The downside of this is that "email" is now a concept at the edge of the domain -- an adapter is
  made specifically for e-mail.  I think I'm OK with this since email is in fact a domain concept of the person,
  so it makes some sense that email is known inside the domain -- but sending mail requires an adapter.  We keep this
  inside the domain language by using "PostOffice" instead of SMTPSender or something - thanks J. B. Rainsberger!
* Looking at the Email and PostOffice class, I wondered about having a method on E-mail:
    void deliver(PostOffice postOffice);
  Which puts some behavior on the Email object, which is right now simply data fields.  I'm not strongly sold on it
  now, though, so taking a note and moving on.
* What happens when the passed date format is invalid, at parse time?  How should the system respond to errors in
  input data?

## Ideas

* End to end tests may want to use some form of SMTP simulator if we'd like to get more confidence that we're
  correctly communicating with some form of SMTP server.  At the start, I chose to supply a fake implementation that
  has nothing to do with email or SMTP.

## Guiding Principles

* End to end tests should avoid being too coupled to production abstractions.  We want to avoid situations where
  the test behaves correctly using the production code, except the production code is actually doing the wrong thing,
  causing the test to incorrectly pass.  There seem to be some fuzzy barriers on this though...
  * For example, the `Date` class, which is a wrapper around the third-party Jodatime library.  The tests do need
    to invoke the mailer given a specific date, hence this class must be used.  Theory: Sharing simple value objects
    is not as risky.