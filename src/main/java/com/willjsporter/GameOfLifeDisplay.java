package com.willjsporter;

import java.util.Arrays;

class GameOfLifeDisplay {

    private final GameOfLife gameOfLife;
    private final char[][] grid;

    public GameOfLifeDisplay(GameOfLife gameOfLife, int gridSize) {
        this.gameOfLife = gameOfLife;
        this.grid = new char[gridSize][gridSize];
    }

    public char[][] displayGameGrid() {
        setAllGridCellsBlank();
        markLivingCells();
        return this.grid;
    }

    private void setAllGridCellsBlank() {
        for (char[] column : this.grid) {
            Arrays.fill(column, '_');
        }
    }

    private void markLivingCells() {
        this.gameOfLife.getLivingCells()
            .forEach(liveCell -> this.grid[liveCell.getX()][liveCell.getY()] = 'X');
    }
}
