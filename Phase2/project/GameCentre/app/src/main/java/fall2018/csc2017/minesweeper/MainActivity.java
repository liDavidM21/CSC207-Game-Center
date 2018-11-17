package fall2018.csc2017.minesweeper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import fall2018.csc2017.slidingtiles.R;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ((TextView)findViewById(R.id.textView)).setText("ASDASDASDASD");

        Log.e("MainActivity","onCreate");
        GameEngine.getInstance().createGrid(this);
    }
}
