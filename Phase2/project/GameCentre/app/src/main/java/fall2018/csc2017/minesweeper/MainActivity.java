package fall2018.csc2017.minesweeper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import fall2018.csc2017.R;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        GameEngine.setIsLost(false);

        Log.e("MainActivityTwo","onCreate");
        GameEngine.getInstance().createGrid(this);
        TextView txt = findViewById(R.id.bombNum);
        String str = Integer.toString(GameEngine.getBombNumber());
        txt.setText(str);
    }
}
