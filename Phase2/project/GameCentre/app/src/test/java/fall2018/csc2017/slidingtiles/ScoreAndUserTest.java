package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ScoreAndUserTest {

    private User user1 = new User("csc207@utoronto.ca", "123456");

    private Score testScore = new Score(100, 100, 4);

    private UserManager userManager = new UserManager();

    @Test
    public void testGetInfo(){
        assertEquals("csc207@utoronto.ca", user1.getUserEmail());
        assertEquals("123456", user1.password);
        assertEquals(0, user1.getTopScore().size());
    }

    @Test
    public void testCalculateScore(){
        user1.addScore(testScore);
        assertEquals(1, user1.returnBestScore().compareTo(new Score(10000)));
        assertEquals(-1, user1.returnBestScore().compareTo(new Score(12000)));
        assertEquals(0, user1.returnBestScore().compareTo(new Score(11500)));
    }
    @Test
    public void testAddScore(){
        user1.addScore(testScore);
        user1.addScore(new Score(100, 100, 5));
        assertEquals(1, user1.returnBestScore().compareTo(testScore));
    }

    @Test
    public void testSwitchGame(){
        user1.addScore(testScore);
        assertEquals(1, user1.getTopScore().size());
        user1.switch_game("2048");
        assertEquals(0, user1.getTopScore().size());
        user1.switch_game("Sliding Tiles");
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