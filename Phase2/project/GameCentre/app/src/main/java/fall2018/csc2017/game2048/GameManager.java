package fall2018.csc2017.game2048;

import java.io.Serializable;

public class GameManager implements Serializable {

    private static GameManager single_instance = GameManager.get_instance();

    public int[][] cards;
    public int score;

    public static GameManager get_instance() {
        if (single_instance == null) {
            single_instance = new GameManager();
        }
        return single_instance;
    }

    public GameManager() {
        cards = new int[4][4];
        score = 0;
    }

    public void getData(Card[][] cards, int score){
        int i = 0;
        int j = 0;
        for (Card[] cl : cards) {
            for (Card c : cl) {
                this.cards[i][j] = c.getNum();
                j += 1;
            }
            i += 1;
            j = 0;
        }
        this.score = score;
    }
    public void setData(Card[][] cards){
        int i = 0;
        int j = 0;
        for(Card[] cl: cards){
            for(Card c: cl){
                cards[i][j].setNum(this.cards[i][j]);
                j+=1;
            }
            i+=1;
            j = 0;
        }
    }
}
