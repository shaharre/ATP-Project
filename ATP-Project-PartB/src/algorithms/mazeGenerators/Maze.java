package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * maze
 */
public class Maze implements Serializable {
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

    @Override
    public String toString() {
        int [][] tmp = maze;
        String S = Arrays.stream(tmp).flatMapToInt(Arrays::stream).mapToObj(String::valueOf).collect(Collectors.joining(""));
        //S = S + start.toString() + " " + end.toString();
        S = "\"" + S + "\"";
        return S;
    }

    //Part B//

    public Maze(byte[] myByteMaze){
        rows = Byte.toUnsignedInt(myByteMaze[0]) * 256 + Byte.toUnsignedInt(myByteMaze[1]);
        columns = Byte.toUnsignedInt(myByteMaze[2]) * 256 + Byte.toUnsignedInt(myByteMaze[3]);
        start = new Position(Byte.toUnsignedInt(myByteMaze[4]) * 256 + Byte.toUnsignedInt(myByteMaze[5]), Byte.toUnsignedInt(myByteMaze[6]) * 256 + Byte.toUnsignedInt(myByteMaze[7]));
        end = new Position(Byte.toUnsignedInt(myByteMaze[8]) * 256 + Byte.toUnsignedInt(myByteMaze[9]), Byte.toUnsignedInt(myByteMaze[10]) * 256 + Byte.toUnsignedInt(myByteMaze[11]));

        maze = new int[rows][columns];
        int index = 12;
        for(int row = 0; row < rows; row++){
            for(int column = 0; column < columns; column++){
                maze[row][column] = (int) myByteMaze[index];
                index++;
            }
        }
    }
    public byte[] toByteArray(){
        //needed to change the size
        byte[] bytesArray = new byte[rows * columns + 12];

        // Rows
        bytesArray[0] = (byte) (rows / 256);
        bytesArray[1] = (byte) (rows % 256);

        // Columns
        bytesArray[2] = (byte) (columns / 256);
        bytesArray[3] = (byte) (columns % 256);

        //StartPosition - Rows
        bytesArray[4] = (byte) (getStartPosition().getRowIndex() / 256);
        bytesArray[5] = (byte) (getStartPosition().getRowIndex() % 256);

        //StartPosition - Column
        bytesArray[6] = (byte) (getStartPosition().getColumnIndex() / 256);
        bytesArray[7] = (byte) (getStartPosition().getColumnIndex() % 256);

        //GoalPosition - Rows
        bytesArray[8] = (byte) (getGoalPosition().getRowIndex() / 256);
        bytesArray[9] = (byte) (getGoalPosition().getRowIndex() % 256);

        //GoalPosition - Columns
        bytesArray[10] = (byte) (getGoalPosition().getColumnIndex() / 256);
        bytesArray[11] = (byte) (getGoalPosition().getColumnIndex() % 256);

        int index = 12;
        for(int row = 0; row < rows; row++){
            for(int column = 0; column < columns; column++){
                bytesArray[index] = (byte) maze[row][column];
                index++;
            }
        }
        return bytesArray;
    }


}
