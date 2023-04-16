package algorithms.search;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    @Test
    void testBest() {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(0, 0);
        SearchableMaze searchable = new SearchableMaze(maze);
        BestFirstSearch searcher = new BestFirstSearch();
        Solution solution = searcher.solve(searchable);
        assertNull(solution, "Null object");

        Maze maze1 = mg.generate(-1, -9);
        SearchableMaze searchable1 = new SearchableMaze(maze1);
        BestFirstSearch searcher1 = new BestFirstSearch();
        Solution solution1 = searcher.solve(searchable);
        assertNull(solution1, "Null object");

        Maze maze2 = mg.generate(8, 9);
        SearchableMaze searchable2 = new SearchableMaze(maze2);
        BestFirstSearch searcher2 = new BestFirstSearch();
        assertEquals(Solution.class, searcher2.solve(searchable2).getClass());

        Maze maze3 = mg.generate(-1, 9);
        SearchableMaze searchable3 = new SearchableMaze(maze3);
        BestFirstSearch searcher3 = new BestFirstSearch();
        Solution solution3 = searcher.solve(searchable);
        assertNull(solution3, "Null object");

        Maze maze4 = mg.generate(20, 20);
        SearchableMaze searchable4 = new SearchableMaze(maze4);
        BestFirstSearch searcher4 = new BestFirstSearch();
        assertEquals(Solution.class, searcher4.solve(searchable4).getClass());

    }
}