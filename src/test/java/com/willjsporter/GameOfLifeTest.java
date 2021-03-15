package com.willjsporter;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class GameOfLifeTest {
//    More complex initial states are illustrated in comments.
//    Numbers indicating the initial cell layout with the value being the number of neighbours that cell has.
//    _ represents an empty cell.

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
        Cell cellWithOneNeighbour2 = Cell.at(3,3);
        Cell cellWithTwoNeighbours = Cell.at(2,2);

        final GameOfLife testGameOfLife = new GameOfLife(Set.of(cellWithNoNeighbours, cellWithOneNeighbour1, cellWithOneNeighbour2, cellWithTwoNeighbours));
        testGameOfLife.tick();
        assertThat(testGameOfLife.getLivingCells(), is(Set.of(cellWithTwoNeighbours)));
    }

    @Test
    public void tickShouldLeadToNewCell_whenEmptyCellHasExactly3Neighbours() {
//        1_1       ==>     ___
//        ___       ==>     _0_
//        1__       ==>     ___
        Cell cellWithOneNeighbour1 = Cell.at(1,1);
        Cell cellWithOneNeighbour2 = Cell.at(1,3);
        Cell cellWithOneNeighbour3 = Cell.at(3,3);

        final GameOfLife testGameOfLife = new GameOfLife(Set.of(cellWithOneNeighbour1, cellWithOneNeighbour2, cellWithOneNeighbour3));
        testGameOfLife.tick();
        assertThat(testGameOfLife.getLivingCells(), is(Set.of(Cell.at(2,2))));
    }

    @Test
    public void tickShouldKeepAliveOrCreateACell_ifLocationHas3Neighbours() {
//        1_1       ==>     _2_
//        _3_       ==>     23_
//        1__       ==>     ___
        Cell cellWithOneNeighbour1 = Cell.at(1,1);
        Cell cellWithOneNeighbour2 = Cell.at(1,3);
        Cell cellWithOneNeighbour3 = Cell.at(3,3);
        Cell cellWithThreeNeighbours = Cell.at(2,2);

        final GameOfLife testGameOfLife = new GameOfLife(Set.of(cellWithOneNeighbour1, cellWithOneNeighbour2, cellWithOneNeighbour3, cellWithThreeNeighbours));
        testGameOfLife.tick();

        Cell createdCellAt12 = Cell.at(1, 2);
        Cell createdCellAt23 = Cell.at(2, 3);
        assertThat(testGameOfLife.getLivingCells(), is(Set.of(cellWithThreeNeighbours, createdCellAt12, createdCellAt23)));
    }

    @Test
    public void tickShouldClearCells_with4Neighbours_andCreateWhere3Neighbours() {
//        1_1       ==>     _2_
//        _4_       ==>     2_2
//        1_1       ==>     _2_
        Cell cellWithOneNeighbour1 = Cell.at(1, 1);
        Cell cellWithOneNeighbour2 = Cell.at(1, 3);
        Cell cellWithOneNeighbour3 = Cell.at(3, 1);
        Cell cellWithOneNeighbour4 = Cell.at(3, 3);
        Cell cellWithFourNeighbours = Cell.at(2, 2);

        final GameOfLife testGameOfLife = new GameOfLife(Set.of(cellWithOneNeighbour1, cellWithOneNeighbour2, cellWithOneNeighbour3, cellWithOneNeighbour4, cellWithFourNeighbours));
        testGameOfLife.tick();

        Cell createdCellAt12 = Cell.at(1, 2);
        Cell createdCellAt21 = Cell.at(2, 1);
        Cell createdCellAt23 = Cell.at(2, 3);
        Cell createdCellAt32 = Cell.at(3, 2);
        assertThat(testGameOfLife.getLivingCells(), is(Set.of(createdCellAt12, createdCellAt21, createdCellAt23, createdCellAt32)));
    }

    @Test
    public void tickShouldKeepCells_with2Or3NeighboursOnly() {
//        Blend of situations
//       ___1___1_      ==>     _33______
//       234____2_      ==>     33_0__121
//       _3_1___1_      ==>     33_______

        Cell cellWithTwoNeighbours1 = Cell.at(1, 2);
        Cell cellWithThreeNeighbours1 = Cell.at(2, 1);
        Cell cellWithThreeNeighbours2 = Cell.at(2, 2);
        Cell cellWithFourNeighbours = Cell.at(3, 2);
        Cell cellWithOneNeighbour1 = Cell.at(4, 1);
        Cell cellWithOneNeighbour2 = Cell.at(4, 3);
        Cell cellWithOneNeighbour3 = Cell.at(8, 1);
        Cell cellWithTwoNeighbours2 = Cell.at(8, 2);
        Cell cellWithOneNeighbour4 = Cell.at(8, 3);

        final GameOfLife testGameOfLife = new GameOfLife(Set.of(cellWithTwoNeighbours1,
            cellWithThreeNeighbours1,
            cellWithThreeNeighbours2,
            cellWithFourNeighbours,
            cellWithOneNeighbour1,
            cellWithOneNeighbour2,
            cellWithOneNeighbour3,
            cellWithTwoNeighbours2,
            cellWithOneNeighbour4
        ));

        testGameOfLife.tick();

        Cell createdCellAt11 = Cell.at(1, 1);
        Cell createdCellAt42 = Cell.at(4, 2);
        Cell createdCellAt62 = Cell.at(7, 2);
        Cell createdCellAt82 = Cell.at(9, 2);
        Cell createdCellAt23 = Cell.at(2, 3);
        Cell createdCellAt33 = Cell.at(3, 3);

        assertThat(testGameOfLife.getLivingCells(), is(Set.of(
            cellWithTwoNeighbours1,
            cellWithTwoNeighbours2,
            cellWithThreeNeighbours1,
            cellWithThreeNeighbours2,
            createdCellAt11,
            createdCellAt42,
            createdCellAt62,
            createdCellAt82,
            createdCellAt23,
            createdCellAt33
        )));
    }
}