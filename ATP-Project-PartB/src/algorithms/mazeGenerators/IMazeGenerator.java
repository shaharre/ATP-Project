package algorithms.mazeGenerators;

public interface IMazeGenerator {
    /**
     * abstract method - generate a new maze
     * @param rows
     * @param columns
     * @return maze
     */
    public Maze generate(int rows, int columns);
    /**
     * check how long it takes to generate a new maze
     * @param rows
     * @param columns
     * @return long that represent how long it takes to generate a new maze
     */
    public long measureAlgorithmTimeMillis(int rows, int columns);
}
