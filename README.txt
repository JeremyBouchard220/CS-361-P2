# Project 2: Nondeterministic Finite Automata

* Author: Sam Jackson, Jeremy Bouchard
* Class: CS361 Section 1
* Semester: Spring 2021

## Overview

Models a non-deterministic finite autonoma. Also converts NFA 
equivalent DFA

## Compiling and Using

Compile: in the main directory:

javac fa/nfa/NFADriver.java

To run on our test cases:

java fa.nfa.NFADriver ./test/testname.txt

## Discussion

Most of this project was simple, as it was similar to the previous
project. The hardest part, though, was the implementation of
the method that converts the NFA to the equivilant DFA. The
method uses several loops and is a very involved process, but it 
seems to work as intended.

Generally, this project went smoothly.

## Testing

We were provided with a handful of test files with input information.
By running our driver class with these files, we were able to fine tune
our program.


## Sources used

[Java Set Interface](https://docs.oracle.com/javase/7/docs/api/java/util/Set.html)
