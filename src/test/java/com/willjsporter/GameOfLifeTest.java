package com.willjsporter;

import org.junit.jupiter.api.Test;

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

}