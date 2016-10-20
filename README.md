# Victory Coffee #

An implementation of Newspeak on the JVM.

This is a proof-of-concept prototype implementing a subset of Newspeak
more or less as it existed at Cadence in late 2008. It has class
nesting but not mixins.

The AllWrapped branch is the implementation approach where every Newspeak value is represented as a single JVM Object reference, with integers always wrapped. It is simple and wasteful.

The default branch is a current work in progress attempt at a more sophisticated approach with integers implemented as ints. Each Newspeak value is represented as a pair of JVM values, an Object and an int. When the Object value is an UNDEFINED constant, the Newspeak value is represented by the int, otherwise it's represented by the object. The tricky part are return values of message sends. The only way to avoid wrapping seems to be the CPS.