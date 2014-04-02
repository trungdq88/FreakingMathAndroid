package com.fpt.gamestudio.freakingmath.config;

import android.app.Application;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import com.fpt.gamestudio.freakingmath.R;

public class BaseApplication extends Application {

    public static Context mContext;
    static SoundPool sp;
    static int soundIds[];

    /** this is just application context. Use this function carefully to avoid error */
    public static Context getAppContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundIds = new int[10];
        soundIds[0] = sp.load(mContext, R.raw.guess_true, 1);
        soundIds[1] = sp.load(mContext, R.raw.guess_wrong, 1);
        soundIds[2] = sp.load(mContext, R.raw.play, 1);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static void soundWhenGuessTrue() {
        sp.play(soundIds[0], 100, 100, 1, 0, (float) 1.0);
    }

    public static void soundWhenGuessWrong() {
        sp.play(soundIds[1], 100, 100, 1, 0, (float) 1.0);
    }

    public static void soundWhenPlay() {
        sp.play(soundIds[2], 100, 100, 1, 0, (float) 1.0);
    }
}
