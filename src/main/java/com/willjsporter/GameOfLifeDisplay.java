package com.willjsporter;

import java.util.Arrays;

class GameOfLifeDisplay {

    private final GameOfLife gameOfLife;
    private final char[][] grid;

    public GameOfLifeDisplay(GameOfLife gameOfLife, int gridSize) {
        this.gameOfLife = gameOfLife;
        this.grid = new char[gridSize][gridSize];
    }

    public char[][] getGameGrid() {
        setAllGridCellsBlank();
        markLivingCells();
        return this.grid;
    }

    public void printGameGrid() {
        setAllGridCellsBlank();
        markLivingCells();
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid.length; j++) {
                System.out.print(this.grid[i][j]);
            }
            System.out.print("\n");
        }
        System.out.println("\n\n");
    }

    private void setAllGridCellsBlank() {
        for (char[] column : this.grid) {
            Arrays.fill(column, '_');
        }
    }

    private void markLivingCells() {
        this.gameOfLife.getLivingCells().stream()
            .filter(livingCell -> livingCell.getX() >= 0 && livingCell.getX() < grid.length)
            .filter(livingCell -> livingCell.getY() >= 0 && livingCell.getY() < grid.length)
            .forEach(liveCell -> this.grid[liveCell.getX()][liveCell.getY()] = 'X');
    }
}
