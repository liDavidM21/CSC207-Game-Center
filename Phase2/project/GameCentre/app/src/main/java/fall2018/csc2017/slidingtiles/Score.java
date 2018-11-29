package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Score class that represents the score of player.
 */
public class Score implements Comparable<Score>, Serializable {

    /**
     * Final score of this game.
     */
    public int finalScore;
    /**
     * Number of moves to complete the game
     */
    private int numberOfMove;
    /**
     * Time it takes to complete the game.
     */
    private int time;
    private int complexity;

    /**
     * Record the score of the player
     *
     * @param numberOfMove number of move
     * @param time game time.
     */
    public Score(int numberOfMove, int time, int complexity) {
        this.numberOfMove = numberOfMove;
        this.time = time;
        this.complexity = complexity;
        calculateFinalScore();
    }

    public Score(int final_score){
        this.finalScore = final_score;
    }

    /**
     * Calculate the final score base on the number of moves and time.
     */
    private void calculateFinalScore() {
        finalScore = 10000 - 5 * time - 30 * numberOfMove + 5000 * (complexity - 3);
        if (finalScore < 0) {
            finalScore = 0;
        }
    }

    @Override
    public int compareTo(@NonNull Score score) {
        if (this.finalScore < score.finalScore) {
            return -1;
        } else if (this.finalScore > score.finalScore) {
            return 1;
        } else {
            return 0;
        }
    }
}
