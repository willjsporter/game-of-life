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
        final GameOfLife testGameOfLife = new GameOfLife(Set.of(Pair.of(1,2), Pair.of(-2,7)));
        assertThat(testGameOfLife.getLivingCells(), is(Set.of(Pair.of(1,2), Pair.of(-2,7))));
    }

    @Test
    public void shouldInitialiseWithLivingCells_withListWithDuplicates() {
        final GameOfLife testGameOfLife = new GameOfLife(List.of(Pair.of(1,2), Pair.of(-2,7), Pair.of(1,2)));
        assertThat(testGameOfLife.getLivingCells(), is(Set.of(Pair.of(1,2), Pair.of(-2,7))));
    }

    @Test
    public void tickShouldClearBoard_ifInitialBoardIsEmpty() {
        final GameOfLife testGameOfLife = new GameOfLife(Collections.emptySet());
        testGameOfLife.tick();
        assertThat(testGameOfLife.getLivingCells(), is(Collections.emptySet()));
    }

    @Test
    public void tickShouldClearBoard_ifOnlyOneLivingCell() {
        final GameOfLife testGameOfLife = new GameOfLife(Set.of(Pair.of(1,2)));
        testGameOfLife.tick();
        assertThat(testGameOfLife.getLivingCells(), is(Collections.emptySet()));
    }

    @Test
    public void tickShouldClearBoard_ifMultipleLivingCells_thatAreNotNeighbours() {
        final GameOfLife testGameOfLife = new GameOfLife(Set.of(Pair.of(-2, -2), Pair.of(0,0), Pair.of(2,2)));
        testGameOfLife.tick();
        assertThat(testGameOfLife.getLivingCells(), is(Collections.emptySet()));
    }

    @Test
    public void tickShouldClearBoard_ifMultipleLivingCells_withNoMoreThanOneNeighbour() {
        final GameOfLife testGameOfLife = new GameOfLife(Set.of(Pair.of(-2, -2), Pair.of(1, 1), Pair.of(2,2)));
        testGameOfLife.tick();
        assertThat(testGameOfLife.getLivingCells(), is(Collections.emptySet()));
    }

    @Test
    public void tickShouldKeepCellsAlive_forCellsWith2Neighbours() {
        Pair cellWithNoNeighbours = Pair.of(-5, -5);
        Pair cellWithOneNeighbour1 = Pair.of(1,1);
        Pair cellWithOneNeighbour2 = Pair.of(1,3);
        Pair cellWithTwoNeighbours = Pair.of(1,2);

        final GameOfLife testGameOfLife = new GameOfLife(Set.of(cellWithNoNeighbours, cellWithOneNeighbour1, cellWithOneNeighbour2, cellWithTwoNeighbours));
        testGameOfLife.tick();
        assertThat(testGameOfLife.getLivingCells(), is(Set.of(cellWithTwoNeighbours)));
    }

    @Test
    public void tickShouldKeepCellsAlive_forCellsWith3Neighbours() {
        Pair cellWithOneNeighbour1 = Pair.of(1,1);
        Pair cellWithOneNeighbour2 = Pair.of(1,3);
        Pair cellWithOneNeighbour3 = Pair.of(3,3);
        Pair cellWithThreeNeighbours = Pair.of(2,2);

        final GameOfLife testGameOfLife = new GameOfLife(Set.of(cellWithOneNeighbour1, cellWithOneNeighbour2, cellWithOneNeighbour3, cellWithThreeNeighbours));
        testGameOfLife.tick();
        assertThat(testGameOfLife.getLivingCells(), is(Set.of(cellWithThreeNeighbours)));
    }

}