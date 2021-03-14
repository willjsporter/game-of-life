package com.willjsporter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GameOfLife {

    private final Set<Pair> livingCells;

    public GameOfLife(Collection<Pair> initialLivingCells) {
        this.livingCells = new HashSet<>(initialLivingCells);
    }

    public Set<Pair> getLivingCells() {
        return livingCells;
    }

    public void tick() {
        final Set<Pair> cellsToRemove = livingCells.stream()
            .filter(cell -> countNeighbours(cell) < 2)
            .collect(Collectors.toSet());
        livingCells.removeAll(cellsToRemove);
    }

    private long countNeighbours(Pair pair) {
        return this.livingCells.stream()
            .filter(cell -> Math.abs(cell.getX() - pair.getX()) <= 1)
            .filter(cell -> Math.abs(cell.getY() - pair.getY()) <= 1)
            .count() - 1;
    }
}
