package com.willjsporter;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class GameOfLifeTest {

    @Test
    public void shouldInitialiseWithLivingCells_withSet() {
        final GameOfLife testGameOfLife = new GameOfLife(Set.of(Cell.at(1,2), Cell.at(-2,7)));
        assertThat(testGameOfLife.getLivingCells(), is(Set.of(Cell.at(1,2), Cell.at(-2,7))));
    }

    @Test
    public void shouldInitialiseWithLivingCells_withListWithDuplicates() {
        final GameOfLife testGameOfLife = new GameOfLife(List.of(Cell.at(1,2), Cell.at(-2,7), Cell.at(1,2)));
        assertThat(testGameOfLife.getLivingCells(), is(Set.of(Cell.at(1,2), Cell.at(-2,7))));
    }

    @Test
    public void tickShouldClearBoard_ifInitialBoardIsEmpty() {
        final GameOfLife testGameOfLife = new GameOfLife(Collections.emptySet());
        testGameOfLife.tick();
        assertThat(testGameOfLife.getLivingCells(), is(Collections.emptySet()));
    }

    @Test
    public void tickShouldClearBoard_ifOnlyOneLivingCell() {
        final GameOfLife testGameOfLife = new GameOfLife(Set.of(Cell.at(1,2)));
        testGameOfLife.tick();
        assertThat(testGameOfLife.getLivingCells(), is(Collections.emptySet()));
    }

    @Test
    public void tickShouldClearBoard_ifMultipleLivingCells_thatAreNotNeighbours() {
        final GameOfLife testGameOfLife = new GameOfLife(Set.of(Cell.at(-2, -2), Cell.at(0,0), Cell.at(2,2)));
        testGameOfLife.tick();
        assertThat(testGameOfLife.getLivingCells(), is(Collections.emptySet()));
    }

    @Test
    public void tickShouldClearBoard_ifMultipleLivingCells_withNoMoreThanOneNeighbour() {
        final GameOfLife testGameOfLife = new GameOfLife(Set.of(Cell.at(-2, -2), Cell.at(1, 1), Cell.at(2,2)));
        testGameOfLife.tick();
        assertThat(testGameOfLife.getLivingCells(), is(Collections.emptySet()));
    }

    @Test
    public void tickShouldKeepCellsAlive_forCellsWith2Neighbours() {
        Cell cellWithNoNeighbours = Cell.at(-5, -5);
        Cell cellWithOneNeighbour1 = Cell.at(1,1);
        Cell cellWithOneNeighbour2 = Cell.at(1,3);
        Cell cellWithTwoNeighbours = Cell.at(1,2);

        final GameOfLife testGameOfLife = new GameOfLife(Set.of(cellWithNoNeighbours, cellWithOneNeighbour1, cellWithOneNeighbour2, cellWithTwoNeighbours));
        testGameOfLife.tick();
        assertThat(testGameOfLife.getLivingCells(), is(Set.of(cellWithTwoNeighbours)));
    }

    @Test
    public void tickShouldKeepCellsAlive_forCellsWith3Neighbours() {
        Cell cellWithOneNeighbour1 = Cell.at(1,1);
        Cell cellWithOneNeighbour2 = Cell.at(1,3);
        Cell cellWithOneNeighbour3 = Cell.at(3,3);
        Cell cellWithThreeNeighbours = Cell.at(2,2);

        final GameOfLife testGameOfLife = new GameOfLife(Set.of(cellWithOneNeighbour1, cellWithOneNeighbour2, cellWithOneNeighbour3, cellWithThreeNeighbours));
        testGameOfLife.tick();
        assertThat(testGameOfLife.getLivingCells(), is(Set.of(cellWithThreeNeighbours)));
    }

}