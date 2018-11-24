package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import fall2018.csc2017.R;
public class Game_choose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_choose);
        addGame1button();
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

    private void switchToGame1(){
        Intent tmp = new Intent(this, StartingActivity.class);
        startActivity(tmp);
    }
}
