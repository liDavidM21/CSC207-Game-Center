package fall2018.csc2017.minesweeper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import fall2018.csc2017.R;

public class MainActivity extends Activity {

    MainActivity main;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = this;
        setContentView(R.layout.activity_main2);
        GameEngine.setIsLost(false);
        Log.e("MainActivityTwo","onCreate");
        GameEngine.getInstance().createGrid(this);
        TextView txt = findViewById(R.id.bombNum);
        String str = "#BOMBS: " + Integer.toString(GameEngine.getBombNumber());
        txt.setText(str);
        addHardButtonListener();
    }

    private void addHardButtonListener() {

        final Button hardButton = findViewById(R.id.newGame);
        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tmp = new Intent(main, MainActivity.class);
                startActivity(tmp);
                finish();
            }
        });
    }

}
