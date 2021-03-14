package com.willjsporter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GameOfLife {

    private final Set<Cell> livingCells;

    public GameOfLife(Collection<Cell> initialLivingCells) {
        this.livingCells = new HashSet<>(initialLivingCells);
    }

    public Set<Cell> getLivingCells() {
        return livingCells;
    }

    public void tick() {
        final Set<Cell> cellsToRemove = livingCells.stream()
            .filter(this::cellShouldDie)
            .collect(Collectors.toSet());
        livingCells.removeAll(cellsToRemove);
    }

    private long countNeighbours(Cell cell) {
        return this.livingCells.stream()
            .filter(livingCell -> Math.abs(livingCell.getX() - cell.getX()) <= 1)
            .filter(livingCell -> Math.abs(livingCell.getY() - cell.getY()) <= 1)
            .count() - 1;
    }

    private boolean cellShouldDie(Cell cell) {
        final long neighbourCount = countNeighbours(cell);
        return neighbourCount < 2 || neighbourCount > 3;
    }
}
