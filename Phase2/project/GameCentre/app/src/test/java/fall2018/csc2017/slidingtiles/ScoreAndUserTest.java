package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreAndUserTest {

    User user1 = new User("csc207@utoronto.ca", "123456");

    Score finalScore = new Score(10000);


    @Test
    public void testGetInfo(){
        assertEquals("csc207@utoronto.ca", user1.getUser_email());
        assertEquals("123456", user1.password);
    }

    @Test
    public void testAddScore(){
    }
    @Test
    public void compareTo() {
    }
}