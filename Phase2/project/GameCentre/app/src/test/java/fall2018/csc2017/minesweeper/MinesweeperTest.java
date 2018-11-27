package fall2018.csc2017.minesweeper;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinesweeperTest {


    GameEngine gameEngine = new GameEngine();

    @Test
    public void testSetterAndGetter() {
        assertEquals(10, gameEngine.getWIDTH());
        assertEquals(14, gameEngine.getHEIGHT());
        assertEquals(14, gameEngine.getBombNumber());
        assertFalse(gameEngine.isLost());
        gameEngine.setWIDTH(12);
        gameEngine.setHEIGHT(16);
        gameEngine.setBombNumber(20);
        gameEngine.setIsLost(true);
        assertEquals(12, gameEngine.getWIDTH());
        assertEquals(16, gameEngine.getHEIGHT());
        assertEquals(20, gameEngine.getBombNumber());
        assertTrue(gameEngine.isLost());
    }


    @Test
    public void testGetCellAt(){
        assertEquals(gameEngine.getCellAt(100), gameEngine.getCellAt(0, 10));
    }

    @Test
    public void testGenerator(){
        assertEquals(10, Generator.generate(14, 10, 14).length);
    }

}