package sami.interview;

/**
 * Created by Sami on 23:57 - 30/03/17.
 */

@SuppressWarnings("Duplicates")
public class Q3 {

    /**
     * This is the overloaded public class to solve the question using a recursive solution.
     * It initializes the parameters based on the maze configuration and constraints as stated
     * in the problem.
     * It will determine the start and end cell for the maze, to be passed in the recursive function
     *
     * @param maze
     * @return
     */
    public int computePaths(int[][] maze) {
        Cell start = Cell.getStartCell(maze);
        Cell end = Cell.getEndCell(maze);
        int paths = computePaths(maze, start, end, Cell.getValidCells(maze), 0);
        return paths;
    }

    /**
     *
     * @param maze this grid will also be determined by the calling function of the recurse stack
     * @param start current start to traverse to the end cell
     * @param end end cell won't change throughout the recurses, as supplied by the calling public
     *            method of <b>computePaths</b>
     * @param validCellsCount this is the parameter to validate whether the current paths satisfy
     *                        the problem constraint for having to visit all the cells.
     *                        This value won't change as supplied by the {@linkplain Cell#getValidCells(int[][])}
     * @param counter number of current cells that have already been traversed
     * @return total hamiltonian paths found
     */
    private int computePaths(int[][] maze, Cell start, Cell end,
                            final int validCellsCount,
                            int counter) {
        //check the increment of current path, return 0 if the path is not found
        if (++counter > validCellsCount)
            return 0;
        //variable to hold the result(s) of its recursive functions stack
        int result = 0;

        //sanity check of the current cell
        if (start.row < 0 || start.col < 0)
            return 0;
        //reached the bottom right cells without finding any solutions (tried all possible paths)
        if (start.row == maze.length || start.col == maze[0].length)
            return 0;
        //if the cell has already been visited, then return 0
        if (maze[start.row][start.col] == 1)
            return 0;

        //if it arrives at the target cell, and the number of paths equal the total number of
        //the required constraint of having visited all nodes, increment the result and return
        if (start.equals(end) && ++counter == validCellsCount) {
            result++;
            return result;
        }

        //set visited flag on the grid
        maze[start.row][start.col] = 1;
        Cell[] nextCells = new Cell[4]; //direction possibility
        nextCells[0] = new Cell(start.row + 1, start.col); //down
        nextCells[2] = new Cell(start.row, start.col + 1); //right
        nextCells[1] = new Cell(start.row - 1, start.col); //up
        nextCells[3] = new Cell(start.row, start.col - 1); //left

        //compute all results from current start, with 4 grid directions
        for (Cell nc : nextCells) {
            result += computePaths(maze, nc, end, validCellsCount, counter);
        }
        //unset the visited
        maze[start.row][start.col] = 0;
        return result;
    }

    /**
     * This is the data structure utility class to hold the value of the cell.
     * It is done in a familiar Java structure for clear implementation, while
     * the class {@link Q3_Optimize} implemented the optimized Cell data structure
     * using bit manipulation to compress and store the grid location information
     */
    private static class Cell {
        public final int row;
        public final int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        /**
         * @param maze find the start cell from the given maze by finding <i>int 2</i>
         * @return Cell object of the start cell from the maze
         */
        public static Cell getStartCell(final int[][] maze) {
            for (int i = 0; i < maze.length - 1; i++) {
                for (int j = 0; j < maze[0].length; j++) {
                    if (maze[i][j] == 2) return new Cell(i, j);
                }
            }
            return new Cell(0, 0);
        }

        /**
         * @param maze find the end cell from the given maze by finding  <i>int 3</i>
         * @return cell object of the target cel
         */
        public static Cell getEndCell(final int[][] maze) {
            for (int i = maze.length - 1; i > 0; i--) {
                for (int j = maze[0].length - 1; j > 0; j--) {
                    if (maze[i][j] == 3) return new Cell(i, j);
                }
            }
            return new Cell(maze.length - 1, maze[0].length - 1);
        }

        /**
         * This is used for counting the number of cells in the maze grid.
         * It could also be refactored into a <i>getNumberOfCells</i>, yet
         * the naming previously chosen was to reflect on the purpose on
         * the method intended usage.
         *
         * @return number of cells in a maze, excluding the <i>int 1</i>
         */
        public static int getValidCells(int[][] maze) {
            int validCellsCount = 1;
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[i].length; j++) {
                    if (maze[i][j] != 1)
                        validCellsCount++;
                }
            }
            return validCellsCount;
        }

        /**
         * The override needed for the definition such that a cell is equal
         * if its cell's row and column is the same to that of other.
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if ((obj == null) || (obj.getClass() != this.getClass()))
                return false;
            Cell cell = (Cell) obj;
            return row == cell.row && col == cell.col;
        }
    }
}
