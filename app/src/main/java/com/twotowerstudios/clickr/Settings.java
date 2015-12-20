package com.twotowerstudios.clickr;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Settings extends Activity implements View.OnClickListener {
    CheckBox cbDebugMode, cbEnableBackground;
    Button bEnableDebugMode, bEnableBackground, bRestart;
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
            cbEnableBackground.setChecked(true);
        } else {
            cbEnableBackground.setChecked(false);
        }
    }

    private void initialize() {
        layout = (RelativeLayout) findViewById(R.id.RLSettings);

        cbDebugMode = (CheckBox) findViewById(R.id.cbDebugMode);
        cbEnableBackground = (CheckBox) findViewById(R.id.cbEnableBackground);

        bEnableDebugMode = (Button) findViewById(R.id.bEnableDebugMode);
        bEnableBackground = (Button) findViewById(R.id.bEnableBackground);
        bRestart = (Button) findViewById(R.id.bRestart);
        bRestart.setOnClickListener(this);
        bEnableDebugMode.setOnClickListener(this);
        bEnableBackground.setOnClickListener(this);

    }

    private void enableDebugModeDialog() {
        AlertDialog.Builder resetHighScoreBuilder = new AlertDialog.Builder(this);

        resetHighScoreBuilder.setMessage("Are you sure you want to enable debug mode?  You have to restart the application for it to work correctly.")
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

        resetHighScoreBuilder.setMessage("Are you sure you want to enable the experimental background? You have to restart the application for it to work correctly.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPrefs.setBoolean(getApplicationContext(), "Background", true);
                        layout.setBackgroundResource(R.drawable.background);
                        cbEnableBackground.setChecked(true);
                        Toast.makeText(getApplicationContext(), "Enabled new background", Toast.LENGTH_SHORT).show();
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
            case R.id.bRestart:
                Intent mStartActivity = new Intent(getApplicationContext(), MainMenu.class);
                int mPendingIntentId = 123456;
                PendingIntent mPendingIntent = PendingIntent.getActivity(getApplicationContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager mgr = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                System.exit(0);
                break;
            case R.id.bEnableDebugMode:
                if (SharedPrefs.getBoolean(Settings.this, "DebugMode")) {
                    SharedPrefs.setBoolean(Settings.this, "DebugMode", false);
                    cbDebugMode.setChecked(false);
                    Toast.makeText(this, "Disabled debug mode, please restart application to apply new changes.", Toast.LENGTH_SHORT).show();
                } else {
                    enableDebugModeDialog();
                }
                break;
            case R.id.bEnableBackground:
                if (SharedPrefs.getBoolean(getApplicationContext(), "Background")) {
                    SharedPrefs.setBoolean(getApplicationContext(), "Background", false);
                    cbEnableBackground.setChecked(false);
                    Toast.makeText(this, "Disabled new background, please restart application to apply new changes.", Toast.LENGTH_SHORT).show();
                } else {
                    enableBackgroundDialog();
                }
                break;
        }
    }
}
