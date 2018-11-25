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
    public int final_score;
    /**
     * Number of moves to complete the game
     */
    private int number_of_move;
    /**
     * Time it takes to complete the game.
     */
    private int time;

    /**
     * Record the score of the player
     *
     * @param number_of_move
     * @param time
     */
    public Score(int number_of_move, int time) {
        this.number_of_move = number_of_move;
        this.time = time;
        calculate_final_score();
    }

    public Score(int final_score){
        this.final_score = final_score;
    }

    /**
     * Calculate the final score base on the number of moves and time.
     */
    private void calculate_final_score() {
        final_score = 10000 - 5 * time - 30 * number_of_move;
        if (final_score < 0) {
            final_score = 0;
        }
    }

    @Override
    public int compareTo(@NonNull Score score) {
        if (this.final_score < score.final_score) {
            return -1;
        } else if (this.final_score > score.final_score) {
            return 1;
        } else {
            return 0;
        }
    }
}
