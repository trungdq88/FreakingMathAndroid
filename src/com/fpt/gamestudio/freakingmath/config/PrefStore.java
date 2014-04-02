package com.fpt.gamestudio.freakingmath.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * App Preferences
 * @author Huynh Quang Thao
 */
public class PrefStore {

    ///////////////////////////////////////////////////
    ///////////////  PREFERENCE KEY   /////////////////
    /** preference key that mark this is first run or not */
    public static final String PREF_MAX_SCORE = "max_user_score";


    ///////////////////////////////////////////////////////////////
    /////////////////   DEFAULT VALUE   ///////////////////////////
    public static final int DEFAULT_MAX_SCORE = 0;


    ////////////////////////////////////////////////////////////////////
    /////////////////////////////  GETTER //////////////////////////////
    public static SharedPreferences getSharedPreferencesWithContext(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(BaseApplication.getAppContext());
    }

    public static int getMaxScore() {
        return getSharedPreferences().getInt(PREF_MAX_SCORE, DEFAULT_MAX_SCORE);
    }


    ////////////////////////////////////////////////////////////////////
    /////////////////////////////  SETTER //////////////////////////////
    public static void setHighScore(int score) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putInt(PREF_MAX_SCORE, score);
        editor.commit();
    }

    //////////////////////////////////////////////////////////////////////
    ///////////////////// QUERY DATA EXIST ///////////////////////////////
}