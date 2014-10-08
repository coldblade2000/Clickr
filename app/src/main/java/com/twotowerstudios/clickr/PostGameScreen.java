package com.twotowerstudios.clickr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class PostGameScreen extends Activity implements View.OnClickListener {
    TextView tvDifficulty, tvEndScore, tvHighScore;
    Button bPlayAgain, bGoToMainScreen;
    int score, difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_game_screen);
        initialize();
    }

    private void initialize() {
        tvDifficulty = (TextView) findViewById(R.id.tvDifficulty);
        tvEndScore = (TextView) findViewById(R.id.tvEndScore);
        tvHighScore = (TextView) findViewById(R.id.tvHighScore);

        bPlayAgain = (Button) findViewById(R.id.bPlayAgain);
        bGoToMainScreen = (Button) findViewById(R.id.bGoToMainScreen);

        bPlayAgain.setOnClickListener(this);
        bGoToMainScreen.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        score = bundle.getInt("score");
        tvEndScore.setText("" + score);
        difficulty = bundle.getInt("difficulty");
        switch (difficulty) {
            case 1:
                tvDifficulty.setText("Easy");
                break;
            case 2:
                tvDifficulty.setText("Medium");
                break;
            case 3:
                tvDifficulty.setText("Classic");
                break;
            case 4:
                tvDifficulty.setText("Hard");
                break;
            case 5:
                tvDifficulty.setText("Extreme");
                break;
        }

        int currentHighScore = SharedPrefs.getInt(this, "highScore");
        if (currentHighScore < score) {
            currentHighScore = score;
            SharedPrefs.setInt(this, "highScore", score);
        }
        tvHighScore.setText("" + currentHighScore);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bPlayAgain:
                Intent iPlayAgain = new Intent(this, DifficultyScreen.class);
                startActivity(iPlayAgain);
                break;

            case R.id.bGoToMainScreen:
                Intent iGoToMainScreen = new Intent(this, MainMenu.class);
                startActivity(iGoToMainScreen);
                break;
        }

    }
}
