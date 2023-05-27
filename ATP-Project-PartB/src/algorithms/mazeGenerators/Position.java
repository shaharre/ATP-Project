package algorithms.mazeGenerators;

import java.io.Serializable;

/**
 * Postion
 */
public class Position implements Serializable {

    private int RowIndex;
    private int ColumnIndex;

    /**
     * constructor
     * @param rowIndex
     * @param columnIndex
     */
    public Position(int rowIndex, int columnIndex) {
        RowIndex = rowIndex;
        ColumnIndex = columnIndex;
    }

    /**
     * get row index
     * @return current row index
     */
    public int getRowIndex(){
        return this.RowIndex;
    }

    /**
     * get column index
     * @return current column index
     */
    public int getColumnIndex(){
        return this.ColumnIndex;
    }

    /**
     * toString
     * @return string that represent the position
     */
    @Override
    public String toString() {
        return "{" + RowIndex + "," + ColumnIndex + "}";
    }
}
