package algorithms.mazeGenerators;

/**
 *abstract class - generate new maze and measure generate time
 */
public abstract class AMazeGenerator implements IMazeGenerator{
    /**
     * abstract method - generate a new maze
     * @param rows
     * @param columns
     * @return maze
     */
    public abstract Maze generate(int rows, int columns);

    /**
     * check how long it takes to generate a new maze
     * @param rows
     * @param columns
     * @return long that represent how long it takes to generate a new maze
     */
    public long measureAlgorithmTimeMillis(int rows, int columns){
        if (rows <=0 || columns <=0){
            return 1;
        }
        long before = System.currentTimeMillis();
        generate(rows, columns);
        long after = System.currentTimeMillis();
        return after - before;
    }

}
