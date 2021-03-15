package com.willjsporter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.IntStream.rangeClosed;

public class GameOfLife {

    private final Set<Cell> livingCells;

    public GameOfLife(Collection<Cell> initialLivingCells) {
        this.livingCells = new HashSet<>(initialLivingCells);
    }

    public Set<Cell> getLivingCells() {
        return livingCells;
    }

    public void tick() {
        final Set<Cell> cellsToAdd = getCellsToAdd();

        final Set<Cell> cellsToRemove = livingCells.stream()
            .filter(this::cellShouldDie)
            .collect(Collectors.toSet());
        livingCells.removeAll(cellsToRemove);

        livingCells.addAll(cellsToAdd);
    }

    private Set<Cell> getCellsToAdd() {
        final Set<Cell> allNeighbours = livingCells.stream()
            .flatMap(this::getNeighbouringCells)
            .distinct()
            .filter(neighbour -> countLivingNeighbours(neighbour) == 3)
            .collect(Collectors.toSet());
        allNeighbours.removeAll(livingCells);
        return allNeighbours;
    }

    private Stream<Cell> getNeighbouringCells(Cell cell) {
        return rangeClosed(cell.getX() - 1, cell.getX() + 1)
            .boxed()
            .flatMap(x -> rangeClosed(cell.getY() - 1, cell.getY() + 1)
                .mapToObj(y -> Cell.at(x, y)))
            .filter(neighbour -> !neighbour.equals(cell));
    }

    private long countLivingNeighbours(Cell cell) {
        return this.livingCells.stream()
            .filter(livingCell -> Math.abs(livingCell.getX() - cell.getX()) <= 1)
            .filter(livingCell -> Math.abs(livingCell.getY() - cell.getY()) <= 1)
            .filter(neighbour -> !neighbour.equals(cell))
            .count();
    }

    private boolean cellShouldDie(Cell cell) {
        final long neighbourCount = countLivingNeighbours(cell);
        return neighbourCount < 2 || neighbourCount > 3;
    }
}
