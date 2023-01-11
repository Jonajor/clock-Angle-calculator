# Clock Angle Calculator

This project contains a jar file that can be used to calculate the angle between the hour and minute hands on a clock, based on the ISO8601 time format.

## Project Structure
- `ClockAngle`: data class that contains the properties angle, hourAngle, and minAngle.
- `ClockService`: the main class that handle the calculation of angle, extracting hour and minute and validating the input.
- `InvalidTimeFormatException`: Exception class to handle invalid time format input.
- `Menu`: class that contains the method that interact with user
- `TimeInputProvider`: interface to manage different input types
- `Main`: The entry point of the application

## Running the application

### With command line arguments

To run the jar file with parameters, use the following command:

```shell
java -jar jonathan-jorge-clock-challenge.jar "06 00" "00 00" 23:59
```

This will execute the jar file with the specified time inputs and print the resulting clock angles to the console.

### Without command line arguments

To run the jar file without parameters, use the following command:

```shell
java -jar jonathan-jorge-clock-challenge.jar
```

When executing the jar file without parameters, a menu will be displayed in the terminal for the user to enter the hour and minutes in one of the allowed formats:

```
Allowed format: 
HH MM (ex: 08 15 or 14 30)
HH:MM (ex: 08:15 or 14:30)
HH-MM (ex: 08-15 or 14-30)
HH/MM (ex: 08/15 or 14/30)
HHˆMM (ex: 08ˆ15 or 14ˆ30)
HH;MM (ex: 08;15 or 14;30)
Enter hour and minute information:

```
The resulting clock angle will be displayed in the console.

Alternatively, you can run the script.sh in the root of the project.
```shell
sh script.sh
```

### Unit tests
To run the unit tests for the project, use the following command:

```shell
gradle test
```

### Code coverage
To run the test coverage with jacoco, use the following command:

```shell
gradle jacocoTestReport
```
This will generate a code coverage report in the `build/reports/jacoco/test/html` directory.

### Dependencies
- Java 17
- Gradle 6.x (only for test and coverage)