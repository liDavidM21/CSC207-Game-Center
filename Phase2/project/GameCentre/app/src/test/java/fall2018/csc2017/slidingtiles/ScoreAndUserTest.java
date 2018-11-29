package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Iterator;

import static org.junit.Assert.*;

public class ScoreAndUserTest {

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private User user1 = new User("csc207@utoronto.ca", "123456");

    private User empty_user = new User("csc207@utoronto.ca.test", "111111");

    private Score testScore = new Score(11500,  3, timestamp);

    private UserManager userManager = new UserManager();

    @Test
    public void testGetInfo(){
        user1.switch_game("Sliding Tiles");
        assertEquals("csc207@utoronto.ca", user1.getUserEmail());
        assertEquals("123456", user1.password);
        assertEquals(0, user1.getTopScore().size());
    }

    @Test
    public void testCalculateScore(){
        user1.switch_game("Sliding Tiles");
        user1.addScore(testScore);
        assertEquals(1, user1.returnBestScore().compareTo(new Score(10000, timestamp)));
        assertEquals(-1, user1.returnBestScore().compareTo(new Score(12000, timestamp)));
        assertEquals(0, user1.returnBestScore().compareTo(new Score(11500, timestamp)));
        empty_user.switch_game("Sliding Tiles");
        assertNull(empty_user.returnBestScore());
        assertEquals(1, user1.compareTo(empty_user));
    }
    @Test
    public void testAddScore(){
        user1.switch_game("Sliding Tiles");
        user1.addScore(testScore);
        user1.addScore(new Score(10000, 4, timestamp));
        assertEquals(1, user1.returnBestScore().compareTo(testScore));
    }

    @Test
    public void testSwitchGame(){
        user1.switch_game("Sliding Tiles");
        user1.addScore(testScore);
        assertEquals(1, user1.getTopScore().size());
        user1.switch_game("2048");
        assertEquals(0, user1.getTopScore().size());
        user1.switch_game("Mine Sweeper");
        assertEquals(0, user1.getTopScore().size());
    }

    @Test
    public void testAddUser(){
        UserManager.set_instance(userManager);
        assertEquals(userManager, UserManager.get_instance());
        userManager.add(user1);
        assertEquals(1, userManager.getUser().size());
        userManager.setLoginUser(user1);
        assertEquals(user1, userManager.getLoginUser());
        userManager.add(new User("csc207@mail.utoronto.ca", "123456"));
        Iterator userIterator = userManager.iterator();
        assertTrue(userIterator.hasNext());
        assertEquals(user1, userIterator.next());
    }
}