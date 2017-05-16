### Concurrency
#### Basics

When you run a main thread from within SBT,
* SBT starts your program in a separate thread inside the SBT process
* to ensure that SBT runs the program in a separate JVM process, we need to do the following
    * `> set fork := true`
    
Starting a thread consists of 2 steps:
* we create a `Thread` object to allocate memory for stack and thread state
* we start computation by calling `start` method

#### Atomic Execution

Every object in JVM has a special entity called __intrinsic lock__ or __monitor__
* used to ensure only one thread executes some synchronized block on that object

#### Reordering

We cannot anticipate the JVM will execute the statements in the exact order of our writing.

This is mainly due to the JMM, according to this JVM is allowed to reorder statements as long as it doesn't change the serial semantics of that program in that thread.

Additionally, threads do not write all updates to main memory
* they may temporarily keep them cached in registers to optimize compile times

Hence, we should not assume that writes by one thread are immediately visible to another thread. If we want that to happen, then we must enclose within a `synchronized` block.

#### Guarded blocks

Creating a new thread is much more expensive than creating a new lightweight object

So, in live scenarios we should avoid creating new thread per request. Threads should be reused by creating a `thread-pool`.



