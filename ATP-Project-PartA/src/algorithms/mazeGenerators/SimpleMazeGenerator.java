package algorithms.mazeGenerators;

import java.util.Random;

/**
 * generate new simple maze
 */
public class SimpleMazeGenerator extends AMazeGenerator{
    /**
     * maze generator
     * @param rows
     * @param columns
     * @return maze
     */
    public Maze generate(int rows, int columns) {
        if(rows < 1 || columns < 1)
            return null;
        Maze maze = new Maze(rows, columns);
        maze = randomWalls(maze);
        maze.setCell(0, 0, 0);
        maze.setCell(rows - 1, columns - 1, 0);
        maze = downRight(maze);
        return maze;
    }

    /**
     * put random walls in the maze
     * @param emptyMaze
     * @return maze
     */
    private Maze randomWalls(Maze emptyMaze){
        Random ran = new Random();
        for (int i = 0; i < emptyMaze.getRows(); i++) {
            for (int j = 0; j < emptyMaze.getColumns(); j++) {
                double prob = ran.nextDouble();
                if(prob <= 0.7) {
                    int randomNum = ran.nextInt(2);
                    emptyMaze.setCell(i, j, randomNum);
                }
            }
        }
        return emptyMaze;
    }

    /**
     * promise at least one valid solution
     * @param maze
     * @return maze with at least one valid solution
     */
    private Maze downRight(Maze maze){
        Random ran = new Random();
        int rowIndex = 0;
        int columnIndex = 0;
        while (rowIndex < maze.getRows() - 1 || columnIndex < maze.getColumns() - 1){
            //down
            if (maze.getRows() - rowIndex > 1) {
                if (maze.getRows() - rowIndex == 2) {
                    maze.setCell(rowIndex + 1, columnIndex, 0);
                    rowIndex++;
                } else {
                    int move = ran.nextInt(maze.getRows() - rowIndex );
                    for (int j = 1; j < move + 1; j++){
                        maze.setCell(rowIndex + j, columnIndex, 0);
                    }
                    rowIndex += move;
                }
            }
            //right
            if (maze.getColumns() - columnIndex > 1) {
                if (maze.getColumns() - columnIndex == 2) {
                    maze.setCell(rowIndex, columnIndex + 1, 0);
                    columnIndex++;
                } else {
                    int move = ran.nextInt(maze.getColumns() - columnIndex );
                    for (int i = 1; i < move + 1; i++) {
                        maze.setCell(rowIndex, columnIndex + i, 0);
                    }
                    columnIndex += move;
                }
            }
        }
        return maze;
    }
}
