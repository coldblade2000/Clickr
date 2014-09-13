package com.twotowerstudios.clickr;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class MainMenu extends Activity {

    //Hello
    TextView tvName, tvStudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        initialize();

    }

    private void initialize() {
        tvName = (TextView) findViewById(R.id.tvName);
        tvStudio = (TextView) findViewById(R.id.tvStudio);

    }
}
