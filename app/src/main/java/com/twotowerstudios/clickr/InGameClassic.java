package com.twotowerstudios.clickr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;



public class InGameClassic extends Activity implements View.OnClickListener {

    CountDownTimer cdtGame;
    Button bStart, bClicker;
    TextView tvBanner, tvScore, tvCountDown;
    View vbStart, vbClicker, vtvBanner, vtvScore, vtvCountDown;
    //Boolean booCountDownActive;
    Boolean booGameActive;
    Handler mHandler = new Handler();
    int score;
    int difficulty, gameDuration, addedTime, moveFrequency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game_classic);
        initialize();

        setDifficultyValues(difficulty);
    }

    protected void onPauseInteraction() {
        cdtGame.cancel();
    }

    private void initialize() {
        addedTime = 0;

        Bundle difficultyBundle = getIntent().getExtras();
        difficulty = difficultyBundle.getInt("iDifficulty");

        score = 0;
        bStart = (Button) findViewById(R.id.bStart);
        bClicker = (Button) findViewById(R.id.bClicker);
        tvBanner = (TextView) findViewById(R.id.tvBanner);
        tvScore = (TextView) findViewById(R.id.tvScore);
        tvCountDown = (TextView) findViewById(R.id.tvCountDown);

        View vbStart = findViewById(R.id.bStart);
        View vbClicker = findViewById(R.id.bClicker);
        View vtvBanner = findViewById(R.id.tvBanner);
        View vtvScore = findViewById(R.id.tvScore);
        View vtvCountDown = findViewById(R.id.tvCountDown);

        vbStart.setVisibility(View.VISIBLE);
        vbClicker.setVisibility(View.GONE);
        vtvCountDown.setVisibility(View.GONE);
        vtvScore.setVisibility(View.GONE);
        vtvBanner.setVisibility(View.VISIBLE);


        vbStart.setOnClickListener(this);
        bClicker.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //moveClickr();
                    score++;
                    if (difficulty == 1 && addedTime < 6) {
                        gameDuration = gameDuration + 2500;
                        addedTime++;
                    } else if (difficulty == 2 && addedTime < 4) {
                        gameDuration = gameDuration + 1000;
                        addedTime++;
                    }
                    tvScore.setText("" + score);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {

                }
                return false;
            }
        });
    }

    private void changeClickerPos() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (booGameActive) {
                    try {
                        Thread.sleep(1000 / moveFrequency);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                moveClickr();

                            }
                        });


                    } catch (Exception e) {

                    }
                }
            }
        }).start();


    }

    private void moveClickr() {
        DisplayMetrics dimension = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dimension);
        int width = dimension.widthPixels;
        int height = dimension.heightPixels;
        Random rInt = new Random();
        bClicker.setX(rInt.nextInt(width - bClicker.getWidth() * 3) + bClicker.getWidth());
        bClicker.setY(rInt.nextInt(height - bClicker.getHeight() * 3) + bClicker.getHeight());

    }

    private void setDifficultyValues(int difficulty) {
        if (difficulty == 1) {
            gameDuration = 20000;
            moveFrequency = 2;
        } else if (difficulty == 2) {
            gameDuration = 15000;
            moveFrequency = 2;
        } else if (difficulty == 3) {
            moveFrequency = 5;
            gameDuration = 10000;
        } else if (difficulty == 4) {
            moveFrequency = 10;
            gameDuration = 7500;
        } else if (difficulty == 5) {
            gameDuration = 6750;
            moveFrequency = 30;
        }
    }

    private void gameActive() {
        tvCountDown.setVisibility(View.GONE);
        tvBanner.setVisibility(View.VISIBLE);
        tvScore.setVisibility(View.VISIBLE);
        bClicker.setVisibility(View.VISIBLE);
        changeClickerPos();
        booGameActive = true;
        cdtGame = new CountDownTimer(gameDuration, 10) {

            @Override
            public void onTick(long l) {
                tvBanner.setText("" + l);
            }

            @Override
            public void onFinish() {

                booGameActive = false;
                onGameEnd(score, difficulty);
            }
        }.start();
    }

    private void onGameEnd(int Score, int difficulty) {
        Intent endGame = new Intent(this, PostGameScreen.class);
        endGame.putExtra("score", score);
        endGame.putExtra("difficulty", difficulty);
        startActivity(endGame);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bStart:
                score = 0;
                tvScore.setText("" + score);
                tvCountDown.setVisibility(View.VISIBLE);
                tvBanner.setVisibility(View.GONE);
                bStart.setVisibility(View.GONE);
                new CountDownTimer(3000, 10) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        //tvBanner.setText("" + millisUntilFinished);
                        if (millisUntilFinished > 2001 && millisUntilFinished < 3000) {
                            tvCountDown.setText("3");
                        } else if (millisUntilFinished > 1001 && millisUntilFinished < 2000) {
                            tvCountDown.setText("2");
                        } else if (millisUntilFinished > 1 && millisUntilFinished < 1000) {
                            tvCountDown.setText("1");
                        } else if (millisUntilFinished <= 1) {
                            tvCountDown.setText("GO!");

                        }


                    }

                    @Override
                    public void onFinish() {
                        /**vtvCountDown.setVisibility(View.GONE);
                         vtvBanner.setVisibility(View.VISIBLE);
                         vtvScore.setVisibility(View.VISIBLE);*/
                        booGameActive = true;
                        gameActive();
                    }
                }.start();
                //startCountdown();
                break;
            case R.id.bClicker:

        }
    }
}
