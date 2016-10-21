# Victory Coffee #

An implementation of Newspeak on the JVM. No truffles, just raw invokedynamic.

This is a proof-of-concept prototype for the subset of Newspeak more or less as it existed at Cadence in late 2008. It has class nesting but not mixins. It does implement proper closures we did not have in 2008. The focus at this point is at the core compilation scheme. Runtime is rudimentary at best, just enough to run some basic test examples. The following are not implemented because they are trivial: subclassing, class initializers, slot initializers.

The AllWrapped branch is the implementation approach where every Newspeak value is represented as a single JVM Object reference, with integers always wrapped. It is simple.

The default branch is a current work in progress aiming at using primitive ints for integers, by using parallel data paths. Each Newspeak value is represented as a pair of JVM values, an Object and an int. When the Object value is an UNDEFINED constant, the Newspeak value is represented by the int, otherwise it's represented by the object. The tricky part are message send return values. A straightforward implementation with calls and returns would still require wrapping. The only way to avoid that seems to be the CPS. Or perhaps some clever type feedback optimization I haven't figured out yet.