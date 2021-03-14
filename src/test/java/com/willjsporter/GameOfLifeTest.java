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

}