package com.willjsporter;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class GameOfLifeDisplayTest {

    private static int testGridSize;

    @Test
    public void displayGameGridShouldReturnBlankForEveryCell_whenEmptyGame() {
        testGridSize = 5;
        final GameOfLifeDisplay testGameOfLifeDisplay = new GameOfLifeDisplay(new GameOfLife(Collections.emptySet()), testGridSize);

        for(int i = 0; i < testGridSize; i ++) {
            for (int j = 0; j < testGridSize; j++) {
                assertThat(testGameOfLifeDisplay.displayGameGrid()[i][j], is('_'));
            }
        }
    }

    @Test
    public void displayGameGridShouldReturnXForEveryCell_InGameOfLife_inScope() {
        testGridSize = 3;
        final GameOfLife gameOfLife = new GameOfLife(Set.of(Cell.at(0,1), Cell.at(1,2), Cell.at(2,1)));
        final GameOfLifeDisplay testGameOfLifeDisplay = new GameOfLifeDisplay(gameOfLife, testGridSize);

        assertThat(testGameOfLifeDisplay.displayGameGrid()[0][0], is('_'));
        assertThat(testGameOfLifeDisplay.displayGameGrid()[0][1], is('X'));
        assertThat(testGameOfLifeDisplay.displayGameGrid()[0][2], is('_'));
        assertThat(testGameOfLifeDisplay.displayGameGrid()[1][0], is('_'));
        assertThat(testGameOfLifeDisplay.displayGameGrid()[1][1], is('_'));
        assertThat(testGameOfLifeDisplay.displayGameGrid()[1][2], is('X'));
        assertThat(testGameOfLifeDisplay.displayGameGrid()[2][0], is('_'));
        assertThat(testGameOfLifeDisplay.displayGameGrid()[2][1], is('X'));
        assertThat(testGameOfLifeDisplay.displayGameGrid()[2][2], is('_'));
    }

    @Test
    public void displayGameGridShouldIgnoreCells_InGameOfLife_outOfScope() {
        testGridSize = 2;
        final GameOfLife gameOfLife = new GameOfLife(Set.of(Cell.at(100, 100), Cell.at(0, 1)));
        final GameOfLifeDisplay testGameOfLifeDisplay = new GameOfLifeDisplay(gameOfLife, testGridSize);

        assertThat(testGameOfLifeDisplay.displayGameGrid()[0][0], is('_'));
        assertThat(testGameOfLifeDisplay.displayGameGrid()[0][1], is('X'));
        assertThat(testGameOfLifeDisplay.displayGameGrid()[1][0], is('_'));
        assertThat(testGameOfLifeDisplay.displayGameGrid()[1][1], is('_'));
    }
}