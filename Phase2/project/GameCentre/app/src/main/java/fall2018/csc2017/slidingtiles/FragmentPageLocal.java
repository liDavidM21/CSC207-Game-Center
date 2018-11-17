package fall2018.csc2017.slidingtiles;
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

import java.util.List;

public class FragmentPageLocal extends Fragment {

    Usermanager current_manager = Usermanager.get_instance();
    static String text;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        User current_user = current_manager.getLoginUser();
        current_user.sort_score();
        List<Score> user_score = current_user.top_score;
        Bundle bundle = getArguments();
        int pageNumber = bundle.getInt("pageNumber");
        view = inflater.inflate(R.layout.page_fragment_layout, container, false);
        TextView email = (TextView) view.findViewById(R.id.UserEmail);
        TextView score = (TextView) view.findViewById(R.id.Score);
        if (user_score.size() < pageNumber) {
            email.setText("HOI! You need to play once to let me know your score!");
            score.setText("");
        } else {
            email.setText(current_user.user_email);
            score.setText(Integer.toString(current_user.top_score.get(pageNumber - 1).final_score));
        }

        return view;

    }

}
