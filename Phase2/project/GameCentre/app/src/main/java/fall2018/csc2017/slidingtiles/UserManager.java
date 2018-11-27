package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A singleton class UserManager that is responsible for managing all user activities.
 */
public class UserManager implements Serializable, Iterable<User> {
    /**
     * The single instance of UserManager.
     */
    private static UserManager single_instance = null;
    /**
     * Current Login user.
     */
    private static User loginUser = null;

    /**
     * Make sure only one UserManager exists.
     *
     * @return: null if no UserManager exists, or the current UserManager instance if there is one.
     */
    public static UserManager get_instance() {
        if (single_instance == null) {
            single_instance = new UserManager();
        }
        return single_instance;
    }

    /**
     * Set the instance of UserManager.
     *
     * @param um: the instance to set.
     */
    public static void set_instance(UserManager um) {
        UserManager.single_instance = um;
    }

    /**
     * Get all the user.
     *
     * @return: all the user registered.
     */
    public static List<User> getUser() {
        return UserManager.get_instance().current_user;
    }

    /**
     * Set login user
     *
     * @param user: the login user.
     */
    public static void setLoginUser(User user) {
        loginUser = user;
    }

    /**
     * Get login user
     *
     * @return: return the current login user
     */
    public static User getLoginUser() {
        return loginUser;
    }

    /**
     * List of all registered users.
     */
    private List<User> current_user = new ArrayList<>();

    public Iterator<User> iterator() {
        return new UserIterator();
    }

    private class UserIterator implements Iterator<User> {
        private int cur_index = 0;

        @Override
        public boolean hasNext() {
            return cur_index < current_user.size();
        }

        @Override
        public User next() {

            User result = current_user.get(cur_index);
            cur_index++;
            return result;
        }
    }

    public void add(User new_user) {
        current_user.add(new_user);
    }

    public void switch_game(String game){
        for(User u: current_user){
            u.switch_game(game);
        }
    }

}
