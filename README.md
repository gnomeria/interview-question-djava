# Some interview questions and solutions

>***These Java classes require Java 8***

The project uses Maven as its build and dependency tools, and jUnit as its
unit testing framework.

To do an automated unit tests of the classes, the system must have maven installed, and run command to run the maven surefire plugin:

- `mvn test`

This will run all tests from `src/test/java/*`

This project's questions classes solutions ( `Q2.java`, `Q3.java`, `Q3_Optimize.java` ) DOES NOT have main class, so that it's
cleaner to see the solution, and running the test classes can be used instead.

********************************

## Question 1

- Write a method that takes a `String` and returns a `Boolean`.
- It should return True if the first character is an uppercase.
- You cannot use `String` APIs.

********************************

********************************

## Question 2

- Write a solution that returns an `Integer` with the total number of files
in a given folder including any files in its’ sub-folders (if any exist)

********************************

## Question 3

- You are in a maze, and you need to find all possible path from an entrance to an exit.

- Here are the constraints:
  - The maze is represented by a **2D grid**.
  - Spots that you can step on are represented by a `0`.
  - Pits that you will fall into(aka spots that you cannot step on) are represented by a `1`.
  - The entrance is represented by a `2`.
  - The exit is represented by a `3`.
  - Each path can only have two end points; **entrance** and **exit**. You cannot use the entrance or exit more than once foreach path.
  - You have to step on every spot exactly once.
  - You can only move like a King in chess(***horizontally*** or ***vertically*** but not diagonally)

### Example:

    2 0 0 0 0
    0 0 0 0 0
    0 0 0 3 1

![1](grid1.png?raw=True "1")
![2](grid2.png?raw=True "2")
![3](grid3.png?raw=True "3")
![4](grid4.png?raw=True "4")

> The answer is 4

Your program should read from standard input with a series of integers with whitespace as delimiter. The ***first two integer*** represents the `width` and `height` of the maze. It will then befollowed by width * height more integers.Your output should be an integer which shows the total number of possiblepath

********************************