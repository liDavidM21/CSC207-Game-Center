package fall2018.csc2017.minesweeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import fall2018.csc2017.R;

public class MainActivity extends AppCompatActivity {
    int numBomb = GameEngine.getBombNumber();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        GameEngine.setIsLost(false);

        Log.e("MainActivityTwo","onCreate");
        GameEngine.getInstance().createGrid(this);
        TextView txt = findViewById(R.id.bombNum);
        String str = Integer.toString(numBomb);
        txt.setText(str);
    }
}
