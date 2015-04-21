# Hadoop Map Reduce Trigram Counter

This project implements a basic trigram counter for Hadoop MapReduce. 

It implements a custom Trigram type and counts the frequency of each trigram in a
text/corpus. 

## Modules

[Trigram.java](/Trigram.java)
A custom trigram datatype

[TrigramCount.java](/TrigramCount.java)
Map reduce program to count trigram instances in a corpus

[compile.sh](/compile.sh)
Simple bash script to compile java classes and create a jar (tgc.jar)

[TrigramTest.java](/TrigramTest.java)
Some basic unit tests for the Trigram data type

[mapper_prototype.py](/mapper_prototype.py)
Python prototype of a trigram mapper
