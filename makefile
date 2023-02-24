JCC = javac

CLASSES = \
Task1.java\
Task2.java\
Task3.java\
Task4.java\

default: classes

classes: $(CLASSES:.java=.class)

Task1.class: Task1.java
        $(JCC) Task1.java

Task2.class: Task2.java
        $(JCC) Task2.java

Task3.class: Task3.java
        $(JCC) Task3.java

Task4.class: Task4.java
        $(JCC) Task4.java

clean:
        rm -f *.class

run1:   Task1.class
        java Task1

run2:   Task2.class
        java Task2

run3:   Task3.class
        java Task3

run4:   Task4.class
        java Task4