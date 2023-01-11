#!/bin/bash

# Input example if the user does not send information a menu will be presented
java -jar jonathan-jorge-clock-challenge.jar "06 00" "00 00" 23:59

# Command to run test
gradle test

## Jacoco Coverage
gradle jacocoTestReport