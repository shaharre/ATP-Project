package algorithms.mazeGenerators;
/**
* Empty Maze Generator - create empty maze
 */
public class EmptyMazeGenerator extends AMazeGenerator{
    /**
     * generate empty maze
     * @param rows
     * @param columns
     * @return empty maze all cells are initialized to zero
     */
    public Maze generate(int rows, int columns) {
        if(rows < 1 || columns < 1)
            return null;
        Maze maze = new Maze(rows, columns);
        return maze;
    }

}
