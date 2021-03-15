# The Game of Life

## What is the Game of Life?
The universe of the Game of Life is an infinite two-dimensional orthogonal grid of 1x1 square cells,
each of which is in one of two possible states, alive or dead. 
Every cell interacts with its eight neighbours, which are the
cells that are horizontally, vertically, or diagonally adjacent.

Birth, life and death of cells are governed by the following four rules:

1. Any live cell with fewer than two live neighbours dies, as if caused by under-population.
2. Any live cell with two or three live neighbours lives on to the next generation.
3. Any live cell with more than three live neighbours dies, as if by overcrowding.
4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

The game starts with an initial board that is given by providing a collection of all living cells.

Iterations of the game are governed by discrete 'ticks' which take the state of the board, apply the rules based on that state and return the next iteration of the board.
The `tick` method in the `GameOfLife class` is what moves the game along to the next iteration. 

As the board is infinite, no finite board is initialised in the game itself.
However, for the purposes of viewing the board, a `GameOfLifeDisplay` class was created for display purposes.

## How to run me

Maven is required to build and run this project. Installation instructions can be found at `https://maven.apache.org/install.html`.

Once maven is installed, to install all external libraries and dependencies, and to build the project after getting the source code, run `mvn install` in the terminal.

To run the program, run 
`mvn clean compile exec:java` 
in the terminal from the home directory of the project.

##Demoing the project

The main method in `App.java` is set up so that the game will initialise with cells in a configuration called "The Glider Pattern".

Running the program will result in a number of iterations that demonstrate the Game of Life on a segment of the infinite board by printing the state of the board at each iteration to the command line.

## How to run the tests

The project was built using TDD so there are a lot of tests to try to cover various edge cases in the game.
The tests also cover the behaviour of the `GameOfLifeDisplay` class that is used to demonstrate the game in the `main` method.

The tests can be run through maven by inputting the command 
```
mvn clean test
```
