JAVAC = javac
JAVA = java
JFLAGS = -d bin -sourcepath src

SRCS = $(wildcard src/*.java)
CLASSES = $(SRCS:.java=.class)

all: $(CLASSES)

%.class: %.java
	$(JAVAC) $(JFLAGS) $<

jar: all
	echo 'Main-Class: CLI' > manifest.txt
	jar cvfm MoneyBuddy.jar manifest.txt -C bin .
	rm -f manifest.txt

run: all
	$(JAVA) -cp bin CLI

clean:
	rm -f bin/*.class
