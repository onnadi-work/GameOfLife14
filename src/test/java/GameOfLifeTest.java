import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameOfLifeTest {

    public static final int DEAD = 0;
    public static final int ALIVE = 1;

    @Test
    public void neighborCountReturns2Given3x1AliveGridAtCenter() {
        int expected = 2;

        int actual = new Grid(3, 1, ALIVE).neighborCount(1, 0);

        assertEquals(expected, actual);
    }

    @Test
    public void neighborCountReturns4Given3x3AliveGridWithDeadCorners_AtCenter() {
        int expected = 4;
        Grid grid = new Grid(3, 3, ALIVE);
        grid.setCell(0, 0, DEAD);
        grid.setCell(2, 0, DEAD);
        grid.setCell(0, 2, DEAD);
        grid.setCell(2, 2, DEAD);

        int actual = grid.neighborCount(1, 1);

        assertEquals(expected, actual);
    }

    @Test
    public void neighborCountReturns8Given3x3AliveGridAtCenter() {
        int expected = 8;
        Grid grid = new Grid(3, 3, ALIVE);

        int actual = grid.neighborCount(1, 1);

        assertEquals(expected, actual);
    }

    @Test
    public void nextCellReturnsAliveGivenAliveAnd2Neighbors() {
        int expected = ALIVE;

        int actual = new Grid(1, 1, ALIVE).nextCell(0, 0, 2);

        assertEquals(expected, actual);
    }

    @Test
    public void nextCellReturnsDeadGivenAliveAnd1Neighbors() {
        int expected = DEAD;

        int actual = new Grid(1, 1, ALIVE).nextCell(0, 0, 1);

        assertEquals(expected, actual);
    }

    @Test
    public void nextCellReturnsDeadGivenAliveAnd4Neighbors() {
        int expected = DEAD;

        int actual = new Grid(1, 1, ALIVE).nextCell(0, 0, 4);

        assertEquals(expected, actual);
    }

    @Test
    public void nextCellReturnsAliveGivenDeadAnd3Neighbors() {
        int expected = ALIVE;

        int actual = new Grid(1, 1, DEAD).nextCell(0, 0, 3);

        assertEquals(expected, actual);
    }

    @Test
    public void nextReturnsDeadGridWithCornersAliveGiven3x3AliveGrid() {
        int size = 3;
        Grid result = new Grid(size, size, ALIVE);
        Grid expected = new Grid(size, size, DEAD);
        expected.setCell(0, 0, ALIVE);
        expected.setCell(2, 0, ALIVE);
        expected.setCell(0, 2, ALIVE);
        expected.setCell(2, 2, ALIVE);

        Grid actual = result.next();

        assertEquals(expected, actual);

    }

    @Test
    public void nextReturnsDeadGridWithMiddlesAliveGiven3x2DeadGridWithTopRowAlive() {

        int xLength = 3;
        int yLength = 2;
        Grid expected = new Grid(xLength, yLength, DEAD);
        expected.setCell(1, 0, ALIVE);
        expected.setCell(1, 1, ALIVE);
        Grid result = new Grid(xLength, yLength, DEAD);
        result.setCell(0, 0, ALIVE);
        result.setCell(1, 0, ALIVE);
        result.setCell(2, 0, ALIVE);

        Grid actual = result.next();

        assertEquals(expected, actual);
    }


}
