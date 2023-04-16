package algorithms.mazeGenerators;

import java.util.Random;

/**
 * maze
 */
public class Maze {
    private int[][] maze;
    private int rows;
    private int columns;
    private Position start;
    private Position end;

    /**
     * getRows
     * @return how many rows are there in the maze
     */
    public int getRows() {
        return rows;
    }

    /**
     * getColumns
     * @return how many columns are there in the maze
     */
    public int getColumns() {
        return columns;
    }

    /**
     * maze constructor
     * @param rows
     * @param columns
     */
    public Maze(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.maze = new int[rows][columns];
        start = new Position(0,0);
        end = new Position(rows-1,columns-1);
    }

    /**
     * set all cells to 1 (all walls)
     */
    public void allWalls(){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                maze[i][j] = 1;
            }
        }
    }

    /**
     * set new value in given index
     * @param row
     * @param column
     * @param value
     */
    public void setCell(int row, int column, int value){
        this.maze[row][column] = value;
    }

    /**
     * returs the value in given index
     * @param row
     * @param column
     * @return the value of given index
     */
    public int getCell(int row, int column){
        return maze[row][column];
    }

    /**
     * get the start position
     * @return the initial cell of the maze - always 0,0
     */
    public Position getStartPosition() {
        if (this == null)
            return null;
        return start;
    }

    /**
     * get the end position
     * @return the goal cell of the maze - always rows-1, columns-1
     */
    public Position getGoalPosition() {
        if (this == null)
            return null;
        return end;
    }

    /**
     * set goal position
     */
    public void setGoal(){
        Random ran = new Random();
        if(this.getColumns() % 2 == 0) {
            for (int i = 0; i < this.getRows(); i++) {
                this.setCell(i, this.getColumns() - 1, ran.nextInt(2));
            }
            this.setCell(this.getRows() - 2, this.getColumns() - 1,0);
        }
        if(this.getRows() % 2 == 0) {
            for (int i = 0; i < this.getColumns(); i++) {
                this.setCell(this.getRows() - 1, i, ran.nextInt(2));
            }
            this.setCell(this.getRows() - 1, this.getColumns() - 2,0);
        }
        this.setCell(this.getRows() - 1, this.getColumns() - 1,0);
    }

    /**
     * print the maze
     */
    public void print(){
        for (int i = 0; i<rows; i++){
            System.out.print("[ ");
            for (int j=0; j<columns;j++){
                System.out.print(maze[i][j] + " ");
            }
            System.out.println("]");
        }
    }
}
