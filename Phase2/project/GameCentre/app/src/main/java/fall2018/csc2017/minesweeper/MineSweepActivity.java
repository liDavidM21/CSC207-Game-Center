package fall2018.csc2017.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
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
import fall2018.csc2017.slidingtiles.UserManager;

/**
 * The initial activity for the minesweeper game.
 * The source code is originated from
 * https://github.com/marcellelek/Minesweeper.git
 * It is used to construct basic game structure and modified by our group member.
 */
public class MineSweepActivity extends AppCompatActivity {

    /**
     * The main save file.
     */
    public static final String SAVE_FILENAME = "save_file.ser";
    /**
     * A temporary save file.
     */
    public static final String TEMP_SAVE_FILENAME = "save_file_tmp.ser";
    /**
     * A temporary save file.
     */
    public static final String AUTO_SAVE_FILENAME = "save_file_auto.ser";

    /**
     * Int created for only pop default mode msg once.
     */
    private static int showDefault = 1;

    /**
     * GameEngine of the game.
     */
    private GameEngine gameengine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        serializeUserManager();
        UserManager.get_instance().switch_game("mMine Sweeper");
        super.onCreate(savedInstanceState);
        gameengine = GameEngine.getInstance();
        if (showDefault == 1) {
            makeToastModeText();
            showDefault++;
        }
        saveToFile(TEMP_SAVE_FILENAME);
        saveToFile(AUTO_SAVE_FILENAME);
        setContentView(R.layout.activity_starting_mine);
        addStartButtonListener();
        addSettingButtonListener();
        addScoreboardButtonListener();
        addSignoutButtonListener();
        addBackButtonListener();
    }

    /**
     * The back button.
     */
    private void addBackButtonListener() {
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
     * Start a new game.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameengine = GameEngine.getInstance();
                switchToGame();
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
     * Button listener for game setting.
     * Enter activity_setting interface.
     */
    private void addSettingButtonListener() {
        Button startButton = findViewById(R.id.SETTING);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSetting();
            }
        });
    }

    /**
     * Attempt to sign out
     */
    private void addSignoutButtonListener() {
        Button loadButton = findViewById(R.id.Signout);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signout();
            }
        });
    }

    /**
     * Sign out user.
     */
    private void signout() {
        Intent tmp = new Intent(this, LoginActivity.class);
        startActivity(tmp);
    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadFromFile();
    }

    /**
     * Switch to the GameActivity view to play the game.
     */
    private void switchToGame() {
        Button button = (Button) findViewById(R.id.StartButton);
        startAnimation(button);
    }

    /**
     * Animation added for start button.
     *
     * @param button
     */
    private void startAnimation(Button button) {
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        // Use bounce interpolator with amplitude 0.c2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 20);
        myAnim.setInterpolator(interpolator);
        button.startAnimation(myAnim);
        final MineSweepActivity tmp1 = this;
        myAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent tmp = new Intent(tmp1, MainActivity.class);
                saveToFile(MineSweepActivity.TEMP_SAVE_FILENAME);
                startActivity(tmp);
            }
        });
    }

    /**
     * Switch to activity_setting interface.
     */
    private void switchToSetting() {
        final MineSweepActivity tmp1 = this;
        Intent tmp = new Intent(tmp1, GameSettingMinesweeper.class);
        saveToFile(MineSweepActivity.TEMP_SAVE_FILENAME);
        startActivity(tmp);
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
     */
    private void loadFromFile() {

        try {
            InputStream inputStream = this.openFileInput(TEMP_SAVE_FILENAME);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                gameengine = (GameEngine) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

    /**
     * Display the default mode -- medium.
     */
    private void makeToastModeText() {
        Toast.makeText(this, "DEFAULT MODE: MEDIUM", Toast.LENGTH_SHORT).show();
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
            outputStream.writeObject(gameengine);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Identify user.
     */
    private void serializeUserManager() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput("UserManager.ser", MODE_PRIVATE));
            outputStream.writeObject(UserManager.get_instance());
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
