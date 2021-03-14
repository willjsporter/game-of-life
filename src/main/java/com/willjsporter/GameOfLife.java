package com.willjsporter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class GameOfLife {

    private final Set<Pair> livingCells;

    public GameOfLife(Collection<Pair> initialLivingCells) {
        this.livingCells = new HashSet<>(initialLivingCells);
    }

    public Set<Pair> getLivingCells() {
        return livingCells;
    }

    public void tick() {
        livingCells.clear();
    }
}
