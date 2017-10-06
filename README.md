# Victory Coffee

>'Half a second,' she said. 'Just let me show you what I've brought. Did you bring some of that filthy Victory Coffee? I thought you would. You can chuck it away again, because we shan't be needing it.' 

A prototype implementation of a subset of Newspeak on the JVM.

This is a proof-of-concept prototype for the subset of Newspeak more or less as
it existed at Cadence in late 2008. It has class nesting but not mixins. It does
implement proper closures we did not have in 2008. The focus was at the core
compilation scheme. Runtime is rudimentary at best, just enough to run some
basic test examples. The following are not implemented because they are trivial:
subclassing, class initializers, slot initializers.

## Branches

The commit tree contains the following five implementation experiments spread
across four branches. The experiments differ in their approach to
representing Newspeak values.

The `AllWrapped` is the earliest implementation in which all Newspeak values are
represented as objects, with integers always wrapped.

The changeset tagged with `ObjectIntPair` represents every Newspeak value
as an `Object`/`int` pair. The value is represented by the Object part if it's
not `NsObject.UNDEFINED` and by the `int` part otherwise.  Method returns
are always `Objects`. To return an `int`, a method wraps it in an object.

The `IntReturnStackSameOrder` branch is a derivative of `ObjectIntPair` which
avoids the self-defeating wrapping above by pushing the `int` onto a
thread-local stack and returning `NsObject.UNDEFINED` to indicate that
the value should be popped from the stack of ints.

The `IntReturnStack` branch is another derivative of commit 58a6826
similar to the above, but in which the order of Object/int parts
of values on the JVM stack is reversed so the int part is pushed first.
This permits a more streamlined comparison of the Object part with
`NsObject.UNDEFINED`, but requires more work to adapt the arguments
at the call sites to implementation method signatures.

The `default` branch is the latest and unfinished work-in progress attempt
at a dual Object/int implementation idea. It works around the inability
to return an Object/into pair from a method call by generating the code
in continutation-passing style. CPS translator is implemented, but closures
aren't, so many tests are failing and the `fib.ns` example won't work.
Given the complexity of the implementation, it doesn't look like this
is a path worth pursuing. 


## Prerequisites and Building

The repository is an IntelliJ project. For the more recent commits
of the `AllWrapped` and `default` branches, it includes the third-
party libraries it needs and should build out of the box.

The included Ant build file is currently failing.
