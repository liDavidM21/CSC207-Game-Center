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
import fall2018.csc2017.R;
import fall2018.csc2017.slidingtiles.Game_choose;
import fall2018.csc2017.slidingtiles.User;
import fall2018.csc2017.slidingtiles.UserManager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FragmentPage extends Fragment {

    List<User> users = new ArrayList<>();
    UserManager current_manager = UserManager.get_instance();
    static String text;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        Bundle bundle = getArguments();
        int pageNumber = bundle.getInt("pageNumber");
        view = inflater.inflate(R.layout.page_fragment_layout, container, false);
        TextView scoreBoard = (TextView) view.findViewById(R.id.BoardName);
        if (Game_choose.getCurrent_game().equals("sliding tiles")){
            scoreBoard.setText("Sliding Tiles" + "\n" + "Global Ranking List");
        } else if (Game_choose.getCurrent_game().equals("minesweeper")){
            scoreBoard.setText("Minesweeper" + "\n" + "Global Ranking List");
        } else {
            scoreBoard.setText("2048" + "\n" + "Global Ranking List");
        }
        setUsers();
        sortScores();
        TextView email = (TextView) view.findViewById(R.id.UserEmail);
        TextView score = (TextView) view.findViewById(R.id.Score);
        String rank;
        if (pageNumber == 1){
            rank = "1st";
        } else if (pageNumber == 2){
            rank = "2nd";
        } else if (pageNumber == 3){
            rank = "3rd";
        } else {
            rank = Integer.toString(pageNumber) + "th";
        }
        System.out.println(UserManager.getLoginUser().getTopScore().size());
        if (users.size() < pageNumber || users.get(pageNumber - 1).returnBestScore() == null) {
            email.setText("HOI! No one has score recorded here!");
            score.setText("");
        } else {
            email.setText(rank + "   Username: " + " \n" + users.get(pageNumber - 1).getUserEmail());
            Timestamp ts = users.get(pageNumber - 1).returnBestScore().getTimeStamp();
            String gameComplexity;
            if (users.get(pageNumber - 1).returnBestScore().getComplexity() == 3){
                gameComplexity = "Easy Mode";
            } else if (users.get(pageNumber - 1).returnBestScore().getComplexity() == 4){
                gameComplexity = "Medium Mode";
            } else if (users.get(pageNumber - 1).returnBestScore().getComplexity() == 0){
                gameComplexity = "";
            } else {
                gameComplexity = "Hard Mode";
            }
            score.setText("Score: " + Integer.toString(users.get(pageNumber - 1).
                    returnBestScore().getFinalScore()) + "\n" + gameComplexity + "\n" +
                    "(Played at " + "\n" + sdf.format(ts) + ")");
        }

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


}
