package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import fall2018.csc2017.R;
import fall2018.csc2017.game2048.game2048Activity;

public class Game_choose extends AppCompatActivity {

    /**
     * Whether the game is minesweeper.
     */
    private static boolean minesweeper = false;

    /**
     * Whether the game is sliding tiles.
     */
    private static boolean sliding_tiles = false;

    /**
     * Whether the game is 2048.
     */
    private static boolean tzfe = false;

    /**
     * Get the string of current game.
     * @return current game chosen.
     */
    public static String get_current_game() {
        if (sliding_tiles){
            return "sliding_tiles";
        }
        else if (tzfe){
            return "2048";
        }
        else{
            return "minesweeper";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_choose);
        addGame1button();
        addGame2button();
        addGame3button();
    }

    /**
     * Button for game sliding tiles.
     */
    private void addGame1button(){
        ImageButton button = findViewById(R.id.slidingtiles);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame1();
            }
        });
    }

    /**
     * Button for game minesweeper.
     */
    private void addGame2button(){
        ImageButton button = findViewById(R.id.minesweeperim);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame2();
            }
        });
    }

    /**
     * Button for game 2048.
     */
    private void addGame3button(){
        ImageButton button = findViewById(R.id.twozerofoureight);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame3();
            }
        });
    }

    /**
     * Switch to game sliding tiles.
     */
    private void switchToGame1(){
        minesweeper = false;
        tzfe = false;
        sliding_tiles = true;
        Intent tmp = new Intent(this, StartingActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to game minesweeper.
     */
    private void switchToGame2(){
        sliding_tiles = false;
        tzfe = false;
        minesweeper = true;
        Intent tmp = new Intent(this,StartingActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to game 2048.
     */
    private void switchToGame3(){
        sliding_tiles = false;
        tzfe = true;
        minesweeper = false;
        Intent tmp = new Intent(this,game2048Activity.class);
        startActivity(tmp);
    }
}
