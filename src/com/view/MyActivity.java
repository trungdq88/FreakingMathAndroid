package com.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.*;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.config.BaseApplication;
import com.config.PrefStore;
import com.startapp.android.publish.StartAppAd;
import com.utils.GameObject;
import com.utils.Helper;
import com.utils.ResizeAnimation;

public class MyActivity extends Activity {

    boolean resultOfGame;
    boolean isFirstRun = true;
    int highScore = 0;
    TextView firstTxt;
    TextView secondTxt;
    TextView resultTxt;
    TextView highScoreTxt;
    RelativeLayout parentLayout;

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


        setContentView(R.layout.main);


        /** StartApp **/
        StartAppAd.init(this, "101007880", "204108768");


        firstTxt = (TextView) findViewById(R.id.first);
        secondTxt = (TextView) findViewById(R.id.second);
        resultTxt = (TextView) findViewById(R.id.result);
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

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;
        final ResizeAnimation animation = new ResizeAnimation(progressBar, width, 7, 0, 7, 1000);
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
                if (!animation.hasEnded()) {
                    animation.cancel();
                }
                if (resultOfGame) {
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
                if (!animation.hasEnded()) {
                    animation.cancel();
                }
                if (!resultOfGame) {
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

        setGameNumber();
    }

    private void setGameNumber() {
        GameObject o = Helper.randomGame();
        resultOfGame = o.isTrue;
        firstTxt.setText(o.first + "");
        secondTxt.setText(o.second + "");
        resultTxt.setText(o.res + "");
        parentLayout.setBackgroundColor(Color.parseColor(Helper.getRandomNiceColor()));
    }

    private void looseGame() {
        if (highScore > PrefStore.getMaxScore()) {
            PrefStore.setHighScore(highScore);
        }
        Intent intent = new Intent(this, GameOver.class);
        intent.putExtra("score", highScore);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        startAppAd.onBackPressed();
        super.onBackPressed();
    }
}
