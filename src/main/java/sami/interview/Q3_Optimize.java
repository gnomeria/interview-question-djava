package sami.interview;

/**
 * Created by Sami on 23:57 - 30/03/17.
 */
@SuppressWarnings("Duplicates")
public class Q3_Optimize {

    /**
     * This is the public compute paths
     *
     * @param maze
     * @return
     * @see Q3#computePaths(int[][])
     */
    public int computePaths(int[][] maze) {
        int start = Cell.getStartCell(maze);
        int end = Cell.getEndCell(maze);
        return computePaths(maze, start, end, Cell.getValidCells(maze), 0);
    }


    /**
     * This is another implementation of the path calculations, done using
     * better cell data structure, while still implementing the same recursive
     * algorithm done before.
     *
     * Possible alternative is to represent the problem as a graph and implement
     * the Depth-First Search on the cells/nodes, to provide better performance.
     *
     * @param start the current start cell is now represented as an integer
     *              compressed data. This also put a constraint on the (M x N)
     *              number of grids can be used to 2^16 of rows/columns.
     * @param end   same as 'start' parameter.
     * @see Q3#computePaths(int[][], Q3.Cell, Q3.Cell, int, int)
     */
    private int computePaths(int[][] maze, int start, int end,
                             final int validCellsCount,
                             int counter) {
        if (++counter > validCellsCount || start < 0)
            return 0;
        int result = 0;
        if (Cell.row(start) == maze.length || Cell.col(start) == maze[0].length)
            return 0;
        if (maze[Cell.row(start)][Cell.col(start)] == 1)
            return 0;
        if (start == end && ++counter == validCellsCount) {
            result++;
            return result;
        }
        maze[Cell.row(start)][Cell.col(start)] = 1;

        result += computePaths(maze, Cell.right(start), end, validCellsCount, counter);
        result += computePaths(maze, Cell.down(start), end, validCellsCount, counter);
        result += computePaths(maze, Cell.left(start), end, validCellsCount, counter);
        result += computePaths(maze, Cell.up(start), end, validCellsCount, counter);

        maze[Cell.row(start)][Cell.col(start)] = 0;
        return result;
    }

    /**
     * This Cell class uses bit manipulation trick to compress the cell row/col info
     * into a single integer object. Because the integer in Java uses 32 bytes, we can
     * split the row/col value into 16 left bits as row, and 16 right bits as column.
     * But introduces constraint on the max level row/col can be calculated for the
     * problem.
     * <p>
     * But since it's an NP complete problem that grows exponentially, 2^17 number of
     * cells will be too hard to compute anyway.
     * </p>
     * This will improve the performance and space size as the structure will only
     * deal with primitive value and the cell manipulation can be done in fast bit
     * operations. This also reduces the operation in the Cell object equals operations
     * like in Q3#Cell
     */
    public static class Cell {
        /**
         * This method is to convert/compress the row and column information of the maze
         * into a single int value
         *
         * @param row index of cell in a maze grid
         * @param col index of cell in a maze grid
         * @return
         */
        public static int compactRowColInfo(int row, int col) {
            return (row << 16) | (col);
        }

        /**
         * This method as well as its column counterpart, uses bitmasking to fast return
         * index value of a cell.
         *
         * The operation works by right shifting the 16 leftmost bits of the cell, to only
         * return its 16 leftmost bits.
         *
         * @param rowcol a cell primitive integer object
         * @return its integer row index
         */
        public static int row(int rowcol) {
            return (rowcol >> 16);
        }

        /**
         * The method works by using binary <b>AND</b> operation and using 0xFFFF bitmask.
         * 0xFFFF is equal to a 2^16 rightmost bits set to 1, or 65535 in decimal.
         *
         * Using AND operation with the bitmask on rowcol will return its 16 rightmost
         * bits, and 'discarding' its leftmost bits (as already designed to represent a row)
         *
         * @see Cell#row(int)
         */
        public static int col(int rowcol) {
            return (rowcol & 0xFFFF);
        }

        /**
         * This is used in the next cells traverse on the recursive computePath function
         * similar as in the inside of the {@linkplain Q3#computePaths(int[][], Q3.Cell, Q3.Cell, int, int)}
         *
         * This method works by double shifting the left bits and to combine 'OR' operation
         * with the masking of the right bits and add 1 to col/rightmost bits.
         * The alternative can be the masking the left bits with appropriate bitmasking.
         * Although the double shifting might be faster and arguably easier to understand.
         *
         * @return 'compressed/compact' value of the left cell from the given cell parameter
         */
        public static int left(final int cell) {
            return ((cell >> 16) << 16) | ((cell & 0xFFFF) - 1);
        }

        /**
         * @see Cell#left(int)
         */
        public static int right(final int cell) {
            return ((cell >> 16) << 16) | ((cell & 0xFFFF) + 1);
        }

        /**
         * @see Cell#left(int)
         */
        public static int up(final int cell) {
            return (((cell >> 16) - 1) << 16 | (cell & 0xFFFF));
        }

        /**
         * @see Cell#left(int)
         */
        public static int down(final int cell) {
            return (((cell >> 16) + 1) << 16 | (cell & 0xFFFF));
        }

        /**
         * The exact same function as in the {@linkplain Q3}, but returns the start cell info
         * by using {@linkplain Cell#compactRowColInfo(int, int)} to combine the values
         */
        private static int getStartCell(final int[][] maze) {
            for (int i = 0; i < maze.length - 1; i++) {
                for (int j = 0; j < maze[0].length; j++) {
                    if (maze[i][j] == 2) return compactRowColInfo(i, j);
                }
            }
            return compactRowColInfo(0, 0);
        }

        /**
         * @see Cell#getStartCell(int[][])
         */
        private static int getEndCell(final int[][] maze) {
            for (int i = maze.length - 1; i > 0; i--) {
                for (int j = maze[0].length - 1; j > 0; j--) {
                    if (maze[i][j] == 3) return compactRowColInfo(i, j);
                }
            }
            return compactRowColInfo(maze.length - 1, maze[0].length - 1);
        }

        /**
         * @see Cell#getEndCell(int[][])
         */
        private static int getValidCells(final int[][] maze) {
            int validCellsCount = 1;
            for (int[] aMaze : maze) {
                for (int j = 0; j < aMaze.length; j++) {
                    if (aMaze[j] != 1)
                        validCellsCount++;
                }
            }
            return validCellsCount;
        }
    }
}
