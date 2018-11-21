package fall2018.csc2017.slidingtiles;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import fall2018.csc2017.R;

/**
 * A login screen that offers sign up.
 */
public class Signup extends AppCompatActivity {


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Usermanager current_manager = Usermanager.get_instance();

    /**
     * Initiate the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        deserialize();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptsignup();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_up_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * Check for validity of email and password, and sign up afterwards.
                 */
                attemptsignup();

            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    /**
     * Attempts to register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual sign up attempt is made.
     */
    private void attemptsignup() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.w
        /**
         * Get the password and email.
         */
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            /**
             * Check if the Email is valid
             */
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailRegistered(email)) {
            /**
             * Check if the email is registered
             */
            mEmailView.setError(getString(R.string.error_register_email));
            focusView = mEmailView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            /**
             * Check if the password is valid.
             */
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            /**
             * Store the user and user's password and email inside UserManager
             * and go back to log in activity class.
             */
            User new_user = new User(email, password);
            current_manager.add(new_user);
            serializeUserManager();
            showProgress(true);
            Intent tmp = new Intent(this, LoginActivity.class);
            startActivity(tmp);
        }
    }

    private boolean isEmailRegistered(String email) {
        /**
         * Check for duplicate user name.
         */
        for (User T : current_manager) {
            if (T.user_email.equals(email)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the email is valid.
     *
     * @param email: The email that needs to be checked.
     * @return: true if email is valid.
     */
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    /**
     * To check if the password is valid
     *
     * @param password: the password that needs to be checked
     * @return: true if the password is a valid password.
     */
    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private void serializeUserManager() {
        /**
         * Save the information inside the UserManager.
         */
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput("UserManager.ser", MODE_PRIVATE));
            outputStream.writeObject(current_manager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private void deserialize() {
        /**
         * Load Data of the UserManager.
         */
        try {
            InputStream inputStream = this.openFileInput("UserManager.ser");
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                Usermanager.set_instance((Usermanager) input.readObject());
                current_manager = Usermanager.get_instance();
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

}

