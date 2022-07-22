# Credit Suisse Assignment

## Description
This SpringBoot application takes a single command line argument, path to a file which contains information about custom-build server logs and application logs. On executing application, it converts logs into events and populate HSQL database table named `event` under `tempdb` folder.

Every event has 2 entries in the input file. One entry when the event was started and another when the event was finished. The entries in the file have no specific order (a finish event could occur before a start event for a given id)

Moreover, events will be flagged/alerted with true in HSQL database if it took longer than 4ms otherwise false.

## Sample input file containing logs
```
{"id":"scsmbstgra", "state":"STARTED", "type":"APPLICATION_LOG", "host":"12345", "timestamp":1491377495212}
{"id":"scsmbstgrb", "state":"STARTED", "timestamp":1491377495213}
{"id":"scsmbstgrc", "state":"FINISHED", "timestamp":1491377495218}
{"id":"scsmbstgra", "state":"FINISHED", "type":"APPLICATION_LOG", "host":"12345", "timestamp":1491377495217}
{"id":"scsmbstgrc", "state":"STARTED", "timestamp":1491377495210}
{"id":"scsmbstgrb", "state":"FINISHED", "timestamp":1491377495216}
```


## Sample HSQL database output (Using HSQL datbase manager)
<img width="803" alt="image" src="https://user-images.githubusercontent.com/30280454/180367255-a0e23c91-98c1-4182-9215-5d32a9f74b2d.png">


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






