# Class 15 ON 10th Feb 2024
## Task 3 Contact Management Application
### Basic Details
  1. Add Contact
  2. View All Contacts
  3. Search Contact
  4. Update and Remove Contact
  5. Export and Import Contact Information
  
### Details
    It is a CLI Application. Used TreeMap to Store Contact Information, FasterXML/jackson to Store and Load information from external text file.
  
### Hardels:
    As Jackson converts the elements as LinkedHashMap, I had to write code to convert it to Expected POJO.

## Task 2 Quiz Application
### Basic Details
   1. Add Quiz
   2. View All Quizes 
   3. Export and Import Quiz List
   4. Participate into Quiz

### Details
    it is a CLI Application. Used ArrayList to Store Contact Information, FasterXML/jackson to Store and Load Quiz Information from external text file.

### Hardels:
    As Jackson converts the elements as LinkedHashMap, I had to write code to convert it to Expected POJO.
    But this time POJO has Multiple layer of Objects it gave me a Lot of irregular errors which I had to solve like
    QuizQuestion class there are two variables like userAnswer initially it wass null so Jackson could not write it as a String same for the
    getResult method i also had return value what ever it is called or not


## Task 2 Task Manager Application
### Basic Details
1. Add Task
2. View All Task and Search Task
3. Update and Remove Task
4. Export and Import Task Information

### Details
    it is a CLI Application. Used HashMap to Store Task Information, FasterXML/jackson to Store and Load Quiz Information from external text file.

### Hardels:
    As Jackson converts the elements as LinkedHashMap, I had to write code to convert it to Expected POJO.
    But this time POJO has Multiple date . so to Export and Import date I had to Set Date Formatter to Object Mapper.