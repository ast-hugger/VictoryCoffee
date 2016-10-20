# Victory Coffee #

An implementation of Newspeak on the JVM. No truffles, just pure invokedynamic.

This is a proof-of-concept prototype implementing a subset of Newspeak
more or less as it existed at Cadence in late 2008. It has class
nesting but not mixins. It does implement proper closures.

This branch is the first cut of the implementation, with all Newspeak values
represented as Java Objects, and all integers wrapped.