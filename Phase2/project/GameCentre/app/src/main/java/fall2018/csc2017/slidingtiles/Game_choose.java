package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import fall2018.csc2017.R;
import fall2018.csc2017.game2048.game2048Activity;
import fall2018.csc2017.minesweeper.MineSweepActivity;

public class Game_choose extends AppCompatActivity {

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
        Intent tmp = new Intent(this, StartingActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to game minesweeper.
     */
    private void switchToGame2(){
        Intent tmp = new Intent(this, MineSweepActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to game 2048.
     */
    private void switchToGame3(){
        Intent tmp = new Intent(this,game2048Activity.class);
        startActivity(tmp);
    }
}
