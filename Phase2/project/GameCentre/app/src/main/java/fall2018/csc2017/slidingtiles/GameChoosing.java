package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import fall2018.csc2017.R;

import fall2018.csc2017.minesweeper.MainActivity;
import fall2018.csc2017.slidingtiles.StartingActivity;

public class GameChoosing extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamechoosing);
        addgame1ButtonListener();
        addgame2ButtonListener();
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
        Intent tmp = new Intent(this, StartingActivity.class);
        startActivity(tmp);
    }

    private void switchToGame2() {
        Intent tmp = new Intent(this, MainActivity.class);
        startActivity(tmp);
    }
}