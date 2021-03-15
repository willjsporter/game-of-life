package com.willjsporter;

import java.util.Arrays;

class GameOfLifeDisplay {

    private final GameOfLife gameOfLife;
    private final char[][] grid;

    public GameOfLifeDisplay(GameOfLife gameOfLife, int gridSize) {
        this.gameOfLife = gameOfLife;
        this.grid = new char[gridSize][gridSize];
        for(int i = 0; i < gridSize; i ++) {
            Arrays.fill(grid[i], '_');
        }
    }

    public char[][] displayGameGrid() {
        return this.grid;
    }
}
