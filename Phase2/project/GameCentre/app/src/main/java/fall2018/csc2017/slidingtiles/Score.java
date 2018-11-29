package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Score class that represents the score of player.
 */
public class Score implements Comparable<Score>, Serializable {

    /**
     * Final score of this game.
     */
    private int finalScore;
    /**
     * Number of moves to complete the game
     */
    private int numberOfMove;
    /**
     * Time it takes to complete the game.
     */
    private int time;
    /**
     * Complexity of the game.
     */
    private int complexity;
    /**
     * Real time when you get the score.
     */
    private Timestamp timeStamp;

    /**
     * Get the final score.
     * @return final score.
     */
    public int getFinalScore() {
        return finalScore;
    }

    /**
     * Get the timeStamp of the score.
     * @return timeStamp of the score.
     */
    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    /**
     * Record the score of the player
     *
     * @param numberOfMove number of move
     * @param time game time.
     * @param complexity the complexity of the game
     * @param timeStamp the real time of the score when you get
     */
    public Score(int numberOfMove, int time, int complexity, Timestamp timeStamp) {
        this.numberOfMove = numberOfMove;
        this.time = time;
        this.complexity = complexity;
        this.timeStamp = timeStamp;
        calculateFinalScore();
    }

    public Score(int finalScore, Timestamp timeStamp) {
        this.finalScore = finalScore;
        this.timeStamp = timeStamp;
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
        if (this.getFinalScore() < score.getFinalScore()) {
            return -1;
        } else if (this.getFinalScore() > score.getFinalScore()) {
            return 1;
        } else {
            return 0;
        }
    }
}
