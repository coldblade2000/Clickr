package com.twotowerstudios.clickr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class DifficultyScreen extends Activity implements View.OnClickListener {

    Button bReturn, bDifficultyEasy, bDifficultyMedium, bDifficultyClassic, bDifficultyHard, bDifficultyExtreme;
    int iDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_screen);
        initialize();
    }

    private void initialize() {
        bReturn = (Button) findViewById(R.id.bReturn);
        bDifficultyEasy = (Button) findViewById(R.id.bDifficultyEasy);
        bDifficultyMedium = (Button) findViewById(R.id.bDifficultyMedium);
        bDifficultyClassic = (Button) findViewById(R.id.bDifficultyClassic);
        bDifficultyHard = (Button) findViewById(R.id.bDifficultyHard);
        bDifficultyExtreme = (Button) findViewById(R.id.bDifficultyExtreme);

        bReturn.setOnClickListener(this);
        bDifficultyEasy.setOnClickListener(this);
        bDifficultyMedium.setOnClickListener(this);
        bDifficultyClassic.setOnClickListener(this);
        bDifficultyHard.setOnClickListener(this);
        bDifficultyExtreme.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bReturn:
                Intent iGoToMainScreen = new Intent(this, MainMenu.class);
                startActivity(iGoToMainScreen);
                break;
            case R.id.bDifficultyEasy:
                iDifficulty = 1;
                Intent iGoToInGameEasy = new Intent(this, InGameClassic.class);
                iGoToInGameEasy.putExtra("iDifficulty", iDifficulty);
                startActivity(iGoToInGameEasy);
                break;
            case R.id.bDifficultyMedium:
                iDifficulty = 2;
                Intent iGoToInGameMedium = new Intent(this, InGameClassic.class);
                iGoToInGameMedium.putExtra("iDifficulty", iDifficulty);
                startActivity(iGoToInGameMedium);
                break;
            case R.id.bDifficultyClassic:
                iDifficulty = 3;
                Intent iGoToInGameClassic = new Intent(this, InGameClassic.class);
                iGoToInGameClassic.putExtra("iDifficulty", iDifficulty);
                startActivity(iGoToInGameClassic);
                break;
            case R.id.bDifficultyHard:
                iDifficulty = 4;
                Intent iGoToInGameHard = new Intent(this, InGameClassic.class);
                iGoToInGameHard.putExtra("iDifficulty", iDifficulty);
                startActivity(iGoToInGameHard);
                break;
            case R.id.bDifficultyExtreme:
                iDifficulty = 5;
                Intent iGoToInGameExtreme = new Intent(this, InGameClassic.class);
                iGoToInGameExtreme.putExtra("iDifficulty", iDifficulty);
                startActivity(iGoToInGameExtreme);
                break;

        }
    }
}
