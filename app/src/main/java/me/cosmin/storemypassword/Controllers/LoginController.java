package me.cosmin.storemypassword.Controllers;

import android.content.Context;
import android.content.Intent;

import me.cosmin.storemypassword.Activities.HomeActivity;
import me.cosmin.storemypassword.Activities.LoginActivity;
import me.cosmin.storemypassword.Helpers.PreferenceData;
import me.cosmin.storemypassword.TestData.Testing;

public class LoginController {

    private Context mContext;

    public LoginController(Context context) {
        mContext = context;
    }

    public void logOut() {
        // TO DO encrypt database
        PreferenceData.setLoggedIn(mContext, false);
        checkLoggedIn();
    }

    public boolean isLoggedIn() {
        return PreferenceData.isLoggedIn(mContext);
    }

    public void checkLoggedIn() {
        if ( !isLoggedIn() ) {
            goToLogin();
        }
    }

    /**
     * Created a function in case more complex functionality is needed in the future
     * @return
     */
    public boolean isAccountSetup() {
        return PreferenceData.getPassword(mContext) != null;
    }

    /**
     * Created a function in case new functionality is added to change the password
     * @param password
     */
    public void setPassword(String password) {
        PreferenceData.setPassword(mContext, password);
    }

    public void successfulLogin() {
        Testing.generate();
        PreferenceData.setLoggedIn(mContext, true);
        goToHome();
    }

    public void goToHome() {
        goTo(HomeActivity.class);
    }

    public void goToLogin() {
        goTo(LoginActivity.class);
    }

    private void goTo(Class activity) {
        Intent intent = new Intent(mContext, activity);
        mContext.startActivity(intent);
    }
}
