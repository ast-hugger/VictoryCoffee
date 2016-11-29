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

## Branches

The commit tree currently contains the following five implementation
experiments spread across four branches.

The AllWrapped branch is the implementation approach where every
Newspeak value is represented as a single JVM Object reference, with
integers always wrapped. It is simple.

Commit 58a6826, not explicitly marked as a branch, is a stable state
of the system in which every Newspeak value is represented in object
fields, temporary variables and method arguments, and on the JVM stack
as an Object/int pair. If the Object part is NsObject.UNDEFINED, then
the value is an integer and the int part is holding its value.  Method
return values are always objects. If a method returns an int, it
returns it wrapped in PrimitiveReturnValue.

The IntReturnStackSameOrder branch is a derivative of commit 58a6826
in which instead of wrapping an int to be returned from a method, the
implementation pushes the int on a thread-local stack of ints and
returns an NsObject.UNDEFINED to indicate that the value should be
popped from the stack of ints by the sender.

The IntReturnStack branch is another derivative of commit 58a6826
similar to the above, but in which the order of Object/int parts
of values on the JVM stack is reversed so the int part is pushed first.
This permits a more streamlined comparison of the Object part with
NsObject.UNDEFINED, but requires more work to adapt the arguments
at the call sites to implementation method signatures.

The default branch is a mostly functional and most likely dead-end
attempt to translate code to CPS to allow "returning" an Object/int
pair from message sends. It is most likely dead-end because there
doesn't appear to be a way to bind arguments to MethodHandles without
creating wrapper objects on ints, which defeats the purpose of this
whole tour de force.

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