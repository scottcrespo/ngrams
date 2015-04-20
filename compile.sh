#!/bin/bash

javac ./TrigramCount.java
javac ./Trigram.java
javac ./TestTrigram.java
jar cf tgc.jar Trigram*.class