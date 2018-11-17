package fall2018.csc2017.game2048;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fall2018.csc2017.slidingtiles.Board;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.Score;
import fall2018.csc2017.slidingtiles.Tile;
import fall2018.csc2017.slidingtiles.Usermanager;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class BoardManager implements Serializable {

    /**
     * The board being managed.
     */
    private Board board;
    /**
     * The number of move the player made in this game, and the time it takes to complete.
     */
    private int move;
    public int time;

    public List<fall2018.csc2017.slidingtiles.Tile> tiles = new ArrayList<>();

    /**
     * Manage a board that has been pre-populated.
     *
     * @param board the board
     */
    BoardManager(Board board) {
        this.board = board;
    }

    /**
     * Return the current board.
     */
    Board getBoard() {
        return board;
    }


    /**
     * Manage a new shuffled board.
     */
    BoardManager() {
        final int numTiles = Board.NUM_ROWS * Board.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            if (numTiles == 9 && tileNum == 8) {
                tiles.add(new fall2018.csc2017.slidingtiles.Tile(9, R.drawable.tile_25));
            } else if (numTiles == 16 && tileNum == 15) {
                tiles.add(new fall2018.csc2017.slidingtiles.Tile(16, R.drawable.tile_25));
            } else {
                tiles.add(new fall2018.csc2017.slidingtiles.Tile(tileNum));
            }
        }

        Collections.shuffle(tiles);
        this.board = new Board(tiles);
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    boolean puzzleSolved() {
        boolean solved = true;

        for (int i = 0; i < Board.NUM_COLS; i++) {
            for (int j = 0; j < Board.NUM_COLS; j++) {
                fall2018.csc2017.slidingtiles.Tile temp = new fall2018.csc2017.slidingtiles.Tile(i * Board.NUM_COLS + j);
                int comp = this.board.getTile(i, j).compareTo(temp);
                if (comp != 0) {
                    solved = false;
                }
            }
        }
        if (solved) {
            Usermanager.getLoginUser().add_score(new Score(move, time));
            move = 0;
            time = 0;
        }
        return solved;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    boolean isValidTap(int position) {

        int row = position / Board.NUM_COLS;
        int col = position % Board.NUM_COLS;
        int blankId = board.numTiles();
        // Are any of the 4 the blank tile?
        fall2018.csc2017.slidingtiles.Tile above = row == 0 ? null : board.getTile(row - 1, col);
        fall2018.csc2017.slidingtiles.Tile below = row == Board.NUM_ROWS - 1 ? null : board.getTile(row + 1, col);
        fall2018.csc2017.slidingtiles.Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile right = col == Board.NUM_COLS - 1 ? null : board.getTile(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    void touchMove(int position) {

        int row = position / Board.NUM_ROWS;
        int col = position % Board.NUM_COLS;
        int blankId = board.numTiles();
        if (isValidTap(position)) {
            move += 1;
            if (row > 0 && board.getTile(row - 1, col) != null && board.getTile(row - 1, col).getId() == blankId) {
                board.swapTiles(row, col, row - 1, col);
            } else if (row < board.NUM_ROWS - 1 && board.getTile(row + 1, col) != null && board.getTile(row + 1, col).getId() == blankId) {
                board.swapTiles(row, col, row + 1, col);
            } else if (col > 0 && board.getTile(row, col - 1) != null && board.getTile(row, col - 1).getId() == blankId) {
                board.swapTiles(row, col, row, col - 1);
            } else {
                board.swapTiles(row, col, row, col + 1);
            }

        }
    }

}
