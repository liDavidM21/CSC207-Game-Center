package fall2018.csc2017.game2048;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import fall2018.csc2017.R;

/**
 * A class to implement the game 2048.
 */
public class MainActivityTwo extends Activity {

    /**
     * A mainActivity.
     */
    private static MainActivityTwo mainActivity = null;

    /**
     * TextView of score.
     */
    private TextView Score;

    /**
     * Current score.
     */
    private static int score = 0;

    /**
     * TextView of the max score.
     */
    private TextView maxScore;

    /**
     * The button for restarting game.
     */
    Button restart;

    /**
     * The button for saving game.
     */
    Button save;

    /**
     * The GameView gameView.
     */
    private GameView gameView;

    /**
     * The maximum step the player can use undo.(default 3)
     */
    private static int undoStep = 3;

    /**
     * Set the maximum step to i
     *
     * @param i the maximum step
     */
    public static void setUndoStep(int i) {
        undoStep = i;
    }

    /**
     * Get the maximum step of undo.
     *
     * @return the maximum step of undo.
     */
    public static int getUndoStep() {
        return undoStep;
    }

    public static int getScore() {
        return score;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Score = findViewById(R.id.Score);
        maxScore = findViewById(R.id.maxScore);
        String s = getSharedPreferences("pMaxScore", MODE_PRIVATE).getInt("maxScore", 0) + "";
        maxScore.setText(s);
        gameView = findViewById(R.id.gameView);
        restart = findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameView.startGame();
            }
        });

        addUndoButtonListener();
        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });

    }

    /**
     * Constructor of class MainActivityTwo.
     */
    public MainActivityTwo() {
        mainActivity = this;
    }

    /**
     * Get the main activity.
     *
     * @return MainActivity.
     */
    public static MainActivityTwo getMainActivity() {
        return mainActivity;
    }

    /**
     * Clear the score.
     */
    public void clearScore() {
        score = 0;
        showScore();
    }

    /**
     * Add score.
     *
     * @param i Score to add.
     */
    public void addScore(int i) {

        score += i;
        showScore();
        SharedPreferences pref = getSharedPreferences("pMaxScore", MODE_PRIVATE);

        //If the current score is higher than the max score, then update the max score.
        if (score > pref.getInt("maxScore", 0)) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("maxScore", score);
            editor.apply();
            String s = pref.getInt("maxScore", 0) + "";
            maxScore.setText(s);
        }

    }

    /**
     * Add the Undo bottom.
     */
    private void addUndoButtonListener() {
        Button undoButton = findViewById(R.id.back);
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gameView.getStateList().size() == 0) {
                    makeToastUndo();
                }
                if (gameView.hasTouched && gameView.getStateList().size() >= 1) {
                    score = gameView.getScoreList().get(gameView.getScoreList().size() - 1);
                    gameView.getScoreList().remove(gameView.getScoreList().size() - 1);
                    showScore();
                    int[][] newState =
                            gameView.getStateList().get(gameView.getStateList().size() - 1);
                    gameView.getStateList().remove(gameView.getStateList().size() - 1);
                    for (int y = 0; y < 4; ++y) {
                        for (int x = 0; x < 4; ++x) {
                            gameView.cards[y][x].setNum(newState[y][x]);
                        }
                    }
                }
            }
        });
    }

    /**
     * Display that the player can't undo anymore.
     */
    private void makeToastUndo() {
        Toast.makeText(this, "Can't undo any more!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Show the current score.
     */
    public void showScore() {
        Score.setText(score + "");
    }

    @Override
    public void onBackPressed() {
        createExitTipDialog();
    }

    private void createExitTipDialog() {
        new AlertDialog.Builder(MainActivityTwo.this)
                .setMessage("Exit？")
                .setTitle("Reminder")
                .setIcon(R.drawable.tip)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

}
