package com.view;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;
import com.config.BaseApplication;
import com.utils.GameObject;
import com.utils.Helper;
import com.utils.ResizeAnimation;

public class MyActivity extends Activity {

    boolean resultOfGame;
    ImageView firstImg;
    ImageView secondImg;
    ImageView resultImg;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        firstImg = (ImageView) findViewById(R.id.first);
        secondImg = (ImageView) findViewById(R.id.second);
        resultImg = (ImageView) findViewById(R.id.result);

        final ImageView progressBar = (ImageView) findViewById(R.id.progressbar);
        progressBar.setBackgroundColor(Color.rgb(100, 100, 50));

        ImageView trueImg = (ImageView) findViewById(R.id.true_btn);
        ImageView falseImg = (ImageView) findViewById(R.id.false_btn);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;
        final ResizeAnimation animation = new ResizeAnimation(progressBar, width, 15, 0, 15, 4000);

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
                } else {
                    looseGame();
                }
            }
        });

        progressBar.setAnimation(animation);
    }

    private void setGameNumber() {
        GameObject o = Helper.randomGame();
        resultOfGame = o.isTrue;
        firstImg.setImageDrawable(Helper.getDrawableFromNumber(o.first));
        secondImg.setImageDrawable(Helper.getDrawableFromNumber(o.second));
        resultImg.setImageDrawable(Helper.getDrawableFromNumber(o.res));
    }

    private void looseGame() {
        Toast.makeText(BaseApplication.getAppContext(), "Game Loose", Toast.LENGTH_LONG);
    }




}
