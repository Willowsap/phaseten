
JFLAGS = -d bin -sourcepath src

default:
	@echo "make target"
	@echo "targets: clean, compile, demo"


# don't use -f option with rm unless you know what you're doing!
clean:
	rm -f **/*.class
	rm -f **/*~

compile:
	javac $(JFLAGS) src/*.java
