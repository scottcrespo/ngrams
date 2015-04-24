# Hadoop Map Reduce Trigram Counter

This project implements a basic trigram counter for Hadoop MapReduce. 

It implements a custom Trigram type and counts the frequency of each trigram in a
text/corpus. 

## Modules

[Trigram.java](./Trigram.java)
A custom trigram datatype

[TrigramCount.java](./TrigramCount.java)
Map reduce program to count trigram instances in a corpus

[compile.sh](./compile.sh)
Simple bash script to compile java classes and create a jar (tgc.jar)

[TrigramTest.java](./TrigramTest.java)
Some basic unit tests for the Trigram data type

[mapper_prototype.py](./mapper_prototype.py)
Python prototype of a trigram mapper



## Usage

Assuming Hadoop is installed and configured properly:

### Compile and Create Jar

`cd` to the root project directory:

`javac ./*.java`
`jar cf tgc.jar *.class`


### Add local text file to HDFS as input

/path/to/bin/hdfs dfs -put /path/to/local/file.txt /path/to/input

### Run the Job

`<path to hadoop> jar <path to local jar> <ClassName> <path to hdfs input directory> <path to hdfs output directory>`

`/path/to/bin/hadoop jar /path/to/local/jar/tgc.jar TrigramCount /path/to/hdfs/input /path/to/hdfs/output`


### Read output from HDFS

`/path/to/bin/hdfs dfs -cat /path/to/output/part-r-00000`
