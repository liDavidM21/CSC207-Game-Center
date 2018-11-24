package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import fall2018.csc2017.R;
import fall2018.csc2017.minesweeper.MineSweepActivity;

public class GameChoosing extends AppCompatActivity {
    private static boolean minesweeper = false;
    private static boolean sliding_tiles = false;
    private static boolean tzfe = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamechoosing);
        addgame1ButtonListener();
        addgame2ButtonListener();
        addgame3ButtonListener();
    }

    public static String get_current_game() {
        if (sliding_tiles == true){
            return "sliding_tiles";
        }
        else if (tzfe == true){
            return "2048";
        }
        else{
            return "minesweeper";
        }
    }



    /**
     * Start a new game.
     */
    private void addgame1ButtonListener() {
        Button game1Button = findViewById(R.id.slidingtiles);
        game1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame1();
            }
        });
    }

    /**
     * Start a new game.
     */
    private void addgame2ButtonListener() {
        Button game2Button = findViewById(R.id.minesweeper);
        game2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame2();
            }
        });
    }

    /**
     * Start a new game.
     */
    private void addgame3ButtonListener() {
        Button game2Button = findViewById(R.id.tzfe);
        game2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame3();
            }
        });
    }

    private void switchToGame1() {
        minesweeper = false;
        tzfe = false;
        sliding_tiles = true;
        Intent tmp = new Intent(this, StartingActivity.class);
        startActivity(tmp);
    }

    private void switchToGame2() {
        sliding_tiles = false;
        tzfe =false;
        minesweeper = true;
        Intent tmp = new Intent(this, MineSweepActivity.class);
        startActivity(tmp);
    }

    private void switchToGame3() {
        sliding_tiles = false;
        minesweeper = false;
        tzfe = true;
        Intent tmp = new Intent(this, StartingActivity.class);
        startActivity(tmp);
    }
}