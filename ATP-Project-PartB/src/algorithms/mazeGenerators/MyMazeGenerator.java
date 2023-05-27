package algorithms.mazeGenerators;
import java.util.*;

public class MyMazeGenerator extends AMazeGenerator{
    private Stack<Position> unvisited;
    private boolean check = false;

    /**
     * generate new maze with at least one valid solution
     * @param rows
     * @param columns
     * @return maze
     */


    // DFS algorithm
    public Maze generate(int rows, int columns) {
        if(rows < 1 || columns < 1)
            return null;
        Maze maze = new Maze(rows, columns);
        maze.allWalls();
        maze.setCell(0,0,0);
        unvisited = new Stack<>();
        unvisited.push(maze.getStartPosition());
        while (!unvisited.isEmpty()){
            ArrayList<String> directions = randomDirectionsList();
            check = false;
            for(int i = 0; i < 4; i++){
                int currRow = unvisited.peek().getRowIndex();
                int currColumn = unvisited.peek().getColumnIndex();
                //up
                if (directions.get(i) == "U"){
                    if(currRow > 1){
                        breakWallRow(maze,currRow,currColumn,-2,-1);
                    }
                }
                //down
                else if (directions.get(i) == "D") {
                    if(maze.getRows() - currRow > 2){
                        breakWallRow(maze,currRow,currColumn,2,1);
                    }
                }
                //left
                else if (directions.get(i) == "L") {
                    if(currColumn > 1){
                        breakWallsCol(maze,currRow,currColumn,-2,-1);
                    }
                }
                //right
                else{
                    if(maze.getColumns() - currColumn > 2){
                        breakWallsCol(maze,currRow,currColumn,2,1);
                    }
                }
            }
            if (!check)
                unvisited.pop();
        }
        maze.setGoal();
        return maze;
    }

    /**
     * shuffle moves list
     * @return shuffle moves list
     */
    public ArrayList<String> randomDirectionsList() {
        ArrayList<String> directions = new ArrayList<>();
        directions.add("U");
        directions.add("D");
        directions.add("L");
        directions.add("R");
        Collections.shuffle(directions);
        return directions;
    }

    /**
     * change wall to empty cell - change value from 1 to 0
     * @param maze
     * @param indexR
     * @param indexC
     * @param rowMove2
     * @param rowMove1
     */
    public void breakWallRow(Maze maze, int indexR, int indexC, int rowMove2, int rowMove1){
         if (maze.getCell(indexR + rowMove2, indexC) != 0){
            check = true;
            maze.setCell(indexR + rowMove2, indexC, 0);
            maze.setCell(indexR + rowMove1, indexC, 0);
            unvisited.push(new Position(indexR + rowMove2, indexC));
        }

    }

    /**
     * change wall to empty cell - change value from 1 to 0
     * @param maze
     * @param indexR
     * @param indexC
     * @param colMove2
     * @param colMove1
     */
    public void breakWallsCol(Maze maze, int indexR, int indexC, int colMove2, int colMove1){
         if (maze.getCell(indexR, indexC + colMove2) != 0){
            check = true;
            maze.setCell(indexR, indexC + colMove2, 0);
            maze.setCell(indexR, indexC + colMove1, 0);
            unvisited.push(new Position(indexR, indexC+colMove2));
        }
    }
}
