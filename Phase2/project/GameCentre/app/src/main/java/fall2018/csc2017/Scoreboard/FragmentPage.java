package fall2018.csc2017.Scoreboard;
/*
Taken from https://www.youtube.com/watch?v=cKweRL0rHBc. The video demonstrate how to create a
swipe view using fragment. Class involved are FragmentPage, FragmentPageLocal, SwipeAdapter,
SwipeAdapterLocal, scoreboard.
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fall2018.csc2017.R;
import fall2018.csc2017.UserAndScore.User;
import fall2018.csc2017.UserAndScore.UserManager;
import fall2018.csc2017.slidingtiles.GameChoose;


public class FragmentPage extends Fragment {

    /**
     * The time format for the time when the game was played.
     */
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    static String text;
    /**
     * List of users.
     */
    List<User> users = new ArrayList<>();
    /**
     * Current user manager.
     */
    UserManager current_manager = UserManager.get_instance();

    TextView scoreBoard;
    TextView email;
    TextView score;

    String scoreboardString;
    String emailString;
    String scoreString;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
//        Bundle bundle = getArguments();
//        int pageNumber = bundle.getInt("pageNumber");
        view = inflater.inflate(R.layout.page_fragment_layout, container, false);

        scoreBoard = (TextView) view.findViewById(R.id.BoardName);
//        if (GameChoose.getCurrentGame().equals("sliding tiles")) {
//            scoreBoard.setText("Sliding Tiles" + "\n" + "Global Ranking List");
//        } else if (GameChoose.getCurrentGame().equals("minesweeper")) {
//            scoreBoard.setText("Minesweeper" + "\n" + "Global Ranking List");
//        } else {
//            scoreBoard.setText("2048" + "\n" + "Global Ranking List");
//        }
//        setUsers();
//        sortScores();
        email = (TextView) view.findViewById(R.id.UserEmail);
        score = (TextView) view.findViewById(R.id.Score);
        scoreBoard.setText(scoreboardString);
        email.setText(emailString);
        score.setText(scoreString);
//        String rank;
//        if (pageNumber == 1) {
//            rank = "1st";
//        } else if (pageNumber == 2) {
//            rank = "2nd";
//        } else if (pageNumber == 3) {
//            rank = "3rd";
//        } else {
//            rank = Integer.toString(pageNumber) + "th";
//        }
//        if (users.size() < pageNumber || users.get(pageNumber - 1).returnBestScore() == null) {
//            email.setText("HOI! No one has score recorded here!");
//            score.setText("");
//        } else {
//            email.setText(rank + "   Username: " + " \n" + users.get(pageNumber - 1).getUserEmail());
//            Timestamp ts = users.get(pageNumber - 1).returnBestScore().getTimeStamp();
//            String gameComplexity;
//            if (users.get(pageNumber - 1).returnBestScore().getComplexity() == 3) {
//                gameComplexity = "Easy Mode";
//            } else if (users.get(pageNumber - 1).returnBestScore().getComplexity() == 4) {
//                gameComplexity = "Medium Mode";
//            } else if (users.get(pageNumber - 1).returnBestScore().getComplexity() == 0) {
//                gameComplexity = "";
//            } else {
//                gameComplexity = "Hard Mode";
//            }
//            score.setText("Score: " + Integer.toString(users.get(pageNumber - 1).
//                    returnBestScore().getFinalScore()) + "\n" + gameComplexity + "\n" +
//                    "(Played at " + "\n" + sdf.format(ts) + ")");
//        }

        return view;

    }

    /**
     * get user list from UserManager.
     */
    private void setUsers() {
        users = current_manager.getUser();
    }

    /**
     * sort user's score based on the final score in descending order.
     */
    private void sortScores() {
        Collections.sort(users, Collections.<User>reverseOrder());
    }


    public void displayScoreboardText(String text){
        System.out.println(scoreBoard);
        scoreboardString = text;
    }
    public void displayEmailText(String text){
        emailString = text;
    }
    public void displayScoreText(String text){
        scoreString = text;
    }
}
