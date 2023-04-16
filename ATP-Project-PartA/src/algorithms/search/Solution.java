package algorithms.search;

import java.util.ArrayList;

/**
 * Solution
 */
public class Solution {
    private ArrayList<AState> solutionPath;

    /**
     * constructor
     */
    public Solution() {
        this.solutionPath = new ArrayList<AState>();
    }

    /**
     * set new solution path according to the given solutionPath
     * @param solutionPath
     */
    public void setSolutionPath(ArrayList<AState> solutionPath) {
        this.solutionPath = solutionPath;
    }

    /**
     *
     * @return array contain the solution path
     */
    public ArrayList<AState> getSolutionPath() {
        return solutionPath;
    }
}
