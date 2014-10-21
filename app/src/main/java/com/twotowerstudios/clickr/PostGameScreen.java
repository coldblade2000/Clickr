package com.twotowerstudios.clickr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class PostGameScreen extends Activity implements View.OnClickListener {
    TextView tvDifficulty, tvEndScore, tvHighScore;
    Button bPlayAgain, bGoToMainScreen;
    ImageView ivHighScore;
    int score, difficulty, currentHighScore;

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

        ivHighScore = (ImageView) findViewById(R.id.ivHighScore);

        bPlayAgain.setOnClickListener(this);
        bGoToMainScreen.setOnClickListener(this);
        ivHighScore.setOnClickListener(this);

        ivHighScore.setVisibility(View.GONE);

        Bundle bundle = getIntent().getExtras();
        score = bundle.getInt("score");
        tvEndScore.setText("" + score);
        difficulty = bundle.getInt("difficulty");
        switch (difficulty) {
            case 1:
                tvDifficulty.setText("Easy");
                currentHighScore = SharedPrefs.getInt(this, "highScoreEasy");
                break;
            case 2:
                tvDifficulty.setText("Medium");
                currentHighScore = SharedPrefs.getInt(this, "highScoreMedium");
                break;
            case 3:
                tvDifficulty.setText("Classic");
                currentHighScore = SharedPrefs.getInt(this, "highScoreClassic");
                break;
            case 4:
                tvDifficulty.setText("Hard");
                currentHighScore = SharedPrefs.getInt(this, "highScoreHard");
                break;
            case 5:
                tvDifficulty.setText("Extreme");
                currentHighScore = SharedPrefs.getInt(this, "highScoreExtreme");
                break;
        }


        if (currentHighScore < score && score > 0) {
            currentHighScore = score;
            ivHighScore.setVisibility(View.VISIBLE);
            switch (difficulty) {
                case 1:
                    SharedPrefs.setInt(this, "highScoreEasy", score);
                    break;
                case 2:
                    SharedPrefs.setInt(this, "highScoreMedium", score);
                    break;
                case 3:
                    SharedPrefs.setInt(this, "highScoreClassic", score);
                    break;
                case 4:
                    SharedPrefs.setInt(this, "highScoreHard", score);
                    break;
                case 5:
                    SharedPrefs.setInt(this, "highScoreExtreme", score);
                    break;

            }
            //SharedPrefs.setInt(this, "highScore", score);
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
