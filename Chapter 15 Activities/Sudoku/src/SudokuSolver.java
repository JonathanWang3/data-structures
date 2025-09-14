import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SudokuSolver {
    private final int M = 3;
    private final int N = M * M;
    private int[][] grid;
    private ArrayList<Set<Integer>> rows;
    private ArrayList<Set<Integer>> cols;
    private ArrayList<Set<Integer>> squares;
    private Set<Integer> nums;

    public SudokuSolver(String fileName) {
        // read the puzzle file
        rows = new ArrayList<>();
        cols = new ArrayList<>();
        squares = new ArrayList<>();
        nums = new HashSet<Integer>();
        
        try (Scanner in = new Scanner(new File(fileName))) {

            this.grid = new int[N][N];

            for (int row = 0; row < N; row++) {
                String line = in.next();

                for (int col = 0; col < N; col++) {
                    String strVal = line.substring(col, col + 1);
                    int number;
                    if (strVal.equals("x")) {
                        number = 0;
                    } else {
                        number = Integer.parseInt(strVal);
                    }
                    this.grid[row][col] = number;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open: " + fileName);
        }

        // create the list of sets for each row (this.rows)
        // ...
        for (int[] row : this.grid) {
            Set<Integer> newRow = new HashSet<>();
            for (int num : row) {
                newRow.add(num);
            }
            this.rows.add(newRow);
        }

        // create the list of sets for each col (this.cols)
        // ...
        for (int i = 0; i < N; i++) {
            Set<Integer> newCol = new HashSet<>();
            for (int j = 0; j < N; j++) {
                newCol.add(this.grid[j][i]);
            }
            this.cols.add(newCol);
        }


        // create the list of sets for each square (this.squares)
        /* the squares are added to the list row-by-row:
            0 1 2
            3 4 5
            6 7 8
         */
        // ...
        for (int i = 0; i<N; i++) {
            squares.add(new HashSet<Integer>());
        }
        // squares.get(index).add(grid[x][y]);
        
        int counter = 0;
        int x;
        int y;
        for (int i = 0; i<(N*N); i++) {
            x = Math.floorDiv((i%9), 3);
            y = Math.floorDiv((i-(i%9)),27);
            squares.get(3*y + x).add(this.grid[Math.floorDiv(i, 9)][i%9]);
        }

        
        /*
        for (int i = 0; i < N; i++) {
            Set<Integer> newSqr = new HashSet<>();
            
            for 
        }*/

        // create a hash set for [1..9] (this.nums)
        for (int i = 1; i<=9; i++) {
            this.nums.add(i);
        }

        // visually inspect that all the sets are correct
        for (int row = 0; row < N; row++) {
            System.out.println("row " + row + ": " + this.rows.get(row));
        }
        for (int col = 0; col < N; col++) {
            System.out.println("col " + col + ": " + this.cols.get(col));
        }
        for (int square = 0; square < N; square++) {
            System.out.println("square " + square + ": " + this.squares.get(square));
        }
        System.out.println(this.nums);
    }

    public boolean solve() {
        // find an empty location, if any
        
        boolean finished = true;
        int nextRow = -1;
        int nextCol = -1;
        for (int row = 0; row < N && finished; row++) {
            for (int col = 0; col < N && finished; col++) {
                if (this.grid[row][col] == 0) {
                    finished = false;
                    nextRow = row;
                    nextCol = col;


                }
            }
        }
        int squareIndex = (nextRow / 3) * 3 + (nextCol / 3);

        // the board is complete; we solved it
        if (finished) {
            return true;
        }

        // get all possible numbers for the row and column we are trying to populate
        /*
            Create a new set based on the this.nums and remove all elements in the sets
            corresponding to nextRow, nextCol, and the corresponding square (use the
            removeAll method).

            Properly indexing the squares list of sets is tricky. Verify that your
            algorithm is correct.
         */
        Set<Integer> possibleNums = new HashSet<Integer>();
        possibleNums.addAll(this.nums);
        possibleNums.removeAll(this.cols.get(nextCol));
        possibleNums.removeAll(this.rows.get(nextRow));
        possibleNums.removeAll(this.squares.get(squareIndex));
        
        
        // ...

        // if there are no possible numbers, we cannot solve the board in its current state
        if (possibleNums.isEmpty()) {
            return false;
        }

        
        // try each possible number
        for (Integer possibleNum : possibleNums) {
            // update the grid and all three corresponding sets with possibleNum
            this.grid[nextRow][nextCol] = possibleNum;
            this.cols.get(nextCol).add(possibleNum);
            this.rows.get(nextRow).add(possibleNum);
            this.squares.get(squareIndex).add(possibleNum);



            // recursively solve the board
            if (this.solve()) {
                // the board is solved!
                return true;
            } else {
                /*
                 Undo the move before trying another possible number by setting the corresponding
                 element in the grid back to 0 and removing possibleNum from all three corresponding
                 sets.
                */
                this.grid[nextRow][nextCol] = 0;
                this.cols.get(nextCol).remove(possibleNum);
                this.rows.get(nextRow).remove(possibleNum);
                this.squares.get(squareIndex).remove(possibleNum);
            }
        }
        return false;
    }

    public String toString() {
        String str = "";

        for (int[] row : grid) {
            for (int val : row) {
                str += val + "\t";
            }

            str += "\n";
        }

        return str;
    }

    public static void main(String[] args) {
        String fileName = "src/puzzle1.txt";

        SudokuSolver solver = new SudokuSolver(fileName);
        System.out.println(solver);
        if (solver.solve()) {
            System.out.println("Solved!");
            System.out.println(solver);
        } else {
            System.out.println("Unsolveable...");
        }
    }
}