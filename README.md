# Credit Suisse Assignment

## Description
This is a SpringBoot application takes a single command line argument, path to a file which contains information about custom-build server logs and application logs. On executing application, it converts logs into events and populate HSQL database table named `event` under `tempdb` folder.

Every event has 2 entries in the file. One entry when the event was started and another when the event was finished. The entries in the file have no specific order (a finish event could occur before a start event for a given id)

Moreover, events will be flagged/alerted with true in HSQL database if it took longer than 4ms otherwise false.


## Requirements
- Java 1.8
- Gradle 7.2
- Unix environment


## How to Build / Test / Run
- Build
    ```
    cd PROJECT_DIRECTORY
    ./gradlew clean build
    ```
- Test
    ```
    cd PROJECT_DIRECTORY
    ./gradlew test
    ```
- Run
    ```
    cd PROJECT_DIRECTORY
    java -jar build/libs/credit-suisse-assignment-0.0.1-SNAPSHOT.jar "logfile.txt"
    ```






