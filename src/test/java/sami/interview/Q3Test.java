package sami.interview;

import org.junit.Test;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.assertEquals;
import static sami.interview.Utils.getDurationBreakdown;

/**
 * Created by Sami on 23:59 - 30/03/17.
 */
public class Q3Test {
    private boolean skipQuestionAnswer = false; //the solution to the question will take time
    protected int[][] maze;
    protected Q3 question3 = new Q3();

    @Test
    public void computeMaze0Paths() throws Exception {
        //0
        maze = new int[][]{
                {2, 0, 0, 0},
                {0, 0, 0, 3}
        };
        assertEquals(0, question3.computePaths(maze));
    }

    @Test
    public void computeMaze1Paths() {
        //1
        maze = new int[][]{
                {2, 0, 0},
                {0, 0, 3}};
        assertEquals(1, question3.computePaths(maze));
    }

    @Test
    public void computeMaze2Paths() {

        //2
        maze = new int[][]{
                {2, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 3}};
        assertEquals(2, question3.computePaths(maze));
    }

    @Test
    public void computeMaze4Paths() {
        //4
        maze = new int[][]{
                {2, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 3, 1}};
        assertEquals(4, question3.computePaths(maze));
    }

    @Test
    public void computeMaze5Paths() {
        long start = currentTimeMillis();
        maze = new int[][]{
                {2, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 3}
        };
        System.out.println("Maze5 testunit result: " + question3.computePaths(maze));
        System.out.println(String.format("Time taken: %s", getDurationBreakdown(currentTimeMillis() - start)));
    }

    @Test
    public void computeQuestionMaze() {
        long start = currentTimeMillis();
        int result = 0;
        //46016
        maze = new int[][]{
                {2, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 3, 1, 1}
        };
        if(!skipQuestionAnswer)
            question3.computePaths(maze);
        System.out.println("Maze5 testunit result: " + question3.computePaths(maze));
        assertEquals(40616, result);
        System.out.println(String.format("Time taken for final solution: %s",
                getDurationBreakdown(currentTimeMillis() - start)));
    }

}