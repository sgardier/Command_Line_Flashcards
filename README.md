# 📝 Study terminal
![Release](https://img.shields.io/badge/Release-v1.0-blueviolet?style=for-the-badge)
![Language](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge)

Study your flash card from the terminal using [Sebastian Leitner's algorithm](https://en.wikipedia.org/wiki/Flashcard)!

## Summary
1. [Try it!](#try-it)
2. [Features](#features)
3. [Technical features](#technical-features)
4. [Improvements](#improvements)
5. [Credits](#credits)

## Try it!

### Run the executable (`studyterminal.jar`)
- [Download and install java](https://www.oracle.com/java/technologies/downloads/).
- Open a terminal at the root of the project directory and type `java -jar studyterminal.jar` from there.
 
### Compile and run the program
```console
javac -d bin -cp "lib/gson-2.8.6.jar" -sourcepath src src/studyterminal/Program.java
java -cp "bin:lib/gson-2.8.6.jar" studyterminal.Program
```

### Compile and run the tests
```console
javac -d bin -cp "bin:lib/gson-2.8.6.jar:lib/junit-platform-console-standalone-1.11.0-M2.jar" -sourcepath tests $(find tests -name "*.java")
java -cp "bin:lib/gson-2.8.6.jar:lib/junit-platform-console-standalone-1.11.0-M2.jar" org.junit.platform.console.ConsoleLauncher --scan-class-path
```

## Features
The algorithm used is the one developped by [Sebastian Leitner](https://en.wikipedia.org/wiki/Flashcard).<br>
Features :
- Add cards to the box.
- Remove cards from the box.
- Review the cards of the day.
- View statistics about your performances of the day.
- Store the "box" of cards for later revisions.

## Technical features
- Publish - Subscribre architecture for the updates of the statistics.
- Command architecture for the actions to be taken by the users.
- Test suite of ~80 tests using JUnit (standalone) and FakeClasses.
- Efficient collections usage for fast execution and low memory footprint.

## Improvements
- The program does not allow to simply revise all the cards of the box. The feature should not be hard to implement since it only requires to add a new Command.

## Credits
- [Simon Gardier](https://github.com/simon-gardier) (Author)
- Nicolas Hendrix (studyterminal.consoles package author)