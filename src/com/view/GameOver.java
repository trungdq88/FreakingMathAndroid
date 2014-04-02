package com.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.config.PrefStore;

public class GameOver extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);

        int highScore = getIntent().getIntExtra("score", 0);

        TextView currentScoreTxt = (TextView) findViewById(R.id.curent_score);
        TextView bestScoreTxt  = (TextView) findViewById(R.id.best_score);
        ImageView newGameBtn = (ImageView) findViewById(R.id.play_btn);

        currentScoreTxt.setText(highScore + "");
        bestScoreTxt.setText(PrefStore.getMaxScore() + "");

        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOver.this, MyActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}