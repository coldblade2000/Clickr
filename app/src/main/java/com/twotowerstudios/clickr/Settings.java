package com.twotowerstudios.clickr;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

public class Settings extends Activity implements View.OnClickListener {
    CheckBox cbDebugMode, cbEnableBackground;
    Button bEnableDebugMode, bEnableBackground;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initialize();
        if (SharedPrefs.getBoolean(this, "DebugMode")) {
            cbDebugMode.setChecked(true);
        } else {
            cbDebugMode.setChecked(false);
        }
        if (SharedPrefs.getBoolean(this, "Background")) {
            layout.setBackgroundResource(R.drawable.background);
        }
    }

    private void initialize() {
        layout = (RelativeLayout) findViewById(R.id.RLSettings);

        cbDebugMode = (CheckBox) findViewById(R.id.cbDebugMode);
        cbEnableBackground = (CheckBox) findViewById(R.id.cbEnableBackground);

        bEnableDebugMode = (Button) findViewById(R.id.bEnableDebugMode);
        bEnableBackground = (Button) findViewById(R.id.bEnableBackground);
        bEnableDebugMode.setOnClickListener(this);
        bEnableBackground.setOnClickListener(this);

    }

    private void enableDebugModeDialog() {
        AlertDialog.Builder resetHighScoreBuilder = new AlertDialog.Builder(this);

        resetHighScoreBuilder.setMessage("Are you sure you want to enable debug mode? Only recommended for experienced users")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPrefs.setBoolean(Settings.this, "DebugMode", true);
                        cbDebugMode.setChecked(true);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog resetHighScoreDialog = resetHighScoreBuilder.create();
        resetHighScoreDialog.show();
    }

    private void enableBackgroundDialog() {
        AlertDialog.Builder resetHighScoreBuilder = new AlertDialog.Builder(this);

        resetHighScoreBuilder.setMessage("Are you sure you want to enable the experimental background? You might have to restart the application to get it to work properly")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPrefs.setBoolean(getApplicationContext(), "Background", true);
                        layout.setBackgroundResource(R.drawable.background);
                        cbEnableBackground.setChecked(true);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog resetHighScoreDialog = resetHighScoreBuilder.create();
        resetHighScoreDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bEnableDebugMode:
                if (SharedPrefs.getBoolean(Settings.this, "DebugMode")) {
                    SharedPrefs.setBoolean(Settings.this, "DebugMode", false);
                    cbDebugMode.setChecked(false);
                } else {
                    enableDebugModeDialog();
                }
                break;
            case R.id.bEnableBackground:
                if (SharedPrefs.getBoolean(getApplicationContext(), "Background")) {
                    SharedPrefs.setBoolean(getApplicationContext(), "Background", false);
                } else {
                    enableBackgroundDialog();
                }
                break;
        }
    }
}
