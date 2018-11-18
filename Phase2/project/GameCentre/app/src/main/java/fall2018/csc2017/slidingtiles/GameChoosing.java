package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import fall2018.csc2017.R;

public class GameChoosing extends AppCompatActivity {
    private static boolean minesweeper = false;
    private static boolean sliding_tiles = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamechoosing);
        addgame1ButtonListener();
        addgame2ButtonListener();
    }

    public static String get_current_game() {
        if (sliding_tiles == true){
            return "sliding_tiles";
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

    private void switchToGame1() {
        minesweeper = false;
        sliding_tiles = true;
        Intent tmp = new Intent(this, StartingActivity.class);
        startActivity(tmp);
    }

    private void switchToGame2() {
        sliding_tiles = false;
        minesweeper = true;
        Intent tmp = new Intent(this, StartingActivity.class);
        startActivity(tmp);
    }
}