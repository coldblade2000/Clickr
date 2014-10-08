package com.twotowerstudios.clickr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainMenu extends Activity implements View.OnClickListener {

    //Hello
    TextView tvName, tvStudio;
    Button bPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        initialize();

    }

    private void initialize() {
        tvName = (TextView) findViewById(R.id.tvName);
        tvStudio = (TextView) findViewById(R.id.tvStudio);
        bPlay = (Button) findViewById(R.id.bPlay);

        bPlay.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bPlay:
                Intent iGoToDifficultyScreen = new Intent(this, DifficultyScreen.class);
                startActivity(iGoToDifficultyScreen);
        }

    }
}
