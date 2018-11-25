package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import fall2018.csc2017.R;
import fall2018.csc2017.minesweeper.MineSweepActivity;
import fall2018.csc2017.game2048.game2048Activity;

public class Game_choose extends AppCompatActivity {
    private static boolean minesweeper = false;
    private static boolean sliding_tiles = false;
    private static boolean tzfe = false;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_choose);
        addGame1button();
        addGame2button();
        addGame3button();
    }

    private void addGame1button(){
        ImageButton button = findViewById(R.id.imageButton4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame1();
            }
        });
    }

    private void addGame2button(){
        ImageButton button = findViewById(R.id.minesweeperim);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame2();
            }
        });
    }

    private void addGame3button(){
        ImageButton button = findViewById(R.id.twozerofoureight);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame3();
            }
        });
    }
    private void switchToGame1(){
        minesweeper = false;
        tzfe = false;
        sliding_tiles = true;
        Intent tmp = new Intent(this, StartingActivity.class);
        startActivity(tmp);
    }

    private void switchToGame2(){
        sliding_tiles = false;
        tzfe =false;
        minesweeper = true;
        Intent tmp = new Intent(this,MineSweepActivity.class);
        startActivity(tmp);
    }

    private void switchToGame3(){
        sliding_tiles = false;
        tzfe = true;
        minesweeper = false;
        Intent tmp = new Intent(this,game2048Activity.class);
        startActivity(tmp);
    }
}
