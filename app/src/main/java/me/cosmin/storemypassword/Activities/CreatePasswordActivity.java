package me.cosmin.storemypassword.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import me.cosmin.storemypassword.Controllers.LoginController;
import me.cosmin.storemypassword.R;

public class CreatePasswordActivity extends Activity {

    private LoginController mLoginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_password);
        mLoginController = new LoginController(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // If account is already setup we don't need to display this so redirect
        if ( mLoginController.isAccountSetup() ) {
            mLoginController.checkLoggedIn();
        }
        if ( mLoginController.isLoggedIn() ) {
            mLoginController.goToHome();
        }
    }

    public void savePassword(View view) {
        EditText newPassword = (EditText) findViewById(R.id.new_password);
        String password = newPassword.getText().toString();

        if( isPasswordValid(password) ) {
            mLoginController.setPassword(password);
            mLoginController.successfulLogin();
        } else {
            newPassword.requestFocus();
            newPassword.setError(getString(R.string.new_password_fail));
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 10 && password.matches(".*\\d+.*");
    }
}
