package com.fpt.gamestudio.freakingmath.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fpt.gamestudio.freakingmath.R;
import com.fpt.gamestudio.freakingmath.config.BaseApplication;
import com.fpt.gamestudio.freakingmath.config.PrefStore;
import com.fpt.gamestudio.freakingmath.utils.UIUtils;
import com.google.analytics.tracking.android.EasyTracker;
import com.startapp.android.publish.StartAppAd;
import com.fpt.gamestudio.freakingmath.utils.GameObject;
import com.fpt.gamestudio.freakingmath.utils.Helper;
import com.fpt.gamestudio.freakingmath.utils.ResizeAnimation;

import java.util.concurrent.atomic.AtomicBoolean;

public class MyActivity extends Activity {

    boolean resultOfGame;
    int highScore = 0;
    TextView firstTxt;
    TextView secondTxt;
    TextView resultTxt;
    TextView operatorTxt;
    TextView highScoreTxt;
    RelativeLayout parentLayout;
    AtomicBoolean isEnd;

    /** StartApp ads **/
    public StartAppAd startAppAd = new StartAppAd(this);
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        UIUtils.setOrientationLockPortrait(this);

        setContentView(R.layout.main);
        isEnd = new AtomicBoolean(false);

        /** StartApp **/
        StartAppAd.init(this, "101007880", "204108768");


        firstTxt = (TextView) findViewById(R.id.first);
        secondTxt = (TextView) findViewById(R.id.second);
        resultTxt = (TextView) findViewById(R.id.result);
        operatorTxt = (TextView) findViewById(R.id.operator);
        highScoreTxt = (TextView) findViewById(R.id.highscore);
        parentLayout = (RelativeLayout) findViewById(R.id.parentLayout);

        parentLayout.setBackgroundColor(Color.parseColor(Helper.getRandomNiceColor()));
        highScoreTxt.setText("0");

        final ImageView progressBar = (ImageView) findViewById(R.id.progressbar);
        progressBar.setBackgroundColor(Color.parseColor("#4788f9"));

        final ImageButton trueImg = (ImageButton) findViewById(R.id.true_btn);
        final ImageButton falseImg = (ImageButton) findViewById(R.id.false_btn);

        trueImg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if(action == MotionEvent.ACTION_DOWN) {
                    trueImg.setBackgroundResource(R.drawable.true_press);
                } else if (action == MotionEvent.ACTION_UP) {
                    trueImg.setBackgroundResource(R.drawable.true_button);
                }
                return false;
            }
        });


        falseImg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if(action == MotionEvent.ACTION_DOWN) {
                    falseImg.setBackgroundResource(R.drawable.wrong_press);
                } else if (action == MotionEvent.ACTION_UP) {
                    falseImg.setBackgroundResource(R.drawable.wrong_button);
                }
                return false;
            }
        });

        final int width = Helper.getWidth();
        final ResizeAnimation animation = new ResizeAnimation(progressBar, width, 7, 0, 7, 1200, isEnd);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (progressBar.getWidth() == 0) {
                    looseGame();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        animation.cancel();
        progressBar.setAnimation(animation);

        trueImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progressBar.getWidth() == 0) isEnd.set(true);
                if (isEnd.get()) {
                    return;
                }

                if (!animation.hasEnded()) {
                    animation.cancel();
                }

                if (resultOfGame) {
                    BaseApplication.soundWhenGuessTrue();
                    // reset game
                    progressBar.getLayoutParams().width = width;
                    setGameNumber();
                    animation.start();
                    highScore++;
                    highScoreTxt.setText(highScore + "");
                } else {
                    looseGame();
                }
            }
        });


        falseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progressBar.getWidth() == 0) isEnd.set(true);
                if (isEnd.get()) {
                    return;
                }

                if (!animation.hasEnded()) {
                    animation.cancel();
                }

                if (!resultOfGame) {
                    BaseApplication.soundWhenGuessTrue();
                    // reset game
                    progressBar.getLayoutParams().width = width;
                    setGameNumber();
                    animation.start();
                    highScore++;
                    highScoreTxt.setText(highScore + "");
                } else {
                    looseGame();
                }
            }
        });
        Helper.resetSetting();
        setGameNumber();
    }

    private void setGameNumber() {
        GameObject o = Helper.randomGame();
        resultOfGame = o.isTrue;
        firstTxt.setText(o.first + "");
        secondTxt.setText(o.second + "");
        resultTxt.setText(o.res + "");
        if (o.isPlusOperate) operatorTxt.setText("+");
        else operatorTxt.setText("-");
        parentLayout.setBackgroundColor(Color.parseColor(Helper.getRandomNiceColor()));
    }

    private void looseGame() {
        isEnd.set(true);
        BaseApplication.soundWhenGuessWrong();
        if (highScore > PrefStore.getMaxScore()) {
            PrefStore.setHighScore(highScore);
        }

        Helper.resetSetting();
        Intent intent = new Intent(this, GameOver.class);
        intent.putExtra("score", highScore);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
       /* startAppAd.onBackPressed();
        super.onBackPressed();*/
    }

    @Override
    public void onStart() {
        super.onStart();
        EasyTracker.getInstance(this).activityStart(this);  // Add this method.
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance(this).activityStop(this);  // Add this method.
    }
}
