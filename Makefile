JCC = javac

TASKS = \
Task1.java\
Task2.java\
Task3.java\
Task4.java\
Task5.java\

default: tasks

tasks: $(TASKS:.java=.class)

#compile all the classes
Task1.class: Task1.java
	$(JCC) Task1.java

Task2.class: Task2.java
	$(JCC) Task2.java

Task3.class: Task3.java
	$(JCC) Task3.java

Task4.class: Task4.java
	$(JCC) Task4.java

Task5.class: Task5.java
	$(JCC) Task5.java

clean:
	rm -f *.class

#commands to run each of the task
run1:   Task1.class
		java Task1

run2:   Task2.class
		java Task2

run3:   Task3.class
		java Task3

run4:   Task4.class
		java Task4

run5:   Task5.class
		java Task5