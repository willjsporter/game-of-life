package com.willjsporter;

import java.util.Set;

public class App {

  public static void main(String... args) {
    Set<Cell> initialGliderPatternCells = Set.of(
        Cell.at(11, 11),
        Cell.at(12, 11),
        Cell.at(13, 11),
        Cell.at(13, 12),
        Cell.at(12, 13)
    );
    final GameOfLife gameOfLife = new GameOfLife(initialGliderPatternCells);
    final GameOfLifeDisplay gameOfLifeDisplay = new GameOfLifeDisplay(gameOfLife, 25);

    for(int i = 0; i < 53; i ++) {
        gameOfLifeDisplay.printGameGrid();
        gameOfLife.tick();
    }

  }
}