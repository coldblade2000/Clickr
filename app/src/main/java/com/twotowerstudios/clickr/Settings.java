package com.twotowerstudios.clickr;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Settings extends Activity implements View.OnClickListener {
    CheckBox cbDebugMode;
    Button bEnableDebugMode;

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
    }

    private void initialize() {
        cbDebugMode = (CheckBox) findViewById(R.id.cbDebugMode);
        bEnableDebugMode = (Button) findViewById(R.id.bEnableDebugMode);
        bEnableDebugMode.setOnClickListener(this);

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
        }
    }
}
