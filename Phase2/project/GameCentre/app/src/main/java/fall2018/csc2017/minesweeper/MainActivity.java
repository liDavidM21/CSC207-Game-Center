package fall2018.csc2017.minesweeper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import fall2018.csc2017.R;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        GameEngine.setIsLost(false);

        ((TextView)findViewById(R.id.textView)).setText("ASDASDASDASD");

        Log.e("MainActivityTwo","onCreate");
        GameEngine.getInstance().createGrid(this);
    }
}
