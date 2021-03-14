package com.willjsporter;

import java.util.Objects;

public class Cell {
    private final int x;
    private final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Cell at(int x, int y) {
        return new Cell(x,y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return getX() == cell.getX() &&
            getY() == cell.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
