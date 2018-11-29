package fall2018.csc2017.minesweeper;

import android.content.Context;
import android.util.Log;

import android.widget.Toast;

import java.io.Serializable;

import fall2018.csc2017.slidingtiles.Score;
import fall2018.csc2017.slidingtiles.UserManager;


/**
 * The source code is originated from
 * https://github.com/marcellelek/Minesweeper.git
 * It is used to construct basic game structure and modified by our group member.
 * GameEngine for process the logic of game minesweeper.
 */
public class GameEngine implements Serializable {
    private static GameEngine instance;

    /**
     * Game state pending win or not.
     */
    private static boolean isLost = false;

    /**
     * Number of bombs.
     */
    private static int BOMB_NUMBER = 14;

    /**
     * Number of horizontal tiles.
     */
    private static int WIDTH = 10;

    /**
     * Number of vertical tiles.
     */
    private static int HEIGHT = 14;

    /**
     * Whether the game is over or not.
     */
    public static boolean is_over = false;

    /**
     * Time spend for the game.
     */
    public int time;

    /**
     * Context of gameEngine.
     */
    private Context context;


    /**
     * Get number of horizontal tiles.
     * @return num tiles in int.
     */
    public static int getWIDTH(){return WIDTH;}

    /**
     * Get number of vertical tiles.
     * @return num tiles in int.
     */
    public static int getHEIGHT(){return HEIGHT;}

    /**
     * Set number of bombs.
     * @param b num of bombs in int.
     */
    public static void setBombNumber(int b){BOMB_NUMBER = b;}

    /**
     * Set number of horizontal tiles.
     * @param w num of tiles in int.
     */
    public static void setWIDTH(int w){WIDTH = w;}

    /**
     * Set number of vertical tiles.
     * @param h num of tiles in int.
     */
    public static void setHEIGHT(int h){HEIGHT = h;}

    /**
     * Check the game state., should the game continue or not.
     * @return boolean representing game state.
     */
    public static boolean isLost(){return isLost;}

    /**
     * Set the game state to determine is game finished or not.
     * @param isLost boolean representing game state.
     */
    public static void setIsLost(boolean isLost) {
        GameEngine.isLost = isLost;
    }

    /**
     * Get number of bombs.
     * @return int number of bombs.
     */
    public static int getBombNumber(){
        return BOMB_NUMBER;
    }
    /**
     * Initialize a new grid for game minesweeper.
     */
    private static Cell[][] MinesweeperGrid = new Cell[WIDTH][HEIGHT];

    /**
     * Return current state game engine.
     * @return game state as game engine.
     */
    public static GameEngine getInstance() {
        if( instance == null ){
            instance = new GameEngine();
        }
        return instance;
    }

    /**
     * Constructor of class GameEngine.
     */
    public GameEngine(){ }

    /**
     * Initialize a new grid for new game.
     * @param context the context of GameEngine.
     */
    public void createGrid(Context context){
        Log.e("GameEngine","createGrid is working");
        this.context = context;

        // create the grid and store it
        int[][] GeneratedGrid = Generator.generate(BOMB_NUMBER,WIDTH, HEIGHT);
        MinesweeperGrid = new Cell[getWIDTH()][getHEIGHT()];
        setGrid(context,GeneratedGrid);
    }

    /**
     * Set tile at location.
     * @param context the context of GameEngine.
     * @param grid grid location
     */
    private void setGrid( final Context context, int[][] grid ){
        for( int x = 0 ; x < getWIDTH() ; x++ ){
            for( int y = 0 ; y < getHEIGHT() ; y++ ){
                if( MinesweeperGrid[x][y] == null ){
                    MinesweeperGrid[x][y] = new Cell( context , x,y);
                }
                MinesweeperGrid[x][y].setValue(grid[x][y]);
                MinesweeperGrid[x][y].invalidate();
            }
        }
    }

    /**
     * Get particular cell at coordinate (x,y).
     * @param position position.
     * @return cell at that location.
     */
    public Cell getCellAt(int position) {
        int x = position % WIDTH;
        int y = position / WIDTH;

        return MinesweeperGrid[x][y];
    }

    /**
     * Get particular cell at coordinate (x,y).
     * @param x int x coordinate.
     * @param y int y coordinate.
     * @return cell at that location.
     */
    public Cell getCellAt( int x , int y ){
        return MinesweeperGrid[x][y];
    }

    /**
     *
     * @param x int x coordinate
     * @param y int y coordinate
     */
    public void click( int x , int y ){
        if( x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT && !getCellAt(x,y).isClicked() &&
                !getCellAt(x,y).isFlagged()){
            getCellAt(x,y).setClicked();

            if( getCellAt(x,y).getValue() == 0 ){
                for( int xt = -1 ; xt <= 1 ; xt++ ){
                    for( int yt = -1 ; yt <= 1 ; yt++){
                        if( xt != yt ){
                            click(x + xt , y + yt);
                        }
                    }
                }
            }

            if( getCellAt(x,y).isBomb() ){
                onGameLost();
            }
        }
        if(!is_over) {
            checkEnd();
        }
    }

    /**
     * Check whether the game ends in current state.
     */
    private void checkEnd(){
        int bombNotFound = BOMB_NUMBER;
        int notRevealed = WIDTH * HEIGHT;
        for ( int x = 0 ; x < WIDTH ; x++ ){
            for( int y = 0 ; y < HEIGHT ; y++ ){
                if( getCellAt(x,y).isRevealed() || getCellAt(x,y).isFlagged() ){
                    notRevealed--;
                }

                if( getCellAt(x,y).isFlagged() && getCellAt(x,y).isBomb() ){
                    bombNotFound--;
                }
            }
        }

        if( bombNotFound == 0 && notRevealed == 0 ){
            is_over = true;
            Toast.makeText(context,"Game won", Toast.LENGTH_SHORT).show();
            UserManager.getLoginUser().add_score(new Score(10000-time));
            time = 0;
        }
    }

    /**
     * Set flag for particular cell is user determined there is a bomb.
     * @param x int x coordinate.
     * @param y int y coordinate.
     */
    public void flag( int x , int y ){
        boolean isFlagged = getCellAt(x,y).isFlagged();
        getCellAt(x,y).setFlagged(!isFlagged);
        getCellAt(x,y).invalidate();
    }

    /**
     * Handle the lost games.
     */
    private void onGameLost(){
        Toast.makeText(context,"Game lost", Toast.LENGTH_SHORT).show();
        isLost = true;

        for ( int x = 0 ; x < WIDTH ; x++ ) {
            for (int y = 0; y < HEIGHT; y++) {
                getCellAt(x,y).setRevealed();
            }
        }
    }
}
