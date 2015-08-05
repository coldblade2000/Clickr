package com.twotowerstudios.clickr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainMenu extends Activity implements View.OnClickListener {

    //Hello
    TextView tvName, tvStudio, tvDebugMode;
    Button bPlay, bThemes, bStore, bSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        initialize();
        if (SharedPrefs.getBoolean(this, "DebugMode")) {
            tvDebugMode.setVisibility(View.VISIBLE);
        }
    }

    private void initialize() {
        tvName = (TextView) findViewById(R.id.tvName);
        tvStudio = (TextView) findViewById(R.id.tvStudio);
        tvDebugMode = (TextView) findViewById(R.id.tvDebugMode);
        bPlay = (Button) findViewById(R.id.bPlay);
        bThemes = (Button) findViewById(R.id.bThemes);
        bStore = (Button) findViewById(R.id.bStore);
        bSettings = (Button) findViewById(R.id.bSettings);

        bPlay.setOnClickListener(this);
        bStore.setOnClickListener(this);
        bThemes.setOnClickListener(this);
        bSettings.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bPlay:
                Intent iGoToDifficultyScreen = new Intent(this, DifficultyScreen.class);
                startActivity(iGoToDifficultyScreen);
                break;
            case R.id.bThemes:
                Toast.makeText(this, "Not Available Yet!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bStore:
                Toast.makeText(this, "Not Available Yet!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bSettings:
                //Toast.makeText(this, "Not Available Yet!", Toast.LENGTH_SHORT).show();
                Intent iGoToSettingsPage = new Intent(this, Settings.class);
                startActivity(iGoToSettingsPage);
                break;
        }

    }
}
