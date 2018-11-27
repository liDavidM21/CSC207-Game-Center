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
import fall2018.csc2017.slidingtiles.User;
import fall2018.csc2017.slidingtiles.UserManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentPage extends Fragment {

    List<User> users = new ArrayList<>();
    UserManager current_manager = UserManager.get_instance();
    static String text;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        Bundle bundle = getArguments();
        int pageNumber = bundle.getInt("pageNumber");
        view = inflater.inflate(R.layout.page_fragment_layout, container, false);
        setUsers();
        sortScores();
        TextView email = (TextView) view.findViewById(R.id.UserEmail);
        TextView score = (TextView) view.findViewById(R.id.Score);
        System.out.println(UserManager.getLoginUser().getTop_score().size());
        if (users.size() < pageNumber || users.get(pageNumber - 1).return_best_score() == null) {
            email.setText("HOI! No one has score recorded here!");
            score.setText("");
        } else {
            email.setText(users.get(pageNumber - 1).getUser_email());
            score.setText(Integer.toString(users.get(pageNumber - 1).return_best_score().final_score));
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
