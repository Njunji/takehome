# takehome

## Programming Language
- **Language**: Java 17
- **Framework**: Spring Boot 3.3.2
- **Build Tool**: Maven

## Project Descriptions and Solutions

 Question 1: String Validator
The checks  for each string for valid characters using a HashSet and scans for consecutive repeats. It tracks the longest valid string and returns a DTO with the result, message and validation details for each string

 Question 2: Sum Combinations
uses recursive backtracking to find all subsets summing to the target, sorting the input array to ensure consistent combination order. Returns a DTO with count, message and combinations

 Question 3: Array Reduction
iteratively applies the operation on indices where `a[i] >= a[i-1]`, checking for progress and negative values. Returns a DTO with result (1 or 0) and message

Question 4
I used a LinkedHashMap to maintain insertion order and count occurrences in one pass, then find the first entry with a count of 1
REST endpoint: POST /api/products/unique.

Question 5: 
The service finds the minimum value, collects indices of all minimums, and computes the smallest distance between consecutive indices.

Question 6: 
I normalized the input (lowercase, remove punctuation), counted word frequencies with a HashMap, sorted by frequency (descending), and applied a final alphabetical sort for the top three.
Time complexity: O(n log n), Space complexity: O(n).
REST endpoint: POST /api/words/most-common.

Question 7
I converted the input array to a linked list, normalized n using modulo, found the new tail and head, and reconnected the list.

REST endpoint: POST /api/list/rotate.

## Prerequisites
- Java 17 JDK
- Maven 3.8.0 or higher
- IDE (optional, e.g., IntelliJ IDEA, Eclipse)
- curl or Postman (for testing REST endpoints)

## How to Run the Code

mvn spring-boot:run

**TEST CASES**

mvn clean test
