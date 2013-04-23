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
* In creating allPeople() in the PersonRegistry, I noted that it now returns a Set<Person>.  I'm thinking of the bits
  in GOOS where they mention that returning a generic collection is possibly a smell or sign of a missing abstraction.
  I might push this early and see.
  As a quick experiment, I tried rewriting the code in BirthdayService to work with a People object rather than a
  Set<Person>.  My initial experiment still felt procedural, though hides the internal collection of people:

          People people = personRegistry.allPeople();
          people.forEach(new Function<Person, Void>() {
              public void apply(Person person) {
                  notifier.notify(person);
              }
          });

  I weighed this against creating a method on People called
      void notify(Notifier notifier);
  But I thought this felt like an odd coupling -- now People have a special feature to notify many folks?  This
  initially felt like a strange coupling to me.
* I split up the adapter portion of the PersonRegistry.  At first I thought I'd implement everything - the file
  loading, parsing, and filtering - in one object.  That felt like a lot of responsibilities for one class, so
  I split it up the responsibilities like so:
    - The in-memory implementation of the PersonRegistry has a PeopleSource that can get the list of people.
      That leaves this registry implementation's job to filter the appropriate people given their birthdays.
    - An integration test checks the PeopleSource, which loads a file, parses each line, and returns a collection of
      People.  This object may still have responsibilities that could be split up, but for now I'm OK with it living
      in all in one class.  As an exercise, I may try to split up the parsing a bit more into a more declarative
      format.
    - Therefore, the in-memory implementation of the PersonRegistry can be unit tested, mocking out the collaborating
      PeopleSource.  This implementation is essentially a filter.
    - It still feels appropriate to me that the PersonRegistry lives in the adapter layer, since I can imagine
      implementing a SQL or other database adapter which might implement the filtering using the tools of that
      datasource (SQL where clauses, etc.)
    - I can imagine implementing what J. B. Rainsberger calls "contract tests" for the PersonRegistry to make sure
      the contracts are obeyed by all implementations.
* Thought: When considering how to build the "handle leap day birthdays" story, I at first thought I would put the
  comparison in the PersonRegistry implementation.  Then I realized that this was domain logic, and seemed like it
  could be implemented in the domain layer.  Said another way, if we added new adapter implementations, I would be
  hesitant to reimplement this logic every single time - it feels like it would be duplication, and easy to forget
  that this logic needed to be implemented.  (One way around this could be to pass some sort of object to the
  Registry that handles this responsibility).  This meant that the PersonRegistry implementation (in-memory with a file
  system data source) would really be "find all people with a birthday on the given date", and something in the
  domain figures out the appropriate date to ask for, given the current year.
  * I brainstormed some ideas of where this comparison logic could live:
      * Directly in the BirthdayService.
      * On the Person.  Something like "Give me your effective birthday given this year/date".
      * In some sort of strategy used by the BirthdayService.
    I am leaning towards having it on the Person, though that makes me think I might have a value object that I'd
    want to mock in my BirthdayService test.  That is, I'll probably want to make sure that the service asks
    something for the effective date, and then looks up the list of people in the registry.  I'll consider this...
  * Thinking more about this, I realized that it probably doesn't make sense on a Person.  Why?
    * The BirthdayService knows nothing about People - only dates (passed in as a parameter), the registry
      and the notifier.
    * The Person is consulted in the InMemoryPersonRegistry; each Person is filtered against the given birthday.
      However, for another adapter implementation like a database, you might not have any Person instances loaded.
      I'm wondering if we created some form of Criteria object and passed that in (and it knew how to filter for each
      appropriate adapter) what that might look like.
* Birthday and Date domain notes -- For checking birthday, we only want to know if the given date's month and day
  are the same as the person's birthday.  There may be a case for a new type here - a MonthAndDay.
* As I tried to implement this "leap birthday" story, I wondered: "Is it OK to implement this business logic in the
  adapter layer?"  While it does seem like a domain concept, it does feel like it should be possible to prevent
  most adapter implementations from botching this case by creating a contract test.

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