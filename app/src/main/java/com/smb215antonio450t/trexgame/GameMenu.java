package com.smb215antonio450t.trexgame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class GameMenu extends Activity implements View.OnClickListener {
    TextView mainview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_game_menu);
        this.mainview = (TextView) findViewById(R.id.newgame);
        this.mainview.setOnTouchListener(new GameColorTouchListener());
        this.mainview.setOnClickListener(this);


    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newgame:
                startActivity(new Intent(this, GameMain.class));
                this.mainview.setTextColor(Color.WHITE);
                return;
            default:
                return;
        }
    }
}
