package com.twotowerstudios.clickr;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by Diego Granada on 08/10/2014.
 */
public class SharedPrefs {

    public static void setInt(Context context, String key, int input) {
        android.content.SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putInt(key, input);
        editor.commit();
    }

    public static void setString(Context context, String key, String input) {
        android.content.SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(key, input);
        editor.commit();
    }

    public static void setBoolean(Context context, String key, boolean input) {
        android.content.SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean(key, input);
        editor.commit();
    }


    public static int getInt(Context context, String s) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(s, -1);
    }

    public static boolean getBoolean(Context context, String s) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(s, false);
    }

}
