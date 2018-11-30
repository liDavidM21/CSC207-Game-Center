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
import java.util.List;

import fall2018.csc2017.R;
import fall2018.csc2017.UserAndScore.Score;
import fall2018.csc2017.UserAndScore.User;
import fall2018.csc2017.UserAndScore.UserManager;
import fall2018.csc2017.slidingtiles.GameChoose;

public class FragmentPageLocal extends Fragment {

    /**
     * The time format for the time when the game was played.
     */
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    static String text;
    /**
     * Current user manager.
     */
    UserManager current_manager = UserManager.get_instance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        User current_user = current_manager.getLoginUser();
        current_user.sort_score();
        List<Score> user_score = current_user.getTopScore();
        Bundle bundle = getArguments();
        int pageNumber = bundle.getInt("pageNumber");
        view = inflater.inflate(R.layout.page_fragment_layout, container, false);
        TextView scoreBoard = (TextView) view.findViewById(R.id.BoardName);
        TextView email = (TextView) view.findViewById(R.id.UserEmail);
        TextView score = (TextView) view.findViewById(R.id.Score);
        String rank;
        if (pageNumber == 1) {
            rank = "1st";
        } else if (pageNumber == 2) {
            rank = "2nd";
        } else if (pageNumber == 3) {
            rank = "3rd";
        } else {
            rank = Integer.toString(pageNumber) + "th";
        }
        if (GameChoose.getCurrentGame().equals("sliding tiles")) {
            scoreBoard.setText("Sliding Tiles" + "\n" + "Local Ranking List");
        } else if (GameChoose.getCurrentGame().equals("minesweeper")) {
            scoreBoard.setText("Minesweeper" + "\n" + "Local Ranking List");
        } else {
            scoreBoard.setText("2048" + "\n" + "Local Ranking List");
        }
        if (user_score.size() < pageNumber) {
            email.setText("HOI! You need to play once to let me know your score!");
            score.setText("");
        } else {
            email.setText(rank + "   Username: " + "\n" + current_user.getUserEmail());
            Timestamp ts = current_user.getTopScore().get(pageNumber - 1).getTimeStamp();
            String gameComplexity;
            if (current_user.getTopScore().get(pageNumber - 1).getComplexity() == 3) {
                gameComplexity = "Easy Mode";
            } else if (current_user.getTopScore().get(pageNumber - 1).getComplexity() == 4) {
                gameComplexity = "Medium Mode";
            } else if (current_user.getTopScore().get(pageNumber - 1).getComplexity() == 0) {
                gameComplexity = "";
            } else {
                gameComplexity = "Hard Mode";
            }
            score.setText("Score: " + Integer.toString(current_user.getTopScore()
                    .get(pageNumber - 1).getFinalScore()) + "\n" + gameComplexity + "\n" + "(Played at " + "\n"
                    + sdf.format(ts) + ")");
        }

        return view;

    }

}
