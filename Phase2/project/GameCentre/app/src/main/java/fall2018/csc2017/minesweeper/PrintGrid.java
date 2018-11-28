package fall2018.csc2017.minesweeper;

import android.util.Log;

/**
 * The source code is originated from
 * https://github.com/marcellelek/Minesweeper.git
 * It is used to construct basic game structure and modified by our group member.
 * Class to print the grid.
 */
public class PrintGrid {

    /**
     * Print grid for test the program.
     * @param grid grid containing game information.
     * @param width width of the grid.
     * @param height height of the grid.
     */
    public static void print( final int[][] grid , int width , int height ){
        for( int x = 0 ; x < width ; x++ ){
            String printedText = "| ";
            for( int y = 0 ; y < height ; y++ ){
                printedText += String.valueOf(grid[x][y]).replace("-1", "B") + " | ";
            }
            Log.e("",printedText);
        }
    }
}
