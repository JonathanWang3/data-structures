
import java.util.Stack;

class Pair {
    int row;
    int col;

    Pair(int r, int c) {
        this.row = r;
        this.col = c;
    }

    int getRow() {
        return this.row; 
    }

    int getCol() {
        return this.col;
    }

}

public class Grid
{
    private static final int SIZE = 10;
    int[][] pixels = new int[SIZE][SIZE];
    Stack<Pair> tsStack = new Stack<>();


    /**
     * Flood fill, starting with the given row and column.
    */
    public void floodfill(int row, int column)
    {
        int count = 0;

        tsStack.push(new Pair(row,column));

        // Check nesw in order

        while (!tsStack.isEmpty()) {
            Pair p = tsStack.pop();
            int r = p.getRow();
            int c = p.getCol();

            count++;
            if (pixels[r][c] == 0)
                pixels[r][c] = count;
            if (r - 1 >= 0 && pixels[r-1][c] == 0) 
                tsStack.push(new Pair(r - 1, c));
            if (c + 1 < SIZE && pixels[r][c+1] == 0) 
                tsStack.push(new Pair(r, c+1));
            if (r + 1 < SIZE && pixels[r+1][c] == 0) 
                tsStack.push(new Pair(r+1, c));
            if (c - 1 >= 0 && pixels[r][c-1] == 0) 
                tsStack.push(new Pair(r, c-1));    
            
        }
        
    }

    @Override
    public String toString()
    {
        String r = "";
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
                r = r + String.format("%4d", pixels[i][j]);
            r = r + "\n";
        }
        return r;
    }
}
