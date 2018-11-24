package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable, Comparable<User> {
    /**
     * The email of the user.
     */
    public String user_email;
    /**
     * The password of the user
     */
    public String password;
    /**
     * List of top score the user have
     */
    public List<Score> top_score;
    public List<Score> score_2048;
    public List<Score> score_Sliding_Tiles;

    /**
     * User class constructor. Given email and password.
     *
     * @param user_email: the given email
     * @param password:   the given password
     */
    public User(String user_email, String password) {
        this.user_email = user_email;
        this.password = password;
        this.top_score = new ArrayList<>();
        this.score_2048 = new ArrayList<>();
        this.score_Sliding_Tiles = new ArrayList<>();
    }

    /**
     * add score to top score
     *
     * @param s: score to add
     */
    public void add_score(Score s) {
        top_score.add(s);
        sort_score();
    }

    /**
     * return the best score of the user.
     *
     * @return Score: best score of the user, null if no game is complete.
     */
    public Score return_best_score() {
        if (top_score.isEmpty()) {
            return null;
        } else {
            Score best_score = top_score.get(0);
            for (Score s : top_score) {
                if (s.final_score > best_score.final_score) {
                    best_score = s;
                }
            }
            return best_score;
        }
    }

    @Override
    public int compareTo(@NonNull User user) {
        if (this.top_score.isEmpty() && user.top_score.isEmpty()) {
            return 0;
        } else if (user.top_score.isEmpty()) {
            return 1;
        } else if (this.top_score.isEmpty()) {
            return -1;
        }
        return this.return_best_score().compareTo(user.return_best_score());
    }

    public void sort_score() {
        int n = top_score.size();
        for (int i = 1; i < n; ++i) {
            Score key = top_score.get(i);
            int j = i - 1;
            while (j >= 0 && top_score.get(j).final_score < key.final_score) {
                top_score.set(j + 1, top_score.get(j));
                j = j - 1;
            }
            top_score.set(j + 1, key);
        }
    }
    public void switch_game(String game){
        if(game.equals("2048")){
            top_score = score_2048;
        }
        else if(game.equals("Sliding Tiles")){
            top_score = score_Sliding_Tiles;
        }
    }
}
