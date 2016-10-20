package me.cosmin.storemypassword.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import me.cosmin.storemypassword.Controllers.LoginController;
import me.cosmin.storemypassword.Helpers.PreferenceData;
import me.cosmin.storemypassword.R;

public class LoginActivity extends Activity {

    private ArrayList<Integer> mRandomLetters;
    private EditText mFirstLetter;
    private EditText mSecondLetter;
    private EditText mThirdLetter;

    private LoginController mLoginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        mLoginController = new LoginController(this);
        hasPassword();
        setNextFocus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if ( mLoginController.isLoggedIn() ) {
            mLoginController.goToHome();
        }
    }

    private void setNextFocus() {
        mFirstLetter = (EditText) findViewById(R.id.first_letter);
        mSecondLetter = (EditText) findViewById(R.id.second_letter);
        mThirdLetter = (EditText) findViewById(R.id.third_letter);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mFirstLetter, InputMethodManager.SHOW_IMPLICIT);

        mFirstLetter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    mSecondLetter.requestFocus();
                }
            }
        });

        mSecondLetter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    mThirdLetter.requestFocus();
                }
            }
        });

        mThirdLetter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    checkPassword(mThirdLetter);
                }
            }
        });

    }

    private void hasPassword() {
        String password = PreferenceData.getPassword(this);
        if ( password == null ) {
            Intent intent = new Intent(this, CreatePasswordActivity.class);
            startActivity(intent);
        } else {
            Random r = new Random();
            mRandomLetters = new ArrayList<>();
            while (mRandomLetters.size() != 3) {
                int letter = r.nextInt(password.length());
                if (!mRandomLetters.contains(letter)) {
                    mRandomLetters.add(letter);
                }
            }
            Collections.sort(mRandomLetters);

            TextView letter = (TextView) findViewById(R.id.first_letter_text);
            letter.setText(displayNumber(mRandomLetters.get(0)));
            letter = (TextView) findViewById(R.id.second_letter_text);
            letter.setText(displayNumber(mRandomLetters.get(1)));
            letter = (TextView) findViewById(R.id.third_letter_text);
            letter.setText(displayNumber(mRandomLetters.get(2)));
        }
    }

    /**
     * Make a number display ready by adding 1 and appending a suffix
     * @return
     */
    private String displayNumber(int number) {
        number++;
        if (number >= 11 && number <= 13) {
            return number + "th";
        }
        switch (number % 10) {
            case 1:
                return number + "st";
            case 2:
                return number + "nd";
            case 3:
                return number + "rd";
            default:
                return number + "th";
        }
    }

    public void checkPassword(View view) {
        String password = PreferenceData.getPassword(this);

        if ( mFirstLetter.getText().toString().length() == 0 ||
                mSecondLetter.getText().toString().length() == 0 ||
                mThirdLetter.getText().toString().length() == 0) {
            displayError();
        } else if ( password.charAt(mRandomLetters.get(0)) == mFirstLetter.getText().charAt(0) &&
                password.charAt(mRandomLetters.get(1)) == mSecondLetter.getText().charAt(0) &&
            password.charAt(mRandomLetters.get(2)) == mThirdLetter.getText().charAt(0) ) {
            mLoginController.successfulLogin();
        } else {
            displayError();
        }
    }

    private void displayError() {
        TextView error = (TextView) findViewById(R.id.password_error);

        error.setVisibility(View.VISIBLE);
        mFirstLetter.setText("");
        mSecondLetter.setText("");
        mThirdLetter.setText("");
        mFirstLetter.requestFocus();
    }
}
