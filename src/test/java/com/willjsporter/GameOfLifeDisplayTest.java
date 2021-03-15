package com.willjsporter;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class GameOfLifeDisplayTest {

    private static int TEST_GRID_SIZE = 5;

    @Test
    public void displayGameGridShouldReturnBlankForEveryCell_whenEmptyGame() {
        final GameOfLifeDisplay testGameOfLifeDisplay = new GameOfLifeDisplay(new GameOfLife(Collections.emptySet()), TEST_GRID_SIZE);
        for(int i = 0; i < TEST_GRID_SIZE; i ++) {
            for (int j = 0; j < TEST_GRID_SIZE; j++) {
                assertThat(testGameOfLifeDisplay.displayGameGrid()[i][j], is('_'));
            }
        }
    }
}