## CreditSuisse Coding Assignment
### Pre Requisites
In order to run this program you will need to first have Java 1.8 installed on your machine and have previously set up 
gradle so that you can use the gradle command.

You can check for these requirements by using the commands:

```gradle -v```

```java -version```

If they are not already installed please go to the [link](https://www.tutorialspoint.com/gradle/gradle_installation.htm) 
and follow the instructions to setup Gradle in order to run this program.

### Running The Program
In order to run the code you will need to either clone the repository locally or you can download the zip file into 
whichever directory you prefer and UnZip it.


After this you simply need to cd into **<Chosen Directory>**/credit-suisse-assignment and run the command 
```gradle run --args='logfile.txt'``` which will build and run the program with the log file to be parsed being 
**logfile.txt** which is a sample file I created based on the example given in the assignment.

In order to run with a different logfile you just need to move the log file into the credit-suisse-assignment directory and replace 
**logfile.txt** with the new file name in the aforementioned command or give a relative path to the logfile that you 
would like to be parsed.

Logging output from the program can be found in Logoutput.txt

 ### Program Performs :
 ==> Take the path to logfile.txt as an input argument.
 Parse the contents of logfile.txt. 
 Flag any long events that take longer than 4ms.
 Write the found event details to file-based HSQLDB (http://hsqldb.org/) in the working folder
 The application creates a new table if necessary and store the following values:
 Event id
 Event duration
 Type and Host if applicable
 Alert (true if the event took longer than 4ms, otherwise false)
 
### Notes
* It is simple application which performs necessary operations.
* I didnot get enough time to write junit test cases but I am still working on it using Junit and mockito.
* As in the assignment description - what the program should do- it just says to "Flag any long events that take longer than 4ms and Write
 the found event details to file-based HSQLDB in the working folder. So I implemented a ```SELECT * FROM Events``` 
 statement which reads all table entries and logs the alerts in debug mode. As well as this I also added a 
 ```DELETE FROM Events``` method to clear the table before closing the connection as to avoid overlap of different files.
* Since I am using log4j for logging I went ahead and assumed that the **log4j.properties** file in **main/resources** 
would set the logging mode automatically based on the server, i.e. Test would be DEBUG etc.
* Exceptions are handled properly.
* Connections are closed properly.Memory leaks are handled.


