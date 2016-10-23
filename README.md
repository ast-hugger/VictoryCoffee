# Victory Coffee #

>'Half a second,' she said. 'Just let me show you what I've brought. Did you bring some of that filthy Victory Coffee? I thought you would. You can chuck it away again, because we shan't be needing it. Look here.' 

An implementation of Newspeak on the JVM. No truffles, just raw
invokedynamic.

This is a proof-of-concept prototype for the subset of Newspeak more
or less as it existed at Cadence in late 2008. It has class nesting
but not mixins. It does implement proper closures we did not have in 2008.
The focus at this point is at the core compilation
scheme. Runtime is rudimentary at best, just enough to run some basic
test examples. The following are not implemented because they are
trivial: subclassing, class initializers, slot initializers.

The AllWrapped branch is the implementation approach where every
Newspeak value is represented as a single JVM Object reference, with
integers always wrapped. It is simple.

The default branch is current work in progress aiming at using
primitive ints for integers, by using parallel data paths. Each
Newspeak value is represented as a pair of JVM values, an Object and
an int. When the Object value is an UNDEFINED constant, the Newspeak
value is represented by the int, otherwise it's represented by the
object. The tricky part are message send return values. A
straightforward implementation with calls and returns would still
require wrapping. The only way to avoid that seems to be the CPS. Or
perhaps some clever type feedback optimization I haven't figured out
yet.

## Prerequisites, Building, and Running ##

The project can be built using the included Ant build file.

This repository is also an IntelliJ project and can be open directly
in IntelliJ. The `$LIBRARIES$` variable will need to be set to
point at the directory with third party frameworks (see below).
IntelliJ is supposed to prompt for that the first time you open
the project.

The core module (Newspeak) depends on ANTLR4 and ASM, which need
to be downloaded separately. The current setup expects a directory
somewhere containing the following:

  * antlr-4.5.3-complete.jar
  * asm-5.1/lib/asm-5.1.jar

In the `build.xml` file, fix the `{lib.dir}` property to point at that
directory.

Note that asm-5.1.jar is not in the library directory itself. You get
this layout when downloading and unpacking the complete (with sources)
distribution of ASM. If the jar is in the library directory itself,
fix the paths in `build.xml` which reference it.

Run

    ant jar

to compile everything and build a compiler app. Then run

    java -jar build/VictoryCoffee.jar fib.ns

to compile and run the Fibonacci example. Which is about as far as the included runtime
library will take you.