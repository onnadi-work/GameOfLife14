import java.util.Arrays;

public class Grid {
    private static final int DEAD = 0;
    private static final int ALIVE = 1;
    public static final int ALIVE_NEIGHBOR_COUNT_1 = 2;
    public static final int ALIVE_NEIGHBOR_COUNT_2 = 3;
    public static final int RESURRECTOR_COUNT = 3;
    int[][] cells;
    int yLength;
    int xLength;

    public Grid(int xLength, int yLength, int fill) {
        this.yLength = yLength;
        this.xLength = xLength;
        cells = new int[yLength][xLength];

        for (int y = 0; y < this.yLength; y++) {
            for (int x = 0; x < this.xLength; x++) {
                setCell(x, y, fill);
            }
        }
    }

    public int neighborCount(int x, int y) {
        return getCell(x - 1, y - 1) + getCell(x, y - 1) + getCell(x + 1, y - 1) +
                getCell(x - 1, y) + getCell(x + 1, y) +
                getCell(x - 1, y + 1) + getCell(x, y + 1) + getCell(x + 1, y + 1);
    }

    public void setCell(int x, int y, int cell) {
        this.cells[y][x] = cell;
    }

    public int getCell(int x, int y) {
        if (x < 0 || x >= xLength || y < 0 || y >= yLength) {
            return DEAD;
        }
        return this.cells[y][x];
    }

    public int nextCell(int x, int y, int neighborCount) {
        if (getCell(x, y) == ALIVE && (neighborCount == ALIVE_NEIGHBOR_COUNT_1 || neighborCount == ALIVE_NEIGHBOR_COUNT_2)) {
            return ALIVE;
        } else if (getCell(x, y) == DEAD && neighborCount == RESURRECTOR_COUNT) {
            return ALIVE;
        } else {
            return DEAD;
        }
    }

    @Override
    public String toString() {
        return Arrays.deepToString(this.cells);
    }


    @Override
    public boolean equals(Object obj) {
        return Arrays.deepEquals(this.cells, ((Grid)obj).cells);
    }

    public Grid next() {
        Grid returnValue = new Grid(xLength, yLength, DEAD);

        for (int y = 0; y < yLength; y++){
            for (int x = 0; x < xLength; x++){
                returnValue.setCell(x, y, nextCell(x, y, neighborCount(x, y)));
            }
        }

        return returnValue;
    }
}
