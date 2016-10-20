package me.cosmin.storemypassword.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceData {

    static final String PASSWORD = "password";
    static final String LOGGED_IN = "logged_in";

    public static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static String getPassword(Context ctx) {
        return getSharedPreferences(ctx).getString(PASSWORD, null);
    }

    public static void setPassword(Context ctx, String password) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PASSWORD, password);
        editor.apply();
    }

    public static boolean isLoggedIn(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(LOGGED_IN, false);
    }

    public static void setLoggedIn(Context ctx, boolean loggedIn) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(LOGGED_IN, loggedIn);
        editor.apply();
    }
}
