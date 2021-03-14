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
            .filter(cell -> countNeighbours(cell) < 2)
            .collect(Collectors.toSet());
        livingCells.removeAll(cellsToRemove);
    }

    private long countNeighbours(Cell cell) {
        return this.livingCells.stream()
            .filter(livingCell -> Math.abs(livingCell.getX() - cell.getX()) <= 1)
            .filter(livingCell -> Math.abs(livingCell.getY() - cell.getY()) <= 1)
            .count() - 1;
    }
}
