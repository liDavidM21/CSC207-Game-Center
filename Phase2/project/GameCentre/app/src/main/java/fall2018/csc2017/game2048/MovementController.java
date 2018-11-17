package fall2018.csc2017.game2048;

import android.content.Context;
import android.widget.Toast;

import fall2018.csc2017.game2048.BoardManager;


public class MovementController {

    private fall2018.csc2017.game2048.BoardManager boardManager = null;

    public MovementController() {
    }

    public void setBoardManager(fall2018.csc2017.game2048.BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    public void processTapMovement(Context context, int position, boolean display) {
        if (boardManager.isValidTap(position)) {
            boardManager.touchMove(position);
            if (boardManager.puzzleSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }