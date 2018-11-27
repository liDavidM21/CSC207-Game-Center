package fall2018.csc2017.game2048;
/**
 * https://evgenii.com/blog/spring-button-animation-on-android/
 * https://stackoverflow.com/questions/36894384/android-move-background-continuously-with-animation
 * https://stackoverflow.com/questions/9107900/how-to-upload-image-from-gallery-in-android
 */


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import fall2018.csc2017.R;
import fall2018.csc2017.slidingtiles.Game_choose;
import fall2018.csc2017.slidingtiles.LoginActivity;
import fall2018.csc2017.Scoreboard.scoreboard;
import fall2018.csc2017.slidingtiles.Usermanager;

/**
 * The initial activity for the game2048.
 */
public class game2048Activity extends AppCompatActivity {

    //TODO: Remove all save/load related stuff.
    /**
     * The main save file.
     */
    public static final String SAVE_FILENAME = "save_file_2048.ser";
    /**
     * A temporary save file.
     */
    public static final String TEMP_SAVE_FILENAME = "save_file_tmp_2048.ser";
    /**
     * A temporary save file.
     */
    public static final String AUTO_SAVE_FILENAME = "save_file_auto_2048.ser";
    /**
     * The board manager.
     */

    /**
     * Initialize the default UndoStep.
     */
    private static int numUndo = 3;

    /**
     * The string of the current game.
     */
    String current_game = "2048";

    /**
     * The current manager.
     */
    private Usermanager current_manager = Usermanager.get_instance();
    private GameManager gm = GameManager.get_instance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        serializeUserManager();
        current_manager.switch_game(current_game);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2048_starting);
        addStartButtonListener();
        addLoadButtonListener();
        addScoreboardButtonListener();
        addSignOutButtonListener();
        addSetPButtonListener();
        addSetMButtonListener();
        addBackButtonListener();
    }

    /**
     * Go back to the game choosing menu.
     */
    private void addBackButtonListener(){
        Button backButton = findViewById(R.id.backTwo);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSelecting();
            }
        });
    }

    /**
     * Switch to game choosing interface.
     * Used by ExitButtonListener.
     */
    private void switchToSelecting() {
        Intent tmp = new Intent(this, Game_choose.class);
        startActivity(tmp);
    }

    /**
     * Add one undo step.
     */
    private void addSetPButtonListener() {
        Button setButton = findViewById(R.id.undoSetP);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txt = findViewById(R.id.undoStp);
                numUndo += 1;
                String str = "Undo " + Integer.toString(numUndo) + " Steps";
                txt.setText(str);
                MainActivityTwo.setUndoStep(numUndo);
                makeToastUndoText();
            }
        });
    }

    /**
     * Minus one undo step.
     */
    private void addSetMButtonListener() {
        Button setButton = findViewById(R.id.undoSetM);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txt = findViewById(R.id.undoStp);
                if (numUndo >= 1) {
                    numUndo -= 1;
                }
                String str = "Undo " + Integer.toString(numUndo) + " Steps";
                if (numUndo == 1) {
                    str = "Undo " + Integer.toString(numUndo) + " Step";
                }
                txt.setText(str);
                MainActivityTwo.setUndoStep(numUndo);
                makeToastUndoText();
            }
        });
    }

    /**
     * Toast information of number of undo step selected.
     */
    private void makeToastUndoText() {
        String str = "Number of Undo Steps:" + numUndo;
        if (numUndo == 0) {
            str = "Minimum Undo Step:" + numUndo;
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    /**
     * Start a new game.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame("start");
            }
        });
    }

    /**
     * Button for entering scoreboard interface.
     */
    private void addScoreboardButtonListener() {
        Button startButton = findViewById(R.id.scoreboardButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToScoreboard();
            }
        });
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToastLoadedText("Loading game");
                switchToGame("load");

            }
        });
    }

    /**
     * Attempt to sign out
     */
    private void addSignOutButtonListener() {
        Button loadButton = findViewById(R.id.Signout);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signout();
            }
        });
    }

    private void signout() {
        Intent tmp = new Intent(this, LoginActivity.class);
        startActivity(tmp);
    }

    /**
     * Display that a game was loaded successfully.
     */
    private void makeToastLoadedText(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadFromFile(TEMP_SAVE_FILENAME);
    }

    /**
     * Switch to the GameActivity view to play the game.
     */
    private void switchToGame(String s) {
        if (s.equals("start")) {
            Button button = findViewById(R.id.StartButton);
            startAnimation(button);
        } else {
            Button button = (Button) findViewById(R.id.LoadButton);
            startAnimation(button);
        }
    }

    /**
     * Animation added for start button.
     *
     * @param button the start button.
     */
    private void startAnimation(Button button) {
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        // Use bounce interpolator with amplitude 0.c2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 20);
        myAnim.setInterpolator(interpolator);
        button.startAnimation(myAnim);
        final game2048Activity tmp1 = this;
        myAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent tmp = new Intent(tmp1,MainActivityTwo.class);
                saveToFile(game2048Activity.TEMP_SAVE_FILENAME);
                startActivity(tmp);
            }
        });
    }

    /**
     * Switch to Scoreboard interface.
     */
    private void switchToScoreboard() {
        Intent tmp = new Intent(this, scoreboard.class);
        startActivity(tmp);
    }

    /**
     * Load the board manager from fileName.
     *
     * @param fileName the name of the file
     */
    private void loadFromFile(String fileName) {

        try {
            InputStream inputStream = this.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                GameView.cards = (Card[][]) input.readObject();
                inputStream.close();
            }
        }
        catch(ClassNotFoundException e){
            System.out.println("No class is found.");
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
    }

    /**
     * Save the board manager to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(GameView.cards);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private void serializeUserManager() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput("UserManager.ser", MODE_PRIVATE));
            outputStream.writeObject(Usermanager.get_instance());
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}

